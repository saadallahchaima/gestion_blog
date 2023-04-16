/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Blog;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;



/**
 *
 * @author saada
 */
public class BlogCell extends ListCell<Blog> {

    private ImageView imageView = new ImageView();
    private Label titre_article = new Label();
    private Label auteur_article = new Label();
    private Label date_a = new Label();
  
   public BlogCell() {
    super();
    VBox vBox = new VBox(titre_article, auteur_article, date_a, imageView);
    HBox hBox = new HBox(imageView, vBox);
    hBox.setAlignment(Pos.CENTER);
    vBox.setAlignment(Pos.CENTER);
    hBox.setSpacing(10);
    vBox.setSpacing(5);
    setGraphic(hBox);
    titre_article.setFont(Font.font("System", FontWeight.BOLD, 14));
    titre_article.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");

    titre_article.setFont(Font.font("System", FontWeight.BOLD, 14));
    date_a.setFont(Font.font("System", FontWeight.BOLD, 12));
    date_a.setFont(Font.font("System", FontWeight.BOLD, 12));

    imageView.setFitWidth(100); // définir une largeur de 100 pixels
    imageView.setFitHeight(100);
    this.setOnMouseClicked(event -> {
        // Implémentez le code que vous souhaitez exécuter lorsque vous cliquez sur la cellule ici
        System.out.println("Cellule cliquée : " + getItem().getID());
        Blog blog = getItem();
        if (blog != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailPost.fxml"));
            Parent root;
            try {
                root = loader.load();
                DetailPostController controller = loader.getController();
                controller.updateBlogDetails(blog);
                Scene scene = new Scene(root);
                javafx.stage.Stage stage = new javafx.stage.Stage();
                stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
                stage.setTitle("Détails du blog");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(BlogCell.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
}


    @Override
    public void updateItem(Blog blog, boolean empty) {
        super.updateItem(blog, empty);
        if (empty || blog == null) {
            setText(null);
            setGraphic(null);
        } else {
            titre_article.setText("Titre Article : " + blog.getTitre_article());
          
            // Nbre_chmbreLabel.setText("Le nombre de chambre encore disponible : "+String.valueOf(cours.getNbre_chambres()));
            date_a.setText("Date : " + String.valueOf(blog.getDate()));
            Image image = new Image("file:/C:/Users/saada/OneDrive/Bureau/test_desck/175.jpg");
            imageView.setImage(image);
            setGraphic(getListCell());
        }

    }

    private HBox getListCell() {
        HBox hBox = new HBox(imageView, new VBox(titre_article,  date_a, auteur_article));
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));
        hBox.setStyle("-fx-background-color: #edece6; -fx-background-radius: 10px;");
        Separator separator = new Separator(Orientation.HORIZONTAL);
        VBox.setVgrow(separator, Priority.ALWAYS);
        VBox vBox = new VBox(hBox, separator);
        return hBox;
    }
}

     
