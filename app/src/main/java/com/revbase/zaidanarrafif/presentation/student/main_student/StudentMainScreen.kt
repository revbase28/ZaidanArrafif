package com.revbase.zaidanarrafif.presentation.student.main_student

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.Siswa
import com.revbase.zaidanarrafif.domain.models.BottomNavItem
import com.revbase.zaidanarrafif.presentation.Screen
import com.revbase.zaidanarrafif.presentation.student.hafalan_screen.HafalanScreen
import com.revbase.zaidanarrafif.presentation.student.jurnal_screen.JurnalMainScreen
import com.revbase.zaidanarrafif.presentation.student.main_student.component.TopBar
import com.revbase.zaidanarrafif.presentation.student.profile_screen.ProfileScreen
import com.revbase.zaidanarrafif.presentation.student.quran_screen.QuranScreen
import com.revbase.zaidanarrafif.presentation.student.rank_screen.RankScreen
import com.revbase.zaidanarrafif.presentation.ui.theme.WhiteBackground

@ExperimentalMaterialApi
@Composable
fun StudentMainScreen(
    mainNavController: NavController,
    viewModel: StudentMainViewModel = hiltViewModel(),
    savedState: Bundle
) {
    val state = viewModel.studentData
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TopBar(
            starCount = state.value.stars,
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
                            route = Screen.JournalMainScreen.route,
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
                StudentNavigation(navController = navController, mainNavController = mainNavController, savedState = savedState, studentData = state.value)
            }
        }
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun StudentNavigation(
    navController: NavHostController,
    mainNavController: NavController,
    savedState: Bundle,
    studentData: Siswa
) {
    NavHost(
        navController = navController,
        startDestination =Screen.QuranScreen.route
    ) {
        composable(route = Screen.QuranScreen.route) {
            QuranScreen(navController = mainNavController, savedState = savedState, studentData = studentData)
        }

        composable(route = Screen.JournalMainScreen.route) {
            JurnalMainScreen(navController = mainNavController)
        }

        composable(route = Screen.HafalanScreen.route) {
            HafalanScreen()
        }

        composable(route = Screen.RankScreen.route) {
            RankScreen()
        }

        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
    }
}