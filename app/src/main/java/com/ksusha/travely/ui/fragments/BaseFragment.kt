package com.ksusha.travely.ui.fragments

import androidx.fragment.app.Fragment
import com.ksusha.travely.MainActivity
import com.ksusha.travely.architecture.AttractionViewModel

abstract class BaseFragment : Fragment(){

    protected val navController by lazy {
        (activity as MainActivity).navController
    }

    protected val activityViewModel: AttractionViewModel
    get() = (activity as MainActivity).viewModel

}