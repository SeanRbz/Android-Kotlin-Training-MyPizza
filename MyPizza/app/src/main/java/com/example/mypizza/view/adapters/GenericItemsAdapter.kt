package com.example.mypizza.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mypizza.data.entities.StoreItems
import com.example.mypizza.databinding.GenericItemsAdapterBinding

class GenericItemsAdapter(val isStore: Boolean,
                          val callback: GenericItemOnClickTrigger  ): RecyclerView.Adapter<GenericItemsAdapter.LandingViewHolder>(){

    private var  listDatas: List<StoreItems> = arrayListOf()


    fun setItems(list:List<StoreItems>){
        listDatas = list
        notifyDataSetChanged()
    }


    interface GenericItemOnClickTrigger{
         fun onCickItem(item:StoreItems,isAdd:Boolean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandingViewHolder {
        val binding = GenericItemsAdapterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return LandingViewHolder(binding,isStore)
    }

    override fun getItemCount() = listDatas.size

    override fun onBindViewHolder(holder: LandingViewHolder, position: Int) {
       holder.bind(listDatas[position])
    }


    inner class LandingViewHolder( private val binding: GenericItemsAdapterBinding,private val store: Boolean
    ) : RecyclerView.ViewHolder(binding.root){

      fun bind(item:StoreItems)= with(binding.root){
          var tempQty = 0

          if(store){
              binding.btnAdd.visibility = View.GONE
              binding.btnMinus.visibility = View.GONE
          }

          binding.itemQty.text = tempQty.toString()
          binding.itemName.text = item.name
          binding.itemPrice.text = item.price.toString()

          binding.btnAdd.setOnClickListener {
              tempQty++
              binding.itemQty.text = tempQty.toString()
              callback.onCickItem(item,true)
          }

          binding.btnMinus.setOnClickListener {
              if(tempQty>0){
                  tempQty--
                  binding.itemQty.text = tempQty.toString()
                  callback.onCickItem(item,false)
              }
          }

      }


    }


}