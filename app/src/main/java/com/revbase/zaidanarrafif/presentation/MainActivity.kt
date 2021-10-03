package com.revbase.zaidanarrafif.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.login_screen.LoginScreen
import com.revbase.zaidanarrafif.presentation.splash.SplashScreen
import com.revbase.zaidanarrafif.presentation.student.main_student.StudentMainScreen
import com.revbase.zaidanarrafif.presentation.student.surah_screen.SurahScreen
import com.revbase.zaidanarrafif.presentation.ui.theme.WhiteBackground
import com.revbase.zaidanarrafif.presentation.ui.theme.ZaidanArrafifTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZaidanArrafifTheme {
                val navController = rememberNavController()
                Surface(color = WhiteBackground) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.LoginScreen.route
                    ) {
                        composable(route = Screen.SplashScreen.route) {
                            SplashScreen(navController = navController)
                        }

                        composable(route = Screen.LoginScreen.route) {
                            LoginScreen(navController = navController)
                        }

                        composable(route = Screen.StudentMainScreen.route) {
                            StudentMainScreen(mainNavController = navController)
                        }

                        composable(
                            route = Screen.SurahScreen.route+"/{${Constant.PARAM_SURAH_NUMBER}}",
                            arguments = listOf(
                                navArgument(name = Constant.PARAM_SURAH_NUMBER) {
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            SurahScreen(navController = navController, it.arguments!!.getInt(
                                Constant.PARAM_SURAH_NUMBER
                            ))
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ZaidanArrafifTheme {
        Greeting("Android")
    }
}