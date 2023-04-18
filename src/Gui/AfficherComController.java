/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Blog;
import Entity.categorieA;
import Entity.comment;
import Service.BlogService;
import Service.CategorieService;
import Service.CommentService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author saada
 */
public class AfficherComController implements Initializable {

    private ListView<comment> listeB;
    Preferences prefs = Preferences.userNodeForPackage(AfficherComController.class);
    private ListView<comment> listeCom;
    @FXML
    private TableView<comment> TableCom;
    @FXML
    private TableColumn<comment, Integer> VID;
    @FXML
    private TableColumn<comment, String> commentaire;
    @FXML
    private TableColumn<comment, String> colNom;
    @FXML
    private TableColumn<comment, String> ColEmail;
    @FXML
    private TableColumn<comment, String> colDate;
    @FXML
    private TableColumn<comment, Integer> colDate1;
    @FXML
    private TableColumn<comment, Integer> colArticle;
    @FXML
    private CheckBox checkapproved;
    @FXML
    private Label lbnom;
    @FXML
    private Label lbemail;
    @FXML
    private Label lbdate;
    @FXML
    private Label lbcom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      Blog b=new Blog();
comment c = new comment();
CommentService cs =new CommentService();
BlogService bb= new BlogService();
       commentaire.setCellValueFactory(new PropertyValueFactory<>("contenu_c"));

        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_c"));

       ColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colDate.setCellValueFactory(new PropertyValueFactory<>("date_com"));
            colDate.setCellValueFactory(cellData -> {
        comment comment = cellData.getValue();
        if (comment != null && comment.getDate_com() != null) {
            return new SimpleStringProperty(comment.getDate_com().toString()); // Remplacez getDate_com() par votre propre méthode pour obtenir la date du commentaire
        } else {
            return new SimpleStringProperty("");
        }
    });
            colDate1.setCellValueFactory(new PropertyValueFactory<>("approved"));
             colArticle.setCellValueFactory(new PropertyValueFactory<>("id_article"));

colArticle.setCellFactory(column -> {
            return new TableCell<comment, Integer>() {
                @Override
                protected void updateItem(Integer itemId, boolean empty) {
                    super.updateItem(itemId, empty);

                    // Vérifier si la cellule est vide
                    if (empty) {
                        setText(null);
                    } else {
                        String Article = getArticle(itemId);
                        setText(Article);
                    }
                } private String getArticle(Integer itemId) {
                    List<Blog> categories = bb.Recuperer(); // Méthode fictive pour obtenir la liste des catégories
                    for (Blog categorie : categories) {
                        if (categorie.getID() == itemId) {
                            return categorie.getTitre_article();
                        }
                    }
                    return "Inconnu";
                }
            };
        });
        boolean add = TableCom.getColumns().add(colArticle);

        colDate1.setCellFactory(column -> new TableCell<comment, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item == 1 ? "Is Approved" : "Not Approved");
                }
            }
        });

      
        // Charger les données dans le TableView
        List<comment> listecomments = loadcommentsFromDatabase(); 
     TableCom.setItems(FXCollections.observableArrayList(listecomments));

  

    }

   
   
    @FXML
    private void back(ActionEvent event) throws IOException {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    
    // Masquer la fenêtre actuelle
    currentStage.hide();
    
    // Charger la nouvelle fenêtre "AfficherBlog.fxml"
    Parent root = FXMLLoader.load(getClass().getResource("AfficherBlog.fxml"));
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void ListeCom(MouseEvent event) {
          comment t = TableCom.getSelectionModel().getSelectedItem();
          
          
          /* try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CommentDetails.fxml"));
            Parent root = loader.load();
              CommentDetailsController controller = loader.getController();
                controller.updatecomDetails(t);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(AfficherBlogController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
            lbnom.setText(t.getNom_c());
        lbemail.setText(t.getEmail());
        lbdate.setText(t.getDate_com().toString());
        lbcom.setText(t.getContenu_c());
        checkapproved.setSelected(t.getApproved() == 1);

   
    }
  
 
          

private List<comment> loadcommentsFromDatabase() {
   List<comment> comments = new ArrayList<>();
           CommentService blogService = new CommentService();
       comment c=new comment();

        List<comment> coco = blogService.Recuperer();
        return coco;
}

    @FXML
    private void updatecom(ActionEvent event) {
         CommentService cc = new CommentService();
    comment c = new comment();
      c =TableCom.getSelectionModel().getSelectedItem();
       c.setID(TableCom.getSelectionModel().getSelectedItem().getID());
    c.setNom_c(lbnom.getText());
    c.setEmail(lbemail.getText());
    c.setDate_com(java.sql.Timestamp.valueOf(lbdate.getText()));
    c.setContenu_c(lbcom.getText());
    
    // Mettre à jour l'état "approved" du commentaire en fonction de l'état de la case à cocher
    if (checkapproved.isSelected()) {
        c.setApproved(1);
    } else {
        c.setApproved(0);
    }
    
    // Appeler la méthode de service pour mettre à jour le commentaire dans la base de données
    cc.ModifierCo(c);
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("commentaire modifié avec succes");
            alert.showAndWait();
          TableCom.refresh();
    }
}



 



