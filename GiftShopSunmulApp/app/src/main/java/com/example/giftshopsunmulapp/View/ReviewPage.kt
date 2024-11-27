package com.example.giftshopsunmulapp.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.giftshopsunmulapp.R
import com.example.giftshopsunmulapp.ViewModels.BasketPageVM
import com.example.giftshopsunmulapp.ViewModels.MainViewModel
import com.example.giftshopsunmulapp.ViewModels.ReviewPageVM
import com.example.giftshopsunmulapp.model.products
import com.example.giftshopsunmulapp.model.reviews
import com.example.giftshopsunmulapp.ui.theme.blue
import com.example.giftshopsunmulapp.ui.theme.lightBlue
import com.example.giftshopsunmulapp.ui.theme.lightGreen
import com.example.giftshopsunmulapp.ui.theme.white
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ReviewPage(navHost: NavHostController, viewModel: ReviewPageVM, prodId:String?)
{
    val listRev by viewModel.ListRev.collectAsState()
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
            MainPageContentRP(navHost,prodId,listRev)
        }
    }
    Box()
    {
        Row(modifier = Modifier.align(Alignment.BottomCenter))
        { viewModel.BtNavnBarP(navHost) }
    }
}

@Composable
fun MainPageContentRP(navHost: NavHostController, prodId:String?,listRev: List<reviews>)
{
    val orderStatus = remember { mutableStateOf("продан") }
    val rating  = remember { mutableStateOf(4) }
    val date  = remember { mutableStateOf("2024-04-22") }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = white)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth().height(85.dp)
                    .background(blue)
            )
            {
                Column(modifier = Modifier.padding(top = 40.dp).align(Alignment.CenterHorizontally))
                {
                    Text(
                        text = "Отзывы",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.W900,
                        color = lightBlue,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))

        if(listRev.isEmpty())
        {
            Column(modifier = Modifier.align(Alignment.CenterHorizontally))
            {
                Text(
                    text = "Отзывов пока нет",
                    fontSize = 25.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.W900,
                    color = blue
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {},
                modifier = Modifier.width(280.dp).height(50.dp).align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = lightBlue,
                    containerColor = blue
                )
            )
            {
                Text(
                    "Написать первый отзыв",
                    fontSize = 15.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.W900,
                    color = lightBlue
                )
            }
        }
        else
        {
            Spacer(modifier = Modifier.height(30.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Заголовок с количеством отзывов
                Text(text = "Отзывы: 5000", style = MaterialTheme.typography.bodyLarge)

                // Кнопка добавления отзыва
                AddReviewButton(orderStatus = orderStatus.value)

                // Отзыв с рейтингом и датой
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RatingStars(rating = rating.value) // Звёзды
                    Spacer(modifier = Modifier.width(8.dp))
                    FormattedDate(dateString = date.value) // Дата
                }

                // Комментарий
                Text(text = "Комментарий: Отличный товар!", style = MaterialTheme.typography.bodyLarge)
            }
            /*LazyColumn(verticalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier.padding(bottom = 70.dp, start = 20.dp, end = 20.dp).background(white))
            {
                items(listRev){ rev ->
                    Column(modifier = Modifier.clip(RoundedCornerShape(15.dp)))
                    {

                    }
                }
            }*/
        }
    }
}

@Composable
fun AddReviewButton(orderStatus: String)
{
    val isButtonEnabled = orderStatus == "продан" // Условие активации кнопки

    Button(
        onClick = { /* Действие при нажатии */ },
        enabled = isButtonEnabled // Управляем доступностью кнопки
    )
    {
        Text(text = "+ Добавить")
    }
}

@Composable
fun RatingStars(rating: Int)
{
    Row()
    {
        for (i in 1..5)
        {
            Icon(
                painter = if (i <= rating) painterResource(R.drawable.star) else painterResource(R.drawable.sparborder),
                contentDescription = null,
                tint = if (i <= rating) blue else blue // Закрашенные и незакрашенные звезды
            )
        }
    }
}

@Composable
fun FormattedDate(dateString: String)
{
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("LLLL dd.MM.yyyy", Locale("ru"))
    val date: Date = inputFormat.parse(dateString) ?: Date()
    val formattedDate = outputFormat.format(date).capitalize(Locale.ROOT)

    Text(text = formattedDate)
}