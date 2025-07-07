package com.example.edmedtech.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.edmedtech.databinding.ItemBasicEducationBinding
import com.example.edmedtech.models.BasicEducationList

class BasicEducationAdapter(
    private val itemsList: List<BasicEducationList>,
    private val onItemClick: (BasicEducationList) -> Unit
):RecyclerView.Adapter<BasicEducationAdapter.BasicEducationViewHolder>() {
    override fun onCreateViewHolder( parent: ViewGroup,viewType: Int): BasicEducationViewHolder {

        val binding = ItemBasicEducationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BasicEducationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasicEducationViewHolder,position: Int) {
       val item = itemsList[position]
        with(holder.binding) {
            imageView.setImageResource(item.imageResId)
            textTitle.text = item.title
            textSubtitle.text = item.subtitle
            textDescription.text = item.description
            buttonAction.text = item.buttonText

            buttonAction.setOnClickListener {
                onItemClick(item)
            }
        }


    }
    override fun getItemCount(): Int = itemsList.size

    class BasicEducationViewHolder(val binding: ItemBasicEducationBinding):RecyclerView.ViewHolder (binding.root){
    }
}