package kr.hs.dgsw.jaehyeon.network.api

import io.reactivex.Single
import kr.hs.dgsw.jaehyeon.network.model.Request.VisitantRequest
import kr.hs.dgsw.jaehyeon.network.model.Response.BaseResponse
import kr.hs.dgsw.jaehyeon.network.model.Response.ManagerResponse
import kr.hs.dgsw.jaehyeon.network.model.Response.VisitantResponse
import retrofit2.Response
import retrofit2.http.*

interface Api {
    @GET("inquire")
    fun getVisitantList(): Single<Response<BaseResponse<List<VisitantResponse>>>>

    @GET("manager")
    fun getManagerList(): Single<Response<BaseResponse<List<ManagerResponse>>>>

    @PUT("insert")
    fun addVisitant(@Body visitantRequest: VisitantRequest): Single<Response<BaseResponse<Boolean>>>

    @DELETE("delete")
    fun deleteVisitant(@Path("id") id: Int): Single<Response<BaseResponse<Boolean>>>
}