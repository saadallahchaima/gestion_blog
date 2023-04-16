/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Blog;
import Entity.comment;
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
public class ComCell extends ListCell<comment> {

    private Label lbarticle= new Label();
    private Label lbcontenu = new Label();
    private Label lbdate = new Label();
      private Label lbemail = new Label();
        private Label lbnomC = new Label();
    
  
   public ComCell() {
    super();
    VBox vBox = new VBox(lbarticle, lbcontenu , lbdate ,lbemail);
    HBox hBox = new HBox(lbnomC, vBox);
    hBox.setAlignment(Pos.CENTER);
    vBox.setAlignment(Pos.CENTER);
    hBox.setSpacing(10);
    vBox.setSpacing(5);
    setGraphic(hBox);
    lbarticle.setFont(Font.font("System", FontWeight.BOLD, 14));
  lbarticle.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");

   lbarticle.setFont(Font.font("System", FontWeight.BOLD, 14));
    lbdate.setFont(Font.font("System", FontWeight.BOLD, 12));
   lbdate.setFont(Font.font("System", FontWeight.BOLD, 12));

  
    this.setOnMouseClicked(event -> {
        // Implémentez le code que vous souhaitez exécuter lorsque vous cliquez sur la cellule ici
        System.out.println("Cellule cliquée : " + getItem().getNom_c());
        comment blog = getItem();
        if (blog != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CommentDetails.fxml"));
            Parent root;
            try {
                root = loader.load();
                DetailPostController controller = loader.getController();
                //controller.updateBlogDetails(blog);
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
    });}


    
    @Override
    public void updateItem(comment blog, boolean empty) {
        super.updateItem(blog, empty);
        if (empty || blog == null) {
            setText(null);
            setGraphic(null);
        } else {
           lbarticle.setText("Titre Article : " + blog.getContenu_c());
          
            // Nbre_chmbreLabel.setText("Le nombre de chambre encore disponible : "+String.valueOf(cours.getNbre_chambres()));
            lbdate.setText("Date : " + String.valueOf(blog.getDate_com()));
            Image image = new Image("file:/C:/Users/saada/OneDrive/Bureau/test_desck/175.jpg");
            setGraphic(getListCell());
        }

    }

    private HBox getListCell() {
        HBox hBox = new HBox(lbnomC, new VBox(lbarticle, lbcontenu , lbdate ,lbemail));
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));
        hBox.setStyle("-fx-background-color: #edece6; -fx-background-radius: 10px;");
        Separator separator = new Separator(Orientation.HORIZONTAL);
        VBox.setVgrow(separator, Priority.ALWAYS);
        VBox vBox = new VBox(hBox, separator);
        return hBox;
    }
}

     
