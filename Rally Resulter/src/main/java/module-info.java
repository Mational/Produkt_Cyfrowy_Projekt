module RallyResult {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires kernel;
    requires layout;
    requires io;
    requires javafx.graphics;

    // Udostępnienie pakietu z klasą główną
    opens pl.mational.rallyresulter.main to javafx.graphics, com.fasterxml.jackson.databind;

    // Udostępnienie pakietu controller dla FXML, jeśli pliki FXML korzystają z kontrolerów
    opens pl.mational.rallyresulter.controller to javafx.fxml;
    opens pl.mational.rallyresulter.model to com.fasterxml.jackson.databind;

    // Eksport pakietów, które chcesz udostępnić innym modułom
    exports pl.mational.rallyresulter.main;
    exports pl.mational.rallyresulter.controller;
    exports pl.mational.rallyresulter.model;
}
