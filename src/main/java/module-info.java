module com.example.codex {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;


    opens com.example.codex to javafx.fxml;
    exports com.example.codex;
}