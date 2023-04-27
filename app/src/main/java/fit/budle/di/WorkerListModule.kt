package fit.budle.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fit.budle.dao.EstCreationDAO
import fit.budle.dao.EstOrderListDAO
import fit.budle.dao.OwnerMainDAO
import fit.budle.dao.WorkerDAO
import fit.budle.di.RetrofitModule.retrofit
import fit.budle.repository.EstCreationRepository
import fit.budle.repository.EstOrderListRepository
import fit.budle.repository.OwnerMainRepository
import fit.budle.repository.WorkerRepository
import fit.budle.repository_impl.EstCreationRepositoryImpl
import fit.budle.repository_impl.EstOrderListRepositoryImpl
import fit.budle.repository_impl.OwnerMainRepositoryImpl
import fit.budle.repository_impl.WorkerRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorkerListModule {

    @Provides
    @Singleton
    fun provideWorkerDAO(): WorkerDAO {
        return retrofit.create(WorkerDAO::class.java)
    }

    @Provides
    @Singleton
    fun provideWorkerRepository(workerDAO: WorkerDAO): WorkerRepository {
        return WorkerRepositoryImpl(workerDAO)
    }

}