/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Blog;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author saada
 */
public class DetailsFrontController implements Initializable {

    @FXML
    private Label lbtitre;
    @FXML
    private Label lbauteur;
    @FXML
    private ImageView imgP;

    /**
     * Initializes the controller class.
     */
 
         private Blog fruit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
  
public void updateBlogDetails(Blog blog) {
 lbtitre.setText("Titre Article: " + blog.getTitre_article());
        lbauteur.setText("Auteur: " + blog.getAuteur_article());
  
}
    @FXML
    private void click(MouseEvent event) {
                

    }
    
    }
    

