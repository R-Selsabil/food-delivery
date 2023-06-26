package com.example.restaurants.views.main_restaurants_activity.adapters
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.restaurants.R

import com.example.restaurants.data.models.Restaurant
import com.example.restaurants.databinding.LayoutRestaurantBinding
import com.example.restaurants.utils.url
import com.example.restaurants.views.main_restaurants_activity.RestaurantClickListener


class AdapterRestaurants(private val context: Context, private val clickListener: RestaurantClickListener):
    RecyclerView.Adapter<AdapterRestaurants.MyViewHolder>(){
    var data = mutableListOf<Restaurant>()
    fun setRestaurant(restaurants : List<Restaurant>) {
        this.data = restaurants.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutRestaurantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        bind(holder,data[position])
    }
    fun bind(holder: MyViewHolder, restaurant: Restaurant) {
        holder.binding.apply {
            Glide.with(context)
                .load("${url}images/restaurants/${restaurant.logo}")
                .into(imageRestaurant)

            textName.text = restaurant.name
            //textAdresse.text = restaurant.address

            textType.text = restaurant.type
            icFb.setOnClickListener {
                clickListener.onFbClicked(restaurant.facebook)
            }
            icIg.setOnClickListener {
                clickListener.onIgClicked(restaurant.instagram)
            }
            icMap.setOnClickListener {
                clickListener.onMapClicked(restaurant.latitude, restaurant.longitude)
            }
            icPhone.setOnClickListener {
                clickListener.onPhoneClicked(restaurant.phone)
            }
            icMail.setOnClickListener {
                clickListener.onEmailClicked(restaurant.email)
            }
        }

        holder.itemView.setOnClickListener{
            val bundle = Bundle()
            bundle.putInt("id_restaurant", restaurant.id)
            it.findNavController().navigate(R.id.action_fragmentActivityRestaurants_to_fragmentMenus, bundle)
            //val intent = Intent(context, ActivityMenu::class.java)
            //context.startActivity(intent)
        }
    }
    class MyViewHolder(val binding: LayoutRestaurantBinding) : RecyclerView.ViewHolder(binding.root)
}



