package com.lealpy.socialnetworkui.presentation.message

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lealpy.socialnetworkui.R
import com.lealpy.socialnetworkui.databinding.FragmentMessageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessageFragment : Fragment(R.layout.fragment_message) {

    private lateinit var binding : FragmentMessageBinding

    private val viewModel : MessageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMessageBinding.bind(view)
        initViews()
        initObservers()
    }

    private fun initViews() {
        val dropDownAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.spinner_list)
        )

        binding.spinner.adapter = dropDownAdapter

        binding.saveButton.setOnClickListener {
            viewModel.onSaveClicked(
                binding.spinner.selectedItemPosition,
                binding.messageEditText.text.toString()
            )
        }

        binding.loadButton.setOnClickListener {
            viewModel.onLoadClicked(binding.spinner.selectedItemPosition)
        }
    }

    private fun initObservers() {
        viewModel.message.observe(viewLifecycleOwner) { message ->
            binding.messageEditText.setText(message)
        }
    }

    companion object {
        const val DATABASE_POSITION = 0
        const val PREFS_POSITION = 1
        const val INTERNAL_STORAGE_POSITION = 2
        const val EXTERNAL_STORAGE_POSITION = 3
    }

}
