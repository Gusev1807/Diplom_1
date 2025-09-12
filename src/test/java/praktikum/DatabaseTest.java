package praktikum;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DatabaseTest {

    private Database database;

    @Before
    public void setUp() {
        database = new Database();
    }

    @Test
    public void testAvailableBuns() {
        List<Bun> buns = database.availableBuns();

        assertNotNull(buns);
        assertEquals(3, buns.size());

        // Проверяем первую булку
        Bun firstBun = buns.get(0);
        assertEquals("black bun", firstBun.getName());
        assertEquals(100f, firstBun.getPrice(), 0.001f);
    }

    @Test
    public void testAvailableIngredients() {
        List<Ingredient> ingredients = database.availableIngredients();

        assertNotNull(ingredients);
        assertEquals(6, ingredients.size());

        // Проверяем первый ингредиент
        Ingredient firstIngredient = ingredients.get(0);
        assertEquals(IngredientType.SAUCE, firstIngredient.getType());
        assertEquals("hot sauce", firstIngredient.getName());
        assertEquals(100f, firstIngredient.getPrice(), 0.001f);
    }
}

