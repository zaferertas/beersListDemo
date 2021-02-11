package com.xxxxx.buscadordecervezas.ui.beerlist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.xxxxx.buscadordecervezas.R
import com.xxxxx.buscadordecervezas.data.Beer
import com.xxxxx.buscadordecervezas.databinding.BeerListFragmentBinding
import com.xxxxx.buscadordecervezas.ui.beerdetails.BeerDetailsFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BeerListFragment : Fragment() {

    private val viewModel: BeerListViewModel by viewModels()

    private lateinit var binding: BeerListFragmentBinding
    private lateinit var adapter: BeersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.title = getString(R.string.app_name)

        adapter = BeersAdapter(
            object :
                BeersAdapter.ItemClickListener {
                override fun onClick(item: Beer) {
                    showDetailsFragment(item)
                }
            })

        binding = BeerListFragmentBinding.inflate(inflater, container, false)
        binding.beersRecyclerView.adapter = adapter

        setUpObservers()
        return binding.root
    }


    private fun setUpObservers() {
        viewModel.beers.observe(viewLifecycleOwner) { result ->

            if (result != null) {
                Log.i("zfrD", "viewModel result" + result.toString())
                adapter.setItems(result)
            } else {
                Log.i("zfrD", "viewModel result == null")
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.search(s.toString())
            }

        })
    }

    private fun showDetailsFragment(beer: Beer) {

        parentFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainerView,
                BeerDetailsFragment.newInstance(beer),
                "details_fragment"
            )
            .addToBackStack(null)
            .commit()
    }


}