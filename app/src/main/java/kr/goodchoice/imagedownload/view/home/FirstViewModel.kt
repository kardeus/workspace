package kr.goodchoice.imagedownload.view.home

import android.content.Context
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import dagger.hilt.android.qualifiers.ApplicationContext
import kr.goodchoice.imagedownload.app.BaseViewModel
import kr.goodchoice.imagedownload.domain.ApiService
import kr.goodchoice.imagedownload.domain.Results
import kr.goodchoice.imagedownload.extension.singleMode
import kr.goodchoice.imagedownload.model.SearchResponse
import retrofit2.Response
import timber.log.Timber
import javax.inject.Named

class FirstViewModel @ViewModelInject constructor(
    @ApplicationContext val context: Context,
    @Named("kakao") private val api: ApiService
) : BaseViewModel() {

    fun getApi() {
        Log.d("FirstViewModel", "getApi()")
        disposable.add(
            api.getApi("asdasd")
                .singleMode(_isProgress) {
                    Timber.d("api Result:${it}")
                    when(it) {
                        is Results.Success<*> -> {
                            val response = it.data as Response<SearchResponse>
                        }
                        is Results.Error -> {

                        }
                    }
                }
        )
    }

}