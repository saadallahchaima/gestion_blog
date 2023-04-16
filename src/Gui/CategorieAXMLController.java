/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Blog;
import Entity.categorieA;
import Service.BlogService;
import Service.CategorieService;
import Util.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author saada
 */
public class CategorieAXMLController implements Initializable {

    ObservableList<categorieA> List = FXCollections.observableArrayList();
    @FXML
    private AnchorPane tableCateg;
    @FXML
    private TableView<categorieA> tab;

    @FXML
    private TextField type_a;
    @FXML
    private TableColumn<categorieA, String> ColType;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button Menu;
    CategorieService cat = new CategorieService();
    String query = null;
    Connection connection = null;
    Connection cnx = MyDB.getInsatnce().getConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ColType.setCellValueFactory(new PropertyValueFactory<>("type"));

        List<categorieA> listePosts = loadPostsFromDatabase();

        tab.setItems(FXCollections.observableArrayList(listePosts));

    }

    private List<categorieA> loadPostsFromDatabase() {
        List<categorieA> categg = new ArrayList<>();

        CategorieService catgService = new CategorieService();
        categg = catgService.Recuperer();

        return categg;
    }

    private void refresh() {
        try {
            List.clear();

            String query = "select * from categorie_a ";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                List.add(new categorieA(
                        resultSet.getInt("id"),
                        resultSet.getString("type_a")
                ));
                // tableCateg.setItems(List);
            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
    }

    @FXML
    private void Add(ActionEvent event) {
        categorieA c = new categorieA();
        String type = type_a.getText();

        // Vérifier si le champ "type" n'est pas vide
        if (!type.isEmpty()) {
            try {

                c.setType(type);

                cat.AjouterCategorie(c);

                List<categorieA> listeCategories = cat.Recuperer(); // Récupérer la liste de catégories
                ObservableList<categorieA> observableListeCategories = FXCollections.observableArrayList(listeCategories); // Convertir en ObservableList
                tab.setItems(observableListeCategories);// Mettre à jour la table avec l'ObservableList
                tab.refresh();
            } catch (Exception e) {
                // Gérer les exceptions en cas d'erreur lors de la création de la catégorie
                e.printStackTrace();
                // Afficher un message d'erreur à l'utilisateur
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de l'ajout de la catégorie");
                alert.setContentText("Une erreur s'est produite lors de l'ajout de la catégorie. Veuillez réessayer.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText("Champ 'Type' vide");
            alert.setContentText("Le champ 'Type' ne peut pas être vide. Veuillez entrer une valeur.");
            alert.showAndWait();
        }
    }

    @FXML
    private void Update(ActionEvent event) {

        categorieA c = new categorieA();
        c = tab.getSelectionModel().getSelectedItem();
        c.setId(tab.getSelectionModel().getSelectedItem().getId());
        c.setType(type_a.getText());

        cat.ModifierCo(c);

        tab.refresh();
        refresh();
    }

    private boolean isValidString(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        // Vérifie si la chaîne ne contient que des lettres
        return value.matches("^[a-zA-Z]+$");
    }
 public void delete() {
      cat.SupprimerCat(tab.getSelectionModel().getSelectedItem().getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("supression reussi");
        alert.setHeaderText(null);
        alert.setContentText("la categorie a été supprimée avec succès.");
        alert.showAndWait();
        System.out.println(tab.getSelectionModel().getSelectedItem().getId());
    }
    @FXML
    private void Delete(ActionEvent event
    ) {
        String nom = type_a.getText();


      
          delete();
       tab.getItems().removeAll(tab.getSelectionModel().getSelectedItem());
        System.out.println(tab);
        
       tab.refresh();

    }

    private void loadCateg() {

        CategorieService c = new CategorieService();
        connection = MyDB.getInsatnce().getConnection();
        refresh();

        ColType.setCellValueFactory(new PropertyValueFactory<>("type_a"));
        System.out.println(ColType.getText());

    }

    @FXML
    private void Actualiser(ActionEvent event) {

        //loadCateg();
        //refresh();
        List<categorieA> nouvellesDonnees = loadPostsFromDatabase();
        tab.setItems(FXCollections.observableArrayList(nouvellesDonnees));

        // Activer ou désactiver le bouton "Actualiser" en fonction de la logique d'actualisation
        // Actualiser.setDisable(false); // ou true pour le désactiver
    }

    @FXML
    void Menu(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherBlog.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(AfficherBlogController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void TabCatg(MouseEvent event) {

        categorieA t = tab.getSelectionModel().getSelectedItem();
        type_a.setText(t.getType());

    }

}
