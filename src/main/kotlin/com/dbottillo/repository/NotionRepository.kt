package com.dbottillo.repository

import com.dbottillo.models.ApiResponse

class NotionRepository: NotionProvider {
    override suspend fun getNextActions(): ApiResponse {
        return ApiResponse(
            success = true
        )
    }
}