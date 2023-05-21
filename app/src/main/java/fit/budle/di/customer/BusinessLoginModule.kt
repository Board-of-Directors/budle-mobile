package fit.budle.di.customer

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fit.budle.dao.customer.BusinessLoginDAO
import fit.budle.di.RetrofitModule
import fit.budle.repository.customer.BusinessLoginRepository
import fit.budle.repository_impl.customer.BusinessLoginRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BusinessLoginModule {
    @Provides
    @Singleton
    fun provideBusinessLoginDAO(): BusinessLoginDAO {
        return RetrofitModule.retrofit.create(BusinessLoginDAO::class.java)
    }

    @Provides
    @Singleton
    fun provideBusinessLoginRepository(businessLoginDAO: BusinessLoginDAO): BusinessLoginRepository {
        return BusinessLoginRepositoryImpl(businessLoginDAO)
    }
}
