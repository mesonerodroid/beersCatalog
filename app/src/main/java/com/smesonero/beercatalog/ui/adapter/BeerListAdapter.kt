package com.smesonero.beercatalog.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.smesonero.beercatalog.R
import com.smesonero.beercatalog.domain_model.Beer
import com.smesonero.beercatalog.ui.fragments.BeersListFragment

class BeerListAdapter(
    private val list: List<Beer>,
    listener: BeersListFragment.beerClickLner
)
    : RecyclerView.Adapter<BeerViewHolder>() {

    lateinit var ctx: Context
    var listener = listener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        ctx = parent.context
        return BeerViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        val beer: Beer = list[position]
        holder.bind(beer)

        if(beer.available){
            holder.itemView.setBackgroundColor(ContextCompat.getColor(ctx, R.color.white));
        }
        else{
            holder.itemView.setBackgroundColor(ContextCompat.getColor(ctx, R.color.lightgrey));
        }
        holder.itemView.setOnClickListener {
            listener.onBeerClick(beer)
        }
    }

    override fun getItemCount(): Int {

//        Log.e("ADAPTER", "Size: "+list.size)
        return list.size
    }


}