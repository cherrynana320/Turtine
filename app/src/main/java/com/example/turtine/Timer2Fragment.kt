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
import com.example.turtine.data.Item2
import com.example.turtine.databinding.FragmentTimer2Binding
import com.example.turtine.databinding.ItemListItemBinding
import kotlinx.android.synthetic.main.fragment_timer_2.*
import java.util.*
import kotlin.concurrent.timer


class Timer2Fragment : Fragment() {
    private var _binding: FragmentTimer2Binding? = null
    private val binding get() = _binding!!
    private val navigationArgs: Timer2FragmentArgs by navArgs()

    private var itemBinding : ItemListItemBinding? = null



    private val viewModel2: InventoryViewModel2 by activityViewModels {
        InventoryViewModelFactory2(
            (activity?.application as InventoryApplication).database2.itemDao2()
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
        _binding = FragmentTimer2Binding.inflate(inflater, container, false)
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
            viewModel2.retrieveItem2(id).observe(viewLifecycleOwner) { selectedItem ->
                time = (selectedItem.itemMin2 * 60 + selectedItem.itemSec2) * 1000

                with(binding) {
                    //루틴내용
                    titleTextView.text = selectedItem.itemRoutine2
                    //시간
                    timerTextView.text = "%02d:%02d".format(
                        Locale.US,
                        selectedItem.itemMin2,
                        selectedItem.itemSec2
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

    private fun start(item2: Item2) {
        fun setTime(time: Int) {
            uiHandler.post {
                val minute = (time / 1000) / 60
                val second = (time / 1000) % 60
                val progress = ((item2.itemMin2 * 60 + item2.itemSec2) * 1000) - time

                if (_binding == null) return@post

                with(binding) {
                    timerTextView.text = "%02d:%02d".format(Locale.US, minute, second)
                    progressIndicator.progress = progress
                }
            }
        }
        lateinit var item2: Item2
        setTime(time)
        timerTask = timer(period = 10) {
            time -= 10
            if (time <= 0) {
                uiHandler.post {
                    binding.doneButton.isVisible = true
                    binding.doneButton.setOnClickListener{
                        requireActivity().onBackPressed()
                        viewModel2.retrieveItem2(id).observe(viewLifecycleOwner) { selectedItem ->
                            item2 = selectedItem
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
