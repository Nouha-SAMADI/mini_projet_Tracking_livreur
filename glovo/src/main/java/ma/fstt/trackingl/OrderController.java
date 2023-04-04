package ma.fstt.trackingl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ma.fstt.model.*;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    @FXML private DatePicker date;
    @FXML private TextField adresse;
    @FXML private ChoiceBox<String> produit;
    @FXML
    private TableView<Commande> mytable ;


    @FXML
    private TableColumn<Commande ,Long> id_commande ;



    @FXML
    private TableColumn <Commande , Date> date_col ;

    @FXML
    private TableColumn<Commande ,String> adrLiv;
    @FXML
    private TableColumn <Produit ,Long> id_produit ;


    @FXML
    private TableColumn <Produit ,String> nom ;

    @FXML private Button ajouter;
    @FXML private Button modifier;
    @FXML private Button supprimer;
    @FXML private Button annuler;
    @FXML
    private Button back;



    @FXML
    protected void onSaveButtonClick() {

        // accees a la bdd

        // Access the database
        try {
            LocalDate dat = date.getValue();

            CommandeDAO cmdDAO = new CommandeDAO();


            // Create a new Commande object and save it to the database
            Commande cmd = new Commande(0l,dat,adresse.getText(), (List<Produit>) produit);
            cmdDAO.save(cmd);

            UpdateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void addProduit(MouseEvent event) {


    }
    public void UpdateTable(){

        id_commande.setCellValueFactory(new PropertyValueFactory<Commande,Long>("id_commande"));
        date_col.setCellValueFactory(new PropertyValueFactory<Commande,Date>("date"));
        adrLiv.setCellValueFactory(new PropertyValueFactory<Commande,String>("adresse_livraison"));






        mytable.setItems(this.getDataCommandes());

    }

    private ObservableList<Commande> getDataCommandes() {
        CommandeDAO cmdDAO = null;

        ObservableList<Commande> listfx = FXCollections.observableArrayList();

        try {
            cmdDAO = new CommandeDAO();
            for (Commande ettemp : cmdDAO.getAll())
                listfx.add(ettemp);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UpdateTable();
        modifier.setDisable(true);
        supprimer.setDisable(true);
    }
    public void tableClick(javafx.scene.input.MouseEvent mouseEvent) {
        Commande cmd = mytable.getSelectionModel().getSelectedItem();



        ajouter.setDisable(true);
        modifier.setDisable(false);
        supprimer.setDisable(false);

    }
    @FXML
    void goBackToMenu(ActionEvent event) {
        try {
            // Load the FXML file for the new scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = loader.load();

            // Create a new scene with the root node
            Scene scene = new Scene(root);

            // Get the current stage and set the new scene
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
