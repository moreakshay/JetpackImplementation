package moreakshay.com.mine.data.remote.dtos


import com.google.gson.annotations.SerializedName

data class DatesResponse(
    @SerializedName("maximum")
    val maximum: String,
    @SerializedName("minimum")
    val minimum: String
)