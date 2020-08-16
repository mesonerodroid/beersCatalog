package com.smesonero.beercatalog.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.smesonero.beercatalog.R
import com.smesonero.beercatalog.domain_model.Beer
import com.smesonero.beercatalog.viewmodel.BeerViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class BeerDetailFragment : Fragment() {


    val beerViewModel : BeerViewModel by viewModels()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate    the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }
//                SecondFragment.fromBundle(arguments).getPrivacyPolicyLink()
//            var id:Int = arguments["beer_id"]

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var id : Int = requireArguments().getInt(Constants.BEER_ARGUMENT_ID)
        var beer = beerViewModel.giveMeBeerById(id)
        drawInfoBeer(beer)

        detail_switch.setOnCheckedChangeListener {compoundButton, barrel ->
            if (barrel){
                detail_textnoavaiable.visibility=View.INVISIBLE
            }
            else{
                detail_textnoavaiable.visibility=View.VISIBLE
            }
            beerViewModel.updateAvailable(beer, barrel)
        }
    }

    private fun drawInfoBeer(beer: Beer) {

        detail_title_text.text = beer.name
        detail_description_text.text = beer.description
        detail_abv_text.text= "ABV: "+beer.abv
        detail_ibu_text.text = "IBU: "+ beer.ibu

        var fpairing = ""
        beer.foodPairing.forEach{
            fpairing+= "#"+it+"\n"
        }
        if(beer.available){
            detail_textnoavaiable.visibility=View.INVISIBLE
            detail_switch.isChecked=true
        }
        else{
            detail_textnoavaiable.visibility=View.VISIBLE
            detail_switch.isChecked=false
        }
        detail_switch.visibility = View.VISIBLE
        detail_foodpairing_text.text = fpairing
        drawImage(beer.image_url)
    }

    private fun drawImage(imageUrl: String) {
        progressBar.setVisibility(View.VISIBLE)
        Picasso.with(context)
            .load(imageUrl)
            .into(detail_image, object: Callback {
                override fun onSuccess() {
                    progressBar.setVisibility(View.GONE)
                }
                override fun onError() {
                    progressBar.setVisibility(View.GONE)
                }
            })
    }
}