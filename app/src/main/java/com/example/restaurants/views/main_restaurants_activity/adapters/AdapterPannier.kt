package com.example.restaurants.views.main_restaurants_activity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurants.data.models.CartModel
import com.example.restaurants.databinding.LayoutPannierBinding


class AdapterPannier(val ctx: FragmentActivity) :
    RecyclerView.Adapter<AdapterPannier.MyViewHolder>() {
    private var data: MutableList<CartModel> = mutableListOf()

    fun setCart(carts : List<CartModel>) {
        data.clear()
        data.addAll(carts)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(
            LayoutPannierBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = data.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val menu = data[position]
        holder.binding.apply {
            textPannierName.text = menu.name
            textPannierType.text = menu.type
            textPannierPrice.text = menu.price
            counterTextPannier.text = menu.quantity.toString()
            //imagePannier.setImageResource(menu.menuItem.logo)

            /*plusButton.setOnClickListener {
                var int = menu.quantity
                int ++
                menu.quantity = int.toString()
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
            }*/



        }
    }


    class MyViewHolder(val binding: LayoutPannierBinding) :
        RecyclerView.ViewHolder(binding.root)
}




