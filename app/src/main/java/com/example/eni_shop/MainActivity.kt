package com.example.eni_shop

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eni_shop.bo.Article
import com.example.eni_shop.repository.ArticleRepository
import com.example.eni_shop.ui.common.NavigationBackIcon
import com.example.eni_shop.ui.screen.ArticleDetailScreen
import com.example.eni_shop.ui.screen.ArticleFormScreen
import com.example.eni_shop.ui.screen.ArticleListScreen
import com.example.eni_shop.ui.theme.ENISHOPTheme

//logt -> tab
private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ENISHOPTheme {
                EniShopApp()
            }
        }
    }
}

@Composable
fun EniShopApp(modifier: Modifier = Modifier) {
    val navHostController = rememberNavController()
    EniShopNavHost(navHostController = navHostController)
}

@Composable
fun EniShopNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navHostController,
        startDestination = ArticleListDestination.route
    ) {
        composable(route = ArticleListDestination.route) {
            ArticleListScreen(
                onNavigateToArticleDetail = { articleId ->
                    navHostController.navigate("${ArticleDetailDestination.route}/$articleId")
                },
                onNavigateToArticleAdd = {
                    navHostController.navigate(ArticleFormDestination.route)
                }
            )
        }
        composable(route = ArticleFormDestination.route) {
            ArticleFormScreen(
                navigationIcon = {
                    if (navHostController.previousBackStackEntry != null) {
                        NavigationBackIcon(
                            onNavigateToBack = {
                                navHostController.popBackStack()
                            }
                        )
                    }
                }
            )
        }
        composable(
            route = ArticleDetailDestination.routeWithArgs,
            arguments = ArticleDetailDestination.arguments
        ) {

            val articleId = it.arguments?.getLong(ArticleDetailDestination.argName)
            if (articleId != null) {
                ArticleDetailScreen(
                    id = articleId,
                    navigationIcon = {
                        if (navHostController.previousBackStackEntry != null) {
                            NavigationBackIcon(
                                onNavigateToBack = {
                                    navHostController.popBackStack()
                                }
                            )
                        }
                    }
                )
            }

        }
    }

}















