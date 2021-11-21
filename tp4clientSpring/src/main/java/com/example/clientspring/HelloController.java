package com.example.clientspring;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label idArea;
    @FXML
    private TextField id;
    @FXML
    private TextField titre;
    @FXML
    private TextField auteurs;
    @FXML
    private TextField text;

    @FXML
    private TextArea fild;


    private Scene scene;
    private Stage stage;

    @FXML
    protected void validedAdd(ActionEvent event) throws IOException {
        id.setText(HelloApplication.addLivre( titre.getText(),auteurs.getText(),text.getText()));
        id.setVisible(true);
        idArea.setVisible(true);

    }

    @FXML
    protected void validedSearchJson(ActionEvent event) throws IOException {
        fild.setVisible(true);
        fild.setText(HelloApplication.searchLivreJson(id.getText()));
    }


    @FXML
    protected void backToFisrt(ActionEvent event) throws IOException {
        goTo(event, "SceneFirst.fxml");
    }

    @FXML
    protected void onAddButtonClick(ActionEvent event) throws IOException {
        goTo(event, "SceneAdd.fxml");
    }

    @FXML
    protected void onSuppButtonClick(ActionEvent event) throws IOException {
        goTo(event, "SceneSupp.fxml");

    }

    @FXML
    protected void onSearchButtonClick(ActionEvent event) throws IOException {
        goTo(event, "SceneSeach.fxml");

    }

    @FXML
    protected void onUpdateButtonClick(ActionEvent event) throws IOException {
        goTo(event, "SceneUpdate.fxml");

    }

    @FXML
    protected void validedSupp(ActionEvent event) throws IOException {
        HelloApplication.suppLivre(id.getText());
        backToFisrt(event);
    }

    private void goTo(ActionEvent event ,String page) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}