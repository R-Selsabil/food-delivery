package com.example.restaurants.views.authentication_activity.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.restaurants.databinding.FragmentSignUpBinding
import com.example.restaurants.viewmodels.UserViewModel
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class FragmentSignUp : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var name: String
    private lateinit var email: String
    private lateinit var phone: String
    private lateinit var password: String
    private lateinit var userModel: UserViewModel
    private lateinit var imageView: ImageView
    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        userModel.initViewModel(requireActivity())

        binding.buttonSignIn.setOnClickListener {
            email = binding.editTextTextEmailAddress.text.toString()
            password = binding.passwordInputLayoutText.text.toString()
            phone = binding.editTextNumberPassword.text.toString()
            name = binding.textInputLayout.text.toString()

            val imageBitmap = (imageView.drawable as? BitmapDrawable)?.bitmap
            val picturePart = imageBitmap?.let { createImagePart(it) }

            userModel.signUpUser(name, email, phone, password, picturePart)

            requireActivity().finish()
        }

        val button = binding.updateImage
        imageView = binding.imageProfile
        button.setOnClickListener {
            dispatchTakePictureIntent()
        }
    }

    private fun createImagePart(bitmap: Bitmap): MultipartBody.Part {
        val filesDir = requireActivity().applicationContext.filesDir
        val file = File(filesDir, "image.png")
        val fos = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, fos)
        fos.flush()
        fos.close()

        val reqFile = RequestBody.create(MediaType.parse("image/*"), file)
        return MultipartBody.Part.createFormData("picture", file.name, reqFile)
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        }
    }
}
