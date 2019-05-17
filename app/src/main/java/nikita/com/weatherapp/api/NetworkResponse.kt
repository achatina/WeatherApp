package nikita.com.weatherapp.api

class NetworkResponse<T> (
    val code: Int,
    val error: String? = null,
    val data: T? = null
)