package etf.ri.rma.newsfeedapp.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import etf.ri.rma.newsfeedapp.screen.FilterScreen
import etf.ri.rma.newsfeedapp.screen.NewsFeedScreen
import etf.ri.rma.newsfeedapp.screen.NewsDetailsScreen

@Composable
fun NewsFeedNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home"){
        composable(route = "filters"){
            FilterScreen(navController)
        }

        composable(route = "home"){
            NewsFeedScreen(navController)
        }

        composable(route = "details/{newsId}") { backStackEntry ->
            val newsId = backStackEntry.arguments?.getString("newsId")
            NewsDetailsScreen(newsId = newsId ?: "", navController = navController)
        }
    }
}
