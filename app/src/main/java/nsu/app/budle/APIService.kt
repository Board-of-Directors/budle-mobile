package nsu.app.budle

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface APIService {
    @POST("checkCode")
    suspend fun checkCodeRequest(@Body requestBody: RequestBody): Response<ResponseBody>

    @GET("getCode")
    suspend fun getCodeRequest(@Query("phoneNumber") phoneNumber: String?): Response<ResponseBody>
}

fun getCode(number: String) {
    val retrofit = Retrofit.Builder().baseUrl("https://budle-app.herokuapp.com/").build()
    val service = retrofit.create(APIService::class.java)
    CoroutineScope(Dispatchers.IO).launch {
        val response = service.getCodeRequest("+79231095499")
        withContext(Dispatchers.Main) {
            if (response.isSuccessful) {
                val gson = GsonBuilder().setPrettyPrinting().create()
                val prettyJson = gson.toJson(
                    JsonParser.parseString(
                        response.body()?.string()
                    )
                )
                Log.d("Pretty Printed JSON :", prettyJson)
            } else {
                Log.e("RETROFIT_ERROR", response.code().toString())
            }
        }
    }
}

fun checkCode(number: String, code: String) {
    val retrofit = Retrofit.Builder().baseUrl("https://budle-app.herokuapp.com/").build()
    val service = retrofit.create(APIService::class.java)
    val jsonObject = JSONObject()
    jsonObject.put("phoneNumber", number)
    jsonObject.put("code", code)
    val jsonObjectString = jsonObject.toString()
    val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
    CoroutineScope(Dispatchers.IO).launch {
        val response = service.checkCodeRequest(requestBody)
        withContext(Dispatchers.Main) {
            if (response.isSuccessful) {
                val gson = GsonBuilder().setPrettyPrinting().create()
                val prettyJson = gson.toJson(
                    JsonParser.parseString(
                        response.body()?.string()
                    )
                )
                Log.d("Pretty Printed JSON :", prettyJson)
            } else {
                Log.e("RETROFIT_ERROR", response.code().toString())
            }
        }
    }
}
