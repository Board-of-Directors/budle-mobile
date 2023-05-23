package fit.budle.di.customer

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fit.budle.dao.customer.OrderCreateDAO
import fit.budle.repository.customer.OrderCreateRepository
import fit.budle.repository_impl.customer.OrderCreateRepositoryImpl
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrderCreateModule {
    @Provides
    @Singleton
    fun provideOrderCreateDAO(retrofit: Retrofit): OrderCreateDAO {
        return retrofit.create(OrderCreateDAO::class.java)
    }

    @Provides
    @Singleton
    fun provideOrderCreateRepository(orderCreateDAO: OrderCreateDAO): OrderCreateRepository {
        return OrderCreateRepositoryImpl(orderCreateDAO)
    }
}
