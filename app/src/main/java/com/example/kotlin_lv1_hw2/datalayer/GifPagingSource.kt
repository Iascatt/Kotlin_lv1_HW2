package com.example.kotlin_lv1_hw2.datalayer

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kotlin_lv1_hw2.objects.Gif
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class GifPagingSource(
    val backend: IAccessor
) : PagingSource<Int, Gif>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Gif> {
        Log.d("PAGING", "GPS")

        try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val gifs = backend.getGifs(nextPageNumber * 10, 10)
            Log.d("PAGING", "GPS try: ${gifs[0].id}")

            val nextKey =
                if (gifs.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    nextPageNumber + 1
                }
            return LoadResult.Page(
                data = gifs,
                prevKey = null, // Only paging forward.
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Gif>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}