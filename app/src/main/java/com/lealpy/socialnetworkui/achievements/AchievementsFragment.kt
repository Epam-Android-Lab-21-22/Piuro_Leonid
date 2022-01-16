package com.lealpy.socialnetworkui.achievements

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.lealpy.socialnetworkui.R
import com.lealpy.socialnetworkui.databinding.FragmentAchievementsBinding

class AchievementsFragment :
    Fragment(R.layout.fragment_achievements),
    AchievementsInterface.AchievementsView
{

    private lateinit var binding: FragmentAchievementsBinding
    private var presenter: AchievementsPresenter? = null
    private val adapter = AchievementAdapter(
        object: AchievementAdapter.OnCrossClickListener {
            override fun onCrossClick(trophyItem: Achievement.TrophyItem) {
                presenter?.onDeleteItemClicked(trophyItem)
            }
        }
    )

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
