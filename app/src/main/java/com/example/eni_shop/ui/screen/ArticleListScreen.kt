package com.example.eni_shop.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.eni_shop.bo.Article
import com.example.eni_shop.repository.ArticleRepository
import com.example.eni_shop.ui.common.EniShopTopBar
import com.example.eni_shop.vm.ArticleListViewModel


@Composable
fun ArticleListScreen(
    modifier: Modifier = Modifier,
    articleListViewModel: ArticleListViewModel = viewModel(factory = ArticleListViewModel.Factory)
) {

    val categories by articleListViewModel.categories.collectAsState()
    val articles by articleListViewModel.filteredArticles.collectAsState()
    val categorySelected by articleListViewModel.selectedCategory.collectAsState()

    Scaffold(
        topBar = { EniShopTopBar() }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            CategoryFilterChip(
                categories = categories,
                categorySelected = categorySelected,
                onCategoryChange = {
                    articleListViewModel.updateSelectedCategory(it)
                }
            )
            ArticleList(articles = articles)
        }
    }
}

@Composable
fun ArticleList(
    articles: List<Article>,
    modifier: Modifier = Modifier
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(articles) { article ->
            ArticleItem(article = article)
        }
    }


}

@Composable
fun ArticleItem(
    article: Article,
    modifier: Modifier = Modifier
) {
    Card(
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            AsyncImage(
                model = article.urlImage,
                contentDescription = article.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.inversePrimary, CircleShape)
                    .padding(8.dp)

            )
            Text(
                text = article.name,
                maxLines = 2,
                minLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
                //fontWeight = FontWeight.Bold,
                //fontSize = 18.sp,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = "${String.format("%.2f", article.price)} €"
            )
        }
    }


}

@Composable
fun CategoryFilterChip(
    modifier: Modifier = Modifier,
    categories: List<String>,
    categorySelected: String,
    onCategoryChange: (String) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            FilterChip(
                selected = categorySelected == category,
                onClick = {
                    if (categorySelected != category) {
                        onCategoryChange(category)
                    } else {
                        onCategoryChange("")
                    }

                },
                label = {
                    Text(text = category.replaceFirstChar {
                        it.uppercase()
                    })
                },
                leadingIcon = {
                    if (categorySelected == category) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = "Sélectionnée")
                    }
                }
            )
        }
    }


}

@Preview
@Composable
private fun Preview() {
    ArticleListScreen()
}