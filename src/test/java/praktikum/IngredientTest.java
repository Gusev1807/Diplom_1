package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTest {

    @Test
    public void testGetName() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "chili sauce", 150f);
        assertEquals("chili sauce", ingredient.getName());
    }

    @Test
    public void testGetPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cutlet", 300f);
        assertEquals(300f, ingredient.getPrice(), 0.001f);
    }

    @Test
    public void testGetType() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100f);
        assertEquals(IngredientType.SAUCE, ingredient.getType());
    }
}

