package fit.budle.di.customer

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fit.budle.dao.customer.OrderListDAO
import fit.budle.di.RetrofitModule.retrofit
import fit.budle.repository.customer.OrderListRepository
import fit.budle.repository_impl.customer.OrderListRepositoryImpl
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
