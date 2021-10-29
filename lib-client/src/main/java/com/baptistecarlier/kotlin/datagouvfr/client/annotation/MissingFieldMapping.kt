package com.baptistecarlier.kotlin.datagouvfr.client.annotation

/**
 * A field mapping is missing regarding to API model.
 */
@RequiresOptIn(message = "A field mapping is missing regarding to API model.")
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class MissingFieldMapping

/**
 * A function parameter is missing regarding to API call.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
internal annotation class MissingApiParamter