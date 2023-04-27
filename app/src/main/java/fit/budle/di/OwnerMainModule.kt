package fit.budle.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fit.budle.dao.OwnerMainDAO
import fit.budle.repository.OwnerMainRepository
import fit.budle.repository_impl.OwnerMainRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OwnerMainModule {

    @Provides
    @Singleton
    fun provideOwnerMainDAO(): OwnerMainDAO {
        return RetrofitModule.retrofit.create(OwnerMainDAO::class.java)
    }

    @Provides
    @Singleton
    fun provideOwnerMainRepository(ownerMainDAO: OwnerMainDAO): OwnerMainRepository {
        return OwnerMainRepositoryImpl(ownerMainDAO)
    }

}