package com.example.turtine.Calendar


import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.turtine.R
import kotlinx.android.synthetic.main.calendar_cell.*

import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter



class CalendarMainActivity : AppCompatActivity(), CalendarAdapter.OnItemListener {

    lateinit var monthYearText: TextView
    lateinit var calendarRecyclerView: RecyclerView
    lateinit var selectedDate: LocalDate
/*
    private var _binding : CalendarCellBinding ? = null
    private val binding get() = _binding!!
*/

    @RequiresApi(Build.VERSION_CODES.O)


    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initWidgets()
        selectedDate = LocalDate.now()
        setMonthView()

    }


    private fun initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView)
        monthYearText = findViewById(R.id.monthYearTV)


    }

    //뷰에 뿌려주는 함수
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setMonthView() {

        monthYearText.setText(monthYearFromDate(selectedDate))

        //월별 일수 가져오기 함수사용 일별 요일이 맞게 설정하는 함수임 그걸 배열에 넣어서 리턴함
        val daysInMonth = daysInMonthArray(selectedDate)

        //캘린더 어뎁터에 월별 맞춤 요일 배열과 이 함수를 넘겨준다 . this는 왜 있지 ? 리스너 부여?
        val calendarAdapter = CalendarAdapter(daysInMonth, this)

        //RecyclerView의 아이템의 배치와 재사용에 대한 정책을 결정하면 LayoutManager의 종류에 따라 아이템의 배치가 변경됩니다
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 7)

        calendarRecyclerView.setLayoutManager(layoutManager)

        calendarRecyclerView.setAdapter(calendarAdapter)
    }

    //리사이클러뷰에 월별로 일수 맞게 설정하면ㅅ 셀 설정하는 것
    @RequiresApi(Build.VERSION_CODES.O)
    private fun daysInMonthArray(date: LocalDate): ArrayList<String> {

        var daysInMonthArray = ArrayList<String>()

        var yearMonth = YearMonth.from(date)

        //해당 월 마지막 날짜 가져오기
        var lastDay = yearMonth.lengthOfMonth()

        //해당 월 첫번째 날 ㄱ ㅏ져오기
        var firstOfMonth = this.selectedDate.withDayOfMonth(1)

        //첫번째날 요일 가져오기
        var dayOfWeek =firstOfMonth.dayOfWeek.value

        for (i in 1..41){
            if(i<=dayOfWeek || i>(lastDay+dayOfWeek)){
                daysInMonthArray.add("")
            }else{
                daysInMonthArray.add((i - dayOfWeek).toString()).toString()
            }
        }
        return daysInMonthArray
    }

    //보여질 년도와 월
    @RequiresApi(Build.VERSION_CODES.O)
    private fun monthYearFromDate(date: LocalDate): String {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM")
       //받아온 날짜를 해당 포맷으로 변경
        return date.format(formatter)
    }

    //버튼클릭함수
    @RequiresApi(Build.VERSION_CODES.O)
    fun previousMonthAction(view: View?) {
        selectedDate = selectedDate.minusMonths(1)
        setMonthView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun nextMonthAction(view: View?) {
        selectedDate = selectedDate.plusMonths(1)
        setMonthView()
    }


    // daytext가 널이 아니면 선택 메시지를 날림
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onItemClick(position: Int, dayText: String?) { //position 은 달력 내의 인덱스를 말함

        val message = "선택된 날짜 : " + monthYearFromDate(selectedDate) +"."+ dayText
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

        val intentToRoutine = Intent(applicationContext, MainActivity::class.java)


        intentToRoutine.putExtra("day", dayText.toString()) /*송신*/
        intentToRoutine.putExtra("Myear", monthYearFromDate(selectedDate).toString())

        startActivity(intentToRoutine)


    }

}



