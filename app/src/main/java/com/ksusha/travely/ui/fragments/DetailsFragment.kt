package com.ksusha.travely.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ksusha.travely.data.Attraction
import com.ksusha.travely.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso

class DetailsFragment : BaseFragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val safeArgs: DetailsFragmentArgs by navArgs()
    private val attraction: Attraction by lazy {
        attractions.find { it.id == safeArgs.attractionId }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.titleTextView.text = attraction.title
        binding.descriptionTextView.text = attraction.description
        Picasso.get().load(attraction.image_url).into(binding.headerImageView)
        binding.monthsToVisitTextView.text = attraction.months_to_visit
        binding.numberOfFactsTextView.text = "${attraction.facts.size} facts"
        binding.numberOfFactsTextView.setOnClickListener {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}