package fit.budle.di

import android.content.SharedPreferences
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val prefs: SharedPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val sessionId = prefs.getString("SessionId", null) //TODO Заменить SessionId на константу
        val request = chain.request().newBuilder()
            .addHeader("SessionId", "$sessionId") //TODO Заменить SessionId на константу
        Log.d("SESSIONID", "$sessionId")
        return chain.proceed(request.build())
    }
}
