package com.example.giftshopsunmulapp.View

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.giftshopsunmulapp.R
import com.example.giftshopsunmulapp.ViewModels.AvtorizationVM
import com.example.giftshopsunmulapp.ui.theme.darkBlue
import com.example.giftshopsunmulapp.ui.theme.lightBlue
import com.example.giftshopsunmulapp.ui.theme.lightGreen
import com.example.giftshopsunmulapp.ui.theme.white
import io.github.jan.supabase.realtime.Column
import kotlinx.coroutines.launch
import java.lang.reflect.Type

@Composable
fun Avtorization(navHost: NavHostController, viewModel: AvtorizationVM = viewModel())
{
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column (
        Modifier
            .fillMaxSize()
            .background(color = white),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.padding(bottom = 40.dp))
        {
            TextField(value = email.value,
                textStyle = TextStyle(fontSize = 20.sp, color = darkBlue, fontWeight = FontWeight.SemiBold),
                modifier = Modifier.width(330.dp).height(60.dp),
                onValueChange = {newText -> email.value = newText},
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.colors
                    (
                    unfocusedContainerColor = lightBlue,
                    focusedContainerColor = lightBlue,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                placeholder = { Text(text = "Почта", color = darkBlue, fontSize = 20.sp, style = MaterialTheme.typography.bodyLarge) }
            )
        }

        var passwordVisibility: Boolean by remember { mutableStateOf(false) }
        TextField(
            value = password.value,
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = darkBlue,
                fontWeight = FontWeight.SemiBold
            ),
            onValueChange = {newText -> password.value = newText},
            modifier = Modifier
                .padding(bottom = 40.dp).width(330.dp).height(60.dp),
            colors = TextFieldDefaults.colors
            (
                unfocusedContainerColor = lightBlue,
                focusedContainerColor = lightBlue,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(20.dp),
            placeholder = { Text(text = "Пароль", color = darkBlue, fontSize = 20.sp,style = MaterialTheme.typography.bodyLarge) },
            visualTransformation = if(passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation(),
            leadingIcon =
            {
                IconButton(onClick =
                { passwordVisibility = !passwordVisibility })
                {
                    if(passwordVisibility)
                    {
                        Icon(painter = painterResource(id = R.drawable.eye_closed), contentDescription = "")
                    }
                    else{
                        Icon(painter = painterResource(id = R.drawable.eye), contentDescription = "")
                    }
                }
            },
        )
        val flag =  rememberSaveable { mutableStateOf(false) }
        val coroutineScope = rememberCoroutineScope()
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { mutableStateOf(SnackbarHostState()) }
        SnackbarHost(snackbarHostState.value)
        Button(
            onClick =
            {
                coroutineScope.launch{
                    val result = viewModel.Auth(email.value, password.value)
                    flag.value = result
                    if (flag.value)
                    {
                        navHost.navigate("Registration")
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
            modifier = Modifier
                .padding(bottom = 20.dp).width(330.dp).height(60.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = lightGreen,
                containerColor = darkBlue
            )
        ){ Text("Авторизоваться", fontSize = 20.sp, fontWeight = FontWeight.SemiBold,style = MaterialTheme.typography.bodyLarge) }

        Button(
            onClick =
            {
                navHost.navigate("Registration")
            },
            modifier = Modifier
                .padding(bottom = 20.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = darkBlue,
                containerColor = Color.Transparent
            )
        )
        {
            Text("Ещё нет аккаунта?",
            fontSize = 16.sp,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.ExtraBold)
        }
    }
}