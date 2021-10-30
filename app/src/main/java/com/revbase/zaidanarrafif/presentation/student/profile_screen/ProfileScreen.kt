package com.revbase.zaidanarrafif.presentation.student.profile_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.navArgument
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.Screen
import com.revbase.zaidanarrafif.presentation.common_component.LoadingScreen
import com.revbase.zaidanarrafif.presentation.common_component.PrimaryButton
import kotlin.math.log

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navController: NavController
) {
    val logoutState = viewModel.logoutState
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        PrimaryButton(
            onClick = { viewModel.logout() },
            text = "Logout",
            textSize = 14.sp
        )
        if(logoutState.value.isLoading){
            LoadingScreen(isDimmed = true)
        }
    }
    
    LaunchedEffect(key1 = logoutState) {
        logoutState.value?.let {
            if(it.logoutCode == Constant.LOGOUT_SUCCESS){
                navController.navigate(Screen.LoginScreen.route){
                    popUpTo(navController.graph.findStartDestination().id)
                }
            }
        }
    }
}