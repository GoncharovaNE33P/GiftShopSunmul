package com.example.giftshopsunmulapp.View

import androidx.compose.animation.AnimatedContent
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.giftshopsunmulapp.R
import com.example.giftshopsunmulapp.ViewModels.MainViewModel
import com.example.giftshopsunmulapp.ui.theme.blue
import com.example.giftshopsunmulapp.ui.theme.lightBlue
import com.example.giftshopsunmulapp.ui.theme.lightGreen
import com.example.giftshopsunmulapp.ui.theme.white


@Composable
fun OrderRegistPage(navHost: NavHostController, viewModel: MainViewModel)
{
    val isDataLoaded by viewModel.isDataLoaded.collectAsState()

    if (!isDataLoaded)
    {
        Box(
            modifier = Modifier.fillMaxSize().background(white),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = blue)
        }
    }
    else
    {
        Box()
        {
            MainPageContentORP()
        }
    }
    Box()
    {
        Row(modifier = Modifier.align(Alignment.BottomCenter))
        { viewModel.BtNavnBarO(navHost) }
    }
}

@Composable
fun MainPageContentORP()
{
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = white)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth().height(90.dp)
                .background(lightGreen),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Column( modifier = Modifier.padding(start = 20.dp, bottom = 10.dp, top = 45.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            )
            {
                Column(
                    modifier = Modifier
                        .background(color = Color.Transparent)
                        .size(30.dp)
                )
                {
                    IconButton(onClick = {  })
                    {
                        Icon(
                            painter = painterResource(id = R.drawable.backprodpage),
                            contentDescription = "",
                            modifier = Modifier.size(30.dp),
                            tint = blue,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.padding(top = 40.dp))
            {
                Text(
                    text = "Оформление заказа",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.W900,
                    color = blue,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
        var selectedMethod by remember { mutableStateOf("Курьер") }
        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier.padding(horizontal = 15.dp))
        {
            Column(modifier = Modifier.background(white).clip(RoundedCornerShape(20.dp)))
            {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(lightGreen)
                        .padding(16.dp)
                )
                {
                    Text(
                        text = "Способ доставки",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                        color = blue
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Переключатель способов доставки
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(blue, RoundedCornerShape(25.dp)) // Синий фон с закруглениями
                            .padding(4.dp), // Внутренние отступы
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // "Пункт выдачи" кнопка
                        Box(
                            modifier = Modifier
                                .weight(1f) // Равная ширина
                                .clip(RoundedCornerShape(25.dp))
                                .background(if (selectedMethod == "Пункт выдачи") lightBlue else Color.Transparent) // Цвет фона при выборе
                                .clickable { selectedMethod = "Пункт выдачи" } // Смена выбора
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Пункт выдачи",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 16.sp,
                                color = if (selectedMethod == "Пункт выдачи") blue else Color.White // Цвет текста
                            )
                        }

                        Spacer(modifier = Modifier.width(4.dp))

                        // "Курьер" кнопка
                        Box(
                            modifier = Modifier
                                .weight(1f) // Равная ширина
                                .clip(RoundedCornerShape(25.dp))
                                .background(if (selectedMethod == "Курьер") lightBlue else Color.Transparent) // Цвет фона при выборе
                                .clickable { selectedMethod = "Курьер" } // Смена выбора
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Курьер",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 16.sp,
                                color = if (selectedMethod == "Курьер") blue else Color.White // Цвет текста
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Информация о доставке
                    AnimatedContent(targetState = selectedMethod, label = "")
                    { method ->
                        Text(
                            text = if (method == "Пункт выдачи")
                            {
                                "На данный момент пункты выдачи отстутствуют в вашем регионе."
                            }
                            else
                            {
                                "г. ..., ул. ..., д. ..., кв. ..., подъезд ..., этаж ..., домофон ..."
                            },
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 16.sp,
                            color = blue // Синий текст
                        )
                    }
                }
            }
        }
    }
}





