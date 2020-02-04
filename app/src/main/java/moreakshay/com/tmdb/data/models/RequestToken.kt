package moreakshay.com.tmdb.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class RequestToken {
    @SerializedName("expires_at")
    @Expose
    private var expiresAt: String? = null
    @SerializedName("request_token")
    @Expose
    private var requestToken: String? = null
    @SerializedName("success")
    @Expose
    private var success: Boolean? = null

    fun getSuccess(): Boolean? {
        return success
    }

    fun setSuccess(success: Boolean?) {
        this.success = success
    }


    fun getExpiresAt(): String? {
        return expiresAt
    }

    fun setExpiresAt(expiresAt: String) {
        this.expiresAt = expiresAt
    }

    fun getRequestToken(): String? {
        return requestToken
    }

    fun setRequestToken(requestToken: String) {
        this.requestToken = requestToken
    }
}