/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Blog;
import Entity.comment;
import Service.BlogService;
import Service.CommentService;
import java.awt.Color;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * FXML Controller class
 *
 * @author saada
 */
public class blogDetailsView extends VBox {

    private ImageView imageView = new ImageView();
    private Label titre_article = new Label();
    private Label auteur_article = new Label();
    private Label date_a = new Label();
    private Label contenu_c = new Label();
    private Button modifierButton = new Button("Modifier");
    private Button supprimerButton = new Button("Supprimer");
    private Button commenterButton = new Button("Commenter");
    private TextField nom = new TextField();
    private TextField commentaire = new TextField();
       private TextField email_c = new TextField();

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public blogDetailsView(Blog blog) {
        super();
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER); // Centrer horizontalement
        this.setAlignment(Pos.CENTER); // Centrer verticalement
        this.setPadding(new Insets(10));
        this.setStyle("-fx-background-color: white;");

        this.setPrefSize(800, 600); // Définir une taille préférée plus grande pour la VBox
        // Définir les styles CSS pour les labels
        titre_article.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        auteur_article.setStyle("-fx-text-fill: #0066cc; -fx-font-weight: bold;"); // Couleur bleue pour le texte
        date_a.setStyle("-fx-text-fill: #009933;"); // Couleur verte pour le texte
        contenu_c.setStyle("-fx-text-fill: #333333;"); // Couleur sombre pour le texte

        titre_article.setText("Titre Article : " + blog.getTitre_article());
        auteur_article.setText("Auteur Article : " + blog.getAuteur_article());
        date_a.setText("Date : " + String.valueOf(blog.getDate()));
        contenu_c.setText("Contenu : " + blog.getContenu_article());
        // ... autres configurations pour les éléments d'interface utilisateur
        BlogService bl = new BlogService();

        modifierButton.setStyle("-fx-background-color: #0066cc; -fx-text-fill: white; -fx-font-weight: bold;"); // Définir le style du bouton Modifier
        supprimerButton.setStyle("-fx-background-color: #cc0000; -fx-text-fill: white; -fx-font-weight: bold;"); // Définir le style du bouton Supprimer
        commenterButton.setStyle("-fx-background-color: #009933; -fx-text-fill: white; -fx-font-weight: bold;"); // Définir le style du bouton Commenter

        modifierButton.setOnAction(event -> {

        });

        supprimerButton.setOnAction(event -> {
            // Gestionnaire d'événements pour le bouton Supprimer
            // Implémentez votre logique pour la suppression du blog ici
            System.out.println("Bouton Supprimer cliqué pour le blog : " + blog);
        });

        commenterButton.setOnAction(event -> {
            // Gestionnaire d'événements pour le bouton Commenter
            // Implémentez votre logique pour les commentaires du blog ici
            System.out.println("Bouton Commenter cliqué pour le blog : " + blog);
        });

        this.getChildren().addAll(titre_article, auteur_article, date_a, contenu_c, imageView);
    }

    public void updateBlogDetails(Blog blog) {
        titre_article.setText("Titre Article : " + blog.getTitre_article());
        contenu_c.setText("Contenu: " + blog.getContenu_article());
        date_a.setText("Date : " + String.valueOf(blog.getDate()));
        // ... autres mises à jour d'interface utilisateur

        // Ajouter les boutons Modifier, Supprimer et Commenter
        Button modifierButton = new Button("Modifier");
        Button supprimerButton = new Button("Supprimer");
        Button commenterButton = new Button("Commenter");
        BlogService bb = new BlogService();

        modifierButton.setOnAction(event -> {

        });

        supprimerButton.setOnAction(event -> {
            // Gestionnaire d'événements pour le bouton Supprimer
            // Implémentez votre logique pour la suppression du blog ici
            BlogService bbb = new BlogService();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer ce blog ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                System.out.println("Bouton Supprimer cliqué pour le blog : " + blog);
                bbb.SupprimerBlog(blog.getID()); // Remplacez "getId()" avec la méthode appropriée pour obtenir l'ID du blog
            } else {
                // L'utilisateur a cliqué sur "Annuler" ou a fermé la boîte de dialogue
                // Vous pouvez ajouter un traitement supplémentaire ici si nécessaire
            }
        });

        commenterButton.setOnAction(event -> {
     comment c = new comment();

        this.setSpacing(10);
        this.setAlignment(Pos.CENTER); // Centrer horizontalement
        this.setAlignment(Pos.CENTER); // Centrer verticalement
        this.setPadding(new Insets(10));
        this.setStyle("-fx-background-color: white;");

        this.setPrefSize(800, 600); // Définir une taille préférée plus grande pour la VBox
        // Définir les styles CSS pour les labels
       nom.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
       commentaire.setStyle("-fx-text-fill: #0066cc; -fx-font-weight: bold;"); // Couleur bleue pour le texte
       email_c.setStyle("-fx-text-fill: #0066cc; -fx-font-weight: bold;"); // Couleur bleue pour le texte
        String nomText = nom.getText();
    String commentaireText = commentaire.getText();
    String emailText = email_c.getText();
       nom.setText("Titre Article : " +c.getNom_c() );
       commentaire.setText("Auteur Article : " + c.getContenu_c());
             email_c.setText("Auteur Article : " + c.getEmail());
             
             CommentService cc = new CommentService();    
    comment cccc = new comment(nomText, commentaireText, emailText);

             cc.AjouterCo(cccc);


        });

        this.getChildren().addAll(modifierButton, supprimerButton, commenterButton);
    }

}
