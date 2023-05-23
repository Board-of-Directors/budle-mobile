package fit.budle.di

import android.content.SharedPreferences
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val prefs: SharedPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val sessionId = prefs.getString(PrefSettings.sessionId, null)
        val request = chain.request().newBuilder()
            .addHeader(PrefSettings.sessionId, "$sessionId")
        Log.d("SESSIONID", "$sessionId")
        return chain.proceed(request.build())
    }
}
