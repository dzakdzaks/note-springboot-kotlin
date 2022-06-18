package com.dzikirqu.dzikirquservice.base

data class BaseResponse<T>(
    var status: Int,
    var message: String = "",
    var data: T? = null
)