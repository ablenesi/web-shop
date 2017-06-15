package edu.bbu.webshop.util

import android.arch.lifecycle.LiveData
import edu.bbu.webshop.api.ApiResponse
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

class LiveDateCallAdapterFactory : CallAdapter.Factory() {

    override fun get(returnType: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): CallAdapter<*, *>? {
        if (getRawType(returnType) != LiveData::class.java) {
            return null
        }

        val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
        if (getRawType(observableType) != ApiResponse::class.java) {
            return null
        }

        val bodyType = getParameterUpperBound(0, observableType as ParameterizedType)
        return LiveDataCallAdapter<Any>(bodyType)
    }

}

/**
 * Adapter to converts [Call][retrofit2.Call] to [LiveData]<[ApiResponse]>.
 */
class LiveDataCallAdapter<R>(val responseType: Type) : CallAdapter<R, LiveData<ApiResponse<R>>> {

    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<R>?) = object : LiveData<ApiResponse<R>>() {
        internal var started = AtomicBoolean(false)
        override fun onActive() {
            super.onActive()
            if (started.compareAndSet(false, true)){
                call?.enqueue(object : Callback<R>{
                    override fun onResponse(call: Call<R>?, response: Response<R>?) {
                        postValue(ApiResponse<R>(response))
                    }

                    override fun onFailure(call: Call<R>?, t: Throwable?) {
                        postValue(ApiResponse<R>(t))
                    }
                })

            }
        }
    }
}
