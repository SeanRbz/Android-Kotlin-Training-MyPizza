package com.example.mypizza.view.ui.store

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.mypizza.data.entities.StoreItems
import com.example.mypizza.databinding.StoreAddItemsDialogBinding
import com.example.mypizza.viewModel.StoreDataViewModel

class StoreCreateItems : DialogFragment() {

    private lateinit var binding: StoreAddItemsDialogBinding

    private val vm: StoreDataViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StoreAddItemsDialogBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddItem.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val price = binding.editTextPrice.text.toString().toDoubleOrNull() ?: 0.0
            val newItem = StoreItems(name = name, price = price,id = null)
            vm.addItem(newItem)
        }


        vm.addItemsSuccess.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(requireContext(), "Add Success", Toast.LENGTH_LONG).show()
                dismiss()
                vm.addItemsSuccess.value = false
            }
        })

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setGravity(Gravity.CENTER)
    }
}