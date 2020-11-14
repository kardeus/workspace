package kr.goodchoice.imagedownload.domain.rx

import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableSingleObserver
import retrofit2.Response
import timber.log.Timber

class RepoSingleObserver<T>(
    private val isProgress: MutableLiveData<Boolean>? = null
) : DisposableSingleObserver<T>() {

    override fun onSuccess(t: T) {
        isProgress?.postValue(false)
        if(t is Response<*>) {
            if(t.isSuccessful) {
                Timber.d("onSuccess${t}")
            }
        }
    }

    override fun onError(e: Throwable) {
        Timber.d("onError${e}")
        isProgress?.postValue(false)
    }

    override fun onStart() {
        super.onStart()
        isProgress?.postValue(true)
    }
}