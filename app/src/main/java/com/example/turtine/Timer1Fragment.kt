package com.example.turtine

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.turtine.data.Item1
import com.example.turtine.databinding.FragmentTimer1Binding
import com.example.turtine.databinding.ItemListItemBinding
import kotlinx.android.synthetic.main.fragment_timer_1.*
import java.util.*
import kotlin.concurrent.timer


class Timer1Fragment : Fragment() {
    private var _binding: FragmentTimer1Binding? = null
    private val binding get() = _binding!!
    private val navigationArgs: Timer1FragmentArgs by navArgs()

    private var itemBinding : ItemListItemBinding? = null



    private val viewModel1: InventoryViewModel1 by activityViewModels {
        InventoryViewModelFactory1(
            (activity?.application as InventoryApplication).database1.itemDao1()
        )
    }


    private var time = 0
    private var timerTask: Timer? = null
    private val uiHandler by lazy { Handler(Looper.getMainLooper()) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimer1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = navigationArgs.itemId // 탐색 인수를 검색하여 새로운 변수에 할당

        lifecycleScope.launchWhenResumed {
            viewModel1.retrieveItem1(id).observe(viewLifecycleOwner) { selectedItem ->
                time = (selectedItem.itemMin1 * 60 + selectedItem.itemSec1) * 1000

                with(binding) {
                    //루틴내용
                    titleTextView.text = selectedItem.itemRoutine1
                    //시간
                    timerTextView.text = "%02d:%02d".format(
                        Locale.US,
                        selectedItem.itemMin1,
                        selectedItem.itemSec1
                    )
                    progressIndicator.max = time
                    progressIndicator.progress = 0

                    start(selectedItem)
                }
            }
        }
    }

    override fun onDestroy() {
        timerTask?.cancel()
        super.onDestroy()
    }

    private fun start(item1: Item1) {
        fun setTime(time: Int) {
            uiHandler.post {
                val minute = (time / 1000) / 60
                val second = (time / 1000) % 60
                val progress = ((item1.itemMin1 * 60 + item1.itemSec1) * 1000) - time

                if (_binding == null) return@post

                with(binding) {
                    timerTextView.text = "%02d:%02d".format(Locale.US, minute, second)
                    progressIndicator.progress = progress
                }
            }
        }
        lateinit var item1: Item1
        setTime(time)
        timerTask = timer(period = 10) {
            time -= 10
            if (time <= 0) {
                uiHandler.post {
                    binding.doneButton.isVisible = true
                    binding.doneButton.setOnClickListener{
                        requireActivity().onBackPressed()
                        viewModel1.retrieveItem1(id).observe(viewLifecycleOwner) { selectedItem ->
                            item1 = selectedItem
                            // 리스트 아이템의 이미지뷰 바꾸기
                            itemBinding?.imageView?.setImageResource(R.drawable.ic_baseline_check_circle_outline_24)
                        }
                    }
                    activity

                }

                cancel()
                return@timer
            }

            setTime(time)
        }
    }
}
