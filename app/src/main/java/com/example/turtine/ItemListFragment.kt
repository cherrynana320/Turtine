/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.turtine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.turtine.databinding.ItemListFragmentBinding

/**
 * Main fragment displaying details for all items in the database.
 */
class ItemListFragment : Fragment() {


    // ++
    private val viewModel1: InventoryViewModel1 by activityViewModels {
        InventoryViewModelFactory1(
            (activity?.application as InventoryApplication).database1.itemDao1()
        )
    }
    private val viewModel2: InventoryViewModel2 by activityViewModels {
        InventoryViewModelFactory2(
            (activity?.application as InventoryApplication).database2.itemDao2()
        )
    }
    private val viewModel3: InventoryViewModel3 by activityViewModels {
        InventoryViewModelFactory3(
            (activity?.application as InventoryApplication).database3.itemDao3()
        )
    }




    private var _binding: ItemListFragmentBinding? = null
    private val binding get() = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = ItemListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }






    // 리스트 페이지에서 보이게 하기
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //++ 아무것도 전달하지 않는 기본 생성자 ItemListAdapter{}를 사용하여 새 adapter 속성을 초기화
        val adapter1 = ItemListAdapter1 (
            onItemClicked = {
                // 항목 id를 전달하는 ItemListFragmentDirections에서 actionItemListFragmentToItemDetailFragment() 메서드를 호출합니다. 반환된 NavDirections 객체를 action에 할당
                val action =
                    ItemListFragmentDirections.actionItemListFragmentToItemDetail1Fragment(it.id1)
                this.findNavController().navigate(action)
            },
            onTimeClicked = {
                val action =
                    ItemListFragmentDirections.actionItemListFragmentToTimer1Fragment(
                        it.itemRoutine1,
                        it.id1
                    )
                this.findNavController().navigate(action)
            }
        )


        val adapter2 = ItemListAdapter2 (
            onItemClicked = {
                // 항목 id를 전달하는 ItemListFragmentDirections에서 actionItemListFragmentToItemDetailFragment() 메서드를 호출합니다. 반환된 NavDirections 객체를 action에 할당
                val action =
                    ItemListFragmentDirections.actionItemListFragmentToItemDetail2Fragment(it.id2)
                this.findNavController().navigate(action)
            },
            onTimeClicked = {
                val action =
                    ItemListFragmentDirections.actionItemListFragmentToTimer2Fragment(
                        it.itemRoutine2,
                        it.id2
                    )
                this.findNavController().navigate(action)
            }
        )

        val adapter3 = ItemListAdapter3 (
            onItemClicked = {
                // 항목 id를 전달하는 ItemListFragmentDirections에서 actionItemListFragmentToItemDetailFragment() 메서드를 호출합니다. 반환된 NavDirections 객체를 action에 할당
                val action =
                    ItemListFragmentDirections.actionItemListFragmentToItemDetail3Fragment(it.id3)
                this.findNavController().navigate(action)
            },
            onTimeClicked = {
                val action =
                    ItemListFragmentDirections.actionItemListFragmentToTimer3Fragment(
                        it.itemRoutine3,
                        it.id3
                    )
                this.findNavController().navigate(action)
            }
        )



        binding.recyclerView1.adapter = adapter1 // 새로 만든 adapter를 recyclerView에 바인딩
        binding.recyclerView2.adapter = adapter2 // 새로 만든 adapter를 recyclerView에 바인딩
        binding.recyclerView3.adapter = adapter3 // 새로 만든 adapter를 recyclerView에 바인딩




        // ++ allItems에 관찰자를 연결하여 데이터 변경사항을 수신 대기
        viewModel1.allItems1.observe(this.viewLifecycleOwner) { items ->
            items.let {
                // 관찰자 내 adapter에서 submitList()를 호출하고 새 목록을 전달
                // 그러면 새 항목이 목록에 포함되어 RecyclerView가 업데이트 됨.
                adapter1.submitList(it)
            }
        }

        viewModel2.allItems2.observe(this.viewLifecycleOwner) { items ->
            items.let {
                // 관찰자 내 adapter에서 submitList()를 호출하고 새 목록을 전달
                // 그러면 새 항목이 목록에 포함되어 RecyclerView가 업데이트 됨.
                adapter2.submitList(it)
            }
        }

        viewModel3.allItems3.observe(this.viewLifecycleOwner) { items ->
            items.let {
                // 관찰자 내 adapter에서 submitList()를 호출하고 새 목록을 전달
                // 그러면 새 항목이 목록에 포함되어 RecyclerView가 업데이트 됨.
                adapter3.submitList(it)
            }
        }




        binding.recyclerView1.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView2.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView3.layoutManager = LinearLayoutManager(this.context)



        binding.floatingActionButton1.setOnClickListener {
            val action = ItemListFragmentDirections.actionItemListFragmentToAddItem1Fragment(
                getString(R.string.add_fragment_title)
            )
            this.findNavController().navigate(action)
        }
        binding.floatingActionButton2.setOnClickListener {
            val action = ItemListFragmentDirections.actionItemListFragmentToAddItem2Fragment(
                getString(R.string.add_fragment_title)
            )
            this.findNavController().navigate(action)
        }
        binding.floatingActionButton3.setOnClickListener {
            val action = ItemListFragmentDirections.actionItemListFragmentToAddItem3Fragment(
                getString(R.string.add_fragment_title)
            )
            this.findNavController().navigate(action)
        }

    }


}
