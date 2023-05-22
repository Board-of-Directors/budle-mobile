package fit.budle.di.customer

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fit.budle.dao.customer.RegistrationDAO
import fit.budle.repository.customer.RegistrationRepository
import fit.budle.repository_impl.customer.RegistrationRepositoryImpl
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RegistrationModule {
    @Provides
    @Singleton
    fun provideRegistrationDAO(retrofit: Retrofit): RegistrationDAO {
        return retrofit.create(RegistrationDAO::class.java)
    }

    @Provides
    @Singleton
    fun provideRegistrationRepository(
        registrationDAO: RegistrationDAO,
        prefs: SharedPreferences,
    ): RegistrationRepository {
        return RegistrationRepositoryImpl(registrationDAO, prefs)
    }
}
