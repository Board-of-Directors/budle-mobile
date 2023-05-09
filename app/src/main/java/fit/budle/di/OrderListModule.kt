package fit.budle.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fit.budle.dao.EstCreationDAO
import fit.budle.dao.EstablishmentDAO
import fit.budle.dao.OrderCreateDAO
import fit.budle.dao.OrderListDAO
import fit.budle.di.RetrofitModule.retrofit
import fit.budle.repository.EstCreationRepository
import fit.budle.repository.EstablishmentRepository
import fit.budle.repository.OrderCreateRepository
import fit.budle.repository.OrderListRepository
import fit.budle.repository_impl.EstCreationRepositoryImpl
import fit.budle.repository_impl.EstablishmentRepositoryImpl
import fit.budle.repository_impl.OrderCreateRepositoryImpl
import fit.budle.repository_impl.OrderListRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrderListModule {
    @Provides
    @Singleton
    fun provideOrderListDAO(): OrderListDAO {
        return retrofit.create(OrderListDAO::class.java)
    }

    @Provides
    @Singleton
    fun provideOrderListRepository(orderListDAO: OrderListDAO): OrderListRepository {
        return OrderListRepositoryImpl(orderListDAO)
    }
}
