package com.lealpy.socialnetworkui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.lealpy.socialnetworkui.databinding.ActivityStartFeaturesBinding

class StartFeaturesActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityStartFeaturesBinding.inflate(layoutInflater)
    }

    private val requestLocationPermissionLauncher = registerForActivityResult(
        RequestPermission(),
        ::onGotLocationPermissionResult
    )

    companion object {
        private const val SCHEME_PACKAGE = "package"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.startMapsActivityBtn.setOnClickListener {
            requestLocationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun onGotLocationPermissionResult(granted: Boolean) {
        if (granted) {
            onLocationPermissionGranted()
        } else {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                askUserForOpeningAppSettings()
            } else {
                Toast.makeText(this, R.string.permission_denied, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun askUserForOpeningAppSettings() {

        val appSettingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts(SCHEME_PACKAGE, packageName, null)
        )

        if (packageManager.resolveActivity(appSettingsIntent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            AlertDialog.Builder(this)
                .setTitle(R.string.permission_denied)
                .setMessage(R.string.permission_denied_forever_message)
                .setPositiveButton(R.string.open) { _, _ ->
                    startActivity(appSettingsIntent)
                }
                .setNegativeButton(R.string.cancel, null)
                .create()
                .show()
        }

    }

    private fun onLocationPermissionGranted() {
        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }

}
