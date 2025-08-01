package com.example.eni_shop.repository

import com.example.eni_shop.bo.Article
import com.example.eni_shop.dao.ArticleDAO
import com.example.eni_shop.dao.DaoType
import com.example.eni_shop.network.ArticleService

class ArticleRepository(
    private val articleService: ArticleService,
    private val articleDAORoomImpl : ArticleDAO
) {

    suspend fun getArticle(id: Long, daoType: DaoType = DaoType.NETWORK): Article? {
        return when(daoType){
            DaoType.NETWORK -> articleService.getArticleById(id)
            else -> articleDAORoomImpl.findById(id)
        }
    }

    suspend fun addArticle(article: Article, daoType: DaoType = DaoType.NETWORK): Long {
        return articleDAORoomImpl.insert(article)
    }

    suspend fun getAllArticles(daoType: DaoType = DaoType.NETWORK): List<Article> {
        return when(daoType){
            DaoType.NETWORK -> articleService.getAllArticles()
            else -> articleDAORoomImpl.findAll()
        }
    }

    suspend fun deleteArticle(article: Article, daoType: DaoType = DaoType.NETWORK){
        return articleDAORoomImpl.delete(article)
    }

    suspend fun getCategories(): List<String> {
        return articleService.getCategories()
    }
}