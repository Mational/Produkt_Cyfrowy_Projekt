package pl.mational.rallyresulter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class EditEventController {

    @FXML
    private ListView<String> eventListView;

    @FXML
    private TextField eventNameField;

    @FXML
    private TextField roundField;

    @FXML
    private GridPane routePatternGrid;

    @FXML
    private TextField timeLimitField;

    @FXML
    private TextArea notesField;

    @FXML
    private Button saveButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button addOdcinekButton;

    @FXML
    private Button addSZButton;

    @FXML
    private Button addOtherEventButton;

    @FXML
    private Button deleteEventButton;

    private ContextMenu gridContextMenu;

    private ObservableList<String> events;

    @FXML
    public void initialize() {
        // Initialize ListView
        events = FXCollections.observableArrayList();
        eventListView.setItems(events);

        // Initialize grid with some default text fields
        initializeRoutePatternGrid();

        // Set up context menu for the grid
        setupContextMenu();

        // Attach listeners to buttons
        addOdcinekButton.setOnAction(event -> addEvent("Odcinek"));
        addSZButton.setOnAction(event -> addEvent("SZ"));
        addOtherEventButton.setOnAction(event -> addEvent("Inne"));
        deleteEventButton.setOnAction(event -> deleteSelectedEvent());

        saveButton.setOnAction(event -> saveEvent());
        closeButton.setOnAction(event -> closeWindow());

        // Attach context menu to grid
        routePatternGrid.setOnContextMenuRequested(this::showContextMenu);
    }

    private void initializeRoutePatternGrid() {
        // Clear existing children
        routePatternGrid.getChildren().clear();

        // Populate grid with default text fields
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 18; col++) {
                TextField textField = new TextField();
                textField.setPromptText("PKP");
                textField.setOnKeyPressed(this::handleKeyPress);
                routePatternGrid.add(textField, col, row);
            }
        }
    }

    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            TextField source = (TextField) event.getSource();
            int row = GridPane.getRowIndex(source) == null ? 0 : GridPane.getRowIndex(source);
            int col = GridPane.getColumnIndex(source) == null ? 0 : GridPane.getColumnIndex(source);

            int nextCol = (col + 1) % 18;
            int nextRow = nextCol == 0 ? row + 1 : row;

            if (nextRow < 8) {
                routePatternGrid.getChildren().stream()
                        .filter(node -> GridPane.getRowIndex(node) == nextRow && GridPane.getColumnIndex(node) == nextCol)
                        .findFirst()
                        .ifPresent(Node::requestFocus);
            }
        }
    }

    private void setupContextMenu() {
        gridContextMenu = new ContextMenu();
        MenuItem addField = new MenuItem("Dodaj pole");
        MenuItem deleteField = new MenuItem("Usuń pole");

        addField.setOnAction(event -> addFieldToGrid());
        deleteField.setOnAction(event -> removeFieldFromGrid());

        gridContextMenu.getItems().addAll(addField, deleteField);
    }

    private void showContextMenu(ContextMenuEvent event) {
        gridContextMenu.show(routePatternGrid, event.getScreenX(), event.getScreenY());
    }

    private void addFieldToGrid() {
        // Example: Add a new TextField to the grid dynamically
        int lastRow = routePatternGrid.getRowConstraints().size();
        int lastCol = routePatternGrid.getColumnConstraints().size();

        if (lastCol < 18) {
            TextField textField = new TextField();
            textField.setPromptText("PKP");
            routePatternGrid.add(textField, lastCol, lastRow - 1);
        } else {
            TextField textField = new TextField();
            textField.setPromptText("PKP");
            routePatternGrid.add(textField, 0, lastRow);
        }
    }

    private void removeFieldFromGrid() {
        if (!routePatternGrid.getChildren().isEmpty()) {
            routePatternGrid.getChildren().remove(routePatternGrid.getChildren().size() - 1);
        }
    }

    private void addEvent(String type) {
        String eventName = "Nowe " + type;
        events.add(eventName);
        eventListView.setItems(events);
    }

    private void deleteSelectedEvent() {
        String selectedEvent = eventListView.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            events.remove(selectedEvent);
        } else {
            showAlert("Błąd", "Nie wybrano wydarzenia do usunięcia.");
        }
    }

    private void saveEvent() {
        String name = eventNameField.getText();
        String round = roundField.getText();
        String timeLimit = timeLimitField.getText();
        String notes = notesField.getText();

        if (name.isEmpty() || round.isEmpty() || timeLimit.isEmpty()) {
            showAlert("Błąd", "Wszystkie pola muszą być wypełnione.");
            return;
        }

        System.out.println("Zapisano wydarzenie: " + name);
        System.out.println("Runda: " + round);
        System.out.println("Czas na przejazd: " + timeLimit);
        System.out.println("Uwagi: " + notes);
    }

    private void closeWindow() {
        System.out.println("Zamknięcie okna");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
