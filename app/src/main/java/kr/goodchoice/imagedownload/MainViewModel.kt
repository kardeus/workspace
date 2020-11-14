package kr.goodchoice.imagedownload

import android.app.Application
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import kr.goodchoice.imagedownload.domain.Results
import kr.goodchoice.imagedownload.extension.singleMode
import kr.goodchoice.imagedownload.model.SearchResponse
import kr.goodchoice.imagedownload.repository.SearchRepository
import timber.log.Timber

class MainViewModel @ViewModelInject constructor(
    application: Application,
    private val repository: SearchRepository
) : AndroidViewModel(application) {

    private val disposable = CompositeDisposable()
    private val _list = MutableLiveData<SearchResponse>()
    val list : LiveData<SearchResponse> = _list


    fun getApi() {
        Log.d("FirstViewModel", "getApi()")
        disposable.add(
            repository.getApi()
                .singleMode() {
                    Timber.d("api Result:${it}")
                    when(it) {
                        is Results.Success<*> -> {
                            val response = it.data as SearchResponse
                            _list.postValue(response)
                        }
                        is Results.Error -> {

                        }
                    }
                }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}