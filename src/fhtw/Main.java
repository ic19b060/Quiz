package fhtw;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    TextArea txt_log = new TextArea();

    @Override
    public void start(Stage primaryStage) throws Exception {


        ObservableList<String> pokedex = FXCollections.observableArrayList();

        VBox topvbox = new VBox();
        topvbox.setPadding(new Insets(10));
        topvbox.setSpacing(10);
        BorderPane borderpoane = new BorderPane();
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 10, 0, 0));
        ListView<String> list_pokedex = new ListView<>();
        TextField txt_name = new TextField();
        txt_name.setPromptText("Name");
        TextField txt_level = new TextField();
        txt_level.setPromptText("Level");
        TextField txt_health = new TextField();
        txt_health.setPromptText("Health Points");
        TextField txt_experience = new TextField();
        txt_experience.setPromptText("Experience");
        Button btn_add = new Button("Add");
        btn_add.setPrefWidth(200);
        Button btn_remove = new Button("Remove");
        btn_remove.setPrefWidth(200);
        Button btn_clear = new Button("Clear Pokedex");
        btn_clear.setPrefWidth(200);


        list_pokedex.setItems(pokedex);


        btn_add.setOnAction(e -> {
            log("Button add clicked");
            String name = txt_name.getText();
            try {
                int level = Integer.parseInt(txt_level.getText());
                int hp = Integer.parseInt(txt_health.getText());
                int exp = Integer.parseInt(txt_experience.getText());

                Pokemon p = new Pokemon(name, level, hp, exp);

                pokedex.add(p.toString());
                log(p + "was added");
            } catch (NumberFormatException d){
                System.out.println("Input should be parsable integer");
                d.printStackTrace();
            }

        });

        btn_remove.setOnAction(e ->
        {
            log("Button remove clicked");
            log(list_pokedex.getSelectionModel().getSelectedItem() + "was removed");
            pokedex.remove(list_pokedex.getSelectionModel().getSelectedItem());

            VBox topbox = new VBox();

            Scene scenee = new Scene(topbox, 300, 275);
            primaryStage.setScene(scenee);
            primaryStage.show();

            });

        btn_clear.setOnAction(e ->
        {
            log("Button clear clicked");
            log("Pokedex was removed");
            pokedex.removeAll(list_pokedex.getItems());
        });


        topvbox.getChildren().addAll(txt_log, borderpoane);
        borderpoane.setLeft(vbox);
        borderpoane.setCenter(list_pokedex);
        vbox.getChildren().addAll(txt_name, txt_level,txt_health,txt_experience, btn_add, btn_remove,btn_clear);
        Scene scene = new Scene(topvbox, 300, 275);
        primaryStage.setTitle("Pokedex");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void log(String s) {
        txt_log.appendText(s + "\n");
    }


    public static void main(String[] args) {
        launch(args);
    }
}



class Pokemon {
    String n;
    int l;
    int hp;
    int exp;

    public Pokemon(String n, int l,int hp, int exp) {
        this.n = n;
        this.l = l;
        this.hp = hp;
        this.exp= exp;
    }


    @Override
    public String toString() {
        return String.format("Pokemon %s, Level: %d, HP: %d, Exp: %d",n,l,hp,exp);

    }


}


