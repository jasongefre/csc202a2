package assignment;

/**
 * Created by JMG on 9/29/2015.
 */
public class FoodCategoryNode {
    //each node in category linked list has both category name and linked list of food items; each node has a single food item
    private FoodItemLinkedList data;
    public String categoryName;
    private FoodCategoryNode next = null;
    private FoodCategoryNode previous = null;


    public FoodCategoryNode (String name)
    {
        categoryName = name;
    }

    public void setNext(FoodCategoryNode next) {this.next = next;}
    public void setPrevious(FoodCategoryNode previous) {this.previous = previous;}
    public void setData(FoodItemLinkedList data) {this.data = data;}
    public FoodCategoryNode getNext() {return next;}
    public FoodCategoryNode getPrevious() {return previous;}
    public FoodItemLinkedList getData(){return data;}

    public String toString() { return categoryName;}

}
