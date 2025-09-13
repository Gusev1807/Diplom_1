package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerIngredientTest {

    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private static final int THIRD_INDEX = 2;
    private static final int INVALID_INDEX = 5;

    private Burger burger;
    private Ingredient ingredient1Mock;
    private Ingredient ingredient2Mock;
    private Ingredient ingredient3Mock;

    @Before
    public void setUp() {
        burger = new Burger();

        // Создаем моки ингредиентов
        ingredient1Mock = mock(Ingredient.class);
        ingredient2Mock = mock(Ingredient.class);
        ingredient3Mock = mock(Ingredient.class);

        // Настройка имен
        when(ingredient1Mock.getName()).thenReturn("Cheese");
        when(ingredient2Mock.getName()).thenReturn("Lettuce");
        when(ingredient3Mock.getName()).thenReturn("Tomato");

        // Настройка типов
        when(ingredient1Mock.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient2Mock.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient3Mock.getType()).thenReturn(IngredientType.SAUCE);

        // Настройка цен
        when(ingredient1Mock.getPrice()).thenReturn(50f);
        when(ingredient2Mock.getPrice()).thenReturn(20f);
        when(ingredient3Mock.getPrice()).thenReturn(10f);
    }

    // Добавление ингредиентов
    @Test
    public void testAddFirstIngredient() {
        burger.addIngredient(ingredient1Mock);
        assertEquals(ingredient1Mock, burger.ingredients.get(FIRST_INDEX));
    }

    @Test
    public void testAddSecondIngredient() {
        burger.addIngredient(ingredient1Mock);
        burger.addIngredient(ingredient2Mock);
        assertEquals(ingredient2Mock, burger.ingredients.get(SECOND_INDEX));
    }

    @Test
    public void testAddThirdIngredient() {
        burger.addIngredient(ingredient1Mock);
        burger.addIngredient(ingredient2Mock);
        burger.addIngredient(ingredient3Mock);
        assertEquals(ingredient3Mock, burger.ingredients.get(THIRD_INDEX));
    }

    // Удаление ингредиентов
    @Test
    public void testRemoveSecondIngredient() {
        burger.addIngredient(ingredient1Mock);
        burger.addIngredient(ingredient2Mock);
        burger.addIngredient(ingredient3Mock);

        // Удаляем второй ингредиент (Lettuce)
        burger.removeIngredient(SECOND_INDEX);

        // После удаления список: [ingredient1Mock, ingredient3Mock]
        assertEquals(ingredient3Mock, burger.ingredients.get(SECOND_INDEX));
    }

    @Test
    public void testRemoveFirstIngredient() {
        burger.addIngredient(ingredient1Mock);
        burger.addIngredient(ingredient2Mock);

        // Удаляем первый ингредиент
        burger.removeIngredient(FIRST_INDEX);

        // После удаления список: [ingredient2Mock]
        assertEquals(ingredient2Mock, burger.ingredients.get(FIRST_INDEX));
    }

    // Перемещение ингредиентов
    @Test
    public void testMoveThirdIngredientToFirst() {
        burger.addIngredient(ingredient1Mock);
        burger.addIngredient(ingredient2Mock);
        burger.addIngredient(ingredient3Mock);

        // Перемещаем третий ингредиент (Tomato) на первое место
        burger.moveIngredient(THIRD_INDEX, FIRST_INDEX);

        // Новый порядок: [ingredient3Mock, ingredient1Mock, ingredient2Mock]
        assertEquals(ingredient3Mock, burger.ingredients.get(FIRST_INDEX));
    }

    @Test
    public void testMoveFirstIngredientToSecond() {
        burger.addIngredient(ingredient1Mock);
        burger.addIngredient(ingredient2Mock);

        // Перемещаем первый ингредиент на второе место
        burger.moveIngredient(FIRST_INDEX, SECOND_INDEX);

        // Новый порядок: [ingredient2Mock, ingredient1Mock]
        assertEquals(ingredient1Mock, burger.ingredients.get(SECOND_INDEX));
    }

    // Проверка выброса исключений
    @Test
    public void testRemoveIngredientInvalidIndexThrows() {
        burger.addIngredient(ingredient1Mock);
        assertThrows(IndexOutOfBoundsException.class, () -> burger.removeIngredient(INVALID_INDEX));
    }

    @Test
    public void testMoveIngredientInvalidIndexThrows() {
        burger.addIngredient(ingredient1Mock);
        assertThrows(IndexOutOfBoundsException.class, () -> burger.moveIngredient(FIRST_INDEX, INVALID_INDEX));
    }
}