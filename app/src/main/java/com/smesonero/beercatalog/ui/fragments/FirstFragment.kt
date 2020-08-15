package com.smesonero.beercatalog.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.smesonero.beercatalog.R
import com.smesonero.beercatalog.viewmodel.BeerViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class FirstFragment : Fragment() {

    val beerViewModel :BeerViewModel by viewModels()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        beerViewModel.beerLiveData.observe(viewLifecycleOwner, Observer {

            Log.e("FIRST", "live cambio: "+it.size + "   "+it.toString())
            //drawData
        })

    }


}