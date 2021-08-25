package com.example.countries.view.mainfragment

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.countries.databinding.FragmentMainBinding
import com.example.countries.view.adapter.CountryListAdapter
@Suppress("DEPRECATION")
class MainFragment : Fragment() {

    lateinit var binding        : FragmentMainBinding
    private val mainViewModel   : MainViewModel by viewModels()
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

        // operation check connect with network.
        val connectManager  = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo     = connectManager.allNetworkInfo

        // swipe refresh.
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            mainViewModel.refresh()
        }

        for( networkInfo in networkInfo){
            if(networkInfo.typeName.equals("WIFI",ignoreCase = true)){
                if(networkInfo.isConnected){
                    // call fun refresh.
                    mainViewModel.refresh()
                    binding.rvCountryList.adapter = countryListAdapter

                    Toast.makeText(requireActivity(),"Connected with Wifi",Toast.LENGTH_SHORT).show()
                    // call fun observe.
                    observeViewModel()

                }else{
                    Toast.makeText(requireActivity(),"Disconnect",Toast.LENGTH_SHORT).show()
                    binding.loadingView.visibility = View.GONE

                }
            }
        }
    }

    // fun observe data.
    private fun observeViewModel() {
        // Show data from api.
        mainViewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            binding.rvCountryList.visibility = View.VISIBLE
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