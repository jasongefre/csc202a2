package assignment;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by JMG on 9/29/2015.
 */
public class Gui extends Driver{
    String a;
    String[] cat;
    String[] catName;
    String[] items;
    int[] catindex;

    public Gui()
    {
    }
    public Scene setBase()
    {
        VBox vb = new VBox();
            vb.setAlignment(Pos.CENTER);
            vb.setPadding(new Insets(10,50,50,50));
            vb.setSpacing(10);
            Label lbl = new Label("ADMIN");
            lbl.setFont(Font.font("Amble CN", FontWeight.BOLD, 24));
            GridPane grid1 = new GridPane();
                grid1.setAlignment(Pos.CENTER);
                grid1.setPadding(new Insets(5));
                grid1.setHgap(10);
                grid1.setVgap(10);

                Button buttonImportT = new Button("Import from .TXT");
                Button buttonImportX = new Button("Import from .XLS");
                Button buttonImportJ = new Button("Add new entry");
                buttonImportT.setMinWidth(150);
                buttonImportX.setMinWidth(150);
                buttonImportJ.setMinWidth(150);
                GridPane.setConstraints(buttonImportT, 0, 0);
                GridPane.setConstraints(buttonImportX, 1, 0);
                GridPane.setConstraints(buttonImportJ, 2, 0);
            grid1.getChildren().add(buttonImportT);
            grid1.getChildren().add(buttonImportX);
            grid1.getChildren().add(buttonImportJ);
            GridPane grid2 = new GridPane();
                grid2.setAlignment(Pos.CENTER);
                grid2.setPadding(new Insets(5));
                grid2.setHgap(10);
                grid2.setVgap(10);

                Button buttonExportT = new Button("Export to .TXT");
                Button buttonExportX = new Button("Export to .XLS");
                buttonExportT.setMinWidth(230);
                buttonExportX.setMinWidth(230);
                GridPane.setConstraints(buttonExportT, 0, 0);
                GridPane.setConstraints(buttonExportT, 1, 0);
            grid2.getChildren().add(buttonExportT);
            grid2.getChildren().add(buttonExportX);
            GridPane grid3 = new GridPane();
                grid3.setAlignment(Pos.CENTER);
                grid3.setPadding(new Insets(5));
                grid3.setHgap(10);
                grid3.setVgap(10);

                Button buttonSearch = new Button("Search");
                Button buttonViewAll = new Button("View all items");
                Button buttonViewCategory = new Button("View categories");
                buttonSearch.setMinWidth(150);
                buttonViewAll.setMinWidth(150);
                buttonViewCategory.setMinWidth(150);
                GridPane.setConstraints(buttonSearch, 0, 0);
                GridPane.setConstraints(buttonViewAll, 1, 0);
                GridPane.setConstraints(buttonViewCategory, 2, 0);
            grid3.getChildren().add(buttonSearch);
            grid3.getChildren().add(buttonViewAll);
            grid3.getChildren().add(buttonViewCategory);
            Button buttonExit = new Button("Exit");

        vb.getChildren().add(lbl);
        vb.getChildren().add(grid1);
        vb.getChildren().add(grid2);
        vb.getChildren().add(grid3);
        vb.getChildren().add(buttonExit);

        buttonImportT.setOnAction(event -> newFoodItemFromTxt()
        );
        buttonImportX.setOnAction(event -> newFoodItemFromExcel()
        );
        buttonImportJ.setOnAction(event -> window.setScene(gui.setInput("Add Data", "", ""))
        );
        buttonSearch.setOnAction(event -> window.setScene(gui.setList("name"))
        );
        buttonViewAll.setOnAction(event -> window.setScene(gui.setList("name"))
        );
        buttonViewCategory.setOnAction(event -> window.setScene(gui.setList("category"))
        );
        buttonExit.setOnAction(event -> Platform.exit()
        );

        return new Scene(vb);
    }
    public Scene setList(String filter)
    {
        //newFoodItemFromTxt();

        VBox vb = new VBox();
            vb.setAlignment(Pos.CENTER);
            vb.setPadding(new Insets(10,50,50,50));
            vb.setSpacing(10);

            Label lbl = new Label();
            if(filter.contains("category"))
            {
                lbl.setText("Category View");
            }
            else if(filter.contains("name")) {
                lbl.setText("View All");
            }
            else if(filter.contains("specific")) {
                lbl.setText("Items Within Category " + selected);
            }
            else if(filter.contains("search")) {
                lbl.setText("Search All Records");
            }
            lbl.setFont(Font.font("Amble CN", FontWeight.BOLD, 24));

            GridPane grid1 = new GridPane();
                grid1.setAlignment(Pos.CENTER);
                grid1.setPadding(new Insets(5));
                grid1.setHgap(10);
                grid1.setVgap(10);

                Label lSearch = new Label("Search:");
                lSearch.setAlignment(Pos.CENTER_RIGHT);
                TextField tSearch = new TextField();
                lSearch.setMinWidth(230);
                tSearch.setMinWidth(230);
                GridPane.setConstraints(lSearch, 0, 0);
                GridPane.setConstraints(tSearch, 1, 0);
            grid1.getChildren().add(lSearch);
            grid1.getChildren().add(tSearch);

            ListView<String> listview;
            listview = new ListView<>();
            listview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            listview.setMinWidth(470);

            //create list / update values
            if(!category.isEmpty()) {
                a = category.toString2().replace(",|", "|");
                cat = a.split("\\|");
                catName = new String[cat.length];
                catindex = new int[cat.length];
                for (int i = 0; i < cat.length; i++) {
                    catName[i] = cat[i].split(":")[0];
                    cat[i] = cat[i].split(":")[1];
                }
                for (int i = 0; i < cat.length; i++)
                {
                    if (filter.contains("category"))
                    {
                        listview.getItems().add(catName[i]);
                    }
                    else {
                        items = cat[i].split(",");
                        if (filter.contains("name") || filter.contains("search") ||(filter.contains("specific") && catName[i].contains(selected)))
                        {
                            if(i>0)
                            {
                                catindex[i] = items.length+catindex[i-1];
                            }
                            else
                            {
                                catindex[i]=items.length;
                            }
                            for (String item : items) {
                                listview.getItems().add(item);
                            }
                        }
                    }
                }
            }
            else //null categories
            {
                listview.getItems().add("");
                System.out.println("ERROR IN LISTVIEW CATEGORIES: INVALID SUBORDINATES");
            }

            GridPane grid2 = new GridPane();
                grid2.setAlignment(Pos.CENTER);
                grid2.setPadding(new Insets(5));
                grid2.setHgap(10);
                grid2.setVgap(10);
                Button buttonReturn = new Button("Return");
                Button buttonDelete = new Button("Delete");
                Button buttonSelect = new Button("Select");
                buttonReturn.setMinWidth(150);
                buttonDelete.setMinWidth(150);
                buttonSelect.setMinWidth(150);
                GridPane.setConstraints(buttonReturn, 0, 0);
                GridPane.setConstraints(buttonDelete, 1, 0);
                GridPane.setConstraints(buttonSelect, 2, 0);
            grid2.getChildren().add(buttonReturn);
            grid2.getChildren().add(buttonDelete);
            grid2.getChildren().add(buttonSelect);

        vb.getChildren().add(lbl);
        vb.getChildren().add(grid1);
        vb.getChildren().add(listview);
        vb.getChildren().add(grid2);

        EventHandler<KeyEvent> handler = new EventHandler<KeyEvent>()
        {
            private boolean willConsume = false;
            @Override
            public void handle(KeyEvent event)
            {
                if (willConsume)
                {
                    event.consume();
                    listview.getItems().clear();
                    for (int i = 0; i < catName.length; i++) {

                        if (filter.contains("category"))
                        {
                            if(catName[i].contains(tSearch.getText()))
                            {
                                listview.getItems().add(catName[i]);
                            }
                        }
                        else if (filter.contains("name") || filter.contains("search") || ((filter.contains("specific") && catName[i].contains(selected))))
                        {
                            items = cat[i].split(",");
                            for (String item : items) {
                                if(item.contains(tSearch.getText()))
                                {
                                    listview.getItems().add(item);
                                }
                            }
                        }
                    }
                }
                if (event.getEventType() == KeyEvent.KEY_PRESSED)
                {
                    willConsume = true;
                    int cPos = tSearch.getCaretPosition();
                    String bText = tSearch.getText().substring(0, cPos);
                    String aText = tSearch.getText().substring(cPos,tSearch.getText().length());
                    if (event.getCode().isLetterKey())
                    {
                        tSearch.setText(bText + event.getText() + aText);
                        tSearch.positionCaret(cPos+1);
                    }
                }
                else if (event.getEventType() == KeyEvent.KEY_RELEASED)
                {
                    willConsume = false;
                }
            }
        };

        tSearch.addEventFilter(javafx.scene.input.KeyEvent.ANY, handler);
        buttonReturn.setOnAction(event -> {
                    window.setScene(base);
                    selected = null;
                }
        );
        buttonDelete.setOnAction(event -> {
                    if (filter.contains("category"))
                    {
                        System.out.println(listview.getSelectionModel().getSelectedItem());
                        removeFoodCat(listview.getSelectionModel().getSelectedItem());
                        listview.getItems().remove(listview.getSelectionModel().getSelectedIndex());
                    }
                    else
                    {
                        if (selected==null) {
                            boolean found=false;
                            for (int i = 0; i < catindex.length; i++) {
                                if(listview.getSelectionModel().getSelectedIndex()<catindex[i] && !found){
                                    selected=catName[i];
                                    found=true;
                                }
                            }
                        }
                        System.out.println(selected);
                        System.out.println(listview.getSelectionModel().getSelectedItem());
                        removeFoodItem(selected, listview.getSelectionModel().getSelectedItem());
                        listview.getItems().remove(listview.getSelectionModel().getSelectedIndex());
                    }
                }
        );
        buttonSelect.setOnAction(event -> {
                    if (filter.contains("category")) {
                        selected = listview.getSelectionModel().getSelectedItem();
                        window.setScene(setList("specific"));
                    } else {
                        if (selected==null) {
                            boolean found=false;
                            for (int i = 0; i < catindex.length; i++) {
                                if(listview.getSelectionModel().getSelectedIndex()<catindex[i] && !found){
                                    selected=catName[i];
                                    found=true;
                                }
                            }
                        }
                        System.out.println(selected);
                        window.setScene(setInput("view/update", selected, listview.getSelectionModel().getSelectedItem()));
                    }
                }
        );

        return new Scene(vb);
    }
    public Scene setInput(String header,String cat, String name) {
        FoodCategoryNode a;
        FoodItemNode b;

        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(10,50,50,50));
        vb.setSpacing(10);

