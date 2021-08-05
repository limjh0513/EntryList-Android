package kr.hs.dgsw.jaehyeon.repository

import io.reactivex.Single
import kr.hs.dgsw.jaehyeon.network.Service
import kr.hs.dgsw.jaehyeon.network.model.Request.VisitantRequest
import kr.hs.dgsw.jaehyeon.network.model.Response.ManagerResponse
import kr.hs.dgsw.jaehyeon.network.model.Response.VisitantResponse
import org.json.JSONObject

class EntryListRepository {
    fun getVisitantList(): Single<List<VisitantResponse>> {
        return Service.retrofit.getVisitantList().map {
            if(!it.isSuccessful){
                val error = JSONObject(it.errorBody().toString())
                throw Throwable(error.getString("Message"))
            }
            it.body()?.data
        }
    }

    fun getManagerList(): Single<List<ManagerResponse>>{
        return Service.retrofit.getManagerList().map{
            if(!it.isSuccessful){
                val error = JSONObject(it.errorBody().toString())
                throw Throwable(error.getString("Message"))
            }
            it.body()?.data
        }
    }

    fun insertVisitant(phone_number: String, residence: String): Single<Boolean>{
        val visitantRequest = VisitantRequest(phone_number, residence)
        return Service.retrofit.addVisitant(visitantRequest).map {
            if(!it.isSuccessful){
                val error = JSONObject(it.errorBody().toString())
                throw Throwable(error.getString("Message"))
            }
            it.body()?.data
        }
    }

    fun deleteVisitant(id: Int): Single<Boolean>{
        return Service.retrofit.deleteVisitant(id).map {
            if(!it.isSuccessful){
                val error = JSONObject(it.errorBody().toString())
                throw Throwable(error.getString("Message"))
            }
            it.body()?.data
        }

    }
}