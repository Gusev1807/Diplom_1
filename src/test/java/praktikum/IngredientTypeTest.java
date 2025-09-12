package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class IngredientTypeTest {

    @Test
    public void testEnumValues() {
        IngredientType[] expected = {IngredientType.SAUCE, IngredientType.FILLING};
        IngredientType[] actual = IngredientType.values();

        assertArrayEquals(expected, actual);
    }
}
