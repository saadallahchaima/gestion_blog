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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author saada
 */
public class CommentaireController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    //@FXML
   /* private void ajouterCommentaire(ActionEvent event) {
          CommentService c = new CommentService();
       BlogService b = new BlogService();
       comment co = new comment();
       Blog bl = new Blog();
      String contenu = contenu.getText();

        // Vérifier si le champ "type" n'est pas vide
        if (!type.isEmpty()) {
            try {
               
                c.setType(type);
               
                cat.AjouterCategorie(c);
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("ajout avec succes");
                alert.showAndWait();
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
    }*/

    @FXML
    private void clearCom(ActionEvent event) {
    }
    
}
