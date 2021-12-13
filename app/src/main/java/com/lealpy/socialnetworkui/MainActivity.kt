package com.lealpy.socialnetworkui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.lealpy.socialnetworkui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val checkNetworkConnection by lazy {
        CheckNetworkConnection(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        callNetworkConnection()
    }

    private fun initViews() {
        binding.startFeatureActivityBtn.setOnClickListener {
            val intent = Intent(this, StartFeaturesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun callNetworkConnection() {
        binding.startFeatureActivityBtn.isEnabled = false
        checkNetworkConnection.observe(this,{ isConnected ->
            if (isConnected) {
                binding.startFeatureActivityBtn.isEnabled = true
            }
            else {
                binding.startFeatureActivityBtn.isEnabled = false
                Snackbar.make(binding.root, getString(R.string.no_internet), Snackbar.LENGTH_SHORT).show()
            }
        })
    }

}
