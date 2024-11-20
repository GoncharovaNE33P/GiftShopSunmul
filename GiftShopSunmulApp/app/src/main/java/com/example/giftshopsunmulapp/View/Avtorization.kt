package com.example.giftshopsunmulapp.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.giftshopsunmulapp.R
import com.example.giftshopsunmulapp.ViewModels.AvtorizationVM
import com.example.giftshopsunmulapp.ViewModels.MainViewModel
import com.example.giftshopsunmulapp.ui.theme.blue
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
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(Modifier.background(color = white).fillMaxSize().imePadding().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text("Вход в\nаккаунт",
            fontSize = 40.sp,
            color = blue,
            textAlign = TextAlign.Center,
            lineHeight = 45.sp,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.fillMaxWidth().padding(top = 80.dp, bottom = 150.dp)
        )

        Column(modifier = Modifier.padding(bottom = 40.dp))
        {
                TextField(value = email.value,
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        color = darkBlue,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily),
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

        var passwordVisibility: Boolean by remember { mutableStateOf(false) }
        TextField(
            value = password.value,
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = darkBlue,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily),
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

        val flag =  rememberSaveable { mutableStateOf(false) }
        val coroutineScope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }
        Button(
            onClick =
            {
                coroutineScope.launch{
                    val result = viewModel.Auth(email.value, password.value)
                    flag.value = result
                    if (flag.value)
                    {
                       // navHost.navigate("ProdPage")
                        navHost.navigate("SearchPage")
                    }
                    else
                    {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Поля пусты или введены некорректные данные!")
                        }
                    }
                }
            },
            modifier = Modifier.padding(bottom = 20.dp).width(330.dp).height(60.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = lightGreen,
                containerColor = blue
            )
        )
        {
            Text(
            "Авторизоваться",
                fontSize = 20.sp,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.ExtraBold
            )
        }

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
            Text(
                "Ещё нет аккаунта?",
                fontSize = 16.sp,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.ExtraBold
            )
        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) { snackbarData ->
            Snackbar(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
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
    val editor = MainViewModel.PrefsHelper.getSharedPreferences().edit()
    editor.putString("user_email", email.value)
    editor.apply()
}