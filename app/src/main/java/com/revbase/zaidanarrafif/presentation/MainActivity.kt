package com.revbase.zaidanarrafif.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.HafalanDTO
import com.revbase.zaidanarrafif.presentation.login_screen.LoginScreen
import com.revbase.zaidanarrafif.presentation.splash.SplashScreen
import com.revbase.zaidanarrafif.presentation.student.hafalan_detail_screen.HafalanDetailScreen
import com.revbase.zaidanarrafif.presentation.student.jurnal_screen.DailyJournalScreen
import com.revbase.zaidanarrafif.presentation.student.main_student.StudentMainScreen
import com.revbase.zaidanarrafif.presentation.student.surah_screen.SurahScreen
import com.revbase.zaidanarrafif.presentation.student.tambah_hafalan.TambahHafalanScreen
import com.revbase.zaidanarrafif.presentation.teacher.main_teacher.TeacherMainScreen
import com.revbase.zaidanarrafif.presentation.ui.theme.WhiteBackground
import com.revbase.zaidanarrafif.presentation.ui.theme.ZaidanArrafifTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    @Inject lateinit var savedState: Bundle

    @ExperimentalPermissionsApi
    @ExperimentalCoilApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZaidanArrafifTheme {
                val navController = rememberNavController()
                Surface(color = WhiteBackground) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.SplashScreen.route
                    ) {
                        composable(route = Screen.SplashScreen.route) {
                            SplashScreen(navController = navController)
                        }

                        composable(route = Screen.LoginScreen.route) {
                            LoginScreen(navController = navController)
                        }

                        composable(route = Screen.StudentMainScreen.route) {
                            StudentMainScreen(mainNavController = navController, savedState = savedState)
                        }

                        composable(route = Screen.TeacherMainScreen.route) {
                            TeacherMainScreen(navController = navController)
                        }

                        composable(Screen.ActivityJournalScreen.route + "/{journalType}") { backStackEntry ->
                            DailyJournalScreen(
                                navController,
                                backStackEntry.arguments?.getString("journalType")!!
                            )
                        }

                        composable(
                            route = Screen.SurahScreen.route + "/{${Constant.PARAM_SURAH_NUMBER}}",
                            arguments = listOf(
                                navArgument(name = Constant.PARAM_SURAH_NUMBER) {
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            SurahScreen(
                                navController = navController, it.arguments!!.getInt(
                                    Constant.PARAM_SURAH_NUMBER
                                ),
                                savedState = savedState
                            )
                        }
                        
                        composable(route = Screen.HafalanDetailScreen.route){
                            var hafalanObj = navController.previousBackStackEntry?.arguments?.getParcelable<HafalanDTO>("hafalanData")
                            hafalanObj?.let {
                                HafalanDetailScreen(navController = navController, hafalanData = it)
                            }
                        }
                        
                        composable(route = Screen.TambahHafalanScreen.route){
                            TambahHafalanScreen(navController = navController)
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