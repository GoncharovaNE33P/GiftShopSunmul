package com.example.giftshopsunmulapp.ViewModels

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.giftshopsunmulapp.domain.utlis.Constants
import com.example.giftshopsunmulapp.model.categories
import com.example.giftshopsunmulapp.model.countryProd
import com.example.giftshopsunmulapp.model.deliveryMethods
import com.example.giftshopsunmulapp.model.history
import com.example.giftshopsunmulapp.model.orderStatus
import com.example.giftshopsunmulapp.model.orders
import com.example.giftshopsunmulapp.model.paymentMethods
import com.example.giftshopsunmulapp.model.prodInOrder
import com.example.giftshopsunmulapp.model.products
import com.example.giftshopsunmulapp.model.productsStatus
import com.example.giftshopsunmulapp.model.shopCart
import com.example.giftshopsunmulapp.model.user
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistoryPageVM: MainViewModel()
{
    private val _listHistory = MutableStateFlow<List<orders>>(emptyList())
    var ListHistory: StateFlow<List<orders>> = _listHistory

    fun getProdCount(countProd: Int): String
    {
        var text = ""
        if (countProd == 1) { text = "товар" }
        else if (countProd in 2..4) { text = "товара" }
        else { text = "товаров" }
        return "${countProd} ${text}"
    }

    private fun loadHistory()
    {
        viewModelScope.launch {
            try
            {
                val userId = Constants.supabase.auth.currentUserOrNull()!!.id
                println(userId)
                val _ord = Constants.supabase.from("orders").select().decodeList<orders>()
                val _prod = Constants.supabase.from("products").select().decodeList<products>()
                val _prod_ord = Constants.supabase.from("prodInOrder").select().decodeList<prodInOrder>()
                val _ord_status = Constants.supabase.from("orderStatus").select().decodeList<orderStatus>()
                val _pay_methods = Constants.supabase.from("paymentMethods").select().decodeList<paymentMethods>()
                val _deliv_methods = Constants.supabase.from("deliveryMethods").select().decodeList<deliveryMethods>()

                _listHistory.value = try {
                    _ord.filter { ord ->
                        val userMatches = ord.users_id == userId
                        val statusMatches = _ord_status.any { status ->
                            status.title == "Продан" && status.id == ord.order_status
                        }
                        userMatches && statusMatches
                    }.map { ord ->
                        // Находим связанные записи из prodInOrder
                        val relatedProdInOrder = _prod_ord.filter { it.orders_id == ord.id }

                        // Находим продукты, связанные с этим заказом
                        val relatedProducts = relatedProdInOrder.mapNotNull { prodInOrd ->
                            _prod.find { it.id == prodInOrd.products_id }
                        }

                        // Обогащаем заказ данными о продуктах
                        ord.copy(
                            products = relatedProducts // Добавляем список продуктов
                        ).also {
                            println("Заказ: ${it.article}, Продукты: ${it.products.map { product -> product.title }}")
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    emptyList()
                }
            }
            catch (e:Exception)
            {
                Log.e("HistoryPageVM", "Error fetching data: ${e.localizedMessage}")
            }
        }
    }
    init
    {
        loadHistory()
    }
}