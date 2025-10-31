package com.pented.learningapp.homeScreen.home.otherUserProfile

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.pented.learningapp.R
import com.pented.learningapp.base.BaseActivity
import com.pented.learningapp.base.BaseViewModel
import com.pented.learningapp.databinding.ActivityLiveClassesBinding
import com.pented.learningapp.databinding.ActivityOtherUserProfileBinding
import com.pented.learningapp.helper.Constants
import com.pented.learningapp.helper.Utils
import com.pented.learningapp.homeScreen.home.otherUserProfile.viewModel.OtherUserProfileVM
import java.text.SimpleDateFormat
import java.util.*


class OtherStudentProfileActivity : BaseActivity<ActivityOtherUserProfileBinding>() {
    private val b get() = BaseActivity.binding as ActivityOtherUserProfileBinding

    override fun layoutID() = R.layout.activity_other_user_profile

    lateinit var otherUserProfileVM: OtherUserProfileVM

    override fun viewModel(): BaseViewModel = ViewModelProvider(this).get(OtherUserProfileVM::class.java)
    override fun initActivity() {
        init()
        observer()
        listner()
    }

    private fun listner() {

        b.ivBack.setOnClickListener {
           onBackPressed()
        }
    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        startActivity(MainActivity::class.java)
//        finishAffinity()
//    }

    //sample usage
    private fun observer() {
        otherUserProfileVM.observedErrorMessageChanges().observe(this, { event ->
            event?.getContentIfNotHandled()?.let {
                showErrorMessage(it, this, b.mainFrame)
            }
        })


        otherUserProfileVM.observedStudentProfileData().observe(this, { event ->
            event?.getContentIfNotHandled()?.let {
                var userImage = it.data?.S3Bucket?.FileName?.let { it1 ->
                    it?.data?.S3Bucket?.BucketFolderPath?.let { it2 ->
                        Utils.getUrlFromS3Details(
                            BucketFolderPath = it2,
                            FileName = it1
                        )
                    }
                }

                Utils.loadCircleImageUser(b.image, userImage.toString())
                b.txtName.text = it?.data?.Name
                b.txtProfileTitle.text = "${it?.data?.Name}'s profile"
               // txtNumber.text = it?.data?.MobileNumber
                b.txtRenking.text = "${(it?.data?.MonthPoints ?: "0")}"
                b.txtCourseDone.text = "${it?.data?.CourseCompleted.toString()} %"
                b.txtPoints.text = "${it?.data?.Points.toString()}"
                b.txtPointsDesc.text = "${it?.data?.Points.toString()} Points"
                b.tvTheChamp.text = "${it?.data?.LevelTitle.toString()}"
                b.txtEmail.text = "${it?.data?.Email}"
                var dividend = it.data.Points?.toInt() ?: 0
                val divisor = 1000
                val quotient = dividend / divisor
                val remainder = dividend % divisor
                var finalMax = (quotient+1) * 1000
                Log.e("FinalMax","Is === ${finalMax}")
                Log.e("quotient","Is === ${quotient}")
                Log.e("remainder","Is === ${remainder}")
                b.progressView1.max = finalMax.toFloat()
                it.data.Points?.toFloat().let {
                    if (it != null) {
                        b.progressView1.progress = it
                    }
                }

                b.txtSchoolName.text = "${it?.data?.SchoolName}"
//                spClass.text = "${dropdownList[selectedPostion].Value}"
//                txtLanguage.text = "${languagesList[selectedPostionLanguage].LanguageName}"
                b.txtAddress.text = "${it?.data?.Adress}"
            }
        })

        otherUserProfileVM.observedChanges().observe(this, { event ->
            event?.getContentIfNotHandled()?.let {
                when (it) {
                    Constants.VISIBLE -> {
                        showDialog()
                    }
                    Constants.HIDE -> {
                        hideDialog()
                    }
                    Constants.NAVIGATE -> {

                    }
                    else -> {
                        showMessage(it, this, b.mainFrame)
                    }
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()

    }



    public fun showDialog() {
        Utils.hideKeyboard(this)
        b.lilProgressBar.progressBar.visibility = View.VISIBLE
        b.lilProgressBar.animationView.visibility = View.VISIBLE
        Utils.getNonWindowTouchable(this)
    }

    public fun hideDialog() {
        b.lilProgressBar.progressBar.visibility = View.GONE
        b.lilProgressBar.animationView.visibility = View.GONE
        Utils.getWindowTouchable(this)
    }

    private fun init() {
        otherUserProfileVM = (getViewModel() as OtherUserProfileVM)
        otherUserProfileVM.callGetOtherStudentProfile(Constants.selectedUserId)
        var cal = Calendar.getInstance()
        var monthName = SimpleDateFormat("MMMM", Locale.ENGLISH).format(cal.getTime())
        b.txtRanking.text = "$monthName's Point"

//        var animation = AnimationUtils.loadAnimation(this@OtherStudentProfileActivity, R.anim.bounce);
//        txtPoints.startAnimation(animation)


        if (Constants.isApiCalling) {

        }
    }
}