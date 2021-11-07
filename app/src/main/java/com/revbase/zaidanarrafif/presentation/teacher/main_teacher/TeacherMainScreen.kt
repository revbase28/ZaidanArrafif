package com.revbase.zaidanarrafif.presentation.teacher.main_teacher

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Ballot
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Pages
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Ballot
import androidx.compose.material.icons.outlined.MicNone
import androidx.compose.material.icons.outlined.Pages
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.domain.models.BottomNavItem
import com.revbase.zaidanarrafif.presentation.Screen
import com.revbase.zaidanarrafif.presentation.student.main_student.BottomNavBar
import com.revbase.zaidanarrafif.presentation.teacher.jurnal_screen.TeacherJournalScreen
import com.revbase.zaidanarrafif.presentation.teacher.profile_screen.TeacherProfileScreen
import com.revbase.zaidanarrafif.presentation.teacher.main_teacher.component.TeacherTopBar
import com.revbase.zaidanarrafif.presentation.ui.theme.WhiteBackground

@ExperimentalMaterialApi
@Composable
fun TeacherMainScreen(
    viewModel: TeacherMainViewModel = hiltViewModel(),
    mainNavController: NavController,
    savedState: Bundle
) {

    val logoutState = viewModel.logoutState

    LaunchedEffect(key1 = logoutState.value) {
        logoutState.value.let {
            if(it.logoutCode == Constant.LOGOUT_SUCCESS){
                mainNavController.navigate(Screen.LoginScreen.route){
                    popUpTo(mainNavController.graph.findStartDestination().id)
                }
            }
        }
    }

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
                            route = Screen.TeacherProfileScreen.route,
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
            TeacherJournalScreen(mainNavController)
        }

        composable(Screen.TeacherProfileScreen.route) {
            TeacherProfileScreen(navController = mainNavController)
        }
    }
}