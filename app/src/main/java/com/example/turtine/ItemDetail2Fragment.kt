package com.example.turtine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.turtine.data.Item2
import com.example.turtine.databinding.FragmentItemDetail2Binding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * [ItemDetailFragment1] displays the details of the selected item.
 */
class ItemDetail2Fragment : Fragment() {
    private val navigationArgs: ItemDetail2FragmentArgs by navArgs()

    private var _binding: FragmentItemDetail2Binding? = null
    private val binding get() = _binding!!


    lateinit var item2: Item2 // ++ 단일 항목에 관한 정보를 저장할 item 생성


    private val viewModel2: InventoryViewModel2 by activityViewModels {
        InventoryViewModelFactory2(
            (activity?.application as InventoryApplication).database2.itemDao2()
        )
    }


    private fun bind2(item2: Item2) {
        binding.apply {
            itemListRoutine.text = item2.itemRoutine2
            itemListMin.text = item2.itemMin2.toString()
            itemListSec.text = item2.itemSec2.toString()
        }
    }



    // ++ 화면에 보여주기
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = navigationArgs.itemId // 탐색 인수를 검색하여 새로운 변수에 할당

        viewModel2.retrieveItem2(id).observe(this.viewLifecycleOwner) { selectedItem ->
            item2 = selectedItem
            bind2(item2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemDetail2Binding.inflate(inflater, container, false)
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
