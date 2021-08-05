package kr.hs.dgsw.jaehyeon.network.model.Response


data class BaseResponse<T>(
    val code: Int,
    val `data`: T,
    val message: String
)