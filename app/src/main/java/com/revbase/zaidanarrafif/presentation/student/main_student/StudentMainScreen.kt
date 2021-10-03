package com.revbase.zaidanarrafif.presentation.student.main_student

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.common.Constant.PARAM_SURAH_NUMBER
import com.revbase.zaidanarrafif.domain.models.BottomNavItem
import com.revbase.zaidanarrafif.presentation.Screen
import com.revbase.zaidanarrafif.presentation.student.hafalan_screen.HafalanScreen
import com.revbase.zaidanarrafif.presentation.student.jurnal_screen.JurnalScreen
import com.revbase.zaidanarrafif.presentation.student.main_student.component.TopBar
import com.revbase.zaidanarrafif.presentation.student.profile_screen.ProfileScreen
import com.revbase.zaidanarrafif.presentation.student.quran_screen.QuranScreen
import com.revbase.zaidanarrafif.presentation.student.rank_screen.RankScreen
import com.revbase.zaidanarrafif.presentation.student.surah_screen.SurahScreen
import com.revbase.zaidanarrafif.presentation.ui.theme.WhiteBackground

@ExperimentalMaterialApi
@Composable
fun StudentMainScreen(
    mainNavController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TopBar(
            starCount = 25,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .height(56.dp)
        )
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomNavBar(
                    items = listOf(
                        BottomNavItem(
                            name = "Qur'an",
                            route = Screen.QuranScreen.route,
                            selectedIcon = Icons.Filled.MenuBook,
                            unselectedIcon = Icons.Outlined.MenuBook
                        ),
                        BottomNavItem(
                            name = "Jurnal",
                            route = Screen.JurnalScreen.route,
                            selectedIcon = Icons.Filled.Ballot,
                            unselectedIcon = Icons.Outlined.Ballot
                        ),
                        BottomNavItem(
                            name = "Hafalan",
                            route = Screen.HafalanScreen.route,
                            selectedIcon = Icons.Filled.Mic,
                            unselectedIcon = Icons.Outlined.MicNone
                        ),
                        BottomNavItem(
                            name = "Peringkat",
                            route = Screen.RankScreen.route,
                            selectedIcon = Icons.Filled.MilitaryTech,
                            unselectedIcon = Icons.Outlined.MilitaryTech
                        ),
                        BottomNavItem(
                            name = "Profil",
                            route = Screen.ProfileScreen.route,
                            selectedIcon = Icons.Filled.Person,
                            unselectedIcon = Icons.Outlined.Person
                        ),
                    ),
                    navController = navController ,
                    onItemClick = {
                        navController.navigate(it.route)
                    }
                )
            },
            backgroundColor = WhiteBackground
        ) {
            Box(modifier = Modifier.padding(bottom = it.calculateBottomPadding())) {
                StudentNavigation(navController = navController, mainNavController = mainNavController)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun StudentNavigation(
    navController: NavHostController,
    mainNavController: NavController
) {
    NavHost(
        navController = navController,
        startDestination =Screen.QuranScreen.route
    ) {
        composable(route = Screen.QuranScreen.route) {
            QuranScreen(navController = mainNavController)
        }

        composable(route = Screen.JurnalScreen.route) {
            JurnalScreen()
        }

        composable(route = Screen.HafalanScreen.route) {
            HafalanScreen()
        }

        composable(route = Screen.RankScreen.route) {
            RankScreen()
        }

        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen()
        }
    }
}