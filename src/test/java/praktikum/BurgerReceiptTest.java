package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerReceiptTest {

    private Burger burger;
    private Bun bunMock;
    private Ingredient ingredient1Mock;
    private Ingredient ingredient2Mock;

    @Before
    public void setUp() {
        burger = new Burger();

        //Моки
        bunMock = mock(Bun.class);
        ingredient1Mock = mock(Ingredient.class);
        ingredient2Mock = mock(Ingredient.class);

        //Настройка моков
        when(bunMock.getPrice()).thenReturn(100f);
        when(bunMock.getName()).thenReturn("Test Bun");

        when(ingredient1Mock.getPrice()).thenReturn(50f);
        when(ingredient1Mock.getName()).thenReturn("Cheese");
        when(ingredient1Mock.getType()).thenReturn(IngredientType.FILLING);

        when(ingredient2Mock.getPrice()).thenReturn(30f);
        when(ingredient2Mock.getName()).thenReturn("Ketchup");
        when(ingredient2Mock.getType()).thenReturn(IngredientType.SAUCE);

        //Сборка бургера
        burger.setBuns(bunMock);
        burger.addIngredient(ingredient1Mock);
        burger.addIngredient(ingredient2Mock);
    }

    @Test
    public void testGetReceiptContainsBunName() {
        String receipt = burger.getReceipt();
        assertTrue("Чек должен содержать имя булки", receipt.contains("Test Bun"));
    }

    @Test
    public void testGetReceiptContainsIngredients() {
        String receipt = burger.getReceipt();
        assertTrue("Чек должен содержать имя ингредиента Cheese", receipt.contains("Cheese"));
        assertTrue("Чек должен содержать имя ингредиента Ketchup", receipt.contains("Ketchup"));
    }

    @Test
    public void testGetReceiptContainsPrice() {
        String receipt = burger.getReceipt();
        float expectedPrice = 2 * 100f + 50f + 30f; // 2*булка + ингредиенты

        assertEquals("Итоговая цена в чеке должна совпадать", expectedPrice, burger.getPrice(), 0.001f);

        String priceLine = String.format("Price: %.1f", expectedPrice);
        assertTrue("Чек должен содержать итоговую цену", receipt.contains(priceLine));
    }

}
