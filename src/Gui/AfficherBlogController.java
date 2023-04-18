/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Blog;
import Service.BlogService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author saada
 */
public class AfficherBlogController implements Initializable {

    @FXML
    private ListView<Blog> listeB;
    Preferences prefs = Preferences.userNodeForPackage(AfficherBlogController.class);
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label lbcontenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        BlogService b = new BlogService();

        listeB.setCellFactory(param -> new BlogCell());
        BlogService blogS = new BlogService();
        List<Blog> blog = blogS.Recuperer();
        ObservableList<Blog> items = FXCollections.observableArrayList(blog);
        listeB.setItems(items);
        listeB.setItems(items);
        // 2. Créez une ArrayList de maps pour stocker les attributs de chaque hôtel
        // Définition de l'écouteur d'événement de clic sur la cellule
        listeB.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            // Récupérer l'index de l'élément sélectionné
            int selectedIndex = newValue.intValue();
            // Récupérer l'objet Hotel correspondant à cet index
            Blog selectedPost = blog.get(selectedIndex);
            // Récupérer l'ID de l'hôtel
            int postId = selectedPost.getID();
            prefs.putInt("selecteBlogID", postId);
            System.out.println(postId);

        });

    }

    @FXML
    private void AjouterBlog(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Article.fxml"));
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
    private void modifierBlog(MouseEvent event) {
        Blog blog = listeB.getSelectionModel().getSelectedItem();
        Blog h1 = new Blog();
        BlogService hs = new BlogService();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierBlog.fxml"));
            Parent root = loader.load();
            ModifierBlogController modifierBlogController = loader.getController();
            h1 = blog;
            modifierBlogController.ModifyData(h1);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
        }

    }

    @FXML
    private void AjouterC(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieAXML.fxml"));
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
    private void listeCom(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCom.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(AfficherBlogController.class.getName()).log(Level.SEVERE, null, ex);
        }}

    @FXML
    private void StatLikes(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("stat.fxml"));
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
    private void StatCom(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("statCom.fxml"));
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
    private void populatedArticle(ActionEvent event) {
        BlogService b =new BlogService();
        int limit = 10;
        List<Blog> articles = b.getArticlesPopulairesParCommentaires(limit); // Remplacez 'limit' par la valeur souhaitée pour limiter le nombre d'articles retournés

    // Créer une liste de chaînes pour stocker les titres des articles
   
    
    
    // Appliquer la liste de titres à la ListView
    ObservableList<Blog> observableArticles = FXCollections.observableArrayList(articles);
    
    // Appliquer la liste observable à la ListView
    listeB.setItems(observableArticles);
    
       
    }
  
}
