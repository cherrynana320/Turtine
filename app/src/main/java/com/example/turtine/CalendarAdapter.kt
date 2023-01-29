package  com.example.turtine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

//요 안에 뷰 홀더가 들어 있음 뷰홀더는 캐리어의 인터셉터임 따로 뷰홀더를 만들어줄거기 때문에 calendarViewHolder를 만듦
internal class CalendarAdapter(private val daysOfMonth: ArrayList<String>, private val onItemListener: OnItemListener) : RecyclerView.Adapter<CalendarViewHolder>() {
    //뷰 홀더가 생성됐을때
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //inflater는 레이아웃이랑 뷰를 연결시켜주는 메소드임
        val view: View = inflater.inflate(R.layout.calendar_cell, parent, false)
        //layoutParams는 부모 레이아웃 안에서 View(뷰)가 어떻게 배치될지를 정의하는 속성이다.
        val layoutParams = view.layoutParams
        layoutParams.height = (parent.height * 0.166666666).toInt()


        return CalendarViewHolder(view, onItemListener)
    }
    //뷰와 뷰홀더가 묶였을 때 , 즉 재활용이 됐을ㄸ ㅐ
    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        //그니까 holder = 켈린더뷰홀더의 dayofMonth를 daysOfMonth[position]로 설정해준다 .
        holder.dayOfMonth.text = daysOfMonth[position]

    }

    //목록의 아이템수
    override fun getItemCount(): Int {
        return daysOfMonth.size
    }

    //무언가 입력했을 때 그 입력 내용을 전달해주는 매개체가 인터페이스임
    //여기에 함수껍데기 구현 해놓고 mainactivity에서 자세한 내용 구현
   interface OnItemListener {
        fun onItemClick(position: Int, dayText: String?)
    }
}