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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author saada
 */
public class ShowPostsController implements Initializable {

    @FXML
    private Button tfquoideneuf;
    @FXML
    private VBox tfpostlist;
        private List<Blog> postsList; // Déclarer une liste de Post
 // VBox pour afficher les cellules de post dans le ScrollPane
         @FXML
    private VBox postCellContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                postsList = new ArrayList<>();
                 ScrollPane scrollPane = new ScrollPane(postCellContainer);
        // Définir les propriétés de la ScrollPane si nécessaire (ex : barre de défilement automatique)

        // Ajouter la ScrollPane au tfpostlist
        tfpostlist.getChildren().add(scrollPane);

                
    } 
 // Méthode pour afficher les détails d'un post lorsque sa cellule est cliquée
    private void showPostDetails(Blog post) {
        // Effacer le contenu précédent de la VBox
        tfpostlist.getChildren().clear();

        // Créer des contrôles JavaFX pour afficher les détails du post
        Label titleLabel = new Label(post.getTitre_article());
        Label auterLabel = new Label(post.getAuteur_article());
        Label contenuLabel = new Label(post.getContenu_article());
        Label imageLabel = new Label(post.getImage());

        // Ajouter les contrôles à la VBox
        tfpostlist.getChildren().addAll(titleLabel, auterLabel, contenuLabel, imageLabel);
    }

 @FXML
private void redirect(ActionEvent event) {
    // Appeler la fonction afficher() du serviceBlog pour charger les données des posts
    BlogService b = new BlogService();
    List<Blog> postsList = b.Recuperer(); // Remplacez ServiceBlog par le nom réel de votre service

    // Effacer le contenu actuel de la VBox
    tfpostlist.getChildren().clear();

    // Parcourir la liste des posts chargés et les afficher dans la VBox
    for (Blog post : postsList) {
        // Créer un contrôle JavaFX pour représenter les propriétés du post
        // et les ajouter à la VBox
        // Exemple avec un Label pour le titre du post
        Label titleLabel = new Label(post.getTitre_article());
        Label auterLabel = new Label(post.getAuteur_article());
       // Label contenuLabel = new Label(post.getContenu_article());
        Label imageLabel = new Label(post.getImage());
        tfpostlist.getChildren().addAll(titleLabel, auterLabel,  imageLabel);

         tfpostlist.getChildren().addAll(titleLabel, auterLabel, imageLabel);

        // Ajouter un EventHandler pour le clic sur le post afin d'afficher les détails du post
               // titleLabel.setOnMouseClicked(event -> handlePostClick(event, post));
                titleLabel.setMouseTransparent(true);

        
}

    
}

    private void handlePostClick(MouseEvent event, Blog post) {
            System.out.println("Titre : " + post.getTitre_article());
    System.out.println("Auteur : " + post.getAuteur_article());
    System.out.println("Contenu : " + post.getContenu_article());
    System.out.println("Image : " + post.getImage());
    }
}
