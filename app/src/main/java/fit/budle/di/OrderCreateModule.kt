package fit.budle.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fit.budle.dao.EstCreationDAO
import fit.budle.dao.EstablishmentDAO
import fit.budle.dao.OrderCreateDAO
import fit.budle.di.RetrofitModule.retrofit
import fit.budle.repository.EstCreationRepository
import fit.budle.repository.EstablishmentRepository
import fit.budle.repository.OrderCreateRepository
import fit.budle.repository_impl.EstCreationRepositoryImpl
import fit.budle.repository_impl.EstablishmentRepositoryImpl
import fit.budle.repository_impl.OrderCreateRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrderCreateModule {
    @Provides
    @Singleton
    fun provideOrderCreateDAO(): OrderCreateDAO {
        return retrofit.create(OrderCreateDAO::class.java)
    }

    @Provides
    @Singleton
    fun provideOrderCreateRepository(orderCreateDAO: OrderCreateDAO): OrderCreateRepository {
        return OrderCreateRepositoryImpl(orderCreateDAO)
    }
}
