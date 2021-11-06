package com.revbase.zaidanarrafif.presentation.teacher.teacher_main_screen

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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.revbase.zaidanarrafif.domain.models.BottomNavItem
import com.revbase.zaidanarrafif.presentation.Screen
import com.revbase.zaidanarrafif.presentation.student.hafalan_screen.HafalanScreen
import com.revbase.zaidanarrafif.presentation.student.jurnal_screen.JurnalMainScreen
import com.revbase.zaidanarrafif.presentation.student.main_student.BottomNavBar
import com.revbase.zaidanarrafif.presentation.student.main_student.StudentNavigation
import com.revbase.zaidanarrafif.presentation.student.main_student.component.TopBar
import com.revbase.zaidanarrafif.presentation.student.profile_screen.ProfileScreen
import com.revbase.zaidanarrafif.presentation.student.quran_screen.QuranScreen
import com.revbase.zaidanarrafif.presentation.student.rank_screen.RankScreen
import com.revbase.zaidanarrafif.presentation.teacher.jurnal_screen.TeacherJournalScreen
import com.revbase.zaidanarrafif.presentation.teacher.teacher_main_screen.component.TeacherTopBar
import com.revbase.zaidanarrafif.presentation.ui.theme.WhiteBackground

@ExperimentalMaterialApi
@Composable
fun TeacherMainScreen(
    mainNavController: NavController,
    savedState: Bundle
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TeacherTopBar(
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
                            name = "Profil",
                            route = Screen.QuranScreen.route,
                            selectedIcon = Icons.Filled.Person,
                            unselectedIcon = Icons.Outlined.Person
                        ),
                        BottomNavItem(
                            name = "Jurnal",
                            route = Screen.TeacherJournalScreen.route,
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
                            name = "Daftar Siswa",
                            route = Screen.ProfileScreen.route,
                            selectedIcon = Icons.Filled.Pages,
                            unselectedIcon = Icons.Outlined.Pages
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
                TeacherNavigation(navController = navController, mainNavController = mainNavController, savedState = savedState)
            }
        }
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun TeacherNavigation(
    navController: NavHostController,
    mainNavController: NavController,
    savedState: Bundle
) {
    NavHost(
        navController = navController,
        startDestination = "teacher_journal_screen"
    ) {
        composable("teacher_journal_screen") {
            TeacherJournalScreen()
        }
    }
}