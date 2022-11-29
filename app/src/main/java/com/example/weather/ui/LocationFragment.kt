package com.example.weather.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.weather.R
import com.example.weather.databinding.FragmentLocationBinding


class LocationFragment : Fragment() {
    private var _binding: FragmentLocationBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.button.setOnClickListener {

                if (!TextUtils.isEmpty(binding.etCity.text.toString()) &&
                    !TextUtils.isEmpty(binding.etCountry.text.toString())) {
                    val bundle: Bundle = bundleOf("citykey" to binding.etCity.text.toString(),
                        "countrykey" to binding.etCountry.text.toString() )
                    it.findNavController()
                        .navigate(R.id.action_locationFragment_to_weatherFragment, bundle);
                }else{
                    Toast.makeText(activity,"Please fill all empty fields", Toast.LENGTH_LONG).show()
                }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}