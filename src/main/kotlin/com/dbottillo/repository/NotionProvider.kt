package com.dbottillo.repository

import com.dbottillo.models.ApiResponse

interface NotionProvider {
    suspend fun getNextActions(): ApiResponse
}