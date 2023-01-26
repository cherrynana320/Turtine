package com.example.turtine

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class RoutineItemAdapter(val context: Context, val RoutineItemList: ArrayList<RoutineItem>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_routine_item, null) // 뷰 붙이기

        val timer_iv = view.findViewById<ImageView>(R.id.list_routine_item_timer_iv)
        val routine_tv = view.findViewById<TextView>(R.id.list_routine_item_tv)

        val routineItem = RoutineItemList[position]

        timer_iv.setImageResource(routineItem.checkImg)
        routine_tv.text = routineItem.routine

        return view
    }

    override fun getItem(position: Int): Any {
        return RoutineItemList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return RoutineItemList.size
    }

}

//class RoutineItemAdapter(val context: Context) : BaseAdapter() {
//
//    var routineList = ArrayList<RoutineItem>()
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val view: View = LayoutInflater.from(context).inflate(R.layout.list_routine_item, null) // 뷰 붙이기
//
//        val timer_iv = view.findViewById<ImageView>(R.id.list_routine_item_timer_iv)
//        val routine_tv = view.findViewById<TextView>(R.id.list_routine_item_tv)
//
//        val routineItem = routineList[position]
//
//        timer_iv.setImageResource(routineItem.checkImg)
//        routine_tv.text = routineItem.routine
//
//        return view
//    }
//
//    override fun getItem(position: Int): Any {
//        return routineList[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return 0
//    }
//
//    override fun getCount(): Int {
//        return routineList.size
//    }
//
//
//    fun setItem (ri : ArrayList<RoutineItem>) {
//       this.routineList = ri
//    }
//
//
//
//
//}