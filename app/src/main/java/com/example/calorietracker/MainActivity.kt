package com.example.calorieTracker

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.calorietracker.navigation.navigate
import com.example.calorietracker.ui.theme.CalorieTrackerTheme
import com.example.core.navigation.Route
import com.example.onboarding_presentation.activity.ActivityScreen
import com.example.onboarding_presentation.age.AgeScreen
import com.example.onboarding_presentation.gender.GenderScreen
import com.example.onboarding_presentation.goal.GoalScreen
import com.example.onboarding_presentation.height.HeightScreen
import com.example.onboarding_presentation.nutrient_goal.NutrientGoalScreen
import com.example.onboarding_presentation.weight.WeightScreen
import com.example.onboarding_presentation.welcome.WelcomeScreen
import com.example.tracker_presentation.search.SearchScreen
import com.example.tracker_presentation.tracker_overview.TrackerOverviewScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalorieTrackerTheme(darkTheme = false) {
                val navController = rememberNavController()
                val snackBarHostState = remember { SnackbarHostState() }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackBarHostState) },
                    ) { _ ->
                    NavHost(
                        navController = navController,
                        startDestination = Route.WELCOME
                    ) {
                        composable(Route.WELCOME) {
                            WelcomeScreen(onNavigate = navController::navigate)
                        }

                        composable(Route.AGE) {
                            AgeScreen(
                                scaffoldState = snackBarHostState,
                                onNavigate = navController::navigate
                            )
                        }

                        composable(Route.GENDER) {
                            GenderScreen(onNavigate = navController::navigate)
                        }

                        composable(Route.HEIGHT) {
                            HeightScreen(
                                scaffoldState = snackBarHostState,
                                onNavigate = navController::navigate
                            )
                        }

                        composable(Route.WEIGHT) {
                            WeightScreen(
                                scaffoldState = snackBarHostState,
                                onNavigate = navController::navigate
                            )
                        }

                        composable(Route.NUTRIENT_GOAL) {
                            NutrientGoalScreen(
                                scaffoldState = snackBarHostState,
                                onNavigate = navController::navigate
                            )
                        }

                        composable(Route.ACTIVITY) {
                            ActivityScreen(
                                onNavigate = navController::navigate
                            )
                        }

                        composable(Route.GOAL) {
                            GoalScreen(
                                onNavigate = navController::navigate
                            )
                        }

                        composable(Route.TRACKER_OVERVIEW) {
                            TrackerOverviewScreen(onNavigate = navController::navigate)
                        }

                        composable(
                            route = Route.SEARCH + "/{mealName}/{dayOfMonth}/{month}/{year}",
                            arguments = listOf(
                                navArgument("mealName") { type = NavType.StringType },
                                navArgument("dayOfMonth") { type = NavType.IntType },
                                navArgument("month") { type = NavType.IntType },
                                navArgument("year") { type = NavType.IntType }
                            )
                        ) {
                            val mealName = it.arguments?.getString("mealName")!!
                            val dayOfMonth = it.arguments?.getInt("dayOfMonth")!!
                            val month = it.arguments?.getInt("month")!!
                            val year = it.arguments?.getInt("year")!!
                            SearchScreen(
                                scaffoldState = snackBarHostState,
                                mealName = mealName,
                                dayOfMonth = dayOfMonth,
                                month = month,
                                year = year,
                                onNavigateUp = {
                                    navController.navigateUp()
                                }
                            )
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalorieTrackerTheme {
        Greeting("Android")
    }
}