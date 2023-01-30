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
import com.example.turtine.data.Item3
import com.example.turtine.databinding.FragmentTimer3Binding
import com.example.turtine.databinding.ItemListItemBinding
import kotlinx.android.synthetic.main.fragment_timer_3.*
import java.util.*
import kotlin.concurrent.timer


class Timer3Fragment : Fragment() {
    private var _binding: FragmentTimer3Binding? = null
    private val binding get() = _binding!!
    private val navigationArgs: Timer3FragmentArgs by navArgs()

    private var itemBinding : ItemListItemBinding? = null

    private val viewModel3: InventoryViewModel3 by activityViewModels {
        InventoryViewModelFactory3(
            (activity?.application as InventoryApplication).database3.itemDao3()
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
        _binding = FragmentTimer3Binding.inflate(inflater, container, false)
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
            viewModel3.retrieveItem3(id).observe(viewLifecycleOwner) { selectedItem ->
                time = (selectedItem.itemMin3 * 60 + selectedItem.itemSec3) * 1000

                with(binding) {
                    //루틴내용
                    titleTextView.text = selectedItem.itemRoutine3
                    //시간
                    timerTextView.text = "%02d:%02d".format(
                        Locale.US,
                        selectedItem.itemMin3,
                        selectedItem.itemSec3
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

    private fun start(item3: Item3) {
        fun setTime(time: Int) {
            uiHandler.post {
                val minute = (time / 1000) / 60
                val second = (time / 1000) % 60
                val progress = ((item3.itemMin3 * 60 + item3.itemSec3) * 1000) - time

                if (_binding == null) return@post

                with(binding) {
                    timerTextView.text = "%02d:%02d".format(Locale.US, minute, second)
                    progressIndicator.progress = progress
                }
            }
        }
        lateinit var item3: Item3
        setTime(time)
        timerTask = timer(period = 10) {
            time -= 10
            if (time <= 0) {
                uiHandler.post {
                    binding.doneButton.isVisible = true
                    binding.doneButton.setOnClickListener{
                        requireActivity().onBackPressed()


                        viewModel3.retrieveItem3(id).observe(viewLifecycleOwner) { selectedItem ->
                            item3 = selectedItem
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
