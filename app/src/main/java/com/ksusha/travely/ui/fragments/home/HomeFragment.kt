package com.ksusha.travely.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.ksusha.travely.databinding.FragmentHomeBinding
import com.ksusha.travely.ui.fragments.BaseFragment

class HomeFragment : BaseFragment(){

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController.navigateUp()
        val homeAdapter = HomeFragmentAdapter { attractionId ->
            val navDirections = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(attractionId)
            navController.navigate(navDirections)
        }

        binding.recyclerView.adapter = homeAdapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireActivity(), RecyclerView.VERTICAL))
        homeAdapter.setData(attractions)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}