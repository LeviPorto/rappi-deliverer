package com.levi.rappideliverer.service

import com.levi.rappideliverer.RappiDelivererApplication
import com.levi.rappideliverer.domain.ItemFood
import com.levi.rappideliverer.domain.Order
import com.levi.rappideliverer.domain.enumeration.DeliveryStatus
import com.levi.rappideliverer.dto.OrderWithItemsFoodDTO
import com.levi.rappideliverer.publisher.OrderPublisher
import com.levi.rappideliverer.repository.OrderRepository
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import java.time.Instant
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [RappiDelivererApplication::class])
@ActiveProfiles("test")
class OrderServiceTest {

    @Autowired
    private val service : OrderService? = null

    @Mock
    private val itemFoodService : ItemFoodService? = null

    @Mock
    private val repository : OrderRepository? = null

    @Mock
    private val publisher : OrderPublisher? = null

    companion object {
        private const val ORDER_USER_ID = 1
        private const val ORDER_RESTAURANT_ID = 1
        private val ORDER_DELIVERY_DATE = Instant.now()
        private val ORDER_DELIVERY_STATUS = DeliveryStatus.DELIVERED
        private const val ORDER_DELIVERY_ADDRESS = "Test Address"
        private const val ORDER_ID = "4tg4g44gg4g44g4g"
        private const val ORDER_ID_1 = "ceefewfeewfee"
        private const val ORDER_ID_2 = "r33r3f3frrvv"

        private const val ITEM_FOOD_FOOD_ID = 1
        private const val ITEM_FOOD_ORDER_ID = 1
        private const val ITEM_FOOD_COMMENT = "Test Comment"
        private const val ITEM_FOOD_QUANTITY = 10
        private const val ITEM_FOOD_ID_1 = "fefeffefaef2232"
        private const val ITEM_FOOD_ID_2 = "4tg4g44gg4g44g4g"
    }

    @Test
    fun create() {
        BDDMockito.given(repository!!.save(Mockito.any(Order::class.java))).willReturn(givenOrder())
        val createdOrder = service!!.create(givenOrder())
        Assert.assertNotNull(createdOrder)
    }

    @Test
    fun createOrderWithItemsFood() {
        BDDMockito.given(itemFoodService!!.batchCreate(Mockito.anyListOf(ItemFood::class.java))).willReturn(givenItemFoods())
        val createdOrderWithItemsFood = service!!.createOrderWithItemsFood(givenOrderWithItemsFoodDTO())
        Assert.assertNotNull(createdOrderWithItemsFood)
    }

    @Test
    fun retrieveById() {
        BDDMockito.given(repository!!.findById(Optional.of(ORDER_ID).get())).willReturn(givenOptionalOrder())
        val order = service!!.retrieveById(ORDER_ID)
        Assert.assertNotNull(order)
    }

    @Test
    fun retrieveDeliveredByUser() {
        BDDMockito.given(repository!!.findByUserIdAndAndDeliveryStatus(ORDER_USER_ID, DeliveryStatus.DELIVERED)).willReturn(givenOrders())
        val deliveredOrdersByUser = service!!.retrieveDeliveredByUser(ORDER_USER_ID)
        Assert.assertNotNull(deliveredOrdersByUser)
    }

    private fun givenOptionalOrder() : Optional<Order> = Optional.of(Order(ORDER_USER_ID, ORDER_RESTAURANT_ID, ORDER_DELIVERY_DATE,
            ORDER_DELIVERY_STATUS, ORDER_DELIVERY_ADDRESS, ORDER_ID))

    private fun givenOrder() : Order = Order(ORDER_USER_ID, ORDER_RESTAURANT_ID, ORDER_DELIVERY_DATE,
            ORDER_DELIVERY_STATUS, ORDER_DELIVERY_ADDRESS, ORDER_ID)

    private fun givenOrders() : List<Order> = listOf(Order(ORDER_USER_ID, ORDER_RESTAURANT_ID, ORDER_DELIVERY_DATE,
            ORDER_DELIVERY_STATUS, ORDER_DELIVERY_ADDRESS, ORDER_ID_1), Order(ORDER_USER_ID, ORDER_RESTAURANT_ID, ORDER_DELIVERY_DATE,
            ORDER_DELIVERY_STATUS, ORDER_DELIVERY_ADDRESS, ORDER_ID_2))

    private fun givenItemFoods() : List<ItemFood> = listOf(ItemFood(ITEM_FOOD_FOOD_ID, ITEM_FOOD_ORDER_ID,
            ITEM_FOOD_COMMENT, ITEM_FOOD_QUANTITY, ITEM_FOOD_ID_1), ItemFood(ITEM_FOOD_FOOD_ID, ITEM_FOOD_ORDER_ID,
            ITEM_FOOD_COMMENT, ITEM_FOOD_QUANTITY, ITEM_FOOD_ID_2))

    private fun givenOrderWithItemsFoodDTO() : OrderWithItemsFoodDTO = OrderWithItemsFoodDTO(givenOrder(),
            givenItemFoods())

}
