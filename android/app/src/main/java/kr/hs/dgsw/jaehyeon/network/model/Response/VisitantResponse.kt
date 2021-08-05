package kr.hs.dgsw.jaehyeon.network.model.Response

import com.google.gson.annotations.SerializedName


data class VisitantResponse(
    val id: Int,
    @SerializedName("phone_number")
    val phoneNumber: String,
    val residence: String,
    @SerializedName("visit_date")
    val visitDate: String,
)