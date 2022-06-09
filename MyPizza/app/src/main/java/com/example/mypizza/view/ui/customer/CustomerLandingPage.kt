package com.example.mypizza.view.ui.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypizza.data.entities.StoreItems
import com.example.mypizza.databinding.CustomerLandingPageBinding
import com.example.mypizza.view.adapters.GenericItemsAdapter
import com.example.mypizza.viewModel.StoreDataViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CustomerLandingPage : Fragment() ,GenericItemsAdapter.GenericItemOnClickTrigger  {

    lateinit var binding: CustomerLandingPageBinding

    private lateinit var adapterList: GenericItemsAdapter

    private val vm: StoreDataViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CustomerLandingPageBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gridViewMenu.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = GenericItemsAdapter(false,this@CustomerLandingPage)
        }

        adapterList =
            binding.gridViewMenu.adapter as GenericItemsAdapter


        vm.storeInfo.observeForever {
            if (it != null) {
                binding.txtStoreName.setText(it.name)
                binding.txtStoreAddress.setText(it.address)
            } else {
                binding.txtStoreName.setText(StoreDataViewModel.DEFAULT_STORE_INFO.name)
                binding.txtStoreAddress.setText(StoreDataViewModel.DEFAULT_STORE_INFO.address)
            }
        }


        vm.storeItems.observeForever {
            if (!it.isNullOrEmpty()) {
                adapterList.setItems(it)
            }
        }
    }

    override fun onCickItem(item: StoreItems, isAdd: Boolean) {

    }
}