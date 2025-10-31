package com.pented.learningapp.homeScreen.leaderboard.activity

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.pented.learningapp.BR
import com.pented.learningapp.R
import com.pented.learningapp.authScreens.model.GetDropdownResponseModel
import com.pented.learningapp.authScreens.model.GetSchoolNameResponseModel
import com.pented.learningapp.base.BaseActivity
import com.pented.learningapp.base.BaseViewModel
import com.pented.learningapp.base.BindingAdapter
import com.pented.learningapp.databinding.ActivityFilterBinding
import com.pented.learningapp.databinding.ActivityLiveClassesBinding
import com.pented.learningapp.helper.Constants
import com.pented.learningapp.helper.JustCopyItVIewModel
import com.pented.learningapp.helper.Utils
import com.pented.learningapp.homeScreen.leaderboard.LeaderBoardFragment
import com.pented.learningapp.homeScreen.leaderboard.LeaderBoardFragment.Companion.getLeaderboardRequestModel
import com.pented.learningapp.homeScreen.leaderboard.model.MonthListModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class FilterActivity : BaseActivity<ActivityFilterBinding>() {
    private val b get() = BaseActivity.binding as ActivityFilterBinding

    override fun layoutID() = R.layout.activity_filter
    override fun viewModel(): BaseViewModel = ViewModelProvider(this).get(JustCopyItVIewModel::class.java)
    lateinit var watchVideoVM: JustCopyItVIewModel
    var totalFilterCount = 0

    var timer = Timer()
    var DELAY: Long = 500

    var monthList = ArrayList<MonthListModel>()
    var monthNumberList = ArrayList<Int>()
    var selectedMonth:Int   = 0
    var selectedYear:Int   = 0
    override fun initActivity() {
        watchVideoVM = (getViewModel() as JustCopyItVIewModel)
        init()
        observer()
        listner()
    }

    private fun listner() {

        b.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        Log.e("Search Text", "Is == ${b.edtSearch?.text.toString()}")
                        if (s?.isNotEmpty() == true) {
                            var searchValue = b.edtSearch?.text.toString()
                            runOnUiThread(Runnable {
                                setNameAdapter(searchValue ?: "")
                            })

                            // homeVM.getSuggestionsList(b.edtSearch.text.toString())
                        }
                    }
                }, DELAY)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.isEmpty() == true) {
                    runOnUiThread(Runnable {
                        setNameAdapter("")
                    })
                }
                timer.cancel()
                timer.purge()
            }
        })



        b.edtSearchCity.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        Log.e("Search Text", "Is == ${b.edtSearchCity?.text.toString()}")
                        if (s?.isNotEmpty() == true) {
                            var searchValue = b.edtSearchCity?.text.toString()
                            runOnUiThread(Runnable {
                                setCityAdapter(searchValue ?: "")
                            })

                            // homeVM.getSuggestionsList(b.edtSearch.text.toString())
                        }
                    }
                }, DELAY)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.isEmpty() == true) {
                    runOnUiThread(Runnable {
                        setCityAdapter("")
                    })
                }
                timer.cancel()
                timer.purge()
            }
        })

        b.edtSearchStandard.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        Log.e("Search Text", "Is == ${b.edtSearchStandard?.text.toString()}")
                        if (s?.isNotEmpty() == true) {
                            var searchValue = b.edtSearchStandard?.text.toString()
                            runOnUiThread(Runnable {
                                setStandardAdapter(searchValue ?: "")
                            })

                            // homeVM.getSuggestionsList(b.edtSearch.text.toString())
                        }
                    }
                }, DELAY)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.isEmpty() == true) {
                    runOnUiThread(Runnable {
                        setStandardAdapter("")
                    })
                }
                timer.cancel()
                timer.purge()
            }
        })

        b.edtSearchSchoolName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        Log.e("Search Text", "Is == ${b.edtSearchSchoolName?.text.toString()}")
                        if (s?.isNotEmpty() == true) {
                            var searchValue = b.edtSearchSchoolName?.text.toString()
                            runOnUiThread(Runnable {
                                setSchoolNameAdapter(searchValue ?: "")
                            })

                            // homeVM.getSuggestionsList(b.edtSearch.text.toString())
                        }
                    }
                }, DELAY)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.isEmpty() == true) {
                    runOnUiThread(Runnable {
                        setSchoolNameAdapter("")
                    })
                }
                timer.cancel()
                timer.purge()
            }
        })

        b.edtSearchMonths.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        Log.e("Search Text", "Is == ${b.edtSearchMonths?.text.toString()}")
                        if (s?.isNotEmpty() == true) {
                            var searchValue = b.edtSearchMonths?.text.toString()
                            runOnUiThread(Runnable {
                                setMonthAdapter(searchValue ?: "")
                            })

                            // homeVM.getSuggestionsList(b.edtSearch.text.toString())
                        }
                    }
                }, DELAY)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.isEmpty() == true) {
                    runOnUiThread(Runnable {
                        setMonthAdapter("")
                    })
                }
                timer.cancel()
                timer.purge()
            }
        })




        b.icCrossMain.setOnClickListener {
            onBackPressed()
        }
        b.btnGetCall.setOnClickListener {
            for (standard in LeaderBoardFragment.getStandardList)
            {
                if(standard.Id == Constants.headerstandardid)
                {
                    standard.isSelected = true
                }
                else{
                    standard.isSelected = false
                }

            }

            for (school in LeaderBoardFragment.getSchoolNameLis)
            {
                school.isSelected = false
            }

            for (city in LeaderBoardFragment.getCityList)
            {
                city.isSelected = false
            }

            for (student in LeaderBoardFragment.getStudentList)
            {
                student.isSelected = false
            }
            for (month in monthList)
            {
                month.isSelected = false
            }
            selectedMonth = 0
            selectedYear = 0

            overridePendingTransition(0, 0);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
            overridePendingTransition(0, 0);
        }
        b.btnPurchaseNow.setOnClickListener {
            var studentIds = ArrayList<Int>()
            var cityList = ArrayList<String>()
            var schoolNameList = ArrayList<String>()
            var starndardIdList = ArrayList<Int>()
            var monthId = 0
            for(student in LeaderBoardFragment.getStudentList)
            {
                if(student.isSelected)
                {
                    student.Id?.toInt()?.let { it1 -> studentIds.add(it1) }
                }
            }
            for(city in LeaderBoardFragment.getCityList)
            {
                if(city.isSelected)
                {
                    city.Value?.let { it1 -> cityList.add(it1) }
                }
            }
            for(schoolName in LeaderBoardFragment.getSchoolNameLis)
            {
                if(schoolName.isSelected)
                {
                    schoolName.Value?.let { it1 -> schoolNameList.add(it1) }
                }
            }
            for(standard in LeaderBoardFragment.getStandardList)
            {
                if(standard.isSelected == true)
                {
                    standard.Id?.toInt()?.let { it1 -> starndardIdList.add(it1) }
                }
            }
//            for(month in monthList)
//            {
//                if(month.isSelected)
//                {
//                    monthId = 0
//                }
//            }


//            getLeaderboardRequestModel.StudentIds = studentIds
//            getLeaderboardRequestModel.Cities = cityList
            getLeaderboardRequestModel.SchoolName = b.edtSearchSchoolName.text.toString()
            getLeaderboardRequestModel.Address = b.edtSearchCity.text.toString()
            getLeaderboardRequestModel.StudentName = b.edtSearch.text.toString()
            getLeaderboardRequestModel.StandardIds = starndardIdList
            getLeaderboardRequestModel.Month = selectedMonth
            getLeaderboardRequestModel.Year = selectedYear

            Log.e("SchoolName", "${Gson().toJson(getLeaderboardRequestModel.SchoolName)}")
            Log.e("Address", "${Gson().toJson(getLeaderboardRequestModel.Address)}")
            Log.e("StudentName", "${Gson().toJson(getLeaderboardRequestModel.StudentName)}")
            Log.e("StandardIds", "${Gson().toJson(getLeaderboardRequestModel.StandardIds)}")
            Log.e("Month", "${Gson().toJson(getLeaderboardRequestModel.Month)}")
            Log.e("Year", "${Gson().toJson(getLeaderboardRequestModel.Year)}")

            if(b.checkboxNearMe.isChecked)
            {
                getLeaderboardRequestModel.NearMe = true
            }
            else{
                getLeaderboardRequestModel.NearMe = false
            }
            finish()
            sendBroadcast(Intent(Constants.FILTERLEADERBOARD))
        }
        b.checkboxNearMe.setOnClickListener {
            if(b.checkboxNearMe.isChecked)
            {
                Log.e("is", "Checked")
            }
            else
            {
                Log.e("is", "Un Checked")
            }
            setTotalFilterCount()
        }

        b.headerName.setOnClickListener {
            if( b.bottomProfile.visibility == View.VISIBLE)
            {
                 b.imgNameArrow.animate().rotation(-90f).start()
                Utils.expandOrCollapseViewGloble( b.bottomProfile, false)
            }
            else if( b.bottomProfile.visibility == View.GONE)
            {
                 b.imgNameArrow.animate().rotation(0f).start()
                Utils.expandOrCollapseViewGloble( b.bottomProfile, true)
                b.recyclerViewName.adapter?.notifyDataSetChanged()
            }
        }
        b.headerCity.setOnClickListener {
            if( b.bottomCity.visibility == View.VISIBLE)
            {
                 b.imgCityArrow.animate().rotation(-90f).start()
                Utils.expandOrCollapseViewGloble( b.bottomCity, false)
            }
            else if( b.bottomCity.visibility == View.GONE)
            {
                 b.imgCityArrow.animate().rotation(0f).start()
                Utils.expandOrCollapseViewGloble( b.bottomCity, true)
                b.recyclerViewCity.adapter?.notifyDataSetChanged()
            }
        }
         b.headerStandard.setOnClickListener {
            if( b.bottomStandard.visibility == View.VISIBLE)
            {
                 b.imgStandardArrow.animate().rotation(-90f).start()
                Utils.expandOrCollapseViewGloble( b.bottomStandard, false)
            }
            else if( b.bottomStandard.visibility == View.GONE)
            {
                 b.imgStandardArrow.animate().rotation(0f).start()
                Utils.expandOrCollapseViewGloble( b.bottomStandard, true)
                b.recyclerViewStandard.adapter?.notifyDataSetChanged()
            }
        }
         b.headerSchoolName.setOnClickListener {
            if( b.bottomSchoolName.visibility == View.VISIBLE)
            {
                 b.imgSchoolArrow.animate().rotation(-90f).start()
                Utils.expandOrCollapseViewGloble( b.bottomSchoolName, false)
            }
            else if( b.bottomSchoolName.visibility == View.GONE)
            {
                 b.imgSchoolArrow.animate().rotation(0f).start()
                Utils.expandOrCollapseViewGloble( b.bottomSchoolName, true)
                 b.recyclerViewSchoolName.adapter?.notifyDataSetChanged()
            }
        }
         b.headerMonth.setOnClickListener {
            if( b.bottomMonths.visibility == View.VISIBLE)
            {
                 b.imgMonthArrow.animate().rotation(-90f).start()
                Utils.expandOrCollapseViewGloble( b.bottomMonths, false)
            }
            else if( b.bottomMonths.visibility == View.GONE)
            {
                 b.imgMonthArrow.animate().rotation(0f).start()
                Utils.expandOrCollapseViewGloble( b.bottomMonths, true)
                b.recyclerViewMonths.adapter?.notifyDataSetChanged()
            }
        }
    }

    private fun observer() {

    }

    private fun init() {
        var selectedMonth:Int   = 0
        var selectedYear:Int   = 0
        getLast11Months()
        setNameAdapter()
        setCityAdapter()
        setStandardAdapter()
        setSchoolNameAdapter()
    }

    private fun getLast11Months() {
        monthList.clear()
        monthNumberList.clear()

     //   val c: Calendar = Calendar.getInstance()
       // c.setTime(Date())
       // val sdf = SimpleDateFormat("MMM YYYY", Locale.ENGLISH)
       // System.out.println(sdf.format(c.getTime())) // NOW
       // var model1 = MonthListModel(sdf.format(c.getTime()), false)

       // monthList.add(model1)
       // monthNumberList.add(c.get(Calendar.MONTH) + 1)

        for(i in 1..11)
        {
//            val c1: Calendar = Calendar.getInstance()
//            c1.setTime(Date())
          //  c.add(Calendar.MONTH, -1)
           // monthNumberList.add(c.get(Calendar.MONTH) + 1)
//            val now: LocalDate = LocalDate.now() // 2015-11-24
//
//            val earlier: LocalDate = now.minusMonths(1) // 2015-10-24


          //  Log.e("Month List", sdf.format(c.getTime()) + "i== $i")
         //   monthList.add(MonthListModel(sdf.format(c.getTime()), false))
        }
        val allDates: MutableList<String> = ArrayList()
        val maxDate = "Jan-2016"
        val monthDate = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)
        val cal = Calendar.getInstance()
        cal.time = Date()
        for (i in 1..12) {
            val month_name1 = monthDate.format(cal.time)
            monthNumberList.add(cal.get(Calendar.MONTH) + 1)
            monthList.add(MonthListModel(month_name1, false))
          //  allDates.add(month_name1)
            cal.add(Calendar.MONTH, -1)
           // Log.e("Month New", allDates[i-1] + "i== $i")
        }
        Log.e("monthNumberList", "=== ${Gson().toJson(monthNumberList)}")
        Log.e("monthList",  "=== ${Gson().toJson(monthList)}")
        println(allDates)
        setMonthAdapter()
    }

    private fun setMonthAdapter(searchValue: String = "") {
        var filterData = ArrayList<MonthListModel>()
        //Log.e("filterData", "= ${Gson().toJson(filterData)}")
        //Log.e("monthList", "= ${Gson().toJson(monthList)}")
        if(!searchValue.isNullOrBlank())
        {
            for(month in monthList)
            {
                if(month.monthName?.contains(searchValue, true) == true)
                {
                    filterData.add(month)
                }
            }
        }
        if(filterData.size > 0)
        {
            b.recyclerViewMonths.adapter = BindingAdapter(
                layoutId = R.layout.row_filter_months,
                br = BR.model,
                list = ArrayList(filterData),
                clickListener = { view, position ->
                    selectedYear = filterData[position].monthName.split(" ")[1].toInt()
                    selectedMonth = monthNumberList[position]
                    when (view.id) {
                        R.id.checkbox -> {
                            if (filterData[position].isSelected) {
                                filterData[position].isSelected = false
                            } else {
                                for (month in filterData) {
                                    month.isSelected = false
                                }
                                filterData[position].isSelected = true
                            }

                            b.recyclerViewMonths.adapter?.notifyDataSetChanged()
                        }
                        R.id.layoutStudentName -> {
                            if (filterData[position].isSelected) {
                                filterData[position].isSelected = false
                            } else {
                                for (month in filterData) {
                                    month.isSelected = false
                                }
                                filterData[position].isSelected = true
                            }
                            b.recyclerViewMonths.adapter?.notifyDataSetChanged()
                        }
                    }
                    var count = filterData.filter { it.isSelected == true }.count()
                    setTotalFilterCount()
                })
        }
        else
        {
            b.recyclerViewMonths.adapter = BindingAdapter(
                layoutId = R.layout.row_filter_months,
                br = BR.model,
                list = ArrayList(monthList),
                clickListener = { view, position ->
                    selectedYear = monthList[position].monthName.split(" ")[1].toInt()
                    selectedMonth = monthNumberList[position]
                    when (view.id) {
                        R.id.checkbox -> {
                            if (monthList[position].isSelected) {
                                monthList[position].isSelected = false
                            } else {
                                for (month in monthList) {
                                    month.isSelected = false
                                }
                                monthList[position].isSelected = true
                            }

                            b.recyclerViewMonths.adapter?.notifyDataSetChanged()
                        }
                        R.id.layoutStudentName -> {
                            if (monthList[position].isSelected) {
                                monthList[position].isSelected = false
                            } else {
                                for (month in monthList) {
                                    month.isSelected = false
                                }
                                monthList[position].isSelected = true
                            }
                            b.recyclerViewMonths.adapter?.notifyDataSetChanged()
                        }
                    }
                    var count = monthList.filter { it.isSelected == true }.count()
                    setTotalFilterCount()
                })
        }

    }

    private fun setTotalFilterCount() {
        var count = 0
        if(LeaderBoardFragment.getStudentList.filter { it.isSelected == true }.count() > 0)
        {
            count++
        }
        if(LeaderBoardFragment.getCityList.filter { it.isSelected == true }.count() > 0)
        {
            count++
        }
        if(LeaderBoardFragment.getStandardList.filter { it.isSelected == true }.count() > 0)
        {
            count++
        }
        if(LeaderBoardFragment.getSchoolNameLis.filter { it.isSelected == true }.count() > 0)
        {
            count++
        }
        if(monthList.filter { it.isSelected == true }.count() > 0)
        {
            count++
        }
        if(b.checkboxNearMe.isChecked)
        {
            count++
        }

        if(count > 0)
        {
            b.txtMainFilter.visibility = View.VISIBLE
            b.txtMainFilterCount.text = "${count} Applied"
        }
        else
        {
            b.txtMainFilter.visibility = View.GONE
        }

    }

    private fun setNameAdapter(searchValue: String = "") {
        var filterData = ArrayList<GetSchoolNameResponseModel.Data>()
        Log.e("filterData", "= ${Gson().toJson(filterData)}")
        Log.e("monthList", "= ${Gson().toJson(monthList)}")
        if(!searchValue.isNullOrBlank())
        {
            for(student in LeaderBoardFragment.getStudentList)
            {
                if(student.Value?.contains(searchValue, true) == true)
                {
                    filterData.add(student)
                }
            }
        }
        if(filterData.size > 0)
        {
            b.recyclerViewName.adapter = BindingAdapter(
                layoutId = R.layout.row_filter_student_name,
                br = BR.model,
                list = ArrayList(filterData),
                clickListener = { view, position ->
                    when (view.id) {
                        R.id.checkbox -> {
                            if (filterData[position].isSelected) {
                                filterData[position].isSelected = false
                                b.recyclerViewName.adapter?.notifyDataSetChanged()
                            } else {
                                filterData[position].isSelected = true
                                b.recyclerViewName.adapter?.notifyDataSetChanged()
                            }
                        }
                        R.id.layoutStudentName -> {
                            if (filterData[position].isSelected) {
                                filterData[position].isSelected = false
                                b.recyclerViewName.adapter?.notifyDataSetChanged()
                            } else {
                                filterData[position].isSelected = true
                                b.recyclerViewName.adapter?.notifyDataSetChanged()
                            }
                        }
                    }

                    var count =
                        filterData.filter { it.isSelected == true }.count()
                    if (count > 0) {
                        b.txtName.text = "Name ( $count )"
                    } else {
                        b.txtName.text = "Name"
                    }
                    setTotalFilterCount()

                })
        }
        else
        {
            b.recyclerViewName.adapter = BindingAdapter(
                layoutId = R.layout.row_filter_student_name,
                br = BR.model,
                list = ArrayList(LeaderBoardFragment.getStudentList),
                clickListener = { view, position ->
                    when (view.id) {
                        R.id.checkbox -> {
                            if (LeaderBoardFragment.getStudentList[position].isSelected) {
                                LeaderBoardFragment.getStudentList[position].isSelected = false
                                b.recyclerViewName.adapter?.notifyDataSetChanged()
                            } else {
                                LeaderBoardFragment.getStudentList[position].isSelected = true
                                b.recyclerViewName.adapter?.notifyDataSetChanged()
                            }
                        }
                        R.id.layoutStudentName -> {
                            if (LeaderBoardFragment.getStudentList[position].isSelected) {
                                LeaderBoardFragment.getStudentList[position].isSelected = false
                                b.recyclerViewName.adapter?.notifyDataSetChanged()
                            } else {
                                LeaderBoardFragment.getStudentList[position].isSelected = true
                                b.recyclerViewName.adapter?.notifyDataSetChanged()
                            }
                        }
                    }

                    var count =
                        LeaderBoardFragment.getStudentList.filter { it.isSelected == true }.count()
                    if (count > 0) {
                        b.txtName.text = "Name ( $count )"
                    } else {
                        b.txtName.text = "Name"
                    }
                    setTotalFilterCount()

                })
        }


    }

    private fun setStandardAdapter(searchValue: String = "") {
        var filterData = ArrayList<GetDropdownResponseModel.Data>()

        if(!searchValue.isNullOrBlank())
        {
            for(student in LeaderBoardFragment.getStandardList)
            {
                if(student.Value?.contains(searchValue, true) == true)
                {
                    filterData.add(student)
                }
            }
        }
        if(filterData.size > 0)
        {
            b.recyclerViewStandard.adapter = BindingAdapter(
                layoutId = R.layout.row_filter_standard_list,
                br = BR.model,
                list = ArrayList(filterData),
                clickListener = { view, position ->
                    when (view.id) {
                        R.id.checkbox -> {
                            if (filterData[position].isSelected == true) {
                                filterData[position].isSelected = false
                                b.recyclerViewStandard.adapter?.notifyDataSetChanged()
                            } else {
                                filterData[position].isSelected = true
                                b.recyclerViewStandard.adapter?.notifyDataSetChanged()
                            }
                        }
                        R.id.layoutStudentName -> {
                            if (filterData[position].isSelected == true) {
                                filterData[position].isSelected = false
                                b.recyclerViewStandard.adapter?.notifyDataSetChanged()
                            } else {
                                filterData[position].isSelected = true
                                b.recyclerViewStandard.adapter?.notifyDataSetChanged()
                            }
                        }
                    }
                    var count =
                        filterData.filter { it.isSelected == true }.count()
                    if (count > 0) {
                        b.txtStandard.text = "Standard ( $count )"
                    } else {
                        b.txtStandard.text = "Standard"
                    }
                    setTotalFilterCount()
                })
        }else{
            b.recyclerViewStandard.adapter = BindingAdapter(
                layoutId = R.layout.row_filter_standard_list,
                br = BR.model,
                list = ArrayList(LeaderBoardFragment.getStandardList),
                clickListener = { view, position ->
                    when (view.id) {
                        R.id.checkbox -> {
                            if (LeaderBoardFragment.getStandardList[position].isSelected == true) {
                                LeaderBoardFragment.getStandardList[position].isSelected = false
                                b.recyclerViewStandard.adapter?.notifyDataSetChanged()
                            } else {
                                LeaderBoardFragment.getStandardList[position].isSelected = true
                                b.recyclerViewStandard.adapter?.notifyDataSetChanged()
                            }
                        }
                        R.id.layoutStudentName -> {
                            if (LeaderBoardFragment.getStandardList[position].isSelected == true) {
                                LeaderBoardFragment.getStandardList[position].isSelected = false
                                b.recyclerViewStandard.adapter?.notifyDataSetChanged()
                            } else {
                                LeaderBoardFragment.getStandardList[position].isSelected = true
                                b.recyclerViewStandard.adapter?.notifyDataSetChanged()
                            }
                        }
                    }
                    var count =
                        LeaderBoardFragment.getStandardList.filter { it.isSelected == true }.count()
                    if (count > 0) {
                        b.txtStandard.text = "Standard ( $count )"
                    } else {
                        b.txtStandard.text = "Standard"
                    }
                    setTotalFilterCount()
                })
        }



    }

    private fun setCityAdapter(searchValue: String = "") {

        var filterData = ArrayList<GetSchoolNameResponseModel.Data>()

        if(!searchValue.isNullOrBlank())
        {
            for(student in LeaderBoardFragment.getCityList)
            {
                if(student.Value?.contains(searchValue, true) == true)
                {
                    filterData.add(student)
                }
            }
        }
        if(filterData.size > 0)
        {
            b.recyclerViewCity.adapter = BindingAdapter(
                layoutId = R.layout.row_filter_student_name,
                br = BR.model,
                list = ArrayList(filterData),
                clickListener = { view, position ->
                    when (view.id) {
                        R.id.checkbox -> {
                            if (filterData[position].isSelected) {
                                filterData[position].isSelected = false
                                b.recyclerViewCity.adapter?.notifyDataSetChanged()
                            } else {
                                filterData[position].isSelected = true
                                b.recyclerViewCity.adapter?.notifyDataSetChanged()
                            }
                        }
                        R.id.layoutStudentName -> {
                            if (filterData[position].isSelected) {
                                filterData[position].isSelected = false
                                b.recyclerViewCity.adapter?.notifyDataSetChanged()
                            } else {
                                filterData[position].isSelected = true
                                b.recyclerViewCity.adapter?.notifyDataSetChanged()
                            }
                        }
                    }
                    var count =
                        filterData.filter { it.isSelected == true }.count()
                    if (count > 0) {
                         b.txtCity.text = "City ( $count )"
                    } else {
                         b.txtCity.text = "City"
                    }
                    setTotalFilterCount()
                })
        }
        else
        {
            b.recyclerViewCity.adapter = BindingAdapter(
                layoutId = R.layout.row_filter_student_name,
                br = BR.model,
                list = ArrayList(LeaderBoardFragment.getCityList),
                clickListener = { view, position ->
                    when (view.id) {
                        R.id.checkbox -> {
                            if (LeaderBoardFragment.getCityList[position].isSelected) {
                                LeaderBoardFragment.getCityList[position].isSelected = false
                                b.recyclerViewCity.adapter?.notifyDataSetChanged()
                            } else {
                                LeaderBoardFragment.getCityList[position].isSelected = true
                                b.recyclerViewCity.adapter?.notifyDataSetChanged()
                            }
                        }
                        R.id.layoutStudentName -> {
                            if (LeaderBoardFragment.getCityList[position].isSelected) {
                                LeaderBoardFragment.getCityList[position].isSelected = false
                                b.recyclerViewCity.adapter?.notifyDataSetChanged()
                            } else {
                                LeaderBoardFragment.getCityList[position].isSelected = true
                                b.recyclerViewCity.adapter?.notifyDataSetChanged()
                            }
                        }
                    }
                    var count =
                        LeaderBoardFragment.getCityList.filter { it.isSelected == true }.count()
                    if (count > 0) {
                         b.txtCity.text = "City ( $count )"
                    } else {
                         b.txtCity.text = "City"
                    }
                    setTotalFilterCount()
                })
        }



    }
    private fun setSchoolNameAdapter(searchValue: String = "") {
        var filterData = ArrayList<GetSchoolNameResponseModel.Data>()

        if(!searchValue.isNullOrBlank())
        {
            for(student in LeaderBoardFragment.getSchoolNameLis)
            {
                if(student.Value?.contains(searchValue, true) == true)
                {
                    filterData.add(student)
                }
            }
        }
        if(filterData.size > 0)
        {
            b.recyclerViewSchoolName.adapter = BindingAdapter(
                layoutId = R.layout.row_filter_student_name,
                br = BR.model,
                list = ArrayList(filterData),
                clickListener = { view, position ->
                    when (view.id) {
                        R.id.checkbox -> {
                            if (filterData[position].isSelected) {
                                filterData[position].isSelected = false
                                 b.recyclerViewSchoolName.adapter?.notifyDataSetChanged()
                            } else {
                                filterData[position].isSelected = true
                                 b.recyclerViewSchoolName.adapter?.notifyDataSetChanged()
                            }
                        }
                        R.id.layoutStudentName -> {
                            if (filterData[position].isSelected) {
                                filterData[position].isSelected = false
                                 b.recyclerViewSchoolName.adapter?.notifyDataSetChanged()
                            } else {
                                filterData[position].isSelected = true
                                 b.recyclerViewSchoolName.adapter?.notifyDataSetChanged()
                            }
                        }
                    }
                    var count =
                        filterData.filter { it.isSelected == true }.count()
                    if (count > 0) {
                         b.txtSchoolName.text = "School Name ( $count )"
                    } else {
                         b.txtCity.text = "School Name"
                    }
                    setTotalFilterCount()
                })
        }
        else
        {
             b.recyclerViewSchoolName.adapter = BindingAdapter(
                layoutId = R.layout.row_filter_student_name,
                br = BR.model,
                list = ArrayList(LeaderBoardFragment.getSchoolNameLis),
                clickListener = { view, position ->
                    when (view.id) {
                        R.id.checkbox -> {
                            if (LeaderBoardFragment.getSchoolNameLis[position].isSelected) {
                                LeaderBoardFragment.getSchoolNameLis[position].isSelected = false
                                 b.recyclerViewSchoolName.adapter?.notifyDataSetChanged()
                            } else {
                                LeaderBoardFragment.getSchoolNameLis[position].isSelected = true
                                 b.recyclerViewSchoolName.adapter?.notifyDataSetChanged()
                            }
                        }
                        R.id.layoutStudentName -> {
                            if (LeaderBoardFragment.getSchoolNameLis[position].isSelected) {
                                LeaderBoardFragment.getSchoolNameLis[position].isSelected = false
                                 b.recyclerViewSchoolName.adapter?.notifyDataSetChanged()
                            } else {
                                LeaderBoardFragment.getSchoolNameLis[position].isSelected = true
                                 b.recyclerViewSchoolName.adapter?.notifyDataSetChanged()
                            }
                        }
                    }
                    var count =
                        LeaderBoardFragment.getSchoolNameLis.filter { it.isSelected == true }
                            .count()
                    if (count > 0) {
                         b.txtSchoolName.text = "School Name ( $count )"
                    } else {
                         b.txtCity.text = "School Name"
                    }
                    setTotalFilterCount()
                })
        }



    }
}