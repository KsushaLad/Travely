package com.ksusha.travely.architecture

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ksusha.travely.data.Attraction

class AttractionViewModel : ViewModel() {

    private val repository = AttractionRepository()
    val attractionListLiveData = MutableLiveData<ArrayList<Attraction>>() //HomeFragment
    val selectedAttractionLiveData = MutableLiveData<Attraction>() //DetailsFragment

    fun initViewModel(context: Context){
            val attractionList = repository.parseAttraction(context)
            attractionListLiveData.postValue(attractionList)
    }

    fun attractionSelected(attractionId: String){
       val attraction = attractionListLiveData.value?.find {
            it.id == attractionId
        } ?: return
        selectedAttractionLiveData.postValue(attraction)
    }

}