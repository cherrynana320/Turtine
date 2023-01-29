package com.example.turtine

import androidx.lifecycle.*
import com.example.turtine.data.Item2
import com.example.turtine.data.ItemDao2
import kotlinx.coroutines.launch

class InventoryViewModel2(private val itemDao2: ItemDao2) : ViewModel() {

    // ++ getItems() 함수는 Flow를 반환하므로 LiveData로
    val allItems2: LiveData<List<Item2>> = itemDao2.getItems2().asLiveData()

    // ** Item 객체를 가져오고 비차단 방식으로 데이터를 데이터베이스에 추가하는 함수
    private fun insertItem2(item2: Item2) {
        // 기본 스레드 밖에서 데이    터베이스와 상호작용하려면 코루틴을 시작하고 그 안에서 DAO 메서드를 호출
        viewModelScope.launch {
            itemDao2.insert2(item2)
        }
    }

    // ** 문자열 세 개를 가져오고, Item 인스턴스를 반환하는 함수
    private fun getNewItem2Entry(itemRoutine: String, itemMin: String, itemSec: String): Item2 {
        return Item2(
            itemRoutine2 = itemRoutine,
            itemMin2 = itemMin.toInt(),
            itemSec2 = itemSec.toInt()
        )
    }

    // ** 항목 세부정보 문자열을 세 개 가져오는 함수
    fun addNewItem2(itemRoutine: String, itemMin: String, itemSec: String) {
        val newItem = getNewItem2Entry(itemRoutine, itemMin, itemSec)
        insertItem2(newItem)
    }


    // ** TextFields의 텍스트가 비어 있지 않은지 확인하는 함수
    fun isEntryValid2(itemRoutine: String, itemMin: String, itemSec: String): Boolean {
        if (itemRoutine.isBlank() || itemMin.isBlank() || itemSec.isBlank()) {
            return false
        }
        return true
    }


    // ++ id를 통해 검색하기
    fun retrieveItem2(id: Int): LiveData<Item2> {
        return itemDao2.getItem2(id).asLiveData()
    }

}

class InventoryViewModelFactory2(private val itemDao2: ItemDao2) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create( modelClass: Class<T> ): T {

        // modelClass가 InventoryViewModel 클래스와 같은지 확인하고 그 인스턴스를 반환.
        if (modelClass.isAssignableFrom(InventoryViewModel2::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel2(itemDao2) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class") // 같지 않으면 예외 발생
    }
}