package com.baptistecarlier.kotlin.datagouvfr.extensions

import com.baptistecarlier.kotlin.datagouvfr.client.models.User

fun User?.displayName(): String =
    "${this?.firstName?.orEmpty()} ${this?.lastName?.orEmpty()}".trim()