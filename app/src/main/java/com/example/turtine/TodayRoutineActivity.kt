package com.example.turtine

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.coroutines.selects.select

class TodayRoutineActivity : AppCompatActivity() {

    // (기상 직후) 리스트뷰 샘플 데이터
//    var RoutineList1 = arrayListOf<RoutineItem>(
//        RoutineItem(R.drawable.ic_baseline_timer_24, "물 한 잔 마시기1") ,
//        RoutineItem(R.drawable.ic_baseline_timer_24, "책 읽기1") ,
//        RoutineItem(R.drawable.ic_baseline_timer_24, "영양제 챙겨먹기1")
//    )


    var RoutineList1 = ArrayList<RoutineItem>()
    lateinit var db : MyDBHelper
    lateinit var sqlDB : SQLiteDatabase



    // (오늘 하루) 리스트뷰 샘플 데이터
    var RoutineList2 = arrayListOf<RoutineItem>(
        RoutineItem(R.drawable.ic_baseline_timer_24, "물 한 잔 마시기2", 5) ,
        RoutineItem(R.drawable.ic_baseline_timer_24, "책 읽기2",5) ,
        RoutineItem(R.drawable.ic_baseline_timer_24, "영양제 챙겨먹기2",5)
    )

    // (자기 전) 리스트뷰 샘플 데이터
    var RoutineList3 = arrayListOf<RoutineItem>(
        RoutineItem(R.drawable.ic_baseline_timer_24, "물 한 잔 마시기3",5) ,
        RoutineItem(R.drawable.ic_baseline_timer_24, "책 읽기3",5) ,
        RoutineItem(R.drawable.ic_baseline_timer_24, "영양제 챙겨먹기3",5)
    )

    lateinit var morning_add_btn : Button




    override fun onCreate(savedInstanceState: Bundle?) { // 액티비티의 실행 시작 지점
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today_routine)

       // val item = arrayOf("사과", "배", "딸기", "키위")
        //listView.adapter =ArrayAdapter(this, android.R.layout.simple_list_item_1, item)


        db = MyDBHelper(this) // db = new DBHandler(this)
        sqlDB = db.readableDatabase


        // 리스트뷰
        var listView1 = findViewById<ListView>(R.id.today_routine_morning_lv)
        var listView2 = findViewById<ListView>(R.id.today_routine_day_lv)
        var listView3 = findViewById<ListView>(R.id.today_routine_night_lv)
        // add 버튼
        morning_add_btn = findViewById<Button>(R.id.today_routine_morning_add_btn)
        var day_add_btn = findViewById<Button>(R.id.today_routine_day_add_btn)
        var night_add_btn = findViewById<Button>(R.id.today_routine_night_add_btn)
        // 하루 마무리 버튼
        var complete_btn = findViewById<Button>(R.id.today_routine_complete_btn)


        RoutineList1 = db.getAllData(sqlDB)

//         ** 리스트 뷰 어댑터와 연결
//         어댑터 정의
        val Adapter1 = RoutineItemAdapter(this, RoutineList1)
        val Adapter2 = RoutineItemAdapter(this, RoutineList2)
        val Adapter3 = RoutineItemAdapter(this, RoutineList3)
//        // 어댑터 연결
        listView1.adapter = Adapter1
        listView2.adapter = Adapter2
        listView3.adapter = Adapter3

        // DatabaseHandler는 MyDBHelper
        // TodoAdpater는 RoutineItemAdapter

//        RoutineList1 = db.getAllData()
//        val Adapter1 = RoutineItemAdapter(this) // taskAdapter = new TodoAdapter(this)
//        listView1.adapter = Adapter1 // tasksRecyclerView.setAdapter(taskAdapter)
//        RoutineList1 = db.getAllData()
//        Adapter1.setItem(RoutineList1)





//        var ri : RoutineItem = RoutineItem(R.drawable.ic_baseline_timer_24, "제발 되면 좋겠어 plz")
//        RoutineList1.add(ri)


        // ** add 버튼 클릭 이벤트
        morning_add_btn.setOnClickListener{
            // 루틴 등록 페이지로 이동
            var intent = Intent(this, SampleActivity::class.java)
            startActivity(intent)
        }








        // ( 리스트 아이템 하나 클릭 시 )
        // ** (기상 직후) 체크 버튼 클릭 이벤트
        listView1.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            // 여기서 parent는 AdapterView
            // 1. 클릭한 아이템 가져오기
            var selectItem = parent.getItemAtPosition(position) as RoutineItem

            // TimerActivity.kt 파일에서 작성되어야 할 코드인 듯
            // 3. 타이머가 끝나면 클릭한 아이템 안에 있는 타이머 이미지가 체크 이미지로 바뀜
            var img = findViewById<ImageView>(R.id.list_routine_item_timer_iv)
            selectItem.checkImg = R.drawable.ic_baseline_check_circle_outline_24

            // 2. 타이머 페이지로 이동하기
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // ** (오늘 하루) 체크 버튼 클릭 이벤트
        listView2.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            // 여기서 parent는 AdapterView
            // 1. 클릭한 아이템 가져오기
            val selectItem = parent.getItemAtPosition(position) as RoutineItem
            // 2. 타이머 페이지로 이동하기
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // ** (자기 전) 체크 버튼 클릭 이벤트
        listView3.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            // 여기서 parent는 AdapterView
            // 1. 클릭한 아이템 가져오기
            val selectItem = parent.getItemAtPosition(position) as RoutineItem
            // 2. 타이머 페이지로 이동하기
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // ** 하루 마무리 버튼 클릭 이벤트
        complete_btn.setOnClickListener{
            // var intent = Intent(this, 액티비티 클래스)
            // 1. 하루 마무리 페이지로 데이터 보내기

            // 2. 하루 마무리 페이지로 이동
            // startActivity(intent)

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        // ** 루틴 데이터 가져와서 리스트뷰에 추가하는 함수
        fun addItem( routineItemList: ArrayList<RoutineItem>, routineItem:RoutineItem)
        {
            routineItemList.add(routineItem)
        }

    }






}