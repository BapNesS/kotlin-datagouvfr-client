package com.baptistecarlier.kotlin.datagouvfr.app.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.baptistecarlier.kotlin.datagouvfr.client.DgfrService
import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.model.Dataset
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import java.io.IOException
import java.lang.IllegalStateException

class DatasetPagingSource(
    private val dgfrService: DgfrService,
    private val query: String
) : PagingSource<Int, Dataset>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Dataset> {
        return try {

            val pageNumber = if (params is LoadParams.Append) params.key else 0

            val datasetPageDgfrResourceFlow = dgfrService
                .getListDatasets(
                    q = query,
                    page = pageNumber,
                    pageSize = params.loadSize
                )
                .filter {
                    (it is DgfrResource.Loading).not()
                }
                .first()

            if (datasetPageDgfrResourceFlow is DgfrResource.Success) {
                val list = datasetPageDgfrResourceFlow.data.data?.toList() ?: emptyList()
                LoadResult.Page(
                    data = list,
                    // Since 0 is the lowest page number, return null to signify no more pages
                    // should be loaded before datasetPageFlow.
                    prevKey = if (pageNumber > 0) pageNumber - 1 else null,
                    // This API defines that datasetPageFlow's out of data when a page returns empty. When out of
                    // data, we return `null` to signify no more pages should be loaded
                    // If the response instead
                    nextKey = if (list.isNotEmpty()) pageNumber + 1 else null
                )
            } else {
                LoadResult.Error(IllegalStateException())
            }

        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Dataset>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            // This loads starting from previous page, but since PagingConfig.initialLoadSize spans
            // multiple pages, the initial load will still load items centered around
            // anchorPosition. This also prevents needing to immediately launch prepend due to
            // prefetchDistance.
            state.closestPageToPosition(anchorPosition)?.nextKey
        }
    }
}
