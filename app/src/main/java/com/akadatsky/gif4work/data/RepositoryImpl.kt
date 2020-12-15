package com.akadatsky.gif4work.data

import com.akadatsky.gif4work.API_KEY
import com.akadatsky.gif4work.presentation.Repository

class RepositoryImpl(private val apiService: ApiService) : Repository {

    override suspend fun performSearch(query: String): List<Data> {
        val response = apiService.search(API_KEY, query, 10)
        return response?.body()?.data ?: emptyList()
    }

}