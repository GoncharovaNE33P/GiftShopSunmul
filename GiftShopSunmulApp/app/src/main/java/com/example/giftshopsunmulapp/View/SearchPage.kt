@file:Suppress("UNUSED_EXPRESSION")

package com.example.giftshopsunmulapp.View

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.giftshopsunmulapp.R
import com.example.giftshopsunmulapp.ViewModels.MainViewModel
import com.example.giftshopsunmulapp.ViewModels.SearchPageVM
import com.example.giftshopsunmulapp.model.products
import com.example.giftshopsunmulapp.ui.theme.blue
import com.example.giftshopsunmulapp.ui.theme.lightBlue
import com.example.giftshopsunmulapp.ui.theme.lightGreen
import com.example.giftshopsunmulapp.ui.theme.white


@Composable
fun SearchPage(navHost: NavHostController, viewModel: SearchPageVM = viewModel())
{
    val userEmail = MainViewModel.PrefsHelper.getSharedPreferences().getString("user_email", null)
    println("сейчас пользователь " + userEmail)

    val FP by viewModel.FoundProd.collectAsState()
    val filteredProducts = remember { mutableStateOf<List<products>?>(null) }
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
            /*Search(FP = FP) { filtredProd ->
                filteredProducts.value = filtredProd
            }

            // Отображение результата
            if (filteredProducts.value == null) {
                // Пока ничего не введено, ничего не отображаем
            } else if (filteredProducts.value!!.isNotEmpty()) {
                MainPageContent(navHost, filteredProducts.value!!, viewModelP)
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Ничего не найдено",
                        color = blue,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
*/
        }
    }
    Box()
    {
        Row(modifier = Modifier.align(Alignment.BottomCenter))
        { viewModel.BtNavnBarS(navHost) }
    }
}

/*@Composable
fun Search(FP:List<products>, onSearchResult: (List<products>) -> Unit)
{
    val searchStr = remember { mutableStateOf("") }
    val foundProd = FP.filter { prod  ->  prod.title.contains(searchStr.value!!,ignoreCase = true) }

    onSearchResult(foundProd)

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 40.dp, start = 30.dp, end = 30.dp)
        .clip(
            RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
        )
        .background(lightBlue),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        TextField(value = searchStr.value, onValueChange = { text -> searchStr.value = text},
            placeholder = {
                Text(
                    text = "Поиск...",
                    color = blue,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            colors = TextFieldDefaults.colors
                (
                unfocusedContainerColor = lightBlue,
                focusedContainerColor = lightBlue,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = blue,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily),
            leadingIcon =
            {
                Icon(
                    painter = painterResource(id = R.drawable.searchbar),
                    contentDescription = "",
                    tint = blue,
                    modifier = Modifier.clickable {

                    }
                )
            }
        )
    }
}

@Composable
fun MainPageContent(navHost: NavHostController, FP: List<products>, viewModelP: ProdPageVM)
{
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = white)
    )
    {
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
                    items(FP) { prod ->
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
                                    modifier = Modifier.fillMaxSize()
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
                                                painter = painterResource(id = R.drawable.shopping_basket1),
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
                                    text = "${prod.rating} – ${viewModelP.getRevCount(prod.countRev)}",
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
}*/