package fit.budle.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fit.budle.dao.EstablishmentDAO
import fit.budle.dao.WorkerDAO
import fit.budle.repository.EstablishmentRepository
import fit.budle.repository.WorkerRepository
import fit.budle.repository_impl.EstablishmentRepositoryImpl
import fit.budle.repository_impl.WorkerRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "http://80.64.174.33:8080/"

    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
        MoshiConverterFactory.create()).build()

    @Provides
    @Singleton
    fun provideWorkerDAO() : WorkerDAO  {
        return retrofit.create(WorkerDAO::class.java)
    }

    @Provides
    @Singleton
    fun provideWorkerRepository(workerDAO: WorkerDAO) : WorkerRepository {
        return WorkerRepositoryImpl(workerDAO)
    }
}