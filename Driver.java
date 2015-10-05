package assignment;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by JMG on 9/29/2015.
 */
public class Driver extends Application {
    static FoodCategoryLinkedList category = new FoodCategoryLinkedList();
    static Stage window;
    static Gui gui = new Gui();
    static Scene base;
    static String selected;

    public static void main(String[] args) {
        newFoodItemFromTxt();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        primaryStage.setTitle("assignment 2");
        base = gui.setBase();
        window.setScene(base);
        window.show();
    }

    public static void auFoodItem(String categoryName, String foodName, String foodPrice, String foodQuantity, String foodDescription, String foodSize, String foodSpecialOrder) {
        FoodCategoryNode fcn;
        FoodItemNode fin;
        if (category.search(categoryName) == null) //create category node
        {
            category.add(new FoodCategoryNode(categoryName));
        }
        fcn = category.search(categoryName);
        if (fcn.getData() == null) //create food item linked list
        {
            fcn.setData(new FoodItemLinkedList());
        }
        if (fcn.getData().search(foodName) == null) //create food item node
        {
            fcn.getData().add(new FoodItemNode(foodName));
        }
        fin = fcn.getData().search(foodName);
        if (fin.getData() == null)  //create food item class
        {
            fin.setData(new FoodItemClass(foodName, foodPrice, foodQuantity, foodDescription, foodSize, foodSpecialOrder));
        }
        //update values
        if (!Objects.equals(fin.getData().price,foodPrice)) {
            fin.getData().price = foodPrice;
        }
        if (!Objects.equals(fin.getData().quantity,foodQuantity)) {
            fin.getData().quantity = foodQuantity;
        }
        if (!Objects.equals(fin.getData().description, foodDescription)) {
            fin.getData().description = foodDescription;
        }
        if (!Objects.equals(fin.getData().size, foodSize)) {
            fin.getData().size = foodSize;
        }
        if (!Objects.equals(fin.getData().specialOrder, foodSpecialOrder)) {
            fin.getData().specialOrder = foodSpecialOrder;
        }
    }

    public static void removeFoodItem(String categoryName, String foodName)
    {
        FoodCategoryNode fcn;
        if (category.search(categoryName) != null) //if the category exists
        {
            fcn = category.search(categoryName);
            if (fcn.getData() != null) //ensure the item linkedlist exists
            {
                if (fcn.getData().search(foodName) != null) //does the node exist?
                {
                    fcn.getData().deleteFood(foodName);
                }
            }
        }
    }
    public static void removeFoodCat(String categoryName)
    {
        if (category.search(categoryName) != null) //if the category exists
        {
            category.deleteCategory(categoryName);
        }
    }
    public static void newFoodItemFromGui(){

    }

    public static void newFoodItemFromTxt()
    {
        String[] line;
        String l;
        String filepath = "C:/_Java/";
        String filename = "importTXT.txt";
        String file = filepath + filename;

        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            while((l=input.readLine())!=null)
            {
                line = l.split(",");
                auFoodItem(line[0],line[1], line[2], line[3], line[4], line[5], line[6]);
            }
        } catch(FileNotFoundException e){
            System.out.println("File not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO error.");
            e.printStackTrace();
        }
    }

    public static void newFoodItemFromExcel()
    {
        String filepath = "C:/_Java/";
        String filename = "importXLS.XLS";
        String file = filepath + filename;
        String line[] = new String[7];
        try {
            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(file)));
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            HSSFCell cell;
            int cols = 0;
            int tmp = 0;
            int rows;
            rows = sheet.getPhysicalNumberOfRows();

            for(int i = 0; i < 10 || i < rows; i++)
            {
                row = sheet.getRow(i);
                if(row != null)
                {
                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                    if(tmp > cols) cols = tmp;
                }
            }

            for(int r = 0; r < rows; r++)
            {
                row = sheet.getRow(r);
                if(row != null)
                {
                    for(int c = 0; c < 7; c++)
                    {
                        cell = row.getCell(c);
                        if(cell != null)
                        {
                            line[c]=cell.toString();
                        }
                        else
                        {
                            line[c] = "";
                        }
                    }
                    auFoodItem(line[0],line[1], line[2], line[3], line[4], line[5], line[6]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO error.");
            e.printStackTrace();
        }
    }


}
