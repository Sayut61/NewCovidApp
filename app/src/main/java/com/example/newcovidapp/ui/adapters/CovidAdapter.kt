package com.example.newcovidapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newcovidapp.R
import com.example.newcovidapp.data.DetailCovidInfoByCountry
import kotlinx.android.synthetic.main.covid_item.view.*
interface CovidAdapterListener{
    fun onCountryClick(country: DetailCovidInfoByCountry)
}
class CovidAdapter(val getAllNameCountries: List<DetailCovidInfoByCountry>, val listener: Int): RecyclerView.Adapter<CovidViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidViewHolder {
        val viewHolder = CovidViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.covid_item, parent, false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: CovidViewHolder, position: Int) {
        val country = getAllNameCountries[position]
        holder.itemView.setOnClickListener{
            listener.onCountryClick(country)
        }
        holder.bind(country)
    }

    override fun getItemCount(): Int {
        return getAllNameCountries.size
    }

}

class CovidViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    fun bind(countries: DetailCovidInfoByCountry){
        itemView.nameCountryTextView.text = countries.country
    }
}