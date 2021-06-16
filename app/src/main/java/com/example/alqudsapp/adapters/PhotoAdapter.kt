package com.example.alqudsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alqudsapp.R
import com.example.alqudsapp.models.City
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.categories_item.view.*


class PhotoAdapter(var activity: Context?, var data: MutableList<City>, var clickListener: onCategoryItemClickListener): RecyclerView.Adapter<PhotoAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageCategoryElement  =itemView.categoryImage
        val nameCategoryElement  =itemView.categoryName

        fun initialize(data: City, action:onCategoryItemClickListener){

            Picasso.get().load(data.imageCategoryElement).into(imageCategoryElement)
            nameCategoryElement.text = data.nameCategoryElement
            itemView.setOnClickListener {
                action.onItemClick(data,adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.MyViewHolder {
        var View: View = LayoutInflater.from(activity).inflate(R.layout.categories_item,parent,false)
        val myHolder:MyViewHolder = MyViewHolder(View)
        return myHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.initialize(data.get(position), clickListener)
    }
    interface onCategoryItemClickListener{
        fun onItemClick(data:City, position: Int)
    }
}