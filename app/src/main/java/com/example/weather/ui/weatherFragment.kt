package com.example.weather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weather.network.WeatherService
import com.example.weather.network.RetrofitInstance
import com.example.weather.repository.ForcastWeather
import com.example.weather.repository.ForcastWeatherImpl
import com.example.weather.viewmodel.WeatherViewModel
import com.example.weather.viewmodel.WeatherViewModelFactory
import com.example.weather.databinding.FragmentWeatherBinding


class weatherFragment : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: WeatherViewModel
    private lateinit var viewModelFactory: WeatherViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        val view = binding.root
        getDataWeather()
        return view
    }

    private fun getDataWeather() {
        val retService= RetrofitInstance.getRetrofitInstance().create(WeatherService::class.java)
        val forcastWeather: ForcastWeather = ForcastWeatherImpl()
        viewModelFactory =  WeatherViewModelFactory(makeLocationString(),forcastWeather)
        viewModel = ViewModelProvider(this,viewModelFactory).get(WeatherViewModel::class.java)
        viewModel.weather.observe(viewLifecycleOwner, Observer {
            val data= it?.days
            binding.tvTemp.text=data?.get(0)?.temp.toString()
            binding.tvCondition.text=data?.get(0)?.conditions.toString()
            binding.tvCity.text=it?.resolvedAddress
            binding.tvDate.text=data?.get(0)?.datetime.toString()
            if(data!=null){
                binding.tvSign.visibility=View.VISIBLE
            }

        })
    }

    fun makeLocationString():String {
        var input:String?=requireArguments().getString("citykey")
        var input1:String?=requireArguments().getString("countrykey")
        val address="$input1 $input"
        return address
    }



}