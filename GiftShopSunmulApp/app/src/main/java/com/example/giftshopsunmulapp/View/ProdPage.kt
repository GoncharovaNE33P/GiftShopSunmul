package com.example.giftshopsunmulapp.View

import android.view.RoundedCorner
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.giftshopsunmulapp.ViewModels.ProdPageVM
import com.example.giftshopsunmulapp.ui.theme.blue
import com.example.giftshopsunmulapp.ui.theme.darkBlue
import com.example.giftshopsunmulapp.ui.theme.lightBlue
import com.example.giftshopsunmulapp.ui.theme.lightGreen
import com.example.giftshopsunmulapp.ui.theme.typography
import com.example.giftshopsunmulapp.ui.theme.white
import io.github.jan.supabase.realtime.Column
import kotlinx.coroutines.launch
import java.lang.reflect.Type
//@Preview
@Composable
fun ProdPage(navHost: NavHostController, viewModel: ProdPageVM = viewModel())
{
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = white)
    )
    {
        Column(modifier = Modifier
            .background(color = white).padding(top = 50.dp, start = 15.dp)
            .clip(RoundedCornerShape(15.dp)))
        {
            LazyRow(
                modifier = Modifier
                    .background(color = lightBlue)
                    .height(90.dp)
                    .width(380.dp)
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(5)
                {
                    Column(horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip((RoundedCornerShape(5.dp)))
                                .background(color = lightGreen)
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(text = "Категория", fontSize = 12.sp,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.ExtraBold)
                    }
                }
            }
        }
    }
}