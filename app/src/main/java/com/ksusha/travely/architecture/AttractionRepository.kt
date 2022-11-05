package com.ksusha.travely.architecture

import android.content.Context
import com.ksusha.travely.R
import com.ksusha.travely.data.Attraction
import com.ksusha.travely.data.AttractionResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class AttractionRepository {

   fun parseAttraction(context: Context): List<Attraction>{
        val textFromFile = context.resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val adapter: JsonAdapter<AttractionResponse> = moshi.adapter(AttractionResponse::class.java)
        return adapter.fromJson(textFromFile)!!.attractions
    }

}