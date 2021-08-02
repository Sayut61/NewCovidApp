package com.example.newcovidapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newcovidapp.R
import com.example.newcovidapp.data.CountryCovidInfo
import com.example.newcovidapp.ui.main.main_fragment.FragmentMain
import kotlinx.android.synthetic.main.covid_item.view.*
interface CovidAdapterListener{
    fun onCountryClick(countryCovidInfo: CountryCovidInfo)
}
class CovidAdapter(val getAllNameCountryCovidInfos: List<CountryCovidInfo>, val listener: FragmentMain): RecyclerView.Adapter<CovidViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidViewHolder {
        val viewHolder = CovidViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.covid_item, parent, false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: CovidViewHolder, position: Int) {
        val country = getAllNameCountryCovidInfos[position]
        holder.itemView.setOnClickListener{
            listener.onCountryClick(country)
        }
        holder.bind(country)
    }

    override fun getItemCount(): Int {
        return getAllNameCountryCovidInfos.size
    }

}

class CovidViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    fun bind(countries: CountryCovidInfo){
        itemView.nameCountryTextView.text = countries.country
        itemView.casesCountiesTextView.text = countries.cases.toString()
    }
}