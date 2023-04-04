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
import javafx.stage.Stage;
import ma.fstt.model.Livreur;
import ma.fstt.model.LivreurDAO;
import ma.fstt.model.Produit;
import ma.fstt.model.ProduitDAO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    @FXML
    private TextField nom ;


    @FXML
    private TextArea description ;

    @FXML
    private TextField quantite ;

    @FXML
    private TextField prix ;
    @FXML
    private Button back ;

    @FXML
    private TableView<Produit> mytable ;


    @FXML
    private TableColumn<Produit ,Long> col_id ;

    @FXML
    private TableColumn <Produit ,String> col_nom ;

    @FXML
    private TableColumn <Produit ,String> col_description ;
    @FXML
    private TableColumn <Produit ,Integer> col_qty ;
    @FXML
    private TableColumn <Produit ,Double> col_prix ;
    @FXML private Button ajouter;
    @FXML private Button modifier;
    @FXML private Button supprimer;
    @FXML private Button annuler;


    @FXML
    protected void onSaveButtonClick() {

        // accees a la bdd

        try {
            ProduitDAO produitDAO = new ProduitDAO();

            Produit prod = new Produit(0l , nom.getText() , description.getText(),Integer.parseInt(quantite.getText()),Double.parseDouble(prix.getText()));

            produitDAO.save(prod);


            UpdateTable();

            nom.setText("");
            description.setText("");
            quantite.setText("");
            prix.setText("");



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Produit,Long>("id_produit"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
        col_description.setCellValueFactory(new PropertyValueFactory<Produit,String>("description"));
        col_qty.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("quantite"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Produit,Double>("prix"));





        mytable.setItems(this.getDataProduits());
    }

    public static ObservableList<Produit> getDataProduits(){

        ProduitDAO produitDAO = null;

        ObservableList<Produit> listfx = FXCollections.observableArrayList();

        try {
            produitDAO = new ProduitDAO();
            for (Produit ettemp : produitDAO.getAll())
                listfx.add(ettemp);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
        modifier.setDisable(true);
        supprimer.setDisable(true);

    }
    @FXML
    protected void onDeleteButtonClick() {

        Produit selectedProduct = mytable.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            // No item selected, display error message
            System.out.println("Error: no item selected for deletion");

        }

        try {
            ProduitDAO produitDAO = new ProduitDAO();
            produitDAO.delete(selectedProduct);
            UpdateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    protected void onUpdateButtonClick() {
        Produit selectedProduct = mytable.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            // No item selected, display error message
            System.out.println("Error: no item selected for update");
        } else {
            try {
                // Get the updated name and telephone values
                String updatedNom = nom.getText();
                String updatedDescription = description.getText();
                int updatedQty= Integer.parseInt(quantite.getText());
                double updatedPrice= Double.parseDouble(prix.getText());

                // Create a new Livreur object with the updated values and the same ID as the selected Livreur
                Produit updatedProduct = new Produit(selectedProduct.getId_produit(), updatedNom, updatedDescription,updatedQty,updatedPrice);

                // Update the Livreur in the database
                ProduitDAO produitDAO = new ProduitDAO();
                produitDAO.update(updatedProduct);

                // Update the table
                UpdateTable();

                // Clear the text fields and disable the modifier and supprimer buttons
                nom.setText("");
                description.setText("");
                quantite.setText("");
                prix.setText("");

                ajouter.setDisable(false);
                modifier.setDisable(true);
                supprimer.setDisable(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @FXML
    protected  void reset(ActionEvent event){
        ajouter.setDisable(false);
        modifier.setDisable(true);
        supprimer.setDisable(true);
        nom.setText("");
        description.setText("");
        quantite.setText("");
        prix.setText("");
    }

    public void tableClick(javafx.scene.input.MouseEvent mouseEvent) {
        Produit produit = mytable.getSelectionModel().getSelectedItem();
        nom.setText(produit.getNom());
        description.setText(produit.getDescription());
        quantite.setText(String.valueOf(produit.getQuantite()));
        prix.setText(String.valueOf(produit.getPrix()));


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
