package assignment;

/**
 * Created by JMG on 9/29/2015.
 */
public class FoodItemNode {
    //each node in Item linked list  has both Item name and linked list of food items; each node has a single food item
    private FoodItemClass data;
    String foodName;
    private FoodItemNode next = null;
    private FoodItemNode previous = null;


    public FoodItemNode (String name)
    {
        foodName = name;
    }

    public void setNext(FoodItemNode next) {this.next = next;}
    public void setPrevious(FoodItemNode previous) {this.previous = previous;}
    public void setData(FoodItemClass data) {this.data = new FoodItemClass(data.name,data.price,data.quantity,data.description,data.size,data.specialOrder);}
    public FoodItemNode getNext() {return next;}
    public FoodItemNode getPrevious() {return previous;}
    public FoodItemClass getData(){return data;}

    public String toString()
    {
        String s = "";
        s+=foodName + ": ";
        if (data!=null){
            s+=data.toString() + "\n";
        }
        return s;
    }
    public String toString2()
    {
        String s = "";
        s+=foodName + ",";
        return s;
    }
}
