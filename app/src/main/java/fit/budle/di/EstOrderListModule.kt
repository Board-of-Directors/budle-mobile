package fit.budle.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fit.budle.dao.EstOrderListDAO
import fit.budle.di.RetrofitModule.retrofit
import fit.budle.repository.EstOrderListRepository
import fit.budle.repository_impl.EstOrderListRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EstOrderListModule {

    @Provides
    @Singleton
    fun provideEstOrderListDAO(): EstOrderListDAO {
        return retrofit.create(EstOrderListDAO::class.java)
    }

    @Provides
    @Singleton
    fun provideEstOrderListRepository(estOrderListDAO: EstOrderListDAO): EstOrderListRepository {
        return EstOrderListRepositoryImpl(estOrderListDAO)
    }

}