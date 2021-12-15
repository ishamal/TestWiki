package com.example.wikitest.view.viewModel

import android.view.View
import androidx.lifecycle.*
import com.example.wikitest.view.repositary.EntryRepository
import com.example.wikitest.view.service.MainService
import com.example.wikitest.view.service.response.Entry
import com.example.wikitest.view.service.response.EntryDetail
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository: EntryRepository = EntryRepository(MainService.client)
//    val isLoading = ObservableBoolean()

    private val mainEntryResponse  = MutableLiveData<List<Entry>>()
    val mainEntryLiveData : LiveData<List<Entry>> get() = mainEntryResponse


    private val mainEntryDetailResponse  = MutableLiveData<List<EntryDetail>>()
    val mainEntryDetailLiveData : LiveData<List<EntryDetail>> get() = mainEntryDetailResponse

    fun getEntry(loadingView : View?) {
//        isLoading.set(true)
        viewModelScope.launch {
            val result = repository.getMainList( loadingView)
            if (result != null) {
//                isLoading.set(false)
                mainEntryResponse.postValue(result)
            } else {
//                isLoading.set(false)
            }
        }
    }


    fun getDetails(id : String?, loadingView : View?) {
        viewModelScope.launch {
            val result = repository.getDetailEntry(id, loadingView)
            if (result != null) {
                mainEntryDetailResponse.postValue(result)
            }
        }
    }


}


@Suppress("UNCHECKED_CAST")
class MainViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel() as T
    }
}