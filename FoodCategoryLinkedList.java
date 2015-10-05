package assignment;

import java.util.Objects;

/**
 * Created by JMG on 9/29/2015.
 */
public class FoodCategoryLinkedList {
    //each node in category linked list  has both category name and linked list of food Categorys; each node has a single food Category
    FoodCategoryNode head = null;
    int count = 0;
    public FoodCategoryLinkedList ()
    {
        head = null;
        count = 0;
    }
    public void add (FoodCategoryNode node)
    {
        FoodCategoryNode current;
        if(head==null) {
            head = node;
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
    public void deleteCategory (String name)
    {
        FoodCategoryNode current;
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
    public FoodCategoryNode search (String name)
    {
        FoodCategoryNode current;
        current = head;
        while (current != null)
        {
            if(Objects.equals(current.categoryName, name))
            {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }
    public String toString()
    {
        FoodCategoryNode current;
        String s = "";
        current = head;
        while (current!=null)
        {

            s+=current.toString() + ": \n";
            if(current.getData()!=null)
            {
                s+= current.getData().toString() + "\n";
            }
            current = current.getNext();
        }
        return s;
    }
    public String toString2()
    {
        FoodCategoryNode current;
        String s = "";
        current = head;
        while (current!=null)
        {

            s+=current.toString() + ":";
            if(current.getData()!=null)
            {
                s+= current.getData().toString2() + "|";
            }
            current = current.getNext();
        }
        return s;
    }

    public boolean isEmpty()
    {
        return count<1;
    }
}
