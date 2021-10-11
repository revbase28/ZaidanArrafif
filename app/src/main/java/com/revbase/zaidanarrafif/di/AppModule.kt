package com.revbase.zaidanarrafif.di

import android.content.Context
import com.revbase.zaidanarrafif.common.Constant.QURAN_API_BASE_URL
//import com.revbase.zaidanarrafif.data.DownloadRepoImpl
import com.revbase.zaidanarrafif.data.QuranRepoImpl
import com.revbase.zaidanarrafif.data.remote.JournalRepoImpl
import com.revbase.zaidanarrafif.data.remote.QuranAPI
import com.revbase.zaidanarrafif.domain.repositories.JournalRepository
//import com.revbase.zaidanarrafif.domain.repositories.DownloadRepository
import com.revbase.zaidanarrafif.domain.repositories.QuranRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
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
    fun provideJournalRepository(): JournalRepository =
        JournalRepoImpl()

//    @Provides
//    @Singleton
//    fun provideDownloadRepository(@ApplicationContext context: Context): DownloadRepository =
//        DownloadRepoImpl(context)
}