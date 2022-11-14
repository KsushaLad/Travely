package com.ksusha.travely.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.ksusha.travely.R
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
        val epoxyController = HomeFragmentController { attractionId ->
            activityViewModel.attractionSelected(attractionId)
            navController.navigate(R.id.action_homeFragment_to_detailsFragment)
        }

        binding.recyclerView.setController(epoxyController)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireActivity(), RecyclerView.VERTICAL))

        activityViewModel.attractionListLiveData.observe(viewLifecycleOwner){ attractions ->
            epoxyController.attractions = attractions
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}