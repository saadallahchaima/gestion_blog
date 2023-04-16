/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Blog;
import Entity.categorieA;
import Entity.comment;
import Service.CommentService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author saada
 */
public class CommentDetailsController implements Initializable {

    @FXML
    private Label lbnom;
    @FXML
    private Label lbemail;
    @FXML
    private Label lbdate;
    @FXML
    private Label lbcom;
    @FXML
    private CheckBox checkapproved;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void updatecomDetails(comment co) {
      
   lbnom.setText(co.getNom_c());
        lbemail.setText(co.getEmail());
        lbdate.setText(co.getDate_com().toString());
        lbcom.setText(co.getContenu_c());
        checkapproved.setSelected(co.getApproved() == 1);
   
}

@FXML
private void updatecom(ActionEvent event) {
    // Récupérer l'objet commentaire associé aux données affichées dans les labels
    CommentService cc = new CommentService();
    comment c = new comment();
   
    List<String> listeModifiable = new ArrayList<>();
    listeModifiable.add("Option 1");
listeModifiable.add("Option 2");
for (String element : listeModifiable) {
    CheckBox checkBox = new CheckBox(element);
  
        if (checkBox.isSelected()) {
           cc.ModifierCommentaire(c);
        } else {
            // Code à exécuter lorsque la case à cocher est décochée
            // Mettre à jour la liste modifiable en conséquence
        }
   
    // Ajouter la case à cocher à votre interface utilisateur
    // ...
}listeModifiable.add("Option 4"); // Ajouter un nouvel élément à la liste
listeModifiable.remove("Option 2");

}


}




