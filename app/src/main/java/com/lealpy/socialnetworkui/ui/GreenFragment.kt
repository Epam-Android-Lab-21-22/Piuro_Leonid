package com.lealpy.socialnetworkui.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.lealpy.socialnetworkui.R
import com.lealpy.socialnetworkui.databinding.FragmentGreenBinding

class GreenFragment : Fragment(R.layout.fragment_green) {

    private lateinit var binding : FragmentGreenBinding

    private val backStackListener = FragmentManager.OnBackStackChangedListener {
        binding.greenCounter.text = parentFragmentManager.backStackEntryCount.toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentGreenBinding.bind(view)

        initViews()
        parentFragmentManager.addOnBackStackChangedListener(backStackListener)
    }

    private fun initViews() {
        binding.greenCounter.setOnClickListener { addFragment() }
        binding.greenCounter.text = parentFragmentManager.backStackEntryCount.toString()
    }

    private fun addFragment() {
        parentFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, GreenFragment())
            .setReorderingAllowed(true)
            .addToBackStack(GREEN_FRAGMENT_KEY)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        parentFragmentManager.removeOnBackStackChangedListener(backStackListener)
    }

    companion object {
        const val GREEN_FRAGMENT_KEY = "GREEN_FRAGMENT_KEY"
    }
}
