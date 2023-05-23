package fit.budle.di.customer

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fit.budle.dao.customer.BusinessLoginDAO
import fit.budle.repository.customer.BusinessLoginRepository
import fit.budle.repository_impl.customer.BusinessLoginRepositoryImpl
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BusinessLoginModule {
    @Provides
    @Singleton
    fun provideBusinessLoginDAO(retrofit: Retrofit): BusinessLoginDAO {
        return retrofit.create(BusinessLoginDAO::class.java)
    }

    @Provides
    @Singleton
    fun provideBusinessLoginRepository(
        businessLoginDAO: BusinessLoginDAO,
        prefs: SharedPreferences,
    ): BusinessLoginRepository {
        return BusinessLoginRepositoryImpl(businessLoginDAO, prefs)
    }
}
