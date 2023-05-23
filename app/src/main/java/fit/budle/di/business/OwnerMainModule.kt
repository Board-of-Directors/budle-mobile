package fit.budle.di.business

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fit.budle.dao.business.OwnerMainDAO
import fit.budle.repository.business.OwnerMainRepository
import fit.budle.repository_impl.business.OwnerMainRepositoryImpl
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OwnerMainModule {

    @Provides
    @Singleton
    fun provideOwnerMainDAO(retrofit: Retrofit): OwnerMainDAO {
        return retrofit.create(OwnerMainDAO::class.java)
    }

    @Provides
    @Singleton
    fun provideOwnerMainRepository(ownerMainDAO: OwnerMainDAO): OwnerMainRepository {
        return OwnerMainRepositoryImpl(ownerMainDAO)
    }

}