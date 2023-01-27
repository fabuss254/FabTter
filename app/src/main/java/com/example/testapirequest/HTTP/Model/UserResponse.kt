package com.example.testapirequest.HTTP.Model

import com.squareup.moshi.Json

data class UserResponse(
    @field:Json(name = "data") val data: UserResponseData,
)

data class UserResponseData(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "username") val username: String,
)