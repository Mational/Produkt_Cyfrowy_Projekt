package pl.mational.rallyresulter.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import pl.mational.rallyresulter.model.Crew;
import pl.mational.rallyresulter.model.CrewMember;

import static pl.mational.rallyresulter.main.RallyResulterApplication.crewList;

public class PanelCrewListController {

    @FXML
    private TableView<CrewWrapper> tableView;

    @FXML
    private TableColumn<CrewWrapper, String> nrColumn;

    @FXML
    private TableColumn<CrewWrapper, String> kierowcaColumn;

    @FXML
    private TableColumn<CrewWrapper, String> pilotColumn;

    private ObservableList<CrewWrapper> observableCrewList;

    @FXML
    public void initialize() {
        // Initialize observable list with crew data
        observableCrewList = FXCollections.observableArrayList();
        for (Crew crew : crewList) {
            observableCrewList.add(new CrewWrapper(crew));
        }

        tableView.setItems(observableCrewList);

        // Ustawinie Nr
        nrColumn.setCellValueFactory(data -> data.getValue().crewIdProperty());

        // Ustawinie Kierowcy
        kierowcaColumn.setCellValueFactory(data -> data.getValue().driverProperty());
        kierowcaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        kierowcaColumn.setOnEditCommit(event -> {
            CrewWrapper crew = event.getRowValue();
            crew.setDriver(event.getNewValue());
            saveChangesToModel();
        });

        // Ustawinie Pilota
        pilotColumn.setCellValueFactory(data -> data.getValue().pilotProperty());
        pilotColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        pilotColumn.setOnEditCommit(event -> {
            CrewWrapper crew = event.getRowValue();
            crew.setPilot(event.getNewValue());
            saveChangesToModel();
        });

        tableView.setEditable(true);
    }

    private void saveChangesToModel() {
        crewList.clear();
        for (CrewWrapper wrapper : observableCrewList) {
            crewList.add(wrapper.toCrew());
        }
    }

    public static class CrewWrapper {
        private final SimpleStringProperty crewId;
        private final SimpleStringProperty driver;
        private final SimpleStringProperty pilot;

        public CrewWrapper(Crew crew) {
            this.crewId = new SimpleStringProperty(String.valueOf(crew.crewId()));
            this.driver = new SimpleStringProperty(crew.driver().firstName() + " " + crew.driver().lastName());
            this.pilot = new SimpleStringProperty(crew.pilot().firstName() + " " + crew.pilot().lastName());
        }

        public SimpleStringProperty crewIdProperty() {
            return crewId;
        }

        public void setDriver(String driver) {
            this.driver.set(driver);
        }

        public SimpleStringProperty driverProperty() {
            return driver;
        }

        public void setPilot(String pilot) {
            this.pilot.set(pilot);
        }

        public SimpleStringProperty pilotProperty() {
            return pilot;
        }

        public Crew toCrew() {
            String[] driverName = driver.get().split(" ", 2);
            String[] pilotName = pilot.get().split(" ", 2);
            return new Crew(Integer.parseInt(crewId.get()),
                    new CrewMember(driverName[0], driverName[1], null, null),
                    new CrewMember(pilotName[0], pilotName[1], null, null),
                    null);
        }
    }
}
