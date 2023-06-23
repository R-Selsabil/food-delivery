package com.example.restaurants.views.main_restaurants_activity.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.restaurants.data.repositories.sharedPreferences

import com.example.restaurants.databinding.FragmentProfileBinding
import com.example.restaurants.views.authentication_activity.ActivityAuthentication

class FragmentProfile : Fragment() {

    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLogOut.setOnClickListener {
            val sp = sharedPreferences(requireActivity())
            sp.logOut()
        }

    }
}

