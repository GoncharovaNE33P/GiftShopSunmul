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

class OrderRegistPageVM: MainViewModel()
{
    private val _listOrders = MutableStateFlow<List<orders>>(emptyList())
    var ListOrders: StateFlow<List<orders>> = _listOrders
    private val _listDeliv_Methods = MutableStateFlow<List<deliveryMethods>>(emptyList())
    var List_Deliv_Methods: StateFlow<List<deliveryMethods>> = _listDeliv_Methods
    private val _listPay_Methods = MutableStateFlow<List<paymentMethods>>(emptyList())
    var ListPay_Methods: StateFlow<List<paymentMethods>> = _listPay_Methods
    private val _listUser= MutableStateFlow<List<user>>(emptyList())
    var ListUser: StateFlow<List<user>> = _listUser

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
        val monthName = date.format(DateTimeFormatter.ofPattern("LLLL", Locale("ru"))).capitalize(
            Locale.ROOT)

        return "$dayMonth $monthName"
    }

    private fun loadLists()
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

                _listDeliv_Methods.value = Constants.supabase.from("deliveryMethods").select().decodeList<deliveryMethods>()
                _listPay_Methods.value = Constants.supabase.from("paymentMethods").select().decodeList<paymentMethods>()
                _listUser.value = Constants.supabase.from("user").select().decodeList<user>().filter { it.id == userId }
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
        loadLists()
    }
}