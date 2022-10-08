package com.ksusha.travely.ui.fragments

import androidx.fragment.app.Fragment
import com.ksusha.travely.MainActivity
import com.ksusha.travely.data.Attraction

abstract class BaseFragment : Fragment(){

    protected val navController by lazy {
        (activity as MainActivity).navController
    }

    protected val attractions: List<Attraction>
    get() = (activity as MainActivity).attractionList

}