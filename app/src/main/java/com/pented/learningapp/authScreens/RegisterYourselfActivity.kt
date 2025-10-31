package com.pented.learningapp.authScreens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.pented.learningapp.R
import com.pented.learningapp.adapter.PageAdapter
import com.pented.learningapp.authScreens.model.GetDropdownResponseModel
import com.pented.learningapp.authScreens.model.GetLanguagesResponseModel
import com.pented.learningapp.authScreens.model.GetSchoolNameResponseModel
import com.pented.learningapp.authScreens.viewModel.RegisterVM
import com.pented.learningapp.base.BaseActivity
import com.pented.learningapp.databinding.ActivityGetStartedBinding
import com.pented.learningapp.databinding.ActivityOtpactivityBinding
import com.pented.learningapp.databinding.ActivityRegisterYourselfBinding
import com.pented.learningapp.helper.Constants
import com.pented.learningapp.helper.JustCopyItVIewModel
import com.pented.learningapp.helper.Utils

class RegisterYourselfActivity : BaseActivity<ActivityRegisterYourselfBinding>() {

    lateinit var ivArrayDotsPager: Array<ImageView>
    override fun viewModel() = ViewModelProvider(this).get(RegisterVM::class.java)
    override fun layoutID() = R.layout.activity_register_yourself
    lateinit var justCopyItVIewModel: RegisterVM
    private val b get() = BaseActivity.binding as ActivityRegisterYourselfBinding

    override fun initActivity() {

        init()
        observer()
        listener()

    }

    private fun observer() {
        justCopyItVIewModel.observedErrorMessageChanges().observe(this, { event ->
            event?.getContentIfNotHandled()?.let {
                showErrorMessage(it, this, b.mainFrame)
            }
        })
        justCopyItVIewModel.observerDropdownChange().observe(this, { event ->
            event?.getContentIfNotHandled()?.let {
//                dropdownList.clear()
//                dropdownList.addAll(it.data)
//                hideDialog()
//                if(!dropdownList.isNullOrEmpty())
//                {
//                    Log.e("Data is","Here ${it.data[0].Value}")
//                }

            }
        })

        justCopyItVIewModel.observerLanguageChange().observe(this, { event ->
            event?.getContentIfNotHandled()?.let {
//                languagesList.clear()
//                languagesList.addAll(it.data)
//                hideDialog()
//                if(!languagesList.isNullOrEmpty())
//                {
//                    Log.e("Data is","Here ${it.data[0].LanguageName}")
//                }

            }
        })

        justCopyItVIewModel.observerSchoolNameChange().observe(this, { event ->
            event?.getContentIfNotHandled()?.let {
//                schoolNameList.clear()
//                schoolNameList.addAll(it.data)
//                hideDialog()
//                if(!schoolNameList.isNullOrEmpty())
//                {
//                    Log.e("Data is","Here ${schoolNameList[0].Value}")
//                }

            }
        })

        justCopyItVIewModel.observedChanges().observe(this, { event ->
            event?.getContentIfNotHandled()?.let {
                when (it) {
                    Constants.VISIBLE -> {
                        showDialog()
                    }
                    Constants.HIDE -> {
                       hideDialog()
                    }
                    Constants.NAVIGATE -> {
                        hideDialog()
                       // sendBroadcast(Intent("StandardFound"))
                    }
                    else -> {
                        hideDialog()
                        showMessage(it, this, b.mainFrame)
                    }
                }
            }
        })
    }

    private fun init() {
        justCopyItVIewModel = (getViewModel() as RegisterVM)
     //   justCopyItVIewModel.callGetDropdownList()

        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = PageAdapter(supportFragmentManager)
        b.dotsIndicator.setViewPager(viewPager)

        viewPager.setOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
    public fun showDialog() {
        Utils.hideKeyboard(this)
        b.progressRegister.progressBar.visibility = View.VISIBLE
        Utils.getNonWindowTouchable(this)
    }

    public fun hideDialog() {
        b.progressRegister.progressBar.visibility = View.GONE
        Utils.getWindowTouchable(this)
    }
    private fun listener() {
        b.llBack.setOnClickListener { onBackPressed() }
    }


}