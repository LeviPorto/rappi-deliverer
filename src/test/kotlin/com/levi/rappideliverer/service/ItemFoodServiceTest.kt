package com.levi.rappideliverer.service

import com.levi.rappideliverer.RappiDelivererApplication
import com.levi.rappideliverer.domain.ItemFood
import com.levi.rappideliverer.repository.ItemFoodRepository
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

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [RappiDelivererApplication::class])
@ActiveProfiles("test")
class ItemFoodServiceTest {

    @Autowired
    private val service : ItemFoodService? = null

    @Mock
    private val repository : ItemFoodRepository? = null

    companion object {
        private const val ITEM_FOOD_FOOD_ID = 1
        private const val ITEM_FOOD_ORDER_ID = 1
        private const val ITEM_FOOD_COMMENT = "Test Comment"
        private const val ITEM_FOOD_QUANTITY = 10
        private const val ITEM_FOOD_ID_1 = "fefeffefaef2232"
        private const val ITEM_FOOD_ID_2 = "4tg4g44gg4g44g4g"
    }

    @Test
    fun create() {
        BDDMockito.given(repository!!.saveAll(Mockito.anyListOf(ItemFood::class.java))).willReturn(givenItemFoods())
        val createdItemsFood = service!!.batchCreate(givenItemFoods())
        Assert.assertNotNull(createdItemsFood)
    }

    private fun givenItemFoods() : List<ItemFood> = listOf(ItemFood(ITEM_FOOD_FOOD_ID, ITEM_FOOD_ORDER_ID,
            ITEM_FOOD_COMMENT, ITEM_FOOD_QUANTITY, ITEM_FOOD_ID_1), ItemFood(ITEM_FOOD_FOOD_ID, ITEM_FOOD_ORDER_ID,
            ITEM_FOOD_COMMENT, ITEM_FOOD_QUANTITY, ITEM_FOOD_ID_2))

}
