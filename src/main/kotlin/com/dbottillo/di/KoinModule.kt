package com.dbottillo.di

import com.dbottillo.repository.NotionProvider
import com.dbottillo.repository.NotionRepository
import org.koin.dsl.module

val koinModule = module {
    single<NotionProvider>{
        NotionRepository()
    }
}