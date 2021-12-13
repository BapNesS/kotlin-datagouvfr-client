package com.baptistecarlier.kotlin.datagouvfr.client.annotation

/**
 * A function parameter is missing regarding to API call.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
internal annotation class MissingApiParamter
