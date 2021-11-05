package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property args The job execution arguments
 * @property crontab
 * @property description The job description
 * @property enabled Is this job enabled
 * @property id The job unique identifier
 * @property interval
 * @property kwargs The job execution keyword arguments
 * @property lastRunAt The last job execution date
 * @property lastRunId The last execution task id
 * @property name The job unique name
 * @property schedule The schedule display
 * @property task The task name
 */
@Serializable
data class Job(
    @SerialName("name")
    var name: String,
    @SerialName("task")
    var task: Job.TaskEnum,
    /*@SerialName("args")
    var args: List<Map<String, Any?>>? = null,*/
    @SerialName("crontab")
    var crontab: Crontab? = null,
    @SerialName("description")
    var description: String? = null,
    @SerialName("enabled")
    var enabled: Boolean? = null,
    @SerialName("id")
    var id: String? = null,
    /*@SerialName("interval")
    var interval: Interval? = null,*/
    @SerialName("kwargs")
    var kwargs: JobKwargs? = null,
    @SerialName("last_run_at")
    var lastRunAt: LocalDateTime? = null,
    @SerialName("last_run_id")
    var lastRunId: String? = null,
    @SerialName("schedule")
    var schedule: String? = null
) {
    /**
     * The task name
     * Values: TESTMINUSHIGHMINUSQUEUE, TESTMINUSDEFAULTMINUSQUEUE,
     *  UPDATEMINUSDATASETSMINUSREUSESMINUSMETRICS, PIWIKMINUSBULKMINUSTRACKMINUSAPI,
     *  COUNTMINUSTAGS, PURGEMINUSORGANIZATIONS, TESTMINUSLOG, EXPORTMINUSCSV, HARVEST,
     *  APIGOUVFRMINUSLOADMINUSAPIS, TESTMINUSLOWMINUSQUEUE, PURGEMINUSREUSES,
     *  PIWIKMINUSUPDATEMINUSMETRICS, PURGEMINUSCHUNKS, PIWIKMINUSCURRENTMINUSMETRICS,
     *  PURGEMINUSDATASETS, PURGEMINUSHARVESTERS, PIWIKMINUSYESTERDAYMINUSMETRICS,
     *  COMPUTEMINUSSITEMINUSMETRICS, SENDMINUSFREQUENCYMINUSREMINDER,
     *  PURGEMINUSHARVESTMINUSJOBS, TESTMINUSERROR
     */
    @Serializable
    enum class TaskEnum(val value: String) {
        @SerialName("test-high-queue")
        TESTMINUSHIGHMINUSQUEUE("test-high-queue"),
        @SerialName("test-default-queue")
        TESTMINUSDEFAULTMINUSQUEUE("test-default-queue"),
        @SerialName("update-datasets-reuses-metrics")
        UPDATEMINUSDATASETSMINUSREUSESMINUSMETRICS("update-datasets-reuses-metrics"),
        @SerialName("piwik-bulk-track-api")
        PIWIKMINUSBULKMINUSTRACKMINUSAPI("piwik-bulk-track-api"),
        @SerialName("count-tags")
        COUNTMINUSTAGS("count-tags"),
        @SerialName("purge-organizations")
        PURGEMINUSORGANIZATIONS("purge-organizations"),
        @SerialName("test-log")
        TESTMINUSLOG("test-log"),
        @SerialName("export-csv")
        EXPORTMINUSCSV("export-csv"),
        @SerialName("harvest")
        HARVEST("harvest"),
        @SerialName("apigouvfr-load-apis")
        APIGOUVFRMINUSLOADMINUSAPIS("apigouvfr-load-apis"),
        @SerialName("test-low-queue")
        TESTMINUSLOWMINUSQUEUE("test-low-queue"),
        @SerialName("purge-reuses")
        PURGEMINUSREUSES("purge-reuses"),
        @SerialName("piwik-update-metrics")
        PIWIKMINUSUPDATEMINUSMETRICS("piwik-update-metrics"),
        @SerialName("purge-chunks")
        PURGEMINUSCHUNKS("purge-chunks"),
        @SerialName("piwik-current-metrics")
        PIWIKMINUSCURRENTMINUSMETRICS("piwik-current-metrics"),
        @SerialName("purge-datasets")
        PURGEMINUSDATASETS("purge-datasets"),
        @SerialName("purge-harvesters")
        PURGEMINUSHARVESTERS("purge-harvesters"),
        @SerialName("piwik-yesterday-metrics")
        PIWIKMINUSYESTERDAYMINUSMETRICS("piwik-yesterday-metrics"),
        @SerialName("compute-site-metrics")
        COMPUTEMINUSSITEMINUSMETRICS("compute-site-metrics"),
        @SerialName("send-frequency-reminder")
        SENDMINUSFREQUENCYMINUSREMINDER("send-frequency-reminder"),
        @SerialName("purge-harvest-jobs")
        PURGEMINUSHARVESTMINUSJOBS("purge-harvest-jobs"),
        @SerialName("test-error")
        TESTMINUSERROR("test-error")
    }
}
