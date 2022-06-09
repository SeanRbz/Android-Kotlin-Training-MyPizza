package com.example.mypizza.view.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypizza.R
import com.example.mypizza.data.entities.StoreInformation
import com.example.mypizza.data.entities.StoreItems
import com.example.mypizza.databinding.StoreAddItemsDialogBinding
import com.example.mypizza.databinding.StoreLandingPageBinding
import com.example.mypizza.view.adapters.GenericItemsAdapter
import com.example.mypizza.viewModel.StoreDataViewModel
import com.example.mypizza.viewModel.StoreDataViewModel.Companion.DEFAULT_STORE_INFO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StoreLandingPage : Fragment(), GenericItemsAdapter.GenericItemOnClickTrigger {

    private lateinit var binding: StoreLandingPageBinding

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
        binding = StoreLandingPageBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gridViewMenu.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = GenericItemsAdapter(true,this@StoreLandingPage)
        }
         adapterList =
            binding.gridViewMenu.adapter as GenericItemsAdapter

        vm.storeInfo.observeForever {
            if (it != null) {
                binding.txtStoreName.setText(it.name)
                binding.txtStoreAddress.setText(it.address)
            } else {
                vm.insertStoreInfo(DEFAULT_STORE_INFO)
            }
        }


        binding.btnAddItem.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.StoreLandingPage_to_StoreAddItems)
        }

        binding.btnEdit.setOnClickListener {
            val name = binding.txtStoreName.text.toString()
            val address = binding.txtStoreAddress.text.toString()
            val data = StoreInformation(name = name, address = address)
            vm.updateStoreInfo(data)
        }


        vm.updateSuccess.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(requireContext(), "Update Success", Toast.LENGTH_LONG).show()
                vm.updateSuccess.value = false
            }
        })


        vm.storeItems.observeForever {
            if (!it.isNullOrEmpty()) {
                adapterList.setItems(it)
            }
        }

    }

    override fun onCickItem(item: StoreItems, isAdd: Boolean) {

    }

}