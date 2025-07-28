package com.example.eni_shop.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.eni_shop.bo.Article
import com.example.eni_shop.repository.ArticleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

private const val TAG = "ArticleListViewModel"

class ArticleListViewModel(private val articleRepository: ArticleRepository) : ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    //val articles = _articles.asStateFlow()

    private val _filteredArticles = MutableStateFlow<List<Article>>(emptyList())
    val filteredArticles = _filteredArticles.asStateFlow()

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories = _categories.asStateFlow()

    private val _selectedCategory = MutableStateFlow<String>("")
    val selectedCategory = _selectedCategory.asStateFlow()

    init {
        _articles.value = articleRepository.getAllArticles()
        _filteredArticles.value = _articles.value
        _categories.value = listOf("electronics", "jewelery", "men's clothing", "women's clothing")
    }

    fun updateSelectedCategory(category: String) {
        _selectedCategory.value = category
        filterArticlesByCategory()
    }

    private fun filterArticlesByCategory() {

        _filteredArticles.value = if (selectedCategory.value != "") {
            _articles.value.filter {
                it.category == selectedCategory.value
            }
        } else {
            _articles.value
        }
    }


    // Define ViewModel factory in a companion object
    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
//                // Get the Application object from extras
//                val application = checkNotNull(extras[APPLICATION_KEY])
//                // Create a SavedStateHandle for this ViewModel from extras
//                val savedStateHandle = extras.createSavedStateHandle()

                return ArticleListViewModel(
                    ArticleRepository()
                ) as T
            }
        }
    }
}