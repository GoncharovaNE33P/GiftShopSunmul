package com.example.giftshopsunmulapp.View

import android.annotation.SuppressLint
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.giftshopsunmulapp.R
import com.example.giftshopsunmulapp.ViewModels.AvtorizationVM
import com.example.giftshopsunmulapp.domain.utlis.NetworkMonitor
import com.example.giftshopsunmulapp.ui.theme.NameFont1
import com.example.giftshopsunmulapp.ui.theme.blue
import com.example.giftshopsunmulapp.ui.theme.darkBlue
import com.example.giftshopsunmulapp.ui.theme.lightBlue
import com.example.giftshopsunmulapp.ui.theme.lightGreen
import com.example.giftshopsunmulapp.ui.theme.typography
import com.example.giftshopsunmulapp.ui.theme.white
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainPage(navHost: NavHostController,networkMonitor: NetworkMonitor)
{
    val isConnected by networkMonitor.isConnected.collectAsState()
    val currentConnectionState by rememberUpdatedState(isConnected)
println("!!!!!!! " + isConnected)
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(currentConnectionState) {
        if (currentConnectionState) {
            snackbarHostState.showSnackbar("Соединение с Интернетом установлено.")
            delay(1000L) // Задержка для отображения сообщения
            navHost.navigate("Avtorization")
        } else {
            snackbarHostState.showSnackbar("Нет подключения к Интернету!")
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(color = white))
    {
        Column(Modifier.background(color = white).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center)
        {
            Text(
                "SUNMUL",
                fontSize = 90.sp,
                style = typography.bodyMedium,
                color = lightGreen,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Black,
                modifier = Modifier.fillMaxWidth(),
            )

            Text(
                "Подарок, преподнесенный с улыбкой,\nценен вдвойне.",
                fontSize = 16.sp,
                style = typography.bodyLarge,
                color = darkBlue,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.fillMaxWidth(),
            )

            Image(painter = painterResource(id = R.drawable.gift),
                contentDescription = "Logo",
                modifier = Modifier.size(400.dp).padding(top = 40.dp))
        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
        { snackbarData ->
            Snackbar(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                containerColor = lightBlue
            ) {
                Text(
                    text = snackbarData.message,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = blue,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}