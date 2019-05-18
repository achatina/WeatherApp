package nikita.com.weatherapp.api

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val UNEXPECTED_ERROR = -1
const val SUCCESS = 200

fun retrofitClient(gson: Gson, baseUrl: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

/**
 *
 * Execute Retrofit call and returns proper value
 *
 * @return NetworkResponse with
 * @param T type
 *
 * if response was successful, method will
 * @return NetworkResponse with
 * @param T type
 *
 * If while processing was found an error, it will return NetworkResponse with string error
 *
 */
fun <T> processCall(call: Call<T>): NetworkResponse<T> {
    val response = try {
        call.execute()
    } catch (ex: Throwable) {
        return NetworkResponse(UNEXPECTED_ERROR)
    }

    return NetworkResponse(response.code(), response.message(), response.body())
}