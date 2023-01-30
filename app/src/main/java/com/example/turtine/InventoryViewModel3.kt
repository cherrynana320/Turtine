package com.example.turtine

import androidx.lifecycle.*
import com.example.turtine.data.Item3
import com.example.turtine.data.ItemDao3
import kotlinx.coroutines.launch

class InventoryViewModel3(private val itemDao3: ItemDao3) : ViewModel() {

    // ++ getItems() 함수는 Flow를 반환하므로 LiveData로
    val allItems3: LiveData<List<Item3>> = itemDao3.getItems3().asLiveData()

    // ** Item 객체를 가져오고 비차단 방식으로 데이터를 데이터베이스에 추가하는 함수
    private fun insertItem3(item3: Item3) {
        // 기본 스레드 밖에서 데이    터베이스와 상호작용하려면 코루틴을 시작하고 그 안에서 DAO 메서드를 호출
        viewModelScope.launch {
            itemDao3.insert3(item3)
        }
    }

    // ** 문자열 세 개를 가져오고, Item 인스턴스를 반환하는 함수
    private fun getNewItem3Entry(itemRoutine: String, itemMin: String, itemSec: String): Item3 {
        return Item3(
            itemRoutine3 = itemRoutine,
            itemMin3 = itemMin.toInt(),
            itemSec3 = itemSec.toInt()
        )
    }

    // ** 항목 세부정보 문자열을 세 개 가져오는 함수
    fun addNewItem3(itemRoutine: String, itemMin: String, itemSec: String) {
        val newItem = getNewItem3Entry(itemRoutine, itemMin, itemSec)
        insertItem3(newItem)
    }


    // ** TextFields의 텍스트가 비어 있지 않은지 확인하는 함수
    fun isEntryValid3(itemRoutine: String, itemMin: String, itemSec: String): Boolean {
        if (itemRoutine.isBlank() || itemMin.isBlank() || itemSec.isBlank()) {
            return false
        }
        return true
    }


    // ++ id를 통해 검색하기
    fun retrieveItem3(id: Int): LiveData<Item3> {
        return itemDao3.getItem3(id).asLiveData()
    }

}

class InventoryViewModelFactory3(private val itemDao3: ItemDao3) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create( modelClass: Class<T> ): T {

        // modelClass가 InventoryViewModel 클래스와 같은지 확인하고 그 인스턴스를 반환.
        if (modelClass.isAssignableFrom(InventoryViewModel3::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel3(itemDao3) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class") // 같지 않으면 예외 발생
    }
}