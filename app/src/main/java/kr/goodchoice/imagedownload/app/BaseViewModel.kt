package kr.goodchoice.imagedownload.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val disposable = CompositeDisposable()

    protected val _isProgress = MutableLiveData<Boolean>()
    val isProgress : LiveData<Boolean> = _isProgress


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}