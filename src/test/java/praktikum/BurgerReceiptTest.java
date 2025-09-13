package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
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

        // Создаем моки
        bunMock = mock(Bun.class);
        ingredient1Mock = mock(Ingredient.class);
        ingredient2Mock = mock(Ingredient.class);

        // Настройка моков: булка
        when(bunMock.getPrice()).thenReturn(100f);
        when(bunMock.getName()).thenReturn("Test Bun");

        // Настройка моков: ингредиенты
        when(ingredient1Mock.getPrice()).thenReturn(50f);
        when(ingredient1Mock.getName()).thenReturn("Cheese");
        when(ingredient1Mock.getType()).thenReturn(IngredientType.FILLING);

        when(ingredient2Mock.getPrice()).thenReturn(30f);
        when(ingredient2Mock.getName()).thenReturn("Ketchup");
        when(ingredient2Mock.getType()).thenReturn(IngredientType.SAUCE);

        // Собираем бургер
        burger.setBuns(bunMock);
        burger.addIngredient(ingredient1Mock);
        burger.addIngredient(ingredient2Mock);
    }

    @Test
    public void testGetReceiptFullText() {
        // Формируем ожидаемый рецепт
        String expectedReceipt = String.format(
                "(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n",
                bunMock.getName(),
                ingredient1Mock.getType().toString().toLowerCase(), ingredient1Mock.getName(),
                ingredient2Mock.getType().toString().toLowerCase(), ingredient2Mock.getName(),
                bunMock.getName(),
                burger.getPrice()
        );

        String actualReceipt = burger.getReceipt();
        assertEquals("Рецепт бургера сформирован неверно", expectedReceipt, actualReceipt);
    }
}
