package kr.goodchoice.imagedownload.di.module

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
class GlideModule {

    @Provides
    fun provideGlide(
        @ApplicationContext context: Context
    ) : RequestManager {
        return Glide.with(context)
    }

}