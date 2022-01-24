package com.lealpy.socialnetworkui.achievements.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.lealpy.socialnetworkui.R
import com.lealpy.socialnetworkui.achievements.AchievementsInterface
import com.lealpy.socialnetworkui.achievements.presenter.AchievementsPresenter
import com.lealpy.socialnetworkui.achievements.model.Achievement
import com.lealpy.socialnetworkui.achievements.model.AchievementsModel
import com.lealpy.socialnetworkui.databinding.FragmentAchievementsBinding

class AchievementsFragment :
    Fragment(R.layout.fragment_achievements),
    AchievementsInterface.AchievementsView
{

    private lateinit var binding: FragmentAchievementsBinding
    private var presenter: AchievementsPresenter? = null
    private val adapter = AchievementAdapter { trophyItem ->
        presenter?.onDeleteItemClicked(trophyItem)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAchievementsBinding.bind(view)
        presenter = AchievementsPresenter(this, AchievementsModel(requireContext()))
        initViews(savedInstanceState)
    }

    override fun onDestroyView() {
        presenter?.detachView()
        super.onDestroyView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(ACHIEVEMENTS_KEY, presenter?.onViewAsksOutState())
    }

    private fun initViews(savedInstanceState : Bundle?) {
        binding.achievementsRecyclerView.adapter = adapter
        presenter?.viewIsReady(savedInstanceState)
    }

    override fun showAchievements(achievements : List<Achievement>) {
        adapter.submitList(achievements)
    }

    override fun showProgress() {
        binding.achievementsRecyclerView.visibility = View.GONE
        binding.achievementsProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.achievementsRecyclerView.visibility = View.VISIBLE
        binding.achievementsProgressBar.visibility = View.GONE
    }

    companion object {
        const val ACHIEVEMENTS_KEY = "ACHIEVEMENTS_KEY"
    }

}
