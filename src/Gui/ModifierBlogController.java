/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Blog;
import Service.BlogService;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author saada
 */
public class ModifierBlogController implements Initializable {

    @FXML
    private Label titre_article;
    @FXML
    private TextField auteur_article;
    @FXML
    private ImageView image_article;
    @FXML
    private TextArea contenu_c;
    @FXML
    private TextField date_a;
      Preferences userP = Preferences.userNodeForPackage(ModifierBlogController.class);
                String Id = userP.get("selectedHotelId", "..") ;
    @FXML
    private TextField url_image;
    private Blog Blog ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void ModifyData(Blog Blog) {
        
  this.Blog = Blog;
        titre_article.setText(Blog.getTitre_article());
        auteur_article.setText(Blog.getAuteur_article());
       // date_a.setValue(Blog.getDate().toLocalDate());
        contenu_c.setText(Blog.getContenu_article());  
            url_image.setText(Blog.getImage());  

    }

    @FXML
    private void modifier(ActionEvent event) {
         Blog b = new Blog();
    BlogService sc = new BlogService();
    String titreb = titre_article.getText();
    String auteurb = auteur_article.getText();



    String contenub = contenu_c.getText();
    Image image = image_article.getImage();

      BlogService hs = new  BlogService();
     
                Blog v = new Blog(titreb, auteurb, contenub, url_image.getText(),LocalDate.MAX );

  
       hs.ModifierBlog2(b); 
    }

    @FXML
    private void supprimer(ActionEvent event) {
    }
    
}
