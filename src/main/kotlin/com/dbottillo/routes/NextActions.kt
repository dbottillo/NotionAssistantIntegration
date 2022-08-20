package com.dbottillo.routes

import com.dbottillo.repository.NotionProvider
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.getNextActions(){
    val notionProvider: NotionProvider by inject()

    get("/add"){
        val text = call.request.queryParameters["text"]
        if (text.isNullOrEmpty()){
            call.respondText(
                status = HttpStatusCode.BadRequest,
                text = "Text parameter null or missing."
            )
        } else {
            val apiResponse = notionProvider.addAction()

            call.respond(
                status = HttpStatusCode.OK,
                message = apiResponse
            )
        }
    }
}