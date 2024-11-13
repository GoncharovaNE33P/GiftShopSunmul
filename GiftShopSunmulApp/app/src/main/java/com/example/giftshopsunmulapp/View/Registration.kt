package com.example.giftshopsunmulapp.View

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.giftshopsunmulapp.R
import com.example.giftshopsunmulapp.ViewModels.AvtorizationVM
import com.example.giftshopsunmulapp.ViewModels.RegistrationVM
import com.example.giftshopsunmulapp.ui.theme.blue
import com.example.giftshopsunmulapp.ui.theme.darkBlue
import com.example.giftshopsunmulapp.ui.theme.lightBlue
import com.example.giftshopsunmulapp.ui.theme.lightGreen
import com.example.giftshopsunmulapp.ui.theme.white
import kotlinx.coroutines.launch

@Composable
fun Registration(navHost: NavHostController, viewModel: RegistrationVM = viewModel())
{
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    var passwordVisibility: Boolean by remember { mutableStateOf(false) }
    val flag =  rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { snackbarData ->
                Snackbar(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    containerColor = lightBlue
                ) {
                    Text(
                        text = snackbarData.visuals.message,
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = darkBlue,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                Modifier
                    .background(color = white)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Создание\nаккаунта",
                    fontSize = 40.sp,
                    color = blue,
                    textAlign = TextAlign.Center,
                    lineHeight = 45.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp, bottom = 30.dp)
                )

                Column(modifier = Modifier.padding(bottom = 30.dp)) {
                    TextField(
                        value = email.value,
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            color = darkBlue,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                        ),
                        modifier = Modifier
                            .width(330.dp)
                            .height(60.dp),
                        onValueChange = { newText -> email.value = newText },
                        shape = RoundedCornerShape(20.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = lightBlue,
                            focusedContainerColor = lightBlue,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        placeholder = {
                            Text(
                                text = "Имя",
                                color = darkBlue,
                                fontSize = 20.sp,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                    )
                }
                Column(modifier = Modifier.padding(bottom = 30.dp)) {
                    TextField(
                        value = email.value,
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            color = darkBlue,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                        ),
                        modifier = Modifier
                            .width(330.dp)
                            .height(60.dp),
                        onValueChange = { newText -> email.value = newText },
                        shape = RoundedCornerShape(20.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = lightBlue,
                            focusedContainerColor = lightBlue,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        placeholder = {
                            Text(
                                text = "Телефон",
                                color = darkBlue,
                                fontSize = 20.sp,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                    )
                }
                Column(modifier = Modifier.padding(bottom = 30.dp)) {
                    TextField(
                        value = email.value,
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            color = darkBlue,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                        ),
                        modifier = Modifier
                            .width(330.dp)
                            .height(60.dp),
                        onValueChange = { newText -> email.value = newText },
                        shape = RoundedCornerShape(20.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = lightBlue,
                            focusedContainerColor = lightBlue,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        placeholder = {
                            Text(
                                text = "Почта",
                                color = darkBlue,
                                fontSize = 20.sp,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                    )
                }
                Column(modifier = Modifier.padding(bottom = 30.dp)) {
                    TextField(
                        value = email.value,
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            color = darkBlue,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                        ),
                        modifier = Modifier
                            .width(330.dp)
                            .height(60.dp),
                        onValueChange = { newText -> email.value = newText },
                        shape = RoundedCornerShape(20.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = lightBlue,
                            focusedContainerColor = lightBlue,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        placeholder = {
                            Text(
                                text = "ДР",
                                color = darkBlue,
                                fontSize = 20.sp,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                    )
                }
                Column(modifier = Modifier.padding(bottom = 30.dp)) {
                    TextField(
                        value = password.value,
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            color = darkBlue,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily),
                        onValueChange = {newText -> password.value = newText},
                        modifier = Modifier.width(330.dp).height(60.dp),
                        colors = TextFieldDefaults.colors
                            (
                            unfocusedContainerColor = lightBlue,
                            focusedContainerColor = lightBlue,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(20.dp),
                        placeholder = {
                            Text(
                                text = "Пароль",
                                color = darkBlue,
                                fontSize = 20.sp,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.ExtraBold
                            )
                        },
                        visualTransformation = if(passwordVisibility) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        trailingIcon =
                        {
                            IconButton(onClick = { passwordVisibility = !passwordVisibility })
                            {
                                Icon(
                                    painter = if (passwordVisibility) painterResource(id = R.drawable.eye_closed)
                                    else painterResource(id = R.drawable.eye),
                                    contentDescription = "",
                                    modifier = Modifier.padding(end = 10.dp)
                                )
                            }
                        },
                    )
                }
                Column(modifier = Modifier.padding(bottom = 30.dp)) {
                    TextField(
                        value = password.value,
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            color = darkBlue,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily),
                        onValueChange = {newText -> password.value = newText},
                        modifier = Modifier.width(330.dp).height(60.dp),
                        colors = TextFieldDefaults.colors
                            (
                            unfocusedContainerColor = lightBlue,
                            focusedContainerColor = lightBlue,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(20.dp),
                        placeholder = {
                            Text(
                                text = "Повторить пароль",
                                color = darkBlue,
                                fontSize = 20.sp,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.ExtraBold
                            )
                        },
                        visualTransformation = if(passwordVisibility) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        trailingIcon =
                        {
                            IconButton(onClick = { passwordVisibility = !passwordVisibility })
                            {
                                Icon(
                                    painter = if (passwordVisibility) painterResource(id = R.drawable.eye_closed)
                                    else painterResource(id = R.drawable.eye),
                                    contentDescription = "",
                                    modifier = Modifier.padding(end = 10.dp)
                                )
                            }
                        },
                    )
                }

                Button(
                    onClick = {
                        coroutineScope.launch {
                            val result = viewModel.Auth(email.value, password.value)
                            if (result) {
                                navHost.navigate("ProdPage")
                            } else {
                                snackbarHostState.showSnackbar("Поля пусты или введены некорректные данные!")
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .width(330.dp)
                        .height(60.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = lightGreen,
                        containerColor = blue
                    )
                ) {
                    Text(
                        "Зарегистрироваться",
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                Button(
                    onClick = { navHost.navigate("Avtorization") },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = darkBlue,
                        containerColor = Color.Transparent
                    )
                ) {
                    Text(
                        "Уже имеете аккаунт?",
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}