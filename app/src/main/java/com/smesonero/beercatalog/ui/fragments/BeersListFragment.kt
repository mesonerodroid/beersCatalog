package com.smesonero.beercatalog.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.smesonero.beercatalog.R
import com.smesonero.beercatalog.domain_model.Beer
import com.smesonero.beercatalog.ui.adapter.BeerListAdapter
import com.smesonero.beercatalog.viewmodel.BeerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_first.*




@AndroidEntryPoint
class BeersListFragment : Fragment() {


    interface beerClickLner {
        fun onBeerClick(beer: Beer)
    }


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


        beer_progress.visibility=View.VISIBLE
        beerViewModel.getLiveData()
        beerViewModel.beerLiveData.observe(viewLifecycleOwner, Observer {
            Log.e("FIRST", "live change: "+it.size + "   "+it.toString())
            drawList(it)
        })



//        beerViewModel.selectedBeerLiveData.observe(viewLifecycleOwner, Observer{
//
//            Log.e("FIRST", "Selected beer: "+it.name);
//            val args = Bundle()
//            args.putInt("beer_id", it.id)
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, args)
//        })
    }

    private fun drawList(list: List<Beer>) {
        beer_progress.visibility=View.INVISIBLE
        recyclerBeers.apply {
            layoutManager = LinearLayoutManager(activity)

            val listener = object: beerClickLner{
                override fun onBeerClick(beer: Beer) {
                    val args = Bundle()
                    args.putInt(Constants.BEER_ARGUMENT_ID, beer.id)
                    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, args)
                }
            }
            adapter = BeerListAdapter(list, listener)

        }
        recyclerBeers.addItemDecoration(DividerItemDecoration(recyclerBeers.getContext(), DividerItemDecoration.VERTICAL))
    }
}
