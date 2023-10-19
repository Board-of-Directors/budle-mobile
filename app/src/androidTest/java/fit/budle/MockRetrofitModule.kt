package fit.budle

import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import fit.budle.di.RetrofitModule
import okhttp3.HttpUrl

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RetrofitModule::class]
)
class MockRetrofitModule : RetrofitModule() {
    override fun baseUrl(): HttpUrl {
        return MockServer.server.url("http://localhost/")
    }
}
