package com.revbase.zaidanarrafif.di

import android.app.Activity
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.revbase.zaidanarrafif.common.Constant.QURAN_API_BASE_URL
import com.revbase.zaidanarrafif.common.PreferenceManager
import com.revbase.zaidanarrafif.data.DownloadRepoImpl
import com.revbase.zaidanarrafif.data.QuranRepoImpl
import com.revbase.zaidanarrafif.data.JournalRepoImpl
import com.revbase.zaidanarrafif.data.LoginRepoImpl
import com.revbase.zaidanarrafif.data.remote.quran.QuranAPI
import com.revbase.zaidanarrafif.data.remote.zaidan.AuthInterceptor
import com.revbase.zaidanarrafif.data.remote.zaidan.ZaidanAPI
import com.revbase.zaidanarrafif.domain.repositories.DownloadRepository
import com.revbase.zaidanarrafif.domain.repositories.JournalRepository
import com.revbase.zaidanarrafif.domain.repositories.LoginRepository
import com.revbase.zaidanarrafif.domain.repositories.QuranRepository
import com.revbase.zaidanarrafif.domain.use_case.play_audio_use_case.PlayAudioUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient().newBuilder()
            .build()

    @Provides
    @Singleton
    @Named("Quran")
    fun provideRetrofitForQuran(client: OkHttpClient) :Retrofit =
        Retrofit.Builder()
            .baseUrl(QURAN_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideQuranAPI(@Named("Quran") retrofit: Retrofit): QuranAPI =
        retrofit.create(QuranAPI::class.java)

    @Provides
    @Singleton
    fun provideQuranRepository(api: QuranAPI): QuranRepository =
        QuranRepoImpl(api)

    @Provides
    @Singleton
    fun provideDownloadRepository(@ApplicationContext context: Context): DownloadRepository =
        DownloadRepoImpl(context)

    @Provides
    @Singleton
    @Named("Journal")
    fun provideRetrofitForJournal(client: OkHttpClient) :Retrofit =
        Retrofit.Builder()
            .baseUrl("https://zaidanarrafif.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideZaidanAPI(@Named("Journal") retrofit: Retrofit): ZaidanAPI =
        retrofit.create(ZaidanAPI::class.java)

    @Provides
    @Singleton
    fun provideLoginRepository(api:ZaidanAPI): LoginRepository =
        LoginRepoImpl(api)

    @Provides
    @Singleton
    fun provideJournalRepository(api:ZaidanAPI): JournalRepository =
        JournalRepoImpl(api)

    @Provides
    @Singleton
    fun providePlayAudioUseCase(@ApplicationContext context: Context): PlayAudioUseCase =
        PlayAudioUseCase(context)

    @Provides
    @Singleton
    fun providePreferencesManager(@ApplicationContext context: Context): PreferenceManager =
        PreferenceManager(context)
}