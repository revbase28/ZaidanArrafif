package com.revbase.zaidanarrafif.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.revbase.zaidanarrafif.R

@Composable
fun SplashScreen(
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
        ,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.zaidan_educare_logo), 
            contentDescription = "Zaidan Educare Logo"
        )
    }
}