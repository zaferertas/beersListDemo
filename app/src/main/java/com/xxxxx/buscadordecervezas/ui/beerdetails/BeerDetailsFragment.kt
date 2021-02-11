package com.xxxxx.buscadordecervezas.ui.beerdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.xxxxx.buscadordecervezas.data.Beer
import com.xxxxx.buscadordecervezas.databinding.BeerDetailsFragmentBinding

class BeerDetailsFragment : Fragment() {

    companion object {
        private const val BEER_KEY = "beer_key"

        @JvmStatic
        fun newInstance(beer: Beer): BeerDetailsFragment {
            return BeerDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(BEER_KEY, beer)
                }
            }
        }
    }

    private lateinit var binding: BeerDetailsFragmentBinding
    private lateinit var beer: Beer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BeerDetailsFragmentBinding.inflate(inflater, container, false)

        arguments?.let {
            beer = it.getSerializable(BEER_KEY) as Beer
            bindDataToViews(beer)

        }

        return binding.root
    }

    private fun bindDataToViews(beer: Beer) {
        binding.beerDetailsId.text = String.format("%s", beer.id)
        binding.beerDetailsName.text = beer.name
        binding.beerDetailsTagline.text = beer.tagline
        binding.beerDetailsAbvValue.text = String.format("%s%%", beer.abv)
        binding.beerDetailsIbuValue.text = String.format("%s", beer.ibu)
        // binding.beerDetailsDate.text = DateFormatter.formatDate(beer.firstBrewed, false)
        binding.beerDetailsDescription.text = beer.description

        context?.let {
            Glide.with(it).load(beer.imageUrl)
                .into(binding.beerDetailsImage)
        }
    }


}