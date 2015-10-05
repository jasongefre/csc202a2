package assignment;

/**
 * Created by JMG on 9/29/2015.
 */
public class FoodItemClass {
    //each node in category linked list  has both category name and linked list of food items; each node has a single food item
    String name;
    String price;
    String quantity;
    String description;
    String size;
    String specialOrder;

    public FoodItemClass(String name, String price, String quantity, String description, String size, String specialOrder)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.size = size;
        this.specialOrder = specialOrder;
    }
    public String toString()
    {
        String s = "";
        s += "Name: " + name + " || ";
        s += "Price: " + price + " || ";
        s += "Quantity: " + quantity + " || ";
        s += "Description: " + description + " || ";
        s += "Size: " + size + " || ";
        s += "Special Order: " + specialOrder;
        return s;
    }
}
