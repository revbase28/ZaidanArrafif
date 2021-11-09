package com.revbase.zaidanarrafif.presentation.teacher.profile_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.Screen
import com.revbase.zaidanarrafif.presentation.common_component.LoadingScreen
import com.revbase.zaidanarrafif.presentation.common_component.PrimaryButton
import com.revbase.zaidanarrafif.presentation.teacher.main_teacher.TeacherMainViewModel

@ExperimentalCoilApi
@Composable
fun TeacherProfileScreen(
    viewModel: TeacherProfileViewModel = hiltViewModel(),
    navController: NavController
) {

    val logoutState = viewModel.logoutState


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 32.dp, end = 32.dp, top = 24.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Profile",
            style = TextStyle(
                color = Color(0xFF2C5392),
                fontFamily = Constant.LATO_FONT_FAMILY,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier
            .fillMaxSize()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
                    .offset(y = 50.dp),
                shape = RoundedCornerShape(size = 10.dp),
                elevation = 2.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 64.dp)
                ) {
                    viewModel.teacher?.let {
                        Text(
                            text = it.nama_guru,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 14.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    PrimaryButton(
                        onClick = { viewModel.logout() },
                        text = "Logout" ,
                        textSize = 14.sp
                    )
                }
            }

            Image(painter = rememberImagePainter(
                data = "https://www.seekpng.com/png/detail/349-3499598_portrait-placeholder-placeholder-person.png"),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .align(Alignment.TopCenter)
                    .border(width = 5.dp, color = Color.White, shape = CircleShape)
            )
        }
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