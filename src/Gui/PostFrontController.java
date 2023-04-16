/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Blog;
import Service.BlogService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author saada
 */
public class PostFrontController implements Initializable {

    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label lbtitre;
    @FXML
    private ImageView imgP;
    @FXML
    private Label lbauteur;
    @FXML
    private Label lbcontenu;
    @FXML
    private ScrollPane ScrolPosts;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    //private MyListener myListener;

 private ListView<Blog> listeB;
    Preferences prefs = Preferences.userNodeForPackage(PostFrontController.class);
private DetailsFrontController detailsFrontController;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Blog blog =new Blog();
      
     listeB.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
    // Récupérer l'objet Blog correspondant à l'élément sélectionné
    Blog selectedBlog = listeB.getSelectionModel().getSelectedItem();
    if (selectedBlog != null) {
        // Récupérer les détails de l'article du blog sélectionné
        String titre = selectedBlog.getTitre_article();
        String auteur = selectedBlog.getAuteur_article();
        String contenu = selectedBlog.getContenu_article();

        // Mettre à jour l'interface utilisateur avec les détails du blog sélectionné
        lbtitre.setText("Titre Article : " + titre);
        lbauteur.setText("Auteur : " + auteur);
        lbcontenu.setText("Contenu : " + contenu);
    }
});







               
        
    }   
    public void updateBlogDetails(Blog blog) {
        lbtitre.setText("Titre Article : " + blog.getTitre_article());
        lbauteur.setText("Auteur : " + blog.getAuteur_article());
        
        // Ajouter les objets Label et ImageView comme contenu du ScrollPane
        ScrolPosts.setContent(lbtitre);
        // Vous pouvez également ajouter d'autres objets ici, comme lbauteur et imgP
    }
       private List<Blog> getData() {
        List<Blog> fruits = new ArrayList<>();
        Blog fruit = new Blog();return fruits;
        
}
        Blog v = new Blog();
        
}
