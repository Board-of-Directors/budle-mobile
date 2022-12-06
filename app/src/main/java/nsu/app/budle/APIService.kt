package nsu.app.budle

import android.util.Log
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
    @POST("register")
    suspend fun sendUserRequest(@Body requestBody: RequestBody): Response<ResponseBody>

    @POST("checkCode")
    suspend fun checkCodeRequest(@Body requestBody: RequestBody): Response<ResponseBody>

    @GET("getCode")
    suspend fun getCodeRequest(@Query("phoneNumber") phoneNumber: String?): Response<ResponseBody>
}

@Serializable
data class Answer(val success: Boolean, val result: Boolean?, val exception: Exception?)

@Serializable
data class Exception(val message: String, val type: String)

suspend fun getCode(number: String): Answer? {
    val retrofit = Retrofit.Builder().baseUrl("http://192.168.43.224:30555/").build()
    val service = retrofit.create(APIService::class.java)
    var result: Answer? = null
    val message: Answer? = CoroutineScope(Dispatchers.IO).async {
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

suspend fun checkCode(number: String, code: String): Answer? {
    val retrofit = Retrofit.Builder().baseUrl("http://192.168.43.224:30555/").build()
    val service = retrofit.create(APIService::class.java)
    val jsonObject = JSONObject()
    jsonObject.put("phoneNumber", number)
    jsonObject.put("code", code)
    val jsonObjectString = jsonObject.toString()
    val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
    var result: Answer? = null
    val message: Answer? = CoroutineScope(Dispatchers.IO).async {
        val response = service.checkCodeRequest(requestBody)
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

suspend fun sendUser(number: String, name: String, password: String): Answer? {
    val retrofit = Retrofit.Builder().baseUrl("http://192.168.43.224:30555/").build()
    val service = retrofit.create(APIService::class.java)
    val jsonObject = JSONObject()
    jsonObject.put("name", name)
    jsonObject.put("phoneNumber", number)
    jsonObject.put("password", password)
    val jsonObjectString = jsonObject.toString()
    val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
    var result: Answer? = null
    val message: Answer? = CoroutineScope(Dispatchers.IO).async {
        val response = service.sendUserRequest(requestBody)
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
