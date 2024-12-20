package com.example.giftshopsunmulapp.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.giftshopsunmulapp.R
import com.example.giftshopsunmulapp.ViewModels.MainViewModel
import com.example.giftshopsunmulapp.model.categories
import com.example.giftshopsunmulapp.model.products
import com.example.giftshopsunmulapp.ui.theme.blue
import com.example.giftshopsunmulapp.ui.theme.darkBlue
import com.example.giftshopsunmulapp.ui.theme.lightBlue
import com.example.giftshopsunmulapp.ui.theme.lightGreen
import com.example.giftshopsunmulapp.ui.theme.white

@Composable
fun ProdUnderCategory(navHost: NavHostController, viewModel: MainViewModel, categId:String?)
{
    val userEmail = MainViewModel.PrefsHelper.getSharedPreferences().getString("user_email", null)
    println("сейчас пользователь " + userEmail)
    println("сейчас категория " + categId)

    val products by viewModel.ListProd.collectAsState()

    val isDataLoaded by viewModel.isDataLoaded.collectAsState()

    if (!isDataLoaded) {
        Box(
            modifier = Modifier.fillMaxSize().background(white),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = blue)
        }
    } else {
        Box()
        {
            MainPageContentPUC(
                navHost,
                categId,
                products,
                viewModel)
        }
    }
    Box()
    {
        Row(modifier = Modifier.align(Alignment.BottomCenter))
        { viewModel.BtNavnBarP(navHost) }
    }

}

@Composable
fun MainPageContentPUC(navHost: NavHostController, categId:String?,products:List<products>, viewModel: MainViewModel)
{
    val categ =products.find{ it.categories_id == categId }!!.categories!!

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = white)
    )
    {
        Column( modifier = Modifier.padding(start = 20.dp, bottom = 10.dp, top = 30.dp),
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
                IconButton(onClick = { navHost.navigate("ProdPage") })
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
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier.background(white)
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth().height(35.dp)
                    .background(lightGreen)
                    .padding(horizontal = 15.dp)
            )
            {
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.align(Alignment.CenterHorizontally))
                {
                    Text(
                        text = "Список товаров по категории: ${categ.title}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.W900,
                        color = blue,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
        Column( modifier = Modifier
            .background(color = white)
            .padding(top = 10.dp, start = 15.dp)
            .clip(RoundedCornerShape(15.dp)) )
        {
            Column( modifier = Modifier
                .padding(bottom = 60.dp)
                .width(380.dp) )
            {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier.padding(8.dp)
                )
                {
                    items(products.filter { it.categories_id == categId }) { prod ->
                        Column(horizontalAlignment = Alignment.Start,
                            modifier = Modifier.width(180.dp)) {
                            Box(
                                modifier = Modifier
                                    .size(180.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(color = white)
                                    .border(
                                        border = BorderStroke(2.dp, color = lightGreen),
                                        shape = RoundedCornerShape(5.dp)
                                    )
                            )
                            {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(prod.image)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = "",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier.fillMaxSize().clickable {
                                        navHost.navigate("ProdCardPage/${prod.id}")
                                    }
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.width(180.dp))
                            {
                                Text(
                                    text = "${prod.price} ₽",
                                    fontSize = 20.sp,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = blue,
                                    fontWeight = FontWeight.ExtraBold,
                                    modifier = Modifier.weight(1f)
                                )

                                Column( modifier = Modifier
                                    .clip(RoundedCornerShape(5.dp)),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                )
                                {
                                    Column(
                                        modifier = Modifier
                                            .background(color = blue)
                                            .size(24.dp)
                                    )
                                    {
                                        IconButton(onClick = {  })
                                        {
                                            Icon(
                                                painter = painterResource(id = R.drawable.shopping_basket_prod_page),
                                                contentDescription = "",
                                                modifier = Modifier.size(20.dp),
                                                tint = lightGreen,
                                            )
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = prod.title,
                                fontSize = 15.sp,
                                style = MaterialTheme.typography.bodyLarge,
                                color = blue,
                                fontWeight = FontWeight.ExtraBold,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Row(verticalAlignment = Alignment.CenterVertically)
                            {
                                Icon(
                                    painter = painterResource(id = R.drawable.star),
                                    contentDescription = "",
                                    modifier = Modifier.size(18.dp),
                                    tint = blue,
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "${prod.rating} – ${viewModel.getRevCount(prod.countRev)}",
                                    fontSize = 15.sp,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = blue,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}