package nsu.app.budle

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
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

@Serializable
data class CheckNumber(val success: Boolean, val result: Boolean?, val exception: Exception?)

@Serializable
data class Exception(val message: String, val type: String)

suspend fun getCode(number: String): CheckNumber? {
    val retrofit = Retrofit.Builder().baseUrl("https://budle-app.herokuapp.com/").build()
    val service = retrofit.create(APIService::class.java)
    var result: CheckNumber? = null
    val message: CheckNumber? = CoroutineScope(Dispatchers.IO).async {
        val response = service.getCodeRequest(number)
        withContext(Dispatchers.IO) {
            if (response.isSuccessful) {
                val jsonData = Json.parseToJsonElement(response.body()!!.string())
                result = Json.decodeFromJsonElement(jsonData)
            } else {
                Log.e("RETROFIT_ERROR", response.code().toString())
            }
        }
        return@async result
    }.await()
    return message
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
