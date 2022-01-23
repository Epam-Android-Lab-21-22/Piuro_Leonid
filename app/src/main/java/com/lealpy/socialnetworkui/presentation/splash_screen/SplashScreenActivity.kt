package com.lealpy.socialnetworkui.presentation.splash_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lealpy.socialnetworkui.R
import com.lealpy.socialnetworkui.presentation.MainActivity
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        startMainActivity()
    }

    private fun startMainActivity() {
        Completable.create { emitter ->
            Thread.sleep(THREAD_SLEEP_MILLIS)
            emitter.onComplete()
        }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                },
                { error ->
                    Log.e(APP_LOG_TAG, error.message.toString())
                }
            )
    }

    companion object {
        private const val THREAD_SLEEP_MILLIS = 2000L
        const val APP_LOG_TAG = "APP_LOG_TAG"
    }

}