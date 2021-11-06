package com.revbase.zaidanarrafif.presentation.login_screen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.revbase.zaidanarrafif.R
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.Screen
import com.revbase.zaidanarrafif.presentation.common_component.ErrorText
import com.revbase.zaidanarrafif.presentation.common_component.FailedActionAlertDialog
import com.revbase.zaidanarrafif.presentation.common_component.LoadingScreen
import com.revbase.zaidanarrafif.presentation.common_component.PrimaryButton
import com.revbase.zaidanarrafif.presentation.login_screen.components.CustomPasswordTextField
import com.revbase.zaidanarrafif.presentation.login_screen.components.CustomTextField
import com.revbase.zaidanarrafif.presentation.login_screen.components.SelectRole
import com.revbase.zaidanarrafif.presentation.ui.theme.LightGrey

@ExperimentalMaterialApi
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val activity = (LocalContext.current as? Activity)
    var textFieldUsernameBackground by remember { mutableStateOf(LightGrey) }
    var textFieldPasswordBackground by remember { mutableStateOf(LightGrey) }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isTeacherRoleSelected by remember {
        mutableStateOf(false)
    }
    var isStudentRoleSelected by remember {
        mutableStateOf(false)
    }
    var usernameTextFieldHasError by remember { mutableStateOf(false) }
    var passwordTextFieldHasError by remember { mutableStateOf(false) }
    var roleHasError by remember { mutableStateOf(false) }
    var isFailedDialogShown by remember { mutableStateOf(false) }

    val loginSiswaState = viewModel.loginSiswaState.value
    val loginGuruState = viewModel.loginGuruState.value

    LaunchedEffect(key1 = loginSiswaState, key2 = loginGuruState) {
        if (loginSiswaState.error.isNotBlank() || loginGuruState.error.isNotBlank()) {
            isFailedDialogShown = true
        }

        if (isStudentRoleSelected) {
            loginSiswaState.loginData?.let {
                navController.navigate(Screen.StudentMainScreen.route){
                    popUpTo(Screen.LoginScreen.route){
                        inclusive = true
                    }
                }
            }
        } else if (isTeacherRoleSelected) {
            loginGuruState.loginData?.let {
                navController.navigate(Screen.TeacherMainScreen.route){
                    popUpTo(Screen.LoginScreen.route){
                        inclusive = true
                    }
                }
            }
        }
    }

    BackHandler {
        activity?.finish()
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
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
                        SelectRole(
                            isStudentRoleSelected = isStudentRoleSelected,
                            isTeacherRoleSelected = isTeacherRoleSelected,
                            onStudentSelect = {
                                isStudentRoleSelected = true
                                isTeacherRoleSelected = false
                                roleHasError = false
                            },
                            onTeacherSelect = {
                                isStudentRoleSelected = false
                                isTeacherRoleSelected = true
                                roleHasError = false
                            },
                            hasError = roleHasError
                        )
                        if (roleHasError) {
                            Spacer(modifier = Modifier.height(4.dp))
                            ErrorText(errorText = "Role harus dipilih")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        CustomTextField(
                            color = textFieldUsernameBackground,
                            text = username,
                            hasError = usernameTextFieldHasError,
                            label = "Username",
                            modifier = Modifier
                                .fillMaxWidth(),
                            onFocusChanged = {
                                if (it.isFocused || username.isNotBlank()) {
                                    textFieldUsernameBackground = Color.White
                                } else {
                                    textFieldUsernameBackground = LightGrey
                                }
                            },
                            onTextChanged = {
                                username = it
                                usernameTextFieldHasError = false
                            }
                        )
                        if (usernameTextFieldHasError) {
                            Spacer(modifier = Modifier.height(4.dp))
                            ErrorText(errorText = "Username harus diisi")
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        CustomPasswordTextField(
                            color = textFieldPasswordBackground,
                            text = password,
                            hasError = passwordTextFieldHasError,
                            modifier = Modifier
                                .fillMaxWidth(),
                            onFocusChanged = {
                                if (it.isFocused || password.isNotBlank()) {
                                    textFieldPasswordBackground = Color.White
                                } else {
                                    textFieldPasswordBackground = LightGrey
                                }
                            },
                            onTextChanged = {
                                password = it
                                passwordTextFieldHasError = false
                            }
                        )
                        if (passwordTextFieldHasError) {
                            Spacer(modifier = Modifier.height(4.dp))
                            ErrorText(errorText = "Password harus diisi")
                        }
                        Spacer(modifier = Modifier.height(40.dp))
                        PrimaryButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                if (viewModel.verifyInput(
                                        username = username,
                                        password = password,
                                        isEitherRoleSelected = isStudentRoleSelected || isTeacherRoleSelected
                                    )
                                ) {
                                    if (isStudentRoleSelected)
                                        viewModel.loginSiswa(username, password)
                                    else
                                        viewModel.loginGuru(username, password)
                                } else {
                                    if (username.isBlank()) usernameTextFieldHasError = true
                                    if (password.isBlank()) passwordTextFieldHasError = true
                                    if (!(isStudentRoleSelected || isTeacherRoleSelected)) roleHasError =
                                        true
                                }
                            },
                            text = "Masuk",
                            textSize = 18.sp
                        )
                    }
                }
            }
            if (isFailedDialogShown) {
                FailedActionAlertDialog(
                    onDismiss = { isFailedDialogShown = false },
                    message = "Gagal masuk, coba periksa kembali username atau password dan pastikan kamu terhubung ke internet yaa",
                    animRes = R.raw.lock,
                    title = "Opps, Login gagal.."
                )
            }
        }
        if(loginGuruState.isLoading || loginSiswaState.isLoading){
            LoadingScreen(
                modifier = Modifier.fillMaxSize(),
                isDimmed = true
            )
        }
    }
}