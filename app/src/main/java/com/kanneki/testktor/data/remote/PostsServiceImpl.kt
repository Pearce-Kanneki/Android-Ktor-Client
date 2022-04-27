package com.kanneki.testktor.data.remote

import android.util.Log
import com.kanneki.testktor.data.remote.dto.PostRequest
import com.kanneki.testktor.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*


class PostsServiceImpl(private val client: HttpClient) : PostService {

    override suspend fun getPosts(): List<PostResponse> =
        try {
            client.get { url(HttpRoutes.POSTS) }
        } catch (e: RedirectResponseException) {
            // 3xx - response
            Log.e(HttpRoutes.TAG, "Error", e)
            emptyList()
        } catch (e: ClientRequestException) {
            // 4xx - response
            Log.e(HttpRoutes.TAG, "Error", e)
            emptyList()
        } catch (e: ServerResponseException) {
            // 5xx - response
            Log.e(HttpRoutes.TAG, "Error", e)
            emptyList()
        } catch (e: Exception) {
            Log.e(HttpRoutes.TAG, "Error", e)
            emptyList()
        }

    override suspend fun createPost(postRequest: PostRequest): PostResponse? =
        try {
            client.post<PostResponse> {
                url(HttpRoutes.POSTS)
                contentType(ContentType.Application.Json)
                body = postRequest
            }
        } catch (e: RedirectResponseException) {
            // 3xx - response
            Log.e(HttpRoutes.TAG, "Error", e)
            null
        } catch (e: ClientRequestException) {
            // 4xx - response
            Log.e(HttpRoutes.TAG, "Error", e)
            null
        } catch (e: ServerResponseException) {
            // 5xx - response
            Log.e(HttpRoutes.TAG, "Error", e)
            null
        } catch (e: Exception) {
            Log.e(HttpRoutes.TAG, "Error", e)
            null
        }

}