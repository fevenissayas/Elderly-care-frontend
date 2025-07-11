package com.example.elderlycare2.di
//package com.example.medical_schedule_app.di
//
//import com.example.medical_schedule_app.data.api.AdminApiService
//import com.example.medical_schedule_app.data.api.ApiService
//import com.example.medical_schedule_app.data.api.DoctorApiService
//import com.example.medical_schedule_app.data.api.ReceptionistApiService
import com.example.elderlycare2.data.api.ApiService
import com.example.elderlycare2.data.api.NurseApi
import com.example.elderlycare2.data.api.ScheduleApi
import com.example.elderlycare2.data.api.TasksApi
import com.example.elderlycare2.data.api.UserProfileApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Base URL for the API
    private const val BASE_URL = "http://10.6.208.38:8000/"

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNurseApi(retrofit: Retrofit): NurseApi {
        return retrofit.create(NurseApi::class.java)
    }

    @Provides
    @Singleton
    fun provideScheduleApi(retrofit: Retrofit): ScheduleApi {
        return retrofit.create(ScheduleApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTasksApi(retrofit: Retrofit): TasksApi {
        return retrofit.create(TasksApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserProfileApi(retrofit: Retrofit): UserProfileApi {
        return retrofit.create(UserProfileApi::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideUserApi(retrofit: Retrofit): UserApi {
//        return retrofit.create(UserApi::class.java)
//    }

}
