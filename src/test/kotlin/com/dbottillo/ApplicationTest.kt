package com.dbottillo

import com.dbottillo.models.ApiResponse
import com.dbottillo.repository.NotionProvider
import com.google.common.truth.Truth.assertThat
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import org.koin.java.KoinJavaComponent.inject

class ApplicationTest {

    /* If there is a need to get access to the provider for const or other things
    private val notionProvider: NotionProvider by inject(NotionProvider::class.java)
     */

    @Test
    fun `should return welcome message when serving root path`() = testApplication {
        val response = client.get("/")
        assertThat(response.status).isEqualTo(HttpStatusCode.OK)
        assertThat(response.bodyAsText()).isEqualTo("Welcome to Notion Assistant Integration API")
    }

    @Test
    internal fun `should add a task to notion`() = testApplication {
        val response = client.get("/add?text=example")
        assertThat(response.status).isEqualTo(HttpStatusCode.OK)
        val expected = ApiResponse(
            success = true
        )
        val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    internal fun `should return not found for un-served routes`() = testApplication {
        val response = client.get("/random")
        assertThat(response.status).isEqualTo(HttpStatusCode.NotFound)
        assertThat(response.bodyAsText()).isEqualTo("Page not found.")
    }

    @Test
    internal fun `should return bad request when calling add with no parameter`() = testApplication {
        val response = client.get("/add")
        assertThat(response.status).isEqualTo(HttpStatusCode.BadRequest)
        assertThat(response.bodyAsText()).isEqualTo("Text parameter null or missing.")
    }

    @Test
    internal fun `should return bad request when calling add empty text parameter`() = testApplication {
        val response = client.get("/add?text=")
        assertThat(response.status).isEqualTo(HttpStatusCode.BadRequest)
        assertThat(response.bodyAsText()).isEqualTo("Text parameter null or missing.")
    }
}