package fit.budle.model.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fit.budle.network.APIRequests
import fit.budle.repository.Repository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "http://80.64.174.33:8080/"

    @Provides
    @Singleton
    fun provideAPIClientService() : APIRequests  {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            MoshiConverterFactory.create()).build().create(APIRequests::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: APIRequests) : Repository {
        return Repository(apiService)
    }
}