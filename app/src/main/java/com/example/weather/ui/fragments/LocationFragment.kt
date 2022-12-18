package com.example.weather.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.weather.R
import com.example.weather.databinding.FragmentLocationBinding
import com.example.weather.network.local.LocationDatabase
import com.example.weather.viewmodel.LocationViewModel
import com.example.weather.viewmodel.LocationViewModelFactory


class LocationFragment : Fragment() {
    private var _binding: FragmentLocationBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LocationViewModel
    private lateinit var viewModelFactory: LocationViewModelFactory
    var countriesList = ArrayList<String>()
    var citiesList = ArrayList<String>()
    var country:String?=null
    var city:String?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        val view = binding.root
        val locationDatabase = LocationDatabase.getInstance(requireContext())
        locationDatabase.openHelper
        viewModelFactory = LocationViewModelFactory(locationDatabase!!)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LocationViewModel::class.java)

        val sharedPref = requireContext().getSharedPreferences(
            "mySharedPrefData", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        val f = sharedPref.getBoolean("key", true)
        if (f == true) {
            viewModel.save()
            editor.apply() {
                putBoolean("key", false)
                apply()
            }
        }
        countriesList.clear()
        val adapterCountry: ArrayAdapter<String> =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, countriesList)
        adapterCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val adapterCity: ArrayAdapter<String> =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, citiesList)
        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCountry.setAdapter(adapterCountry)
      //  binding.spinnerCountry.onItemSelectedListener
        binding.spinnerCity.setAdapter(adapterCity)
        //binding.spinnerCity.onItemSelectedListener

        viewModel.getAllLocations()


//        viewModel.countryItem.observe(viewLifecycleOwner, Observer {
//            it.forEach {
//                Log.i("this is1", it.country)
//               // countriesList.clear()
//                countriesList.add(it.country)
//            }
//
//            adapterCountry.notifyDataSetChanged()
//        })
        viewModel.countryItem.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                // This is only executed if the event has never been handled

            it.forEach {
                Log.i("this is1", it.country)
               // countriesList.clear()
                countriesList.add(it.country)
            }

            adapterCountry.notifyDataSetChanged()
            }
        })

        // viewModel.clearAllLocations()

        binding.spinnerCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                 country= binding.spinnerCountry.selectedItem.toString()
                Toast.makeText(requireContext(), country, Toast.LENGTH_LONG).show()

                    viewModel.getCities(country!!)
                viewModel.cityItem.observe(viewLifecycleOwner, Observer {
                    it.getContentIfNotHandled()?.let {
                        it.forEach {
                            //  Log.i("this is1", it.country)
                            //  citiesList.clear()
                            citiesList.add(it.city)

                        }
                        adapterCity.notifyDataSetChanged()
                    }
                })
            }

        }


        binding.spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                city= binding.spinnerCity.selectedItem.toString()
                Log.i("cityyy", city!!)
                Toast.makeText(requireContext(), city, Toast.LENGTH_LONG).show()

            }

        }

        binding.button.setOnClickListener {

            //if (!TextUtils.isEmpty(binding.etCity.text.toString())
            //    !TextUtils.isEmpty(binding.etCountry.text.toString())
            //) {
                val bundle: Bundle = bundleOf("citykey" to city,
                    "countrykey" to country)
                it.findNavController()
                    .navigate(R.id.action_locationFragment_to_weatherFragment, bundle);
          //  } else {
          //     Toast.makeText(activity, "Please fill all empty fields", Toast.LENGTH_LONG).show()
          //  }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onResume() {
        super.onResume()

    }

}