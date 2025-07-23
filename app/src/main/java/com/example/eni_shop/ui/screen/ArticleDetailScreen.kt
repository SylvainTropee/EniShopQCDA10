package com.example.eni_shop.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.eni_shop.bo.Article
import com.example.eni_shop.repository.ArticleRepository
import com.example.eni_shop.ui.common.EniShopTopBar
import com.example.eni_shop.ui.common.toFrenchFormat

@Composable
fun ArticleDetailScreen(modifier: Modifier = Modifier) {

    Scaffold(
        topBar = { EniShopTopBar() }
    ) {
        ArticleDetail(ArticleRepository.getArticle(3)!!, modifier = Modifier.padding(it))
    }
}


@Composable
fun ArticleDetail(article: Article, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = article.name,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Justify,
        )

        Surface(
            color = MaterialTheme.colorScheme.inversePrimary,
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = article.urlImage,
                contentDescription = article.name,
                modifier = Modifier
                    .size(350.dp)
            )
        }
        Text(
            text = article.description,
            textAlign = TextAlign.Justify,
            style = MaterialTheme.typography.bodyMedium
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Prix ${String.format("%.2f", article.price)} €"
            )
            Text(
                text = "Date de sortie : ${article.date.toFrenchFormat()}"
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Checkbox(
                checked = false, onCheckedChange = {}
            )
            Text(text = "Favoris ?")
        }
    }
}


@Preview
@Composable
private fun Preview() {
    ArticleDetailScreen()
}

