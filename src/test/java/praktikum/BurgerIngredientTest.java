package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerIngredientTest {

    private Burger burger;
    private Ingredient ingredient1Mock;
    private Ingredient ingredient2Mock;
    private Ingredient ingredient3Mock;

    @Before
    public void setUp() {
        burger = new Burger();

        // Моки
        ingredient1Mock = mock(Ingredient.class);
        ingredient2Mock = mock(Ingredient.class);
        ingredient3Mock = mock(Ingredient.class);

        when(ingredient1Mock.getName()).thenReturn("Cheese");
        when(ingredient2Mock.getName()).thenReturn("Lettuce");
        when(ingredient3Mock.getName()).thenReturn("Tomato");

        // Типы и цены
        when(ingredient1Mock.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient2Mock.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient3Mock.getType()).thenReturn(IngredientType.SAUCE);

        when(ingredient1Mock.getPrice()).thenReturn(50f);
        when(ingredient2Mock.getPrice()).thenReturn(20f);
        when(ingredient3Mock.getPrice()).thenReturn(10f);
    }

    @Test
    public void testAddIngredient() {
        assertEquals(0, burger.ingredients.size());

        burger.addIngredient(ingredient1Mock);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient1Mock, burger.ingredients.get(0));

        burger.addIngredient(ingredient2Mock);
        assertEquals(2, burger.ingredients.size());
        assertEquals(ingredient2Mock, burger.ingredients.get(1));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredient1Mock);
        burger.addIngredient(ingredient2Mock);
        burger.addIngredient(ingredient3Mock);

        assertEquals(3, burger.ingredients.size());

        burger.removeIngredient(1); // удаляем "Lettuce"
        assertEquals(2, burger.ingredients.size());
        assertEquals(ingredient1Mock, burger.ingredients.get(0));
        assertEquals(ingredient3Mock, burger.ingredients.get(1));
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(ingredient1Mock);
        burger.addIngredient(ingredient2Mock);
        burger.addIngredient(ingredient3Mock);

        // Изначальный порядок: Cheese, Lettuce, Tomato
        burger.moveIngredient(2, 0); // перемещаем Tomato в начало

        assertEquals(ingredient3Mock, burger.ingredients.get(0));
        assertEquals(ingredient1Mock, burger.ingredients.get(1));
        assertEquals(ingredient2Mock, burger.ingredients.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredientInvalidIndex() {
        burger.addIngredient(ingredient1Mock);
        burger.removeIngredient(5); // должен выбросить исключение
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientInvalidIndex() {
        burger.addIngredient(ingredient1Mock);
        burger.moveIngredient(0, 5); // должен выбросить исключение
    }
}