        Label lbl = new Label(header);
        lbl.setFont(Font.font("Amble CN", FontWeight.BOLD, 24));

            GridPane grid1 = new GridPane();
            grid1.setAlignment(Pos.CENTER);
            grid1.setPadding(new Insets(5));
            grid1.setHgap(10);
            grid1.setVgap(10);

            Button buttonPrevious   = new Button("PREVIOUS");
            Button buttonReturn     = new Button("RETURN");
            Button buttonNext       = new Button("NEXT");
            buttonPrevious          .setMinWidth(150);
            buttonReturn            .setMinWidth(150);
            buttonNext              .setMinWidth(150);
            GridPane.setConstraints(buttonPrevious, 0, 0);
            GridPane.setConstraints(buttonReturn, 1, 0);
            GridPane.setConstraints(buttonNext, 2, 0);
            grid1.getChildren().setAll(buttonPrevious,buttonReturn,buttonNext);


            GridPane grid2 = new GridPane();
            grid2.setAlignment(Pos.CENTER);
            grid2.setPadding(new Insets(5));
            grid2.setHgap(10);
            grid2.setVgap(10);

            Label lCategory     = new Label("Category:"    );
            Label lName         = new Label("Name:"        );
            Label lPrice        = new Label("Price:"       );
            Label lQuantity     = new Label("Quantity:"    );
            Label lDescription  = new Label("Description:" );
            Label lSize         = new Label("Size:"        );
            Label lSpecialOrder = new Label("SpecialOrder:");

