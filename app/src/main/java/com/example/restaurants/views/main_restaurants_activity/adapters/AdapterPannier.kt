package com.example.restaurants.views.main_restaurants_activity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurants.databinding.LayoutPannierBinding
import com.example.restaurants.viewmodels.MenuViewModel


class AdapterPannier(val ctx: FragmentActivity) :
    RecyclerView.Adapter<AdapterPannier.MyViewHolder>() {
    private val myModel: MenuViewModel = ViewModelProvider(ctx).get(MenuViewModel::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(
            LayoutPannierBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = myModel.cartItems.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val menu = myModel.cartItems[position]
        holder.binding.apply {
            textPannierName.text = menu.menuItem.name
            textPannierType.text = menu.menuItem.category
            textPannierPrice.text = menu.menuItem.price.toString()
            counterTextPannier.text = menu.quantity.toString()
            //imagePannier.setImageResource(menu.menuItem.logo)

            plusButton.setOnClickListener {
                var int = myModel.qttOfItem.toInt()
                int ++
                myModel.qttOfItem = int.toString()
                println(myModel.qttOfItem)
                counterTextPannier.text = myModel.qttOfItem
            }
            minusButton.setOnClickListener {
                var int = myModel.qttOfItem.toInt()
                if (int > 0) {
                    int--
                    myModel.qttOfItem = int.toString()
                    counterTextPannier.text = myModel.qttOfItem
                }
            }
        }
    }


    class MyViewHolder(val binding: LayoutPannierBinding) :
        RecyclerView.ViewHolder(binding.root)
}




