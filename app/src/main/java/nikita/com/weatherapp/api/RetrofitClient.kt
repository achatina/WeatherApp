package nikita.com.weatherapp.api

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val UNEXPECTED_ERROR = -1

fun retrofitClient(gson: Gson, baseUrl: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

fun <T> processCall(call: Call<T>): NetworkResponse<T> {
    val response = try {
        call.execute()
    } catch (ex: Throwable) {
        return NetworkResponse(UNEXPECTED_ERROR)
    }

    return NetworkResponse(response.code(), response.message(), response.body())
}