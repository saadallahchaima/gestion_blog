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
import Util.MyDB;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author saada
 */
public class ArticleController implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Button Menu;
    @FXML
    private Label title;
    @FXML
    private TextField titre_article;
    @FXML
    private Label auteur;
    @FXML
    private TextField auteur_article;
    @FXML
    private TextField contenu_c;
    @FXML
    private Label categorieA;
    private ComboBox<String> id_categ_a_id;
    @FXML
    private Label Imagetext;
    @FXML
    private ImageView imageP;
    @FXML
    private Label isbest;
    @FXML
    private ComboBox<Integer> is_best;
    @FXML
    private TableView<Blog> TablePosts;
    @FXML
    private TableColumn<Blog, Integer> VID;
    @FXML
    private TableColumn<Blog, String> titre;
    @FXML
    private TableColumn<Blog, Integer> categorie;
    @FXML
    private TableColumn<Blog, Date> date_a;
    @FXML
    private TableColumn<Blog, String> commentaires;
    @FXML
    private TableColumn<Blog, Integer> likes;
    @FXML
    private TableColumn<Blog, String> Contenu;

    @FXML
    private Button Actualiser;
    @FXML
    private Button AddImage;
    @FXML
    private Button Add;
    @FXML
    private Button reset;
    @FXML
    private Button ModP;
    @FXML
    private Button SupprimerPost;
    public static String idxx;

    Blog ss = new Blog();

    private Statement ste;
    private Blog b;
    String query = null;
    Connection connection = null;
    Connection cnx = MyDB.getInsatnce().getConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Blog B = null;

    ObservableList<Blog> List = FXCollections.observableArrayList();
    ObservableList<Blog> list;
    @FXML
    private TextField url_image;
    @FXML
    private ComboBox<String> CategCombox;
    @FXML
    private TableColumn<?, ?> Bauteur;
    @FXML
    private TableColumn<?, ?> Bbest;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // Charger les données de catégories depuis la source de données (par exemple, une base de données)
List<String> categories = loadCategories(); // Remplacez cette ligne avec votre code pour charger les catégories

// Lier les données de catégories à la ComboBox
ObservableList<String> categoriesList = FXCollections.observableArrayList(categories);
CategCombox.setItems(categoriesList);
List<Integer>best = loadIsBestValues();
ObservableList<Integer>bestList = FXCollections.observableArrayList(best);
is_best.setItems(bestList);

CategorieService cccc = new CategorieService();

titre.setCellValueFactory(new PropertyValueFactory<>("titre_article"));
   Contenu.setCellValueFactory(new PropertyValueFactory<>("contenu_c"));
   date_a.setCellValueFactory(new PropertyValueFactory<>("date_a"));
      Bauteur.setCellValueFactory(new PropertyValueFactory<>("auteur_article"));
         Bbest.setCellValueFactory(new PropertyValueFactory<>("is_best"));
       categorie.setCellValueFactory(new PropertyValueFactory<>("id_categ_a_id"));
categorie.setCellFactory(column -> {
    return new TableCell<Blog, Integer>() {
        @Override
        protected void updateItem(Integer itemId, boolean empty) {
            super.updateItem(itemId, empty);

            // Vérifier si la cellule est vide
            if (empty) {
                setText(null);
            } else {
                String typeCategorie = getTypeCategorie(itemId);
                setText(typeCategorie);
            }
        }

        private String getTypeCategorie(Integer itemId) {
            List<categorieA> categories = cccc.Recuperer(); // Méthode fictive pour obtenir la liste des catégories
            for (categorieA categorie : categories) {
                if (categorie.getId() == itemId) {
                    return categorie.getType();
                }
            }
            return "Inconnu";
        }
    };
});

        boolean add = TablePosts.getColumns().add(categorie);





    commentaires.setCellValueFactory(new PropertyValueFactory<>("comments"));

    // Charger les données dans le TableView
    List<Blog> listePosts = loadPostsFromDatabase(); // Remplacer cette méthode par votre logique pour charger les données des posts depuis la base de données
   TablePosts.setItems(FXCollections.observableArrayList(listePosts));

    }

    @FXML
    private void back(ActionEvent event) {
    }

    @FXML
    private void Menu(ActionEvent event) {
    }

    @FXML
    private void ListePosts(MouseEvent event) {


    // Mettre à jour les cellules des colonnes pour afficher les données des posts
    titre.setCellValueFactory(new PropertyValueFactory<>("title"));
    Contenu.setCellValueFactory(new PropertyValueFactory<>("author"));
      
    }

    @FXML
    private void Actualiser(ActionEvent event) {
         
        // Appeler une méthode pour rafraîchir les données dans votre interface utilisateur
        // par exemple, recharger les articles à partir de la base de données
        BlogService blogService = new BlogService();
        List<Blog> articles = blogService.Recuperer(); // Méthode pour récupérer tous les articles depuis la base de données
        // Mettre à jour les données dans votre interface utilisateur
        TablePosts.setItems(FXCollections.observableArrayList(articles));
        // Réinitialiser les champs de texte ou effectuer d'autres actions nécessaires
        resetFields();
  
    }

    @FXML
    private void AddImage(ActionEvent event) throws FileNotFoundException, IOException {

        Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg"));
        File file = fileChooser.showOpenDialog(null);
        //String DBPath = "C:\\\\xampp\\\\htdocs\\\\Version-Integre\\\\public\\\\uploads\\\\" + x + ".jpg";
        String DBPath = "" + x + ".jpg";

        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path = file.getAbsolutePath();
            String res;
            int len;
            len = path.length();

            res = path.substring(47, len);
            System.out.println(res);
            Image img = new Image(file.toURI().toString());
            imageP.setImage(img);
            url_image.setText(res);

            int b = 0;
            while ((b = bin.read()) != -1) {
                bou.write(b);
            }
            bin.close();
            bou.close();

        } else {
            System.out.println("error");

        }
    }

@FXML
private void Add(ActionEvent event) throws SQLException {
    Blog b = new Blog();
    BlogService sc = new BlogService();
    String titreb = titre_article.getText();
    String auteurb = auteur_article.getText();
    String cc = CategCombox.getValue();
    Integer n =is_best.getValue();

    Integer best = n != null && n.equals("1") ? 1 : 0; // Convertir la valeur de cc en entier (0 ou 1)
    String contenub = contenu_c.getText();
    Image imageb = imageP.getImage();

    int Idc = 0;
    if (cc != null) {
        Idc = sc.chercherCategorieA(cc);
    }

    // Vérifier si tous les champs sont remplis
    if (titreb.isEmpty() || cc == null || contenub.isEmpty() || imageb == null || n == null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Champs vides");
        alert.setTitle("Problème");
        alert.setHeaderText(null);
        alert.showAndWait();
    } else {
        // Attribuer les valeurs récupérées des champs de texte à l'objet Blog
        b.setTitre_article(titreb);
        b.setAuteur_article(auteurb);
        b.setContenu_article(contenub);
        b.setImage(idxx);
        b.setIs_best(n);
        b.setDate(LocalDate.MAX);
        b.setId_categ_a_id(Idc);
         Blog v = new Blog(titreb, auteurb, contenub, url_image.getText(), n,LocalDate.MAX , Idc);

        // Ajouter le nouvel objet Blog à la base de données
        System.out.println(b.toString());
        sc.AjouterBlog(b);

        // Actualiser l'UI
        refresh();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Blog ajouté");
        alert.showAndWait();
    }
}





    @FXML
    private void reset(ActionEvent event) {
    }

    @FXML
    private void ModifierPost(ActionEvent event) throws SQLException {
       /* Blog selectedBlog = TablePosts.getSelectionModel().getSelectedItem();
    if (selectedBlog != null) {
     
            Blog b = new Blog();
    BlogService sc = new BlogService();
    String titreb = titre_article.getText();
    String auteurb = auteur_article.getText();
    String cc = CategCombox.getValue();
    Integer n =is_best.getValue();

    Integer best = n != null && n.equals("1") ? 1 : 0; // Convertir la valeur de cc en entier (0 ou 1)
    String contenub = contenu_c.getText();
    Image imageb = imageP.getImage();

           int Idc = 0;
    if (cc != null) {
        Idc = sc.chercherCategorieA(cc);
    }

    // Vérifier si tous les champs sont remplis
    if (titreb.isEmpty() || cc == null || contenub.isEmpty() || imageb == null || n == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs.");
                alert.showAndWait();
                return;
            }

            // Mettre à jour l'article dans la base de données
            BlogService blogService = new BlogService();
            String categorieA = CategCombox.getSelectionModel().getSelectedItem();
         Blog v = new Blog(selectedBlog.getID(),titreb, auteurb, contenub, url_image.getText(), n,LocalDate.MAX , Idc);
            blogService.ModifierBlog2(v);

            // Afficher un message de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("L'article a été modifié avec succès.");
            alert.showAndWait();

            // Actualiser la table des articles
            ActualiserTableArticles();

            // Réinitialiser les champs de texte
            resetFields();
       
    } else {
        // Afficher un message d'erreur si aucun article n'est sélectionné dans la table
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un article à modifier.");
        alert.showAndWait();
    }*/
Blog b = new Blog();
            BlogService blogService = new BlogService();
        b =TablePosts.getSelectionModel().getSelectedItem();
       b.setID(TablePosts.getSelectionModel().getSelectedItem().getID());
       b.setAuteur_article(auteur_article.getText());
       b.setContenu_article(contenu_c.getText());
        b.setDate(LocalDate.MAX);
     
        String Valabilite = (String) CategCombox.getValue();
     
       
      
          
            blogService.ModifierBlog2(b);
           // loadvoy();
            refresh();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("succes");
            alert.setHeaderText(null);
            alert.setContentText("Voyage Modifier Avec succ");
            alert.showAndWait();
    }
    

    @FXML
    private void SupprimerPost(ActionEvent event) {
    }

   private List<String> loadCategories() {
    List<String> categories = new ArrayList<>();
    categories.add("Sport");
    categories.add("Actualité");
    categories.add("healthy");

    return categories;
}
  private List<Integer> loadIsBestValues() {
    List<Integer> isBestValues = new ArrayList<>();
    isBestValues.add(0); // Valeur pour false
    isBestValues.add(1); // Valeur pour true

    return isBestValues;
}
   public void setImagetext() {
        this.Imagetext.setVisible(false);
    }
       public void setAddImage() {
        this.AddImage.setVisible(false);

    }

    public void setURLImage() {
        this.url_image.setVisible(false);

    }

    private List<Blog> loadPostsFromDatabase() {
            List<Blog> posts = new ArrayList<>();

BlogService blogService = new BlogService();
    posts = blogService.Recuperer(); // Supposons que la méthode getAllBlogs() retourne une liste d'objets Blog

    return posts;    }

    private void resetFields() {
    }

    private void ActualiserTableArticles() {
  // Récupérer l'objet Blog sélectionné dans la TableView
    Blog selectedBlog = TablePosts.getSelectionModel().getSelectedItem();
    if (selectedBlog != null) {
        // Mettre à jour les TextField avec les informations de l'objet Blog sélectionné
        titre_article.setText(selectedBlog.getTitre_article2());
        auteur_article.setText(selectedBlog.getAuteur_article2());
        //CategCombox.setValue(selectedBlog.getCategories());
        is_best.setValue(selectedBlog.getIs_best2());
        contenu_c.setText(selectedBlog.getContenu_article2());
        url_image.setText(selectedBlog.getImage2());
    } else {
        // Réinitialiser les TextField si aucun objet Blog n'est sélectionné
        titre_article.setText("");
        auteur_article.setText("");
        CategCombox.setValue(null);
        is_best.setValue(null);
        contenu_c.setText("");
        url_image.setText("");
    }
}

private List<Blog> getNouvellesDonneesFromSource() {
    
    List<Blog> nouvellesDonnees = new ArrayList<>();
    return nouvellesDonnees;    }


}


