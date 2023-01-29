package  com.example.turtine

import android.media.Image
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

//얘는 인터셉턴데 레이아웃 즉 캘린더 셀 xml과 한 세트임
//여기서 아이템뷰는 calendar_cell.xml을 말함
class CalendarViewHolder internal constructor(itemView: View, onItemListener: CalendarAdapter.OnItemListener) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {
    val dayOfMonth: TextView
    val flagview : ImageView

    private val onItemListener: CalendarAdapter.OnItemListener
    override fun onClick(view: View) {
        //여기서 애댑터 포지션으로 달력 일수 위치 인덱스 정해주고 , dayofmonth에 일수 저장한 다음에 text로 빼서 그걸 mainactivity에서 받아서 변수를 사용하고 자세한 함수 구현하고 여기서 실행
        if(dayOfMonth.text != "") {
            onItemListener.onItemClick(adapterPosition, dayOfMonth.text as String)
        }
    }
    //기본생성
    init {
        dayOfMonth = itemView.findViewById(R.id.cellDayText)
        flagview = itemView.findViewById(R.id.flagimage)

        //여기서 This = calendarViewHolder
        this.onItemListener = onItemListener
        itemView.setOnClickListener(this)
    }
 }
