package kr.goodchoice.imagedownload.extension

import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kr.goodchoice.imagedownload.domain.Results
import kr.goodchoice.imagedownload.domain.rx.RepoSingleObserver
import timber.log.Timber

fun <T>Single<T>.singleMode(
    isProgress: MutableLiveData<Boolean>? = null,
    function: ((result: Results) -> Unit)? = null
) : Disposable {
    return this.subscribeOn(Schedulers.io())
        .doOnSuccess { function?.invoke(Results.Success(it)) }
        .doOnError {
            Timber.e(it)
            function?.invoke(Results.Error(it))
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(RepoSingleObserver(isProgress))
}