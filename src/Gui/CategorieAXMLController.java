/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Blog;
import Entity.categorieA;
import Service.CategorieService;
import Util.MyDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author saada
 */
public class CategorieAXMLController implements Initializable {

    ObservableList<categorieA> List = FXCollections.observableArrayList();
    @FXML
    private TextField type_a;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<categorieA> table;
    @FXML
    private TableColumn<categorieA, Integer> id;
    @FXML
    private TableColumn<categorieA, String> name;
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
        name.setCellValueFactory(new PropertyValueFactory<>("type"));

        table.setItems(FXCollections.observableArrayList(cat.Recuperer()));
        aff();

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
                table.setItems(List);
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
                // Définir la propriété "type" de l'objet categorieA
                c.setType(type);
                // Appeler la méthode AjouterCategorie pour ajouter la nouvelle catégorie
                cat.AjouterCategorie(c);
                // Mettre à jour la table avec les données récupérées
                List<categorieA> listeCategories = cat.Recuperer(); // Récupérer la liste de catégories
                ObservableList<categorieA> observableListeCategories = FXCollections.observableArrayList(listeCategories); // Convertir en ObservableList
                table.setItems(observableListeCategories);// Mettre à jour la table avec l'ObservableList

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
            // Afficher un message d'erreur à l'utilisateur si le champ "type" est vide
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText("Champ 'Type' vide");
            alert.setContentText("Le champ 'Type' ne peut pas être vide. Veuillez entrer une valeur.");
            alert.showAndWait();
        }
    }

    @FXML
    private void Update(ActionEvent event) {
        String nom = type_a.getText();

        if (!isValidString(nom)) {
            // Affiche un message d'erreur pour les chaînes de caractères invalides
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Les champs nom , description et utilisation doivent contenir uniquement des lettres.");
            alert.showAndWait();
            return;
        }
        categorieA c = new categorieA();
        c.setType(nom);

        cat.ModifierCo(c);
        aff();

        // Affiche un message d'information pour indiquer que l'ajout a réussi
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajout réussi");
        alert.setHeaderText(null);
        alert.setContentText("La catégorie a été modifiee avec succès.");
        alert.showAndWait();
    }

    public void aff() {

        name.setCellValueFactory(new PropertyValueFactory<>("type_a"));

        table.setItems(FXCollections.observableArrayList(cat.Recuperer()));
    }

    private boolean isValidString(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        // Vérifie si la chaîne ne contient que des lettres
        return value.matches("^[a-zA-Z]+$");
    }

    @FXML
    private void Delete(ActionEvent event
    ) {
        String nom = type_a.getText();

        categorieA c = new categorieA();
        c.setType(nom);

        cat.supprimer(c);
        aff();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("supression reussi");
        alert.setHeaderText(null);
        alert.setContentText("Le modèle a été supprimée avec succès.");
        alert.showAndWait();

    }

    @FXML
    private void Back(ActionEvent event
    ) {
    }

    @FXML
    private void Search(MouseEvent event
    ) {
    }

    @FXML
    private void Search(ActionEvent event
    ) {
    }

    @FXML
    private void clickTable(KeyEvent event
    ) {
    }

    @FXML
    private void clickTable(MouseEvent event
    ) {
        try {
            categorieA C = table.getSelectionModel().getSelectedItem();
            name.setText(C.getType());

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    private void loadCateg() {

        CategorieService c = new CategorieService();
        connection = MyDB.getInsatnce().getConnection();
        refresh();

        name.setCellValueFactory(new PropertyValueFactory<>("type_a"));
        System.out.println(name.getText());

    }

    @FXML
    private void Actualiser(ActionEvent event) {

        loadCateg();
        refresh();
    }

    public void clickTable(Event e) {
        categorieA categorie = (categorieA) table.getSelectionModel().getSelectedItem();
        name.setText(categorie.getType());

    }

    @FXML
    private void clear(ActionEvent event) {
    }

}
