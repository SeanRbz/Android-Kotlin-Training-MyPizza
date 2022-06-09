package com.example.mypizza.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.mypizza.R
import com.example.mypizza.databinding.MainLandingPageBinding

class MainLandingPage : Fragment() {

    lateinit var binding: MainLandingPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainLandingPageBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCustomer.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.MainLandingPage_to_CustomerLoginPage)
        }

        binding.btnStore.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.MainLandingPage_to_StoreLandingPage)
        }

    }
}