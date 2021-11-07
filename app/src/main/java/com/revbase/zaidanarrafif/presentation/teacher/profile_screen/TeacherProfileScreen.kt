package com.revbase.zaidanarrafif.presentation.teacher.profile_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.Screen
import com.revbase.zaidanarrafif.presentation.common_component.PrimaryButton
import com.revbase.zaidanarrafif.presentation.teacher.main_teacher.TeacherMainViewModel

@Composable
fun TeacherProfileScreen(
    viewModel: TeacherProfileViewModel = hiltViewModel(),
    navController: NavController
) {

    val logoutState = viewModel.logoutState

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "TeacherScreen")
        PrimaryButton(
            onClick = { viewModel.logout() },
            text = "Logout" ,
            textSize = 14.sp
        )
    }

    LaunchedEffect(key1 = logoutState.value) {
        logoutState.value.let {
            if(it.logoutCode == Constant.LOGOUT_SUCCESS){
                navController.navigate(Screen.LoginScreen.route){
                    popUpTo(navController.graph.findStartDestination().id)
                }
            }
        }
    }
}