package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerIngredientTest {

    // Индексы ингредиентов по смыслу
    private static final int CHEESE_INDEX = 0;
    private static final int LETTUCE_INDEX = 1;
    private static final int TOMATO_INDEX = 2;
    private static final int INVALID_INDEX = 5;

    private Burger burger;
    private Ingredient cheeseMock;
    private Ingredient lettuceMock;
    private Ingredient tomatoMock;

    @Before
    public void setUp() {
        burger = new Burger();

        // Создаем моки ингредиентов
        cheeseMock = mock(Ingredient.class);
        lettuceMock = mock(Ingredient.class);
        tomatoMock = mock(Ingredient.class);

        // Настройка имен
        when(cheeseMock.getName()).thenReturn("Cheese");
        when(lettuceMock.getName()).thenReturn("Lettuce");
        when(tomatoMock.getName()).thenReturn("Tomato");

        // Настройка типов
        when(cheeseMock.getType()).thenReturn(IngredientType.FILLING);
        when(lettuceMock.getType()).thenReturn(IngredientType.FILLING);
        when(tomatoMock.getType()).thenReturn(IngredientType.SAUCE);

        // Настройка цен
        when(cheeseMock.getPrice()).thenReturn(50f);
        when(lettuceMock.getPrice()).thenReturn(20f);
        when(tomatoMock.getPrice()).thenReturn(10f);
    }

    // Добавление ингредиентов
    @Test
    public void testAddCheese() {
        burger.addIngredient(cheeseMock);
        assertEquals(cheeseMock, burger.ingredients.get(CHEESE_INDEX));
    }

    @Test
    public void testAddLettuce() {
        burger.addIngredient(cheeseMock);
        burger.addIngredient(lettuceMock);
        assertEquals(lettuceMock, burger.ingredients.get(LETTUCE_INDEX));
    }

    @Test
    public void testAddTomato() {
        burger.addIngredient(cheeseMock);
        burger.addIngredient(lettuceMock);
        burger.addIngredient(tomatoMock);
        assertEquals(tomatoMock, burger.ingredients.get(TOMATO_INDEX));
    }

    // Удаление ингредиентов
    @Test
    public void testRemoveLettuce() {
        burger.addIngredient(cheeseMock);
        burger.addIngredient(lettuceMock);
        burger.addIngredient(tomatoMock);

        // Удаляем Lettuce
        burger.removeIngredient(LETTUCE_INDEX);

        // После удаления список: [cheeseMock, tomatoMock]
        assertEquals(tomatoMock, burger.ingredients.get(LETTUCE_INDEX));
    }

    @Test
    public void testRemoveCheese() {
        burger.addIngredient(cheeseMock);
        burger.addIngredient(lettuceMock);

        // Удаляем Cheese
        burger.removeIngredient(CHEESE_INDEX);

        // После удаления список: [lettuceMock]
        assertEquals(lettuceMock, burger.ingredients.get(CHEESE_INDEX));
    }

    // Перемещение ингредиентов
    @Test
    public void testMoveTomatoToFirst() {
        burger.addIngredient(cheeseMock);
        burger.addIngredient(lettuceMock);
        burger.addIngredient(tomatoMock);

        // Перемещаем Tomato на первое место
        burger.moveIngredient(TOMATO_INDEX, CHEESE_INDEX);

        // Новый порядок: [tomatoMock, cheeseMock, lettuceMock]
        assertEquals(tomatoMock, burger.ingredients.get(CHEESE_INDEX));
    }

    @Test
    public void testMoveCheeseToSecond() {
        burger.addIngredient(cheeseMock);
        burger.addIngredient(lettuceMock);

        // Перемещаем Cheese на второе место
        burger.moveIngredient(CHEESE_INDEX, LETTUCE_INDEX);

        // Новый порядок: [lettuceMock, cheeseMock]
        assertEquals(cheeseMock, burger.ingredients.get(LETTUCE_INDEX));
    }

    // Проверка выброса исключений
    @Test
    public void testRemoveIngredientInvalidIndexThrows() {
        burger.addIngredient(cheeseMock);
        assertThrows(IndexOutOfBoundsException.class, () -> burger.removeIngredient(INVALID_INDEX));
    }

    @Test
    public void testMoveIngredientInvalidIndexThrows() {
        burger.addIngredient(cheeseMock);
        assertThrows(IndexOutOfBoundsException.class, () -> burger.moveIngredient(CHEESE_INDEX, INVALID_INDEX));
    }
}
