package com.example.foodapp.data;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * Type converter for Room database to manage conversion of Product lists to and from strings.
 */
public class ProductTypeConverter {
    private static Gson gson = new Gson();

    /**
     * Converts a string to a List of Products.
     *
     * @param data The string representation of the Product list.
     * @return The List of Products converted from the provided string.
     */
    @TypeConverter
    public static List<Product> stringToProductList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Product>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    /**
     * Converts a List of Products to a string.
     *
     * @param products The list of Products to be converted.
     * @return The string representation of the provided Product list.
     */
    @TypeConverter
    public static String productListToString(List<Product> products) {
        return gson.toJson(products);
    }
}
