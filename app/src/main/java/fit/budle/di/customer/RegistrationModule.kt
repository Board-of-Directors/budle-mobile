package fit.budle.di.customer

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fit.budle.dao.customer.RegistrationDAO
import fit.budle.di.RetrofitModule
import fit.budle.repository.customer.RegistrationRepository
import fit.budle.repository_impl.customer.RegistrationRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RegistrationModule {
    @Provides
    @Singleton
    fun provideRegistrationDAO(): RegistrationDAO {
        return RetrofitModule.retrofit.create(RegistrationDAO::class.java)
    }

    @Provides
    @Singleton
    fun provideRegistrationRepository(registrationDAO: RegistrationDAO): RegistrationRepository {
        return RegistrationRepositoryImpl(registrationDAO)
    }
}
