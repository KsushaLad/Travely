package com.ksusha.travely.ui.fragments.home

import com.airbnb.epoxy.EpoxyController
import com.ksusha.travely.R
import com.ksusha.travely.data.Attraction
import com.ksusha.travely.databinding.ViewHolderAttractionBinding
import com.ksusha.travely.ui.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

class HomeFragmentController(private val onClickedCallback: (String) -> Unit) : EpoxyController() {

    var isLoading: Boolean = false
        set(value) {
            field = value
            if (field){
                requestModelBuild()
            }
        }

    var attractions = ArrayList<Attraction>()
    set(value) {
        field = value
        isLoading = false
        requestModelBuild()
    }

    data class AttractionEpoxyModel(
        val attraction: Attraction,
        val onClicked: (String) -> Unit
    ) : ViewBindingKotlinModel<ViewHolderAttractionBinding>(R.layout.view_holder_attraction) {
        override fun ViewHolderAttractionBinding.bind() {
            titleTextView.text = attraction.title
            Picasso.get().load(attraction.image_url).into(headerImageView)
            monthsToVisitTextView.text = attraction.months_to_visit
            item.setOnClickListener {
                onClicked(attraction.id)
            }
        }
    }

    override fun buildModels() {
        if (isLoading){
            return
        }
        if (attractions.isEmpty()){
            return
        }
        attractions.forEach {
            AttractionEpoxyModel(it, onClickedCallback).id(it.id).addTo(this)
        }
    }

}