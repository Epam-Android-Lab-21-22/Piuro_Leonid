package com.lealpy.socialnetworkui.achievements

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.lealpy.socialnetworkui.R
import com.lealpy.socialnetworkui.databinding.FragmentAchievementsBinding

class AchievementsFragment : Fragment(R.layout.fragment_achievements) {

    private lateinit var binding: FragmentAchievementsBinding
    private val viewModel: AchievementsViewModel by activityViewModels()
    private val adapter = AchievementAdapter(
        object: AchievementAdapter.OnCrossClickListener {
            override fun onCrossClick(trophyItem: Achievement.TrophyItem) {
                viewModel.onDeleteItemClicked(trophyItem)
            }
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAchievementsBinding.bind(view)
        initObservers()
        initViews()
    }

    private fun initObservers() {
        viewModel.achievements.observe(viewLifecycleOwner) { achievements ->
            adapter.submitList(achievements)
        }
    }

    private fun initViews() {
        binding.achievementsRecyclerView.adapter = adapter
    }
}
