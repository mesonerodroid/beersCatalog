package com.smesonero.beercatalog.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smesonero.beercatalog.R
import com.smesonero.beercatalog.domain_model.Beer
import org.w3c.dom.Text

class BeerViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.beer_item, parent, false)) {

    private var nameText : TextView?= null
    private var tagLine : TextView?= null


    init {

        nameText = itemView.findViewById(R.id.beer_item_name)
        tagLine= itemView.findViewById(R.id.beer_item_tagline)

    }
    fun bind(beer: Beer){
        nameText?.text = beer.name
        tagLine?.text = beer.tagline

    }
}