package com.example.turtine

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.turtine.data.Item1
import com.example.turtine.databinding.FragmentAddItem1Binding
import com.example.turtine.databinding.ItemListItemBinding

/**
 * Fragment to add or update an item in the Inventory database.
 */
class AddItem1Fragment : Fragment() {
    // ++
    // by activityViewModels() : Kotlin 속성 위임을 사용하여 프래그먼트 전체에서 ViewModel을 공유
    private val viewModel1: InventoryViewModel1 by activityViewModels {
        InventoryViewModelFactory1(
            (activity?.application as InventoryApplication).database1
                .itemDao1() // ItemDao 인스턴스를 전달
        )
    }

    // ++
    lateinit var item1: Item1
    private var itemBinding : ItemListItemBinding? = null

    private val navigationArgs: ItemDetail1FragmentArgs by navArgs()

    private var _binding: FragmentAddItem1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddItem1Binding.inflate(inflater, container, false)
        return binding.root
    }

    // ++ 텍스트 필드가 비었는지 체크
    private fun isEntryValid1(): Boolean {
        // viewModel 인스턴스에서 isEntryValid() 함수를 호출하여 텍스트 뷰의 텍스트를 전달
        // viewModel.isEntryValid() 함수의 값을 반환
        return viewModel1.isEntryValid1(
            binding.itemRoutine.text.toString(),
            binding.itemMin.text.toString(),
            binding.itemSec.text.toString()
        )
    }


    // ++ 아이템을 추가함
    private fun addNewItem1() {
        if (isEntryValid1()) {
            // 사용자가 입력한 항목 세부정보를 전달하고 binding 인스턴스를 사용하여 읽음.
            viewModel1.addNewItem1(
                binding.itemRoutine.text.toString(),
                binding.itemMin.text.toString(),
                binding.itemSec.text.toString()
            )
        }
        // ItemListFragment로 다시 이동
        val action = AddItem1FragmentDirections.actionAddItem1FragmentToItemListFragment()
        findNavController().navigate(action)

    }

    // ++ 리스트 페이지에서 보이게 하기
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 클릭 핸들러를 저장 버튼에 추가하고 addNewItem()을 호출
        binding.saveAction.setOnClickListener {
            // 1. 아이템을 추가하고
            addNewItem1()
        }
    }

    /**
     * Called before fragment is destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        // Hide keyboard.
        val inputMethodManager = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }
}
