/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Blog;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author saada
 */
public class DetailPostController implements Initializable {

    @FXML
    private ImageView image_article;
    @FXML
    private TextArea contenu_c;
    @FXML
    private Label titre_article;
    @FXML
    private Label date_a;
    @FXML
    private Label auteur_article;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

 public  DetailPostController(Blog blog) {
      titre_article.setText("Titre Article : " + blog.getTitre_article());
        auteur_article.setText("Auteur Article : " + blog.getAuteur_article());
        date_a.setText("Date : " + String.valueOf(blog.getDate()));
        contenu_c.setText("Contenu : " + blog.getContenu_article());

        
       
 }
 
public void updateBlogDetails(Blog blog) {
     titre_article.setText("Titre Article : " + blog.getTitre_article());
   contenu_c.setText("Contenu: " + blog.getContenu_article());
   date_a.setText("Date : " + String.valueOf(blog.getDate()));

}

    public DetailPostController(ImageView image_article, TextArea contenu_c, Label titre_article, Label date_a, Label auteur_article) {
        this.image_article = image_article;
        this.contenu_c = contenu_c;
        this.titre_article = titre_article;
        this.date_a = date_a;
        this.auteur_article = auteur_article;
    }

  
    @FXML
    private void modifier(ActionEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) {
    }
    
}
