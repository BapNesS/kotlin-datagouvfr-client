package com.baptistecarlier.kotlin.datagouvfr.extensions

import com.baptistecarlier.kotlin.datagouvfr.client.model.Me
import com.baptistecarlier.kotlin.datagouvfr.client.model.User

fun Me?.displayName(): String =
    "${this?.firstName.orEmpty()} ${this?.lastName.orEmpty()}".trim()
fun User?.displayName(): String =
    "${this?.firstName.orEmpty()} ${this?.lastName.orEmpty()}".trim()