package com.example.eni_shop.network

import com.example.eni_shop.bo.Article
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleService {

    companion object {
        val BASE_URL = "https://fakestoreapi.com/"

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @GET("products")
    suspend fun getAllArticles(): List<Article>

    @GET("products/{id}")
    suspend fun getArticleById(@Path("id") id: Long): Article

    @GET("products/categories")
    suspend fun getCategories(): List<String>

    object callFakeStoreApi {
        val retrofitService: ArticleService by lazy { retrofit.create(ArticleService::class.java) }
    }
}