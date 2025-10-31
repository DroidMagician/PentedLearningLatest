package com.pented.learningapp.authScreens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.pented.learningapp.R
import com.pented.learningapp.base.BaseActivity
import com.pented.learningapp.databinding.ActivityRegisterYourselfBinding
import com.pented.learningapp.databinding.ActivityYourSecurityQuestionBinding
import com.pented.learningapp.helper.JustCopyItVIewModel

class YourSecurityQuestionActivity : BaseActivity<ActivityYourSecurityQuestionBinding>() {

    lateinit var ivArrayDotsPager: Array<ImageView>
    override fun viewModel() = ViewModelProvider(this).get(JustCopyItVIewModel::class.java)
    override fun layoutID() = R.layout.activity_register_yourself_second
    lateinit var justCopyItVIewModel: JustCopyItVIewModel
    private val b get() = BaseActivity.binding as ActivityYourSecurityQuestionBinding

    override fun initActivity() {
        justCopyItVIewModel = (getViewModel() as JustCopyItVIewModel)

        listener()
    }

    private fun listener() {
        b.llBack.setOnClickListener {
            onBackPressed()
        }
        b.btnContinue.setOnClickListener {  }
    }


}