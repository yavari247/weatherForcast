package com.example.weather.ui.fragments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
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
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener


class LocationFragment : Fragment() {
    private var _binding: FragmentLocationBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LocationViewModel
    private lateinit var viewModelFactory: LocationViewModelFactory
    var m = ArrayList<String>()
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
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, m)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinner.setAdapter(adapter)
        binding.spinner.onItemSelectedListener
        viewModel.getAllLocations()



        viewModel.item.observe(viewLifecycleOwner, Observer {
            it.forEach {
                Log.i("this is1", it.country)
                m.add(it.country)
                adapter.notifyDataSetChanged()
            }


        })
        // viewModel.clearAllLocations()

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                var s: String = binding.spinner.selectedItem.toString()
                Toast.makeText(requireContext(), s, Toast.LENGTH_LONG).show()
            }

        }


        binding.button.setOnClickListener {

            if (!TextUtils.isEmpty(binding.etCity.text.toString()) &&
                !TextUtils.isEmpty(binding.etCountry.text.toString())
            ) {
                val bundle: Bundle = bundleOf("citykey" to binding.etCity.text.toString(),
                    "countrykey" to binding.etCountry.text.toString())
                it.findNavController()
                    .navigate(R.id.action_locationFragment_to_weatherFragment, bundle);
            } else {
                Toast.makeText(activity, "Please fill all empty fields", Toast.LENGTH_LONG).show()
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}