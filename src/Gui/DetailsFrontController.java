/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Blog;
import Entity.comment;
import Service.CommentService;
import Util.MyDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    @FXML
    private TextArea tfcontenu;
    @FXML
    private Label titre_article;
    @FXML
    private TextField tftitre;
    @FXML
    private TextField tfauteur;
    @FXML
    private TextField tfdate;
    @FXML
    private TextField tnom;
    @FXML
    private TextField temail;
    @FXML
    private TextArea tcom;
    @FXML
    private TextField tfid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public  DetailsFrontController(){
}

    @FXML
    private void click(MouseEvent event) {
                

    }
    public void updateBlogDetails(Blog blog) {
    tftitre.setText("Titre Article : " + blog.getTitre_article());
    tfcontenu.setText("Contenu: " + blog.getContenu_article());
   tfdate.setText("Date : " + String.valueOf(blog.getDate()));
   tfauteur.setText("Auteur: " + blog.getAuteur_article());




}

 @FXML
private void commenter(ActionEvent event) throws SQLException {
    Connection cnx = MyDB.getInsatnce().getConnection();
    String nom = tnom.getText();
    Blog b = new Blog(); // Vérifier comment obtenir l'objet Blog approprié
    comment c = new comment(); // Vérifier comment obtenir l'objet Comment approprié
    LocalDateTime localDateTime = LocalDateTime.now();
    Timestamp timestamp = Timestamp.valueOf(localDateTime);

    String email = temail.getText();
    String texte = tftitre.getText(); // Utiliser "texte" au lieu de "text" pour éviter les conflits avec les mots clés Java
    String contenu = tcom.getText();
    String id_article = tfid.getText();

int id_article_id = Integer.parseInt(id_article);
    CommentService co = new CommentService();
 
    int approved = 0;

    comment comment = new comment(c.getID(), nom, email,id_article_id, contenu, timestamp, approved);

    // Appeler la méthode AjouterCo pour ajouter le commentaire
    co.AjouterCo(comment);
    System.out.println(comment.toString());

    System.out.println("Ajout réussi avec succès");
}

}

