package com.example.countries.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.databinding.CountryListItemBinding
import com.example.countries.model.CountriesModel
import com.example.countries.util.getProgressDrawable
import com.example.countries.util.loadImage

class CountryListAdapter (private var mCountry: ArrayList<CountriesModel> ) : RecyclerView.Adapter<CountryListAdapter.ViewHolder>() {


    class ViewHolder(var binding : CountryListItemBinding) : RecyclerView.ViewHolder(binding.root) {


    }
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        return ViewHolder(CountryListItemBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false))
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.binding.tvCountryName.text       = mCountry[position].countryName
        viewHolder.binding.tvCapitalCountry.text    = mCountry[position].capital
        viewHolder.binding.ivCountryFlag.loadImage(mCountry[position].flag, getProgressDrawable(viewHolder.itemView.context))

    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = mCountry.size

    fun updateCountry( newCountry : List<CountriesModel>){
        mCountry.clear()
        mCountry.addAll(newCountry)
        notifyDataSetChanged()
    }
}