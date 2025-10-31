package com.pented.learningapp.authScreens.fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.pented.learningapp.MainActivity
import com.pented.learningapp.R
import com.pented.learningapp.base.BaseFragment
import com.pented.learningapp.base.BaseViewModel
import com.pented.learningapp.databinding.FragmentRegisterYourselfOneBinding
import com.pented.learningapp.databinding.FragmentRegisterYourselfTwoBinding
import com.pented.learningapp.helper.JustCopyItVIewModel

class FragmentRegisterYourselfTwo : BaseFragment<FragmentRegisterYourselfTwoBinding>(){


    override fun layoutID() = R.layout.fragment_register_yourself_two
    override fun viewModel(): BaseViewModel = ViewModelProvider(this).get(JustCopyItVIewModel::class.java)
    lateinit var justCopyItVIewModel: JustCopyItVIewModel

    private lateinit var binding: FragmentRegisterYourselfTwoBinding
    companion object {

        fun newInstance(): FragmentRegisterYourselfTwo {
            return FragmentRegisterYourselfTwo()
        }
    }

    override fun initFragment() {
        //getting recyclerview from xml
        justCopyItVIewModel = (getViewModel() as JustCopyItVIewModel)
        binding.btnContinue.setOnClickListener {
            startActivity(Intent(requireActivity(), MainActivity::class.java))
        }
    }


}