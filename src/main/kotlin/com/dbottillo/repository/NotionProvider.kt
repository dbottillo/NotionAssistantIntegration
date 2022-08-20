package com.dbottillo.repository

import com.dbottillo.models.ApiResponse

interface NotionProvider {
    suspend fun addAction(): ApiResponse
}