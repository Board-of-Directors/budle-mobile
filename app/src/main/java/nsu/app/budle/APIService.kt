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
import retrofit2.http.POST

interface APIService {
    @POST("register")
    suspend fun createGuy(@Body requestBody: RequestBody): Response<ResponseBody>
}

fun rawJSON() {

    // Create Retrofit
    val retrofit = Retrofit.Builder()
        .baseUrl("https://budle-app.herokuapp.com/")
        .build()

    // Create Service
    val service = retrofit.create(APIService::class.java)

    // Create JSON using JSONObject

    val jsonObject = JSONObject()
    jsonObject.put("firstName", "Oleg")
    jsonObject.put("lastName", "Veber")
    jsonObject.put("phoneNumber", "+71234567809")
    jsonObject.put("password", "12345")

    // Convert JSONObject to String
    val jsonObjectString = jsonObject.toString()

    // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
    val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

    CoroutineScope(Dispatchers.IO).launch {
        // Do the POST request and get response
        val response = service.createGuy(requestBody)

        withContext(Dispatchers.Main) {
            if (response.isSuccessful) {

                // Convert raw JSON to pretty JSON using GSON library
                val gson = GsonBuilder().setPrettyPrinting().create()
                val prettyJson = gson.toJson(
                    JsonParser.parseString(
                        response.body()
                            ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                    )
                )

                Log.d("Pretty Printed JSON :", prettyJson)

            } else {

                Log.e("RETROFIT_ERROR", response.code().toString())

            }
        }
    }
}
