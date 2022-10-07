package com.ksusha.travely.ui.fragments

import androidx.fragment.app.Fragment
import com.ksusha.travely.MainActivity

abstract class BaseFragment : Fragment(){

    protected val navController by lazy {
        (activity as MainActivity).navController
    }

}