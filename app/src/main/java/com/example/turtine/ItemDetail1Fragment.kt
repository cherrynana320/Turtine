package com.example.turtine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.turtine.data.Item1
import com.example.turtine.data.Item2
import com.example.turtine.data.Item3
import com.example.turtine.databinding.FragmentItemDetail1Binding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * [ItemDetailFragment1] displays the details of the selected item.
 */
class ItemDetail1Fragment : Fragment() {
    private val navigationArgs: ItemDetail1FragmentArgs by navArgs()

    private var _binding: FragmentItemDetail1Binding? = null
    private val binding get() = _binding!!


    lateinit var item1: Item1 // ++ 단일 항목에 관한 정보를 저장할 item 생성
    lateinit var item2: Item2 // ++ 단일 항목에 관한 정보를 저장할 item 생성
    lateinit var item3: Item3 // ++ 단일 항목에 관한 정보를 저장할 item 생성


    // ++ by 위임을 사용하여 속성 초기화를 activityViewModels 클래스에 전달.
    // InventoryViewModelFactory 생성자를 전달
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




    // ++ ItemListAdapter에서 한 작업과 비슷
    // 각각 요소와 데이터 연결하기
    private fun bind1(item1: Item1) {
//        binding.itemName.text = item.itemName
//        binding.itemPrice.text = item.getFormattedPrice()
//        binding.itemCount.text = item.quantityInStock.toString()
        binding.apply {
            itemListRoutine.text = item1.itemRoutine1
            itemListMin.text = item1.itemMin1.toString()
            itemListSec.text = item1.itemSec1.toString()
        }
    }

    private fun bind2(item2: Item2) {
        binding.apply {
            itemListRoutine.text = item2.itemRoutine2
            itemListMin.text = item2.itemMin2.toString()
            itemListSec.text = item2.itemSec2.toString()
        }
    }

    private fun bind3(item3: Item3) {
        binding.apply {
            itemListRoutine.text = item3.itemRoutine3
            itemListMin.text = item3.itemMin3.toString()
            itemListSec.text = item3.itemSec3.toString()
        }
    }










    // ++ 화면에 보여주기
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = navigationArgs.itemId // 탐색 인수를 검색하여 새로운 변수에 할당


        viewModel1.retrieveItem1(id).observe(this.viewLifecycleOwner) { selectedItem ->
            item1 = selectedItem
            bind1(item1)
        }

//        viewModel2.retrieveItem2(id).observe(this.viewLifecycleOwner) { selectedItem ->
//            item2 = selectedItem
//            bind2(item2)
//        }
//
//        viewModel3.retrieveItem3(id).observe(this.viewLifecycleOwner) { selectedItem ->
//            item3 = selectedItem
//            bind3(item3)
//        }
    }





    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemDetail1Binding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Displays an alert dialog to get the user's confirmation before deleting the item.
     */
    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage(getString(R.string.delete_question))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.no)) { _, _ -> }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                deleteItem()
            }
            .show()
    }

    /**
     * Deletes the current item and navigates to the list fragment.
     */
    private fun deleteItem() {
        findNavController().navigateUp()
    }

    /**
     * Called when fragment is destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
