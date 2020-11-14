package kr.goodchoice.imagedownload.di.module

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kr.goodchoice.imagedownload.di.AuthInterceptorOkHttpClient
import kr.goodchoice.imagedownload.di.KakaoInterceptorOkHttpCLient
import kr.goodchoice.imagedownload.di.OtherInterceptorOkHttpClient
import kr.goodchoice.imagedownload.domain.AuthInterceptor
import kr.goodchoice.imagedownload.domain.KakaoInterceptor
import kr.goodchoice.imagedownload.domain.OtherInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

@Module
@InstallIn(ActivityComponent::class)
object NetworkModule {

    private val dateISO8601Format = DateTimeFormat
        .forPattern("yyyy-MM-dd'T'HH:mm:ss.000Z")
        .withZone(DateTimeZone.UTC)
        .withLocale(Locale.ENGLISH)
        .withOffsetParsed()

    private val gson = GsonBuilder()
        .registerTypeAdapter(DateTime::class.java, object : JsonDeserializer<DateTime> {
            override fun deserialize(
                json: JsonElement?,
                typeOfT: Type?,
                context: JsonDeserializationContext?
            ): DateTime {
                return try {
                    dateISO8601Format.parseDateTime(json?.asString)
                }catch (ex: java.text.ParseException) {
                    Timber.e(ex)
                    DateTime()
                }
            }
        }).create()

    val retrofit = Retrofit.Builder()
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))

    @AuthInterceptorOkHttpClient
    @Provides
    fun provideAuthInterceptorOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @OtherInterceptorOkHttpClient
    @Provides
    fun provideOtherInterceptorOkHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(OtherInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @KakaoInterceptorOkHttpCLient
    @Provides
    fun provideKakaoInterceptorOkHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(KakaoInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

}