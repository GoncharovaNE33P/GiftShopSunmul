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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class OrdersPageVM: MainViewModel()
{
    private val _listOrders = MutableStateFlow<List<orders>>(emptyList())
    var ListOrders: StateFlow<List<orders>> = _listOrders

    fun getProdCount(countProd: Int): String
    {
        var text = ""
        if (countProd == 1) { text = "товар" }
        else if (countProd in 2..4) { text = "товара" }
        else { text = "товаров" }
        return "${countProd} ${text}"
    }

    fun getStatus(status: String): String
    {
        var text = ""
        if (status == "Курьер") { text = "Доставка курьером" }
        else if (status == "Пункт выдачи") { text = "Доставка в пункт выдачи" }
        return text
    }

    fun formatDate(inputDate: String): String {
        val date = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        val dayMonth = date.format(DateTimeFormatter.ofPattern("dd.MM"))
        val monthName = date.format(DateTimeFormatter.ofPattern("LLLL", Locale("ru"))).lowercase(Locale("ru"))

        return "$dayMonth $monthName"
    }

    private fun loadOrders()
    {
        viewModelScope.launch {
            try
            {
                val userId = Constants.supabase.auth.currentUserOrNull()!!.id
                println(userId)
                val _ord = Constants.supabase.from("orders").select().decodeList<orders>().sortedByDescending { it.date_order }
                val _prod = Constants.supabase.from("products").select().decodeList<products>()
                val _prod_ord = Constants.supabase.from("prodInOrder").select().decodeList<prodInOrder>()
                val _ord_status = Constants.supabase.from("orderStatus").select().decodeList<orderStatus>()
                val _pay_methods = Constants.supabase.from("paymentMethods").select().decodeList<paymentMethods>()
                val _deliv_methods = Constants.supabase.from("deliveryMethods").select().decodeList<deliveryMethods>()

                _listOrders.value = try {
                    _ord.filter { ord ->
                        val userMatches = ord.users_id == userId
                        userMatches
                    }.map { ord ->
                        ord.ord_status = _ord_status.find { it.id == ord.order_status }
                        ord.pay_methods = _pay_methods.find { it.id == ord.payment_methods }
                        ord.deliv_methods = _deliv_methods.find { it.id == ord.delivery_methods }
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
        loadOrders()
    }
}