package com.autumnsun.websocket.model


data class LoginRequest(
    val is_request: Boolean,
    val id: Int,
    val error: String="",
    val params: ArrayList<Login> = ArrayList(),
    val method: String
)

data class Login (val username:String="",val password:String="")