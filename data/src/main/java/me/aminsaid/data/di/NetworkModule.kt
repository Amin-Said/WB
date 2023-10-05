package me.aminsaid.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.aminsaid.core.utils.Constants
import me.aminsaid.data.BuildConfig
import me.aminsaid.data.remote.api.CityApi
import me.aminsaid.data.remote.api.WeatherApi
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level =
            HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val builder = OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                val original: Request = chain.request()

                val originalHttpUrl: HttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter(Constants.QUERY_API_KEY, BuildConfig.API_KEY)
                    .build()

                val requestBuilder: Request.Builder = original.newBuilder()
                    .url(url)

                val request: Request = requestBuilder.build()
                chain.proceed(request)
            }

        return builder.build()
    }
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideCityApi(retrofit: Retrofit): CityApi {
        return retrofit.create(CityApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

}
