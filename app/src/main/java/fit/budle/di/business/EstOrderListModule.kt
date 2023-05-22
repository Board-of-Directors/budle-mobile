package fit.budle.di.business

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fit.budle.dao.business.EstOrderListDAO
import fit.budle.repository.business.EstOrderListRepository
import fit.budle.repository_impl.business.EstOrderListRepositoryImpl
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EstOrderListModule {

    @Provides
    @Singleton
    fun provideEstOrderListDAO(retrofit: Retrofit): EstOrderListDAO {
        return retrofit.create(EstOrderListDAO::class.java)
    }

    @Provides
    @Singleton
    fun provideEstOrderListRepository(estOrderListDAO: EstOrderListDAO): EstOrderListRepository {
        return EstOrderListRepositoryImpl(estOrderListDAO)
    }

}
