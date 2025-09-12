package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import java.util.Arrays;
import java.util.Collection;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerPriceTest {

    // Параметры теста: цены булки, двух ингредиентов и ожидаемая цена
    private final float bunPrice;
    private final float ingredientPrice1;
    private final float ingredientPrice2;
    private final float expectedPrice;

    public BurgerPriceTest(float bunPrice, float ingredientPrice1, float ingredientPrice2, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice1 = ingredientPrice1;
        this.ingredientPrice2 = ingredientPrice2;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "{index}: Булочка={0}, Ингридиент{1}, Ингридиент2={2} => Цена={3}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, 50f, 50f, 300f},
                {200f, 100f, 0f, 500f},
                {50f, 0f, 0f, 100f},
        });
    }

    private Burger burger;
    private Bun bunMock;
    private Ingredient ingredient1Mock;
    private Ingredient ingredient2Mock;

    @Before
    public void setUp() {
        burger = new Burger();

        // Моки
        bunMock = mock(Bun.class);
        ingredient1Mock = mock(Ingredient.class);
        ingredient2Mock = mock(Ingredient.class);

        //Настройка моков
        when(bunMock.getPrice()).thenReturn(bunPrice);
        when(bunMock.getName()).thenReturn("Test Bun");

        when(ingredient1Mock.getPrice()).thenReturn(ingredientPrice1);
        when(ingredient1Mock.getName()).thenReturn("Ing1");
        when(ingredient1Mock.getType()).thenReturn(IngredientType.FILLING);

        when(ingredient2Mock.getPrice()).thenReturn(ingredientPrice2);
        when(ingredient2Mock.getName()).thenReturn("Ing2");
        when(ingredient2Mock.getType()).thenReturn(IngredientType.SAUCE);

        // Добавляем булку и ингредиенты в булку
        burger.setBuns(bunMock);
        burger.addIngredient(ingredient1Mock);
        burger.addIngredient(ingredient2Mock);

    }

    @Test
    public void testGetPrice() {
        float actualPrice = burger.getPrice();
        assertEquals("Цена бургера рассчитана неверно", expectedPrice, actualPrice, 0.001f);
    }
}
