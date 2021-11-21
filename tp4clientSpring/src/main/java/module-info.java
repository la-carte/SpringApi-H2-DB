module com.example.clientspring {
    requires javafx.controls;
    requires javafx.fxml;
    requires jersey.client;
    requires javax.ws.rs.api;


    opens com.example.clientspring to javafx.fxml;
    exports com.example.clientspring;
}