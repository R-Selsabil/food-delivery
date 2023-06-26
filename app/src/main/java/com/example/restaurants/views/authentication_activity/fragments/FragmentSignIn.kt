package com.example.restaurants.views.authentication_activity.fragments


import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider


import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurants.R
import com.example.restaurants.databinding.FragmentSignInBinding
import com.example.restaurants.data.repositories.sharedPreferences
import com.example.restaurants.retrofit.UserEndPoint
import com.example.restaurants.viewmodels.UserViewModel
import kotlinx.coroutines.*
import java.net.PasswordAuthentication

class FragmentSignIn : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    lateinit var userModel: UserViewModel
    lateinit var progressBar: ProgressBar
    lateinit var recyclerView: RecyclerView
    lateinit var email: String
    lateinit var password: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        userModel.initViewModel(requireActivity())


        binding.textSignUp.setOnClickListener {
            it.findNavController().navigate(R.id.action_fragmentSignIn_to_fragmentSignUp)
        }
        binding.buttonSignIn.setOnClickListener {
            email = binding.editTextTextEmailAddressSignin.text.toString()
            password = binding.passwordInputLayoutSignIn.text.toString()
            userModel.signInUser(email, password)
            requireActivity().finish()
        }
    }


}

