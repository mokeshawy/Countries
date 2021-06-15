package com.example.countries.view.mainfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.countries.databinding.FragmentMainBinding
import com.example.countries.view.adapter.CountryListAdapter

class MainFragment : Fragment() {

    lateinit var binding : FragmentMainBinding
    private val mainViewModel : MainViewModel by viewModels()
     private var countryListAdapter = CountryListAdapter(arrayListOf())

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Connect with view model.
        binding.lifecycleOwner  = this
        binding.mainFragment    = mainViewModel

        // call fun refresh.
        mainViewModel.refresh()
        binding.rvCountryList.adapter = countryListAdapter

        // swipe refresh.
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            mainViewModel.refresh()
        }

        // call fun observe.
        observeViewModel()
    }

    // fun observe data.
    private fun observeViewModel() {
        // update data.
        mainViewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countryListAdapter.updateCountry(countries)
        })

        // error data.
        mainViewModel.countryLoadError.observe(viewLifecycleOwner, Observer { isError ->
            binding.listError.visibility =
                if (isError) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
        })

        // loading data.
        mainViewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            binding.loadingView.visibility =
                if (isLoading) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            if (isLoading) {
                binding.listError.visibility = View.GONE
                binding.rvCountryList.visibility = View.GONE
            }
        })
    }
}