package com.example.clientspring;

//import com.example.demo1.Book;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class UpdateController {


    @FXML
    private TextField id;
    @FXML
    private TextField titre;
    @FXML
    private TextField auteurs;
    @FXML
    private TextField text;


    @FXML
    private Button valider;
    @FXML
    private Label titreLabel;
    @FXML
    private Label auteursLabel;
    @FXML
    private Label textLabel;


    private Scene scene;
    private Stage stage;
    private ArrayList<Node> list;
    private ArrayList<TextField> listText;

    private void updateInter(){
        list = new ArrayList<>();
        listText = new ArrayList<>();
        listText.add(titre);
        listText.add(auteurs);
        listText.add(text);

        list.add(titreLabel);
        list.add(auteursLabel);
        list.add(textLabel);
        list.add(valider);
    }

    @FXML
    protected void valideUpdate(ActionEvent event) throws IOException {
        com.example.clientspring.HelloApplication.updateLivre(id.getText(),titre.getText(),auteurs.getText(), text.getText());

        FXMLLoader fxmlLoader = new FXMLLoader(com.example.clientspring.HelloApplication.class.getResource("SceneFirst.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void clickOnSeach(ActionEvent event){
        String s = com.example.clientspring.HelloApplication.searchLivreJson(id.getText());


        updateInter();
        for (Node n: list) {
            if(n != null){
                n.setVisible(true);
            }
        }
        for (Node n: listText) {
            if(n != null){
                n.setVisible(true);
            }
        }

        s = s.replace("{","");
        s = s.replace("}","");
        s = s.replace("\"","");

        String[] l = s.split(",");
        for (String mot: l) {
            String [] f = mot.split(":");
            switch (f[0]){
                case "title" :
                    listText.get(0).setText(f[1]);
                    break;
                case "author" :
                    listText.get(1).setText(f[1]);
                    break;
                case "text" :
                    listText.get(2).setText(f[1]);
                    break;
            }
        }



    }
}
