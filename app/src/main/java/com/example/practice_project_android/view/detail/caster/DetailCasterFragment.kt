package com.example.practice_project_android.view.detail.caster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.practice_project_android.databinding.FragmentCasterLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCasterFragment : Fragment() {
    private lateinit var binding: FragmentCasterLayoutBinding
    private val viewModel: DetailCasterViewModel by viewModels()
    private val args : DetailCasterFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCasterLayoutBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val casterId = args.casterId
        viewModel.getInfoCaster(casterId)
        binding.toolbar.title = ""
        observale()

        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            setHasOptionsMenu(true)
        }

    }


    private fun observale() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressCircular.isVisible = it
            binding.layoutDetailCaster.isVisible = !it
        }
        viewModel.resultInfoCaster.observe(viewLifecycleOwner) {
            binding.apply {
                Glide.with(root).load("https://image.tmdb.org/t/p/original${it.profile_path}").into(imgAvatar)
                tvName.text = it.name
                tvAbout.text = "\t${it.biography}"
                tvDate.text = it.birthday
                tvPopularity.text = it.popularity.toString()
                tvPlaceBirthday.text = it.place_of_birth
                if (it.gender == 1) {
                    tvGender.text = "Female"
                } else {
                    tvGender.text = "Male"
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController().navigateUp()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
