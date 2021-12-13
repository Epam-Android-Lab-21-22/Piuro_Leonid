package com.lealpy.socialnetworkui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.lealpy.socialnetworkui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        initViews()
        initObservers()

    }

    private fun initViews() {

        binding.getPredictionBtn.setOnClickListener {
            viewModel.onBtnClicked()
        }

    }

    private fun initObservers() {

        viewModel.prediction.observe(this) { prediction ->
            binding.predictionTV.text = prediction
        }

        viewModel.predictionVisibility.observe(this) { predictionVisibility ->
            binding.predictionCV.visibility = predictionVisibility
        }

        viewModel.clickCounterText.observe(this) { clickCounter ->
            binding.predictionCounterTV.text = clickCounter
        }

    }

}