            TextField tCategory     = new TextField();
            TextField tName         = new TextField();
            TextField tPrice        = new TextField();
            TextField tQuantity     = new TextField();
            TextField tDescription  = new TextField();
            TextField tSize         = new TextField();
            TextField tSpecialOrder = new TextField();

            if(!category.isEmpty()) {
                if ((a = category.search(cat))!=null) {
                    tCategory.setText(cat);
                    if (!a.getData().isEmpty()) {
                        if ((b = a.getData().search(name)) != null) {
                            tName.setText(a.getData().search(name).foodName);
                            tPrice.setText(b.getData().price);
                            tQuantity.setText(b.getData().quantity);
                            tDescription.setText(b.getData().description);
                            tSize.setText(b.getData().size);
                            tSpecialOrder.setText(b.getData().specialOrder);
                        }
                    }
                }
            }

            lCategory       .setMinWidth(100);
            lName           .setMinWidth(100);
            lPrice          .setMinWidth(100);
            lQuantity       .setMinWidth(100);
            lDescription    .setMinWidth(100);
            lSize           .setMinWidth(100);
            lSpecialOrder   .setMinWidth(100);
            lCategory    .setAlignment(Pos.CENTER_RIGHT);
            lName        .setAlignment(Pos.CENTER_RIGHT);
            lPrice       .setAlignment(Pos.CENTER_RIGHT);
            lQuantity    .setAlignment(Pos.CENTER_RIGHT);
            lDescription .setAlignment(Pos.CENTER_RIGHT);
            lSize        .setAlignment(Pos.CENTER_RIGHT);
            lSpecialOrder.setAlignment(Pos.CENTER_RIGHT);

            tCategory       .setMinWidth(360);
            tName           .setMinWidth(360);
            tPrice          .setMinWidth(360);
            tQuantity       .setMinWidth(360);
            tDescription    .setMinWidth(360);
            tSize           .setMinWidth(360);
            tSpecialOrder   .setMinWidth(360);

