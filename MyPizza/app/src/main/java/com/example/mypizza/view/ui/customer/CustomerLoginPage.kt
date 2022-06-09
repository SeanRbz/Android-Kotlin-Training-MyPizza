package com.example.mypizza.view.ui.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.mypizza.R
import com.example.mypizza.databinding.CustomerLoginPageBinding
import com.example.mypizza.viewModel.CustomerViewModel

class CustomerLoginPage: Fragment() {

    lateinit var binding: CustomerLoginPageBinding

    private val vm: CustomerViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  CustomerLoginPageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            val userName = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()
            vm.validateUserFields(userName,password).subscribe {isValid: Boolean->
                if(isValid){
                    vm.register(userName,password)
                }else{
                    makeText(requireContext(),"Username and Password is Invalid",Toast.LENGTH_SHORT).show()
                }
            }
        }


        binding.btnLogin.setOnClickListener {
            val userName = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()
            vm.loginUser(userName,password).observe(viewLifecycleOwner, Observer {
                if(it!=null){
                    makeText(requireContext(),"Login Success",Toast.LENGTH_LONG).show()
                    Navigation.findNavController(requireView()).navigate(R.id.CustomerLoginPage_to_CustomerLandingPage)
                }
            })
        }

        vm.loginPageResponse.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                if(it.second!=""){
                    makeText(requireContext(),"Register ${it.second}",Toast.LENGTH_LONG).show()
                }else{
                    makeText(requireContext(),"Register Success",Toast.LENGTH_LONG).show()
                }
                vm.loginPageResponse.value = null
            }
        })

    }

}