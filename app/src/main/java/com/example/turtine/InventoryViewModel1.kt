package com.example.turtine

import androidx.lifecycle.*
import com.example.turtine.data.Item1
import com.example.turtine.data.ItemDao1
import kotlinx.coroutines.launch

class InventoryViewModel1(private val itemDao1: ItemDao1) : ViewModel() {

    // ++ getItems() 함수는 Flow를 반환하므로 LiveData로
    val allItems1: LiveData<List<Item1>> = itemDao1.getItems1().asLiveData()

    // ** Item 객체를 가져오고 비차단 방식으로 데이터를 데이터베이스에 추가하는 함수
    private fun insertItem1(item1: Item1) {
        // 기본 스레드 밖에서 데이    터베이스와 상호작용하려면 코루틴을 시작하고 그 안에서 DAO 메서드를 호출
        viewModelScope.launch {
            itemDao1.insert1(item1)
        }
    }

    // ** 문자열 세 개를 가져오고, Item 인스턴스를 반환하는 함수
    private fun getNewItem1Entry(itemRoutine: String, itemMin: String, itemSec: String): Item1 {
        return Item1(
            itemRoutine1 = itemRoutine,
            itemMin1 = itemMin.toInt(),
            itemSec1 = itemSec.toInt()
        )
    }

    // ** 항목 세부정보 문자열을 세 개 가져오는 함수
    fun addNewItem1(itemRoutine: String, itemMin: String, itemSec: String) {
        val newItem = getNewItem1Entry(itemRoutine, itemMin, itemSec)
        insertItem1(newItem)
    }


    // ** TextFields의 텍스트가 비어 있지 않은지 확인하는 함수
    fun isEntryValid1(itemRoutine: String, itemMin: String, itemSec: String): Boolean {
        if (itemRoutine.isBlank() || itemMin.isBlank() || itemSec.isBlank()) {
            return false
        }
        return true
    }


    // ++ id를 통해 검색하기
    fun retrieveItem1(id: Int): LiveData<Item1> {
        return itemDao1.getItem1(id).asLiveData()
    }

}

class InventoryViewModelFactory1(private val itemDao1: ItemDao1) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create( modelClass: Class<T> ): T {

        // modelClass가 InventoryViewModel 클래스와 같은지 확인하고 그 인스턴스를 반환.
        if (modelClass.isAssignableFrom(InventoryViewModel1::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel1(itemDao1) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class") // 같지 않으면 예외 발생
    }
}