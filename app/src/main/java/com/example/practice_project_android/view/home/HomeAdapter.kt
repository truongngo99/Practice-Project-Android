package com.example.practice_project_android.view.home

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.practice_project_android.databinding.ActivityHomeBinding
import androidx.annotation.NonNull
import androidx.fragment.app.FragmentActivity
import com.example.practice_project_android.view.home.account.AccountFragment
import com.example.practice_project_android.view.home.movie.MovieFragment
import com.example.practice_project_android.view.home.search.SearchFragment
import com.example.practice_project_android.view.home.settings.SettingsFragment
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject


@ActivityScoped
class HomeAdapter @Inject constructor ( activity : FragmentActivity) : FragmentStateAdapter(activity) {
    private val listFragment = mutableListOf(
        MovieFragment(),
        SearchFragment(),
        AccountFragment(),
        SettingsFragment(),
    )
    override fun getItemCount(): Int {
        return  listFragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return  listFragment[position]
    }


}