            GridPane.setConstraints(lCategory, 0, 0);
            GridPane.setConstraints(lName, 0, 1);
            GridPane.setConstraints(lPrice, 0, 2);
            GridPane.setConstraints(lQuantity, 0, 3);
            GridPane.setConstraints(lDescription, 0, 4);
            GridPane.setConstraints(lSize, 0, 5);
            GridPane.setConstraints(lSpecialOrder, 0, 6);
            GridPane.setConstraints(tCategory, 1, 0);
            GridPane.setConstraints(tName, 1, 1);
            GridPane.setConstraints(tPrice, 1, 2);
            GridPane.setConstraints(tQuantity, 1, 3);
            GridPane.setConstraints(tDescription, 1, 4);
            GridPane.setConstraints(tSize, 1, 5);
            GridPane.setConstraints(tSpecialOrder, 1, 6);

            grid2.getChildren().add(lCategory    );
            grid2.getChildren().add(lName        );
            grid2.getChildren().add(lPrice       );
            grid2.getChildren().add(lQuantity    );
            grid2.getChildren().add(lDescription );
            grid2.getChildren().add(lSize        );
            grid2.getChildren().add(lSpecialOrder);
            grid2.getChildren().add(tCategory    );
            grid2.getChildren().add(tName        );
            grid2.getChildren().add(tPrice       );
            grid2.getChildren().add(tQuantity    );
            grid2.getChildren().add(tDescription );
            grid2.getChildren().add(tSize        );
            grid2.getChildren().add(tSpecialOrder);


        GridPane grid3 = new GridPane();
        grid3.setAlignment(Pos.CENTER);
        grid3.setPadding(new Insets(5));
        grid3.setHgap(10);
        grid3.setVgap(10);

        Button buttonUpdate = new Button("UPDATE");
        Button buttonAdd = new Button("ADD");
        Button buttonDelete = new Button("DELETE");
        buttonUpdate.setMinWidth(150);
        buttonAdd   .setMinWidth(150);
        buttonDelete.setMinWidth(150);
        GridPane.setConstraints(buttonUpdate, 0, 0);
        GridPane.setConstraints(buttonAdd, 1, 0);
        GridPane.setConstraints(buttonDelete, 2, 0);
        grid3.getChildren().setAll(buttonUpdate, buttonAdd,buttonDelete);


        vb.getChildren().addAll(lbl,grid1,grid2,grid3);



        //toggle disabling due to a null entry)
        if(!category.isEmpty()) {
            if (category.search(cat)!=null) {
                if (!category.search(cat).getData().isEmpty()) {
                    if (category.search(cat).getData().search(name)!=null) {
                        if (category.search(cat).getData().search(name).getPrevious() == null) {
                            buttonPrevious.setDisable(true);
                        } else {
                            buttonPrevious.setDisable(false);
                        }
                        if (category.search(cat).getData().search(name).getNext() == null) {
                            buttonNext.setDisable(true);
                        } else {
                            buttonNext.setDisable(false);
                        }
                    }
                }
            }
        }

        buttonPrevious.setPrefWidth(100);
        buttonPrevious.setOnAction(event -> window.setScene(setInput(header,cat,category.search(cat).getData().search(name).getPrevious().foodName))
        );

        buttonNext.setPrefWidth(100);
        buttonNext.setOnAction(event -> window.setScene(setInput(header,cat,category.search(cat).getData().search(name).getNext().foodName))
        );

        buttonUpdate.setPrefWidth(100);
        buttonUpdate.setOnAction(event -> {
                    System.out.println(tCategory.getText());
                    System.out.println(tName.getText());
                    removeFoodItem(tCategory.getText(),
                            name);
                    auFoodItem(
                            tCategory.getText(),
                            tName.getText(),
                            tPrice.getText(),
                            tQuantity.getText(),
                            tDescription.getText(),
                            tSize.getText(),
                            tSpecialOrder.getText()
                    );

                }
        );

        buttonAdd.setPrefWidth(100);
        buttonAdd.setOnAction(event -> {
                    auFoodItem(
                            tCategory.getText(),
                            tName.getText(),
                            tPrice.getText(),
                            tQuantity.getText(),
                            tDescription.getText(),
                            tSize.getText(),
                            tSpecialOrder.getText()
                    );
                    if (buttonPrevious.isDisabled()) buttonPrevious.setDisable(false);
                }
        );

        buttonDelete.setPrefWidth(100);
        buttonDelete.setOnAction(event -> {
                    System.out.println(cat);
                    System.out.println(name);
                    window.setScene(setInput(header,cat,category.search(cat).getData().search(name).getPrevious().foodName));
                    removeFoodItem(cat, name);
                }
        );
        buttonReturn.setPrefWidth(100);
        buttonReturn.setOnAction(event -> {
                    window.setScene(base);
                    selected = null;
                }
        );

        return new Scene(vb);
    }
}
