package fit.budle.di.business

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fit.budle.dao.business.WorkerDAO
import fit.budle.repository.business.WorkerRepository
import fit.budle.repository_impl.business.WorkerRepositoryImpl
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorkerListModule {

    @Provides
    @Singleton
    fun provideWorkerDAO(retrofit: Retrofit): WorkerDAO {
        return retrofit.create(WorkerDAO::class.java)
    }

    @Provides
    @Singleton
    fun provideWorkerRepository(workerDAO: WorkerDAO): WorkerRepository {
        return WorkerRepositoryImpl(workerDAO)
    }

}