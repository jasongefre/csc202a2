package assignment;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by JMG on 9/29/2015.
 */
public class FoodItemLinkedList {
    //each node in category linked list has both category name and linked list of food items; each node has a single food item
    FoodItemNode head = null;

    int count = 0;
    public FoodItemLinkedList ()
    {
        head = null;
        count = 0;
    }
    public void add (FoodItemNode node)
    {
        FoodItemNode current;
        if(head==null) {
            head= node;
            count++;
        }
        else{
            current = head;
            while (current.getNext()!=null)
            {
                current = current.getNext();
            }
            current.setNext(node);
            current.getNext().setPrevious(current);

            count++;
        }
    }
    public void deleteFood (String name)
    {
        FoodItemNode current;
        current = search(name);
        if(current!=null)
        {
            if(current.getPrevious()!=null)
            {
                current.getPrevious().setNext(current.getNext());
            }
            if (current.getNext()!=null)
            {
                current.getNext().setPrevious(current.getPrevious());
            }
            count--;
        }
    }
    public FoodItemNode search (String name)
    {
        FoodItemNode current;
        current = head;
        while (current!=null)
        {
            if(Objects.equals(current.foodName, name))
            {
                return current;
            }
            current = current.getNext();
        }
        return null;

    }
    public String toString()
    {
        FoodItemNode current;
        String s = "";
        current = head;
        while (current!=null)
        {
            s+=current.toString();
            current = current.getNext();
        }
        return s;
    }
    public String toString2()
    {
        FoodItemNode current;
        String s = "";
        current = head;
        while (current!=null)
        {
            s+=current.toString2();
            current = current.getNext();
        }
        return s;
    }
    public boolean isEmpty()
    {
        return count<1;
    }
}
