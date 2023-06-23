package com.example.restaurants.views.main_restaurants_activity.fragments

import AddNotePopup
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurants.data.repositories.sharedPreferences

import com.example.restaurants.databinding.FragmentPannierBinding
import com.example.restaurants.viewmodels.MenuViewModel
import com.example.restaurants.views.authentication_activity.ActivityAuthentication
import com.example.restaurants.views.main_restaurants_activity.adapters.AdapterPannier


class FragmentPannier : Fragment() {


    lateinit var binding: FragmentPannierBinding
    lateinit var myModel: MenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentPannierBinding.inflate(layoutInflater)
        val popup = AddNotePopup(this)

        binding.icAddNotes.setOnClickListener {
            popup.showAtLocation(view, Gravity.CENTER, 0, 0)
        }
        binding.buttonConfirm.setOnClickListener {
            val sp = sharedPreferences(requireActivity())
            if(sp.isUserConnected()) {
                Toast.makeText(requireActivity(), "Command confirmed", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this.requireActivity(), ActivityAuthentication::class.java)
                startActivity(intent)
            }
        }
        val view = binding.root
        binding.recyclerViewPannier.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerViewPannier.adapter = AdapterPannier(requireActivity())

        return (view)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myModel = ViewModelProvider(requireActivity()).get(MenuViewModel::class.java)
    }
}