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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.giftshopsunmulapp.R
import com.example.giftshopsunmulapp.ViewModels.OrderRegistPageVM
import com.example.giftshopsunmulapp.ViewModels.OrdersPageVM
import com.example.giftshopsunmulapp.model.deliveryMethods
import com.example.giftshopsunmulapp.model.orders
import com.example.giftshopsunmulapp.model.paymentMethods
import com.example.giftshopsunmulapp.model.user
import com.example.giftshopsunmulapp.ui.theme.blue
import com.example.giftshopsunmulapp.ui.theme.lightBlue
import com.example.giftshopsunmulapp.ui.theme.lightGreen
import com.example.giftshopsunmulapp.ui.theme.white
import io.ktor.util.valuesOf
import java.text.DecimalFormat


@Composable
fun OrderRegistPage(navHost: NavHostController, viewModel: OrderRegistPageVM, ProdId:String?)
{
    val orders by viewModel.ListOrders.collectAsState()
    val payList by viewModel.ListPay_Methods.collectAsState()
    val delivList by viewModel.List_Deliv_Methods.collectAsState()
    val user by viewModel.ListUser.collectAsState()
    val isDataLoaded by viewModel.isDataLoaded.collectAsState()

    if (!isDataLoaded)
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(white),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = blue)
        }
    }
    else
    {
        Box()
        {
            MainPageContentORP(navHost,orders,payList,delivList,user,viewModel,ProdId)
        }
    }
    Box()
    {
        Row(modifier = Modifier.align(Alignment.BottomCenter))
        { viewModel.BtNavnBarO(navHost) }
    }
}

@Composable
fun MainPageContentORP(navHost: NavHostController, orders: List<orders>, payList: List<paymentMethods>,
                       delivList: List<deliveryMethods>,userList: List<user>,viewModel: OrderRegistPageVM, ProdId:String?)
{
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = white)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
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
                    IconButton(onClick = { navHost.navigate("ProdCardPage/${ProdId}") })
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
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(modifier = Modifier.padding(bottom = 70.dp))
        {
            item(){
                var selectedMethod by remember { mutableStateOf("Курьер") }
                Column(modifier = Modifier.padding(horizontal = 15.dp))
                {
                    Column(modifier = Modifier
                        .background(white)
                        .clip(RoundedCornerShape(20.dp)))
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
                                fontWeight = FontWeight.W900,
                                fontSize = 18.sp,
                                color = blue
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(blue, RoundedCornerShape(20.dp))
                                    .padding(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            )
                            {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(if (selectedMethod == "Пункт выдачи") lightBlue else Color.Transparent)
                                        .clickable { selectedMethod = "Пункт выдачи" }
                                        .padding(vertical = 8.dp),
                                    contentAlignment = Alignment.Center
                                )
                                {
                                    Text(
                                        text = "Пункт выдачи",
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.W900,
                                        fontSize = 16.sp,
                                        color = if (selectedMethod == "Пункт выдачи") blue else Color.White
                                    )
                                }

                                Spacer(modifier = Modifier.width(4.dp))

                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(if (selectedMethod == "Курьер") lightBlue else Color.Transparent)
                                        .clickable { selectedMethod = "Курьер" }
                                        .padding(vertical = 8.dp),
                                    contentAlignment = Alignment.Center
                                )
                                {
                                    Text(
                                        text = "Курьер",
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.W900,
                                        fontSize = 16.sp,
                                        color = if (selectedMethod == "Курьер") blue else Color.White
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))
                            Row(verticalAlignment = Alignment.CenterVertically)
                            {
                                IconButton(onClick = {  })
                                {
                                    Icon(
                                        painter = painterResource(id = R.drawable.map_pin),
                                        contentDescription = "", tint = blue,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(5.dp))
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
                                        fontWeight = FontWeight.W900,
                                        fontSize = 16.sp,
                                        color = blue
                                    )
                                }
                            }
                        }
                    }
                }
            }
            items(userList){ user ->
                Spacer(modifier = Modifier.height(15.dp))
                Column(modifier = Modifier.padding(horizontal = 15.dp))
                {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(blue, RoundedCornerShape(20.dp))
                            .padding(vertical = 10.dp, horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Box(
                            modifier = Modifier
                                .height(30.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(lightBlue)
                                .padding(horizontal = 10.dp),
                            contentAlignment = Alignment.Center
                        )
                        {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center),
                                verticalAlignment = Alignment.CenterVertically)
                            {
                                Spacer(modifier = Modifier.width(10.dp))
                                Icon(
                                    painter = painterResource(id = R.drawable.user_mini),
                                    contentDescription = "", tint = lightGreen,
                                    modifier = Modifier.size(15.dp)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = user.name,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.W900,
                                    fontSize = 10.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.width(100.dp),
                                    color = blue
                                )
                                Spacer(modifier = Modifier.width(65.dp))
                                Text(
                                    text = user.phone,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.W900,
                                    fontSize = 10.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.width(100.dp),
                                    color = blue
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
            item(){
                var selectedOption by remember { mutableStateOf("card") }
                val options = listOf(
                    "cash" to "Наличный расчёт",
                    "card" to "Банковская карта",
                )
                Column(modifier = Modifier.padding(horizontal = 15.dp))
                {
                    Column(modifier = Modifier
                        .background(white)
                        .clip(RoundedCornerShape(20.dp)))
                    {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(lightGreen)
                                .padding(10.dp)
                        )
                        {
                            Text(
                                text = "Способ оплаты",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.W900,
                                fontSize = 18.sp,
                                color = blue
                            )
                            Spacer(modifier = Modifier.height(5.dp))

                            options.forEach{
                                    (value,label) ->
                                CostomRadioButton(
                                    isSelected = selectedOption == value,
                                    label = label,
                                    onClick = { selectedOption = value}
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(modifier = Modifier
                                .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            )
                            {
                                Icon(
                                    painter = painterResource(id =  R.drawable.warning), contentDescription = "",
                                    tint = lightBlue,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "СБП - недоступен в вашем регионе",
                                    color =  lightBlue ,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.W900
                                )
                            }
                        }
                    }
                }
            }
            item(){
                Spacer(modifier = Modifier.height(15.dp))
                var itemCount by remember { mutableStateOf(1) }
                var weightPerItem by remember { mutableStateOf(1) }
                var pricePerItem by remember { mutableStateOf(1) }
                val deliveryCost = 150
                val totalWeight = itemCount * weightPerItem
                val totalItemsPrice = itemCount * pricePerItem
                val totalPrice = totalItemsPrice + deliveryCost

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                        .background(blue, RoundedCornerShape(20.dp))
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Ваш заказ",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.W900,
                        color = lightGreen,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    InfoRow(label = "Количество товаров", value = "$itemCount")
                    InfoRow(label = "Вес, гр.", value = "$totalWeight")
                    InfoRow(label = "Товары", value = formatPrice(totalItemsPrice))
                    InfoRow(label = "Стоимость доставки", value = formatPrice(deliveryCost))
                    InfoRow1(label = "Дата доставки", value = "16.10.2024\n" +
                            "10:00-22:00")

                    Spacer(modifier = Modifier.height(16.dp))

                    Divider(color = lightGreen, thickness = 2.dp)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Итого",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.W900,
                            color = lightGreen,
                            fontSize = 18.sp
                        )
                        Text(
                            text = formatPrice(totalPrice),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.W900,
                            color = lightGreen,
                            fontSize = 18.sp
                        )
                    }
                    Button(
                        onClick = { navHost.navigate("OrdersPage") },
                        modifier = Modifier.fillMaxWidth().height(60.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = blue,
                            containerColor = lightGreen
                        )
                    )
                    {
                        Text(
                            text = "Подтвердить заказ",
                            fontSize = 25.sp,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.W900,
                            color = blue,
                        )
                    }
                    val agreement = buildAnnotatedString {
                        append("Нажимая на кнопку, вы соглашаетесь с ")

                        pushStyle(SpanStyle(color = lightGreen, fontWeight = FontWeight.W900))
                        append("Условиями обработки персональных данных")
                        pop()

                        append(", а также с ")

                        pushStyle(SpanStyle(color = lightGreen, fontWeight = FontWeight.W900))
                        append("Условиями продажи")
                        pop()
                    }

                    Text(
                        text = agreement,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.W900,
                        color = lightBlue,
                        textAlign = TextAlign.Justify,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CostomRadioButton(isSelected: Boolean, label: String, onClick: () -> Unit)
{
    Spacer(modifier = Modifier.height(10.dp))
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Icon(
            painter = painterResource(id =
            if (isSelected) R.drawable.selected
            else R.drawable.not_selected), contentDescription = "",
            tint = blue,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            color =  blue,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.W900
        )
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.W900,
            color = lightGreen,
            fontSize = 16.sp
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.W900,
            color = lightGreen,
            fontSize = 16.sp
        )
    }
}

@Composable
fun InfoRow1(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.W900,
            color = lightGreen,
            fontSize = 16.sp
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.W900,
            color = lightGreen,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

fun formatPrice(price: Int): String {
    val formatter = DecimalFormat("#,### ₽")
    return formatter.format(price)
}





