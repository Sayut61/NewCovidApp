package com.example.newcovidapp.ui.main.map_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.example.newcovidapp.R
import com.example.newcovidapp.data.DetailCovidInfoByCountry
import com.example.newcovidapp.datasource.local.CovidDB
import kotlinx.android.synthetic.main.fragment_test_db.*

class FragmentTestDB : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test_db, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

/*        val text = testEditText.text.toString()
        val sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE)
        sharedPreferences?.edit()?.putString("testData", text)?.apply ()

        val loadText = sharedPreferences?.getString("testData", "")*/

        val db = Room.databaseBuilder(requireContext(), CovidDB::class.java, "db").allowMainThreadQueries().build()
        val countriesInfoDao = db.countriesInfoDao()
        insertToDBButton.setOnClickListener {
            val countryInfo = DetailCovidInfoByCountry(
                countryNameEditText.text.toString(),
                countryCasesEditTExt.text.toString().toLong(),
                0, 0,0,0,0
            )
            countriesInfoDao.insert(countryInfo)
        }

        loadFromDBButton.setOnClickListener {
            val listInfo = countriesInfoDao.getAllInfo()
            countriesInfoFromDBtextView.text = listInfo.toString()
        }
    }
}