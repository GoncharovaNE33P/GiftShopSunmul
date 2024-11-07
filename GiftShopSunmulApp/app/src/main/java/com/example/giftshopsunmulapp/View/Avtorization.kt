package com.example.giftshopsunmulapp.View

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.giftshopsunmulapp.ViewModels.AvtorizationVM
import com.example.giftshopsunmulapp.ui.theme.white
import kotlinx.coroutines.launch

@Composable
fun Avtorization(navHost: NavHostController, viewModel: AvtorizationVM = viewModel())
{
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column (
        Modifier.fillMaxSize().background(color = white),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.padding(bottom = 20.dp))
        {
            TextField(value = email.value,
                textStyle = TextStyle(fontSize = 20.sp, color = Color.White),
                onValueChange = {newText -> email.value = newText},
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .border
                        (
                        width = 2.dp,
                        color = Color(0xFF6297fb),shape = RoundedCornerShape(10.dp)
                    ),
                colors = TextFieldDefaults.colors
                    (
                    unfocusedContainerColor = Color(0xFFbcd2fd),
                    focusedContainerColor = Color(0xFFaabee5),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                placeholder = { Text(text = "example@mail.com", color = Color.Gray) }
            )
        }

        var passwordVisibility: Boolean by remember { mutableStateOf(false) }
        TextField(
            value = password.value,
            textStyle = TextStyle(fontSize = 20.sp, color = Color.White),
            visualTransformation = if(passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation(),
            leadingIcon =
            {
                IconButton(onClick =
                { passwordVisibility = !passwordVisibility })
                {
                    /*if(passwordVisibility)
                    {
                       Icon(painter = painterResource(id = R.drawable.baseline_close_24), contentDescription = "")
                    }
                    else{
                       Icon(painter = painterResource(id = R.drawable.baseline_remove_red_eye_24), contentDescription = "")
                    }*/
                }
            },
            onValueChange = {newText -> password.value = newText},
            modifier = Modifier
                .padding(bottom = 25.dp)
                .border
                    (
                    width = 2.dp,
                    color = Color(0xFF6297fb),shape = RoundedCornerShape(10.dp)
                ),
            colors = TextFieldDefaults.colors
                (
                unfocusedContainerColor = Color(0xFFbcd2fd),
                focusedContainerColor = Color(0xFFaabee5),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(10.dp)
        )
        val flag =  rememberSaveable { mutableStateOf(false) }
        val coroutineScope = rememberCoroutineScope()
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { mutableStateOf(SnackbarHostState()) }
        Button(
            onClick =
            {
                coroutineScope.launch{
                    val result = viewModel.Auth(email.value, password.value)
                    flag.value = result
                    if (flag.value)
                    {
                        navHost.navigate("")
                        scope.launch {
                            snackbarHostState.value.showSnackbar("Добро пожаловать!")
                        }
                    }
                    else
                    {
                        scope.launch {
                            snackbarHostState.value.showSnackbar("Поля пусты или введенны некорректные данные!")
                        }
                    }
                }
            },
            modifier = Modifier.padding(bottom = 25.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color(0xFF6297fb)
            )
        )
        {
            Text("Авторизоваться", fontSize = 25.sp)
        }
    }
}