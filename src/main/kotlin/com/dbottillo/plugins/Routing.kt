package com.dbottillo.plugins

import com.dbottillo.routes.getNextActions
import com.dbottillo.routes.root
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(){
    routing {
        root()
        getNextActions()

        // to server static images in case it's needed under the "images" folder in resources
        static("/images") {
            resource("images")
        }
    }
}