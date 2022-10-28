package com.ksusha.travely.ui.fragments

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.navArgs
import com.ksusha.travely.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_attraction_details, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menuItemLocation -> {
                val intentUri = Uri.parse("geo:${attraction.location.latitude},${attraction.location.longitude}?z=9&q=${attraction.title}")
                val intent = Intent(Intent.ACTION_VIEW, intentUri)
                intent.setPackage("com.google.android.apps.maps")
                startActivity(intent)
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.titleTextView.text = attraction.title
        binding.descriptionTextView.text = attraction.description
        Picasso.get().load(attraction.image_url).into(binding.headerImageView)
        binding.monthsToVisitTextView.text = attraction.months_to_visit
        binding.numberOfFactsTextView.text = "${attraction.facts.size} facts"
        binding.numberOfFactsTextView.setOnClickListener {
            val stringBuilder = StringBuilder("")
            attraction.facts.forEach {
                stringBuilder.append("\u2022 $it")
                stringBuilder.append("\n\n")
            }
            val message = stringBuilder.toString().substring(0, stringBuilder.toString().lastIndexOf("\n\n"))
            AlertDialog.Builder(requireContext())
                .setTitle("${attraction.title} Facts")
                .setMessage(message)
                .setPositiveButton("Ok"){ dialog, which ->
                    dialog.dismiss()
                }.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}