package com.revbase.zaidanarrafif.presentation.login_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.revbase.zaidanarrafif.R
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.common.Constant.LATO_FONT_FAMILY
import com.revbase.zaidanarrafif.data.remote.dto.Text
import com.revbase.zaidanarrafif.presentation.Screen
import com.revbase.zaidanarrafif.presentation.common_component.BlueButton
import com.revbase.zaidanarrafif.presentation.login_screen.components.CustomPasswordTextField
import com.revbase.zaidanarrafif.presentation.login_screen.components.CustomTextField
import com.revbase.zaidanarrafif.presentation.login_screen.components.SelectRole
import com.revbase.zaidanarrafif.presentation.ui.theme.Blue
import com.revbase.zaidanarrafif.presentation.ui.theme.LightGrey

@ExperimentalMaterialApi
@Composable
fun LoginScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.zaidan_educare_logo),
                contentDescription = "Zaidan Arrafif Logo"
            )
            Spacer(modifier = Modifier.height(32.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                backgroundColor = Color.White,
                elevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    var textFieldUsernameBackground by remember { mutableStateOf(LightGrey) }
                    var textFieldPasswordBackground by remember { mutableStateOf(LightGrey) }
                    var username by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }

                    SelectRole()
                    Spacer(modifier = Modifier.height(16.dp))
                    CustomTextField(
                        color = textFieldUsernameBackground,
                        text = username,
                        label = "Username",
                        modifier = Modifier
                            .fillMaxWidth(),
                        onFocusChanged = {
                            if(it.isFocused || username.isNotBlank()) {
                                textFieldUsernameBackground = Color.White
                            } else {
                                textFieldUsernameBackground = LightGrey
                            }
                        },
                        onTextChanged = {
                            username = it
                        }
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    CustomPasswordTextField(
                        color = textFieldPasswordBackground,
                        text = password,
                        modifier = Modifier
                            .fillMaxWidth(),
                        onFocusChanged = {
                            if(it.isFocused || password.isNotBlank()) {
                                textFieldPasswordBackground = Color.White
                            } else {
                                textFieldPasswordBackground = LightGrey
                            }
                        },
                        onTextChanged = {
                            password = it
                        }
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    BlueButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            navController.navigate(Screen.StudentMainScreen.route)
                        },
                        text = "Masuk",
                        textSize = 18.sp
                    )
                }
            }
        }
    }
}