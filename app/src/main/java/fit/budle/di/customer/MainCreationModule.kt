package fit.budle.di.customer

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fit.budle.dao.customer.EstablishmentDAO
import fit.budle.repository.customer.EstablishmentRepository
import fit.budle.repository_impl.customer.EstablishmentRepositoryImpl
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainCreationModule {
    @Provides
    @Singleton
    fun provideEstablishmentDAO(retrofit: Retrofit): EstablishmentDAO {
        return retrofit.create(EstablishmentDAO::class.java)
    }

    @Provides
    @Singleton
    fun provideEstablishmentRepository(establishmentDAO: EstablishmentDAO): EstablishmentRepository {
        return EstablishmentRepositoryImpl(establishmentDAO)
    }
}
