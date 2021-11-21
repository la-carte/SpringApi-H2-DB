package com.example.clientspring;

//import com.sun.jersey.api.client.*;

import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.ws.rs.core.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class HelloApplication extends Application {
    private Stage primaryStage;
    private static WebResource service;

    public HelloApplication() throws URISyntaxException {
        this.initializeService();

    }
    private void initializeService() throws URISyntaxException {
        Client client = Client.create(new DefaultClientConfig());
        URI uri = new URI("http://localhost:8080/");
        UriBuilder uriBuilder = UriBuilder.fromUri(uri);
        service = client.resource(uriBuilder.build());
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SceneFirst.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Hello!");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }

    public static String addLivre( String titre, String author, String text) throws IOException{
        /*
        URL url = new URL("http://localhost:8080/books");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("PUT");
        //http.setDoOutput(true);
        http.setDoInput(true);
        http.setDoOutput(true);
        //http.setRequestProperty("Accept", "text/plain");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{\n \"title\":\""+ titre+"\",\n  \"author\": \""
                + author+"\",\n  \"text\":\""+ text+"\"\n}";

        System.out.println(data);
        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(stream);

        dataOutputStream.write(out);
        dataOutputStream.flush();
        dataOutputStream.close();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        String fin = "no id";
        String s = http.getResponseMessage();
        s = s.replace("{","");
        s = s.replace("}","");
        s = s.replace("\"","");

        String[] l = s.split(",");
        for (String mot: l) {
            String [] f = mot.split(":");
            switch (f[0]){
                case "title" :
                    fin = f[1];
                    break;
            }
        }


        http.disconnect();
        return fin;

         */
        String data = "{\n \"title\":\""+ titre+"\",\n  \"author\": \""
                + author+"\",\n  \"text\":\""+ text+"\"\n}";
        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        return service.path("/books").entity(out,MediaType.APPLICATION_JSON).put(new GenericType<String>(){});
    }


    public static void suppLivre(String id){
        service.path("/books/"+id).delete();
    }

    public static String searchLivreJson(String id){

        return service.path("/books/"+id).accept(MediaType.APPLICATION_JSON).get(new GenericType<String>(){});

    }

    public static void updateLivre(String id, String titre, String author, String text) throws IOException {
        String data = "{\n  \"id\":"+ id+",\n  \"title\":\""+ titre+"\",\n  \"author\": \""
                        + author+"\",\n  \"text\":\""+ text+"\"\n}";
        byte[] out = data.getBytes(StandardCharsets.UTF_8);


        service.path("/books").entity(out,MediaType.APPLICATION_JSON).post(new GenericType<String>(){});


/*
        service.path("/books")
                .entity(id,"id")
                .entity(titre,"title")
                .entity(author,"author")
                .entity(text,"text")
                .post(new GenericType<String>(){});

        String test = service.path("/books/")
                .queryParam("id",id)
                .queryParam("title",titre)
                .queryParam("author",auteurs)
                .queryParam("text",text)
                .post(new GenericType<String>(){});

         */
    }
}