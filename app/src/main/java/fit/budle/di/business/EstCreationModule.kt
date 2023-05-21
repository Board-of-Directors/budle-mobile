package fit.budle.di.business

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fit.budle.dao.business.EstCreationDAO
import fit.budle.repository.business.EstCreationRepository
import fit.budle.repository_impl.business.EstCreationRepositoryImpl
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EstCreationModule {

    @Provides
    @Singleton
    fun provideEstCreationDAO(retrofit: Retrofit): EstCreationDAO {
        return retrofit.create(EstCreationDAO::class.java)
    }

    @Provides
    @Singleton
    fun provideEstCreationRepository(estCreationDAO: EstCreationDAO): EstCreationRepository {
        return EstCreationRepositoryImpl(estCreationDAO)
    }

}