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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author saada
 */
public class DetailPostController implements Initializable {

    @FXML
    private Label titre_article;
    private Label date_a;
    private Label auteur_article;
    @FXML
    private Button modif;
    @FXML
    private Button sup;
    @FXML
    private TextField tftitre;
    @FXML
    private TextField tfauteur;
    @FXML
    private TextField tfdate;
    @FXML
    private TextArea tfcontenu;
    @FXML
    private TextField tfbest;
    @FXML
    private TextField tflikes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public DetailPostController() {
    // Initialize the controller
}
 public  DetailPostController(Blog blog) {
 

        
       
 }
 
public void updateBlogDetails(Blog blog) {
    tftitre.setText("Titre Article : " + blog.getTitre_article());
    tfcontenu.setText("Contenu: " + blog.getContenu_article());
   tfdate.setText("Date : " + String.valueOf(blog.getDate()));
   tfauteur.setText("Auteur: " + blog.getAuteur_article());
if (blog.getIs_best() == 1) {
        tfbest.setText("Sélection : isBest");
    } else {
        tfbest.setText("Sélection : notBest");
    }




}
  
    @FXML
    private void modifier(ActionEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) {
    }

    private void commenter(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCom.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(AfficherBlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      
        
        
        
        
        
        
        
        
        
    }



        
    }

   

    
    

