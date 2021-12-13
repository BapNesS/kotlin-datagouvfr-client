package com.baptistecarlier.kotlin.datagouvfr.client.annotation

/**
 * A field mapping is missing regarding to API model.
 */
@RequiresOptIn(message = "A field mapping is missing regarding to API model.")
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class MissingFieldMapping
