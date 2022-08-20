package com.dbottillo.routes

import com.dbottillo.repository.NotionProvider
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.getNextActions(){
    val notionProvider: NotionProvider by inject()

    get("/nextActions"){
        val apiResponse = notionProvider.getNextActions()

        call.respond(
            status = HttpStatusCode.OK,
            message = apiResponse
        )
    }
}