package bike.bikeInfo;

import changescene.ChangeScene;
import control.Bike;
import control.Factory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import loginAdm.CurrentAdmin;

public class BikeInfoController {
    private Factory factory = new Factory();

    @FXML
    private Label priceLbl;

    @FXML
    private Label typeLbl;

    @FXML
    private Label makeLbl;

    @FXML
    private Label dateLbl;

    @FXML
    private Label batteryLbl;
    
    @FXML
    private Button showInfoBtn;

    @FXML
    private TextField bikeIdField;

    @FXML
    private Button bikesBtn;

    @FXML
    private Button docksBtn;

    @FXML
    private Button mapBtn;

    @FXML
    private Button statsBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button adminBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private ListView<String> repairIdListView;


    /**
     * @Author Team 007
     *
     * Displays the info about the bike described in the bikeIdField.
     * This method also fills the ListView with the repairs the bike have had in the past.
     *
     */
    @FXML
    void showInfo(){
        factory.updateSystem();


       /*


       HER MÅ MAN LEGGE TIL REPAIRS TIL LISTVIEW,VENTER PÅ MODEL SKAL LAGE GET ALL REPAIRS


        */


        for(Bike b:factory.getBikes()){System.out.println(b);}
        Bike bike = null;
        int bikeID = Integer.parseInt(bikeIdField.getText());
        for(int i = 0; i<factory.getBikes().size();i++){
            if(factory.getBikes().get(i).getBikeId() == bikeID)bike = factory.getBikes().get(i);
        }//end loop
        if(bike != null){
            String price = "" + bike.getPrice();
            String type = "" + bike.getType().getName();
            String make = "" + bike.getMake();
            String date = "" + bike.getBuyDate().toString();
            String battery = "" + bike.getPowerUsage();
            priceLbl.setText(price);
            typeLbl.setText(type);
            makeLbl.setText(make);
            dateLbl.setText(date);
            batteryLbl.setText(battery);
        }//end if
        if(bike == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Something went wrong!");
            alert.setHeaderText(null);
            alert.setContentText("Cannot find the given bike!");
            alert.showAndWait();
        }
    }//end method


    // main buttons below

    @FXML
    void changeToBikeScene(ActionEvent event) throws Exception {
        ChangeScene cs = new ChangeScene();
        cs.setScene(event, "/bike/BikeView.fxml");
    }

    @FXML
    void changeToDockScene(ActionEvent event) throws Exception {
        ChangeScene cs = new ChangeScene();
        cs.setScene(event, "/dock/DockView.fxml");
    }

    @FXML
    void changeToMapScene(ActionEvent event) throws Exception {
        ChangeScene cs = new ChangeScene();
        cs.setScene(event, "/map/MapView.fxml");
    }

    @FXML
    void changeToStatsScene(ActionEvent event) throws Exception {
        ChangeScene cs = new ChangeScene();
        cs.setScene(event, "/stats/StatsView.fxml");
    }

    @FXML
    void changeToAdminScene(ActionEvent event) throws Exception {
        ChangeScene cs = new ChangeScene();
        cs.setScene(event, "/admin/AdminView.fxml");
    }

    @FXML
    void changeToHomeScene(ActionEvent event) throws Exception {
        ChangeScene cs = new ChangeScene();
        cs.setScene(event, "/main/MainView.fxml");
    }

    @FXML
    void logOut(ActionEvent event) throws Exception {
        CurrentAdmin.getInstance().setAdmin(null);
        ChangeScene cs = new ChangeScene();
        cs.setScene(event, "/login/LoginView.fxml");

    }
}
