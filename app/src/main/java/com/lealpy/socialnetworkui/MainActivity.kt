package com.lealpy.socialnetworkui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
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

        binding.apply {

            val viewsForClickCounter = setOf(
                profileSettings,
                notifications,
                language,
                confidentiality,
                support,
                decoration,
                logout,
            )

            viewsForClickCounter.forEach { view ->
                view.setOnClickListener {
                    viewModel.onClicked()
                }
            }

            navView.selectedItemId = R.id.navigationProfile

            navView.setOnItemSelectedListener {
                viewModel.onClicked()
                true
            }

            statusText.addTextChangedListener {
                viewModel.onSymbolEntered(statusText.length())
            }

            statusLayout.setEndIconOnClickListener {
                viewModel.onClicked()
            }

        }

    }

    private fun initObservers() {

        viewModel.clickCounter.observe(this) {clickCounter ->
            binding.clickCounter.text = clickCounter.toString()
        }

        viewModel.countersProgress.observe(this) {countersProgress ->
            binding.clickProgressBar.progress = countersProgress
        }

        viewModel.symbolsCounter.observe(this) {symbolsCounter ->
            binding.symbolsCounter.text = symbolsCounter.toString()
        }

        viewModel.symbolsProgress.observe(this) {symbolsProgress ->
            binding.symbolsProgressBar.progress = symbolsProgress
        }

    }

}
