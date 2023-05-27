package fit.budle.di

import android.content.SharedPreferences
import android.util.Log
import fit.budle.di.config.SharedPrefConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val prefs: SharedPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val sessionId = prefs.getString(SharedPrefConfig.sessionId, null)
        val request = chain.request().newBuilder()
            .addHeader(SharedPrefConfig.sessionId, "$sessionId")
        Log.d("SESSIONID", "$sessionId")
        return chain.proceed(request.build())
    }
}
