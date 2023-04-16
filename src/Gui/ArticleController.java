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
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

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
    private Button AddImage;
    @FXML
    private Button Add;
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
    private TableColumn<Blog, String> Bauteur;
    @FXML
    private TableColumn<Blog, Integer> Bbest;
    @FXML
    private CheckBox checkbest;
    @FXML
    private TableColumn<?, ?> commentaires;
    @FXML
    private TableColumn<Blog, String> Contenu;
    @FXML
    private Button reset;
    @FXML
    private ComboBox<?> ExporterListe;
    @FXML
    private ComboBox<?> Trie;
    @FXML
    private TextField Recherche;

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
        //List<Integer> best = loadIsBestValues();
        //ObservableList<Integer> bestList = FXCollections.observableArrayList(best);
        //is_best.setItems(bestList);
        Blog b = new Blog();
        CategorieService cccc = new CategorieService();

        titre.setCellValueFactory(new PropertyValueFactory<>("titre_article"));

        Bauteur.setCellValueFactory(new PropertyValueFactory<>("auteur_article"));

        Bbest.setCellValueFactory(new PropertyValueFactory<>("is_best"));
        Bbest.setCellFactory(column -> new TableCell<Blog, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item == 1 ? "Is Best" : "Not Best");
                }
            }
        });
        TablePosts.getColumns().add(Bbest);

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

        // Charger les données dans le TableView
        List<Blog> listePosts = loadPostsFromDatabase(); // Remplacer cette méthode par votre logique pour charger les données des posts depuis la base de données
        TablePosts.setItems(FXCollections.observableArrayList(listePosts));

    }

    @FXML
    private void back(ActionEvent event) {
    }
@FXML
private void Menu(ActionEvent event) throws IOException {
    // Récupérer la fenêtre actuelle
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
    private void ListePosts() {
        Blog t = TablePosts.getSelectionModel().getSelectedItem();
        titre_article.setText(String.valueOf(t.getTitre_article()));
        auteur_article.setText(t.getAuteur_article());
        contenu_c.setText(t.getContenu_article());
        Image image = new Image(getClass().getResourceAsStream(t.getImage()));
       t.setImage("file:/C:Bureau/test_desck/img/");
        // Pour un CheckBox
       // Modifier l'état de la case à cocher
  Scene scene = checkbest.getScene();

    // Utiliser l'objet Scene pour accéder à l'élément checkbest
    CheckBox checkbestInScene = (CheckBox) scene.lookup("#checkbest");
    checkbestInScene.setSelected(true);
    checkbestInScene.setIndeterminate(true);
        String path = "file:/C:/Users/saada/OneDrive/Bureau/test_desck/" + t.getImage();
            //Image image = new Image("file:/C:/Users/saada/OneDrive/Bureau/test_desck/img" + t.getImage());

        CategCombox.setValue(Integer.toString(t.getId_categ_a_id()));

        url_image.setText(t.getImage());
    imageP.setImage(image);

    }

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
    private void Add(ActionEvent event) throws SQLException, IOException {
        Blog b = new Blog();
        BlogService sc = new BlogService();
        String titreb = titre_article.getText();
        String auteurb = auteur_article.getText();
        String cc = CategCombox.getValue();
        String img = url_image.getText();
        boolean isSelected = checkbest.isSelected();
        if (isSelected) {
            isSelected = true;
        } else {
            isSelected = false;
        }
        String displayText = isSelected ? "best" : "not best";
        System.out.println(displayText);
        Integer n = isSelected ? 1 : 0;

        String contenub = contenu_c.getText();
        Image imageb = imageP.getImage();

        int Idc = 0;
        if (cc != null) {
            Idc = sc.chercherCategorieA(cc);
        }

        // Vérifier si tous les champs sont remplis
        if (titreb.isEmpty() || cc == null || contenub.isEmpty() || imageb == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Champs vides");
            alert.setTitle("Problème");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {
            b.setTitre_article(titreb);
            b.setAuteur_article(auteurb);
            b.setContenu_article(contenub);
            b.setImage(idxx);
            b.setIs_best(n);

            b.setDate(LocalDate.MAX);
            b.setId_categ_a_id(Idc);
            Blog v = new Blog(titreb, auteurb, contenub, url_image.getText(), n, LocalDate.MAX, Idc);

            System.out.println(b.toString());
            sc.AjouterBlog(b);

            TablePosts.refresh();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Blog ajouté");
            alert.showAndWait();
            TablePosts.refresh();
        }
    }

    public void delete() {
        BlogService sv = new BlogService();
        sv.SupprimerBlog(TablePosts.getSelectionModel().getSelectedItem().getID());
        System.out.println(TablePosts.getSelectionModel().getSelectedItem().getID());
    }

    @FXML
    private void ModifierPost(ActionEvent event) {
        BlogService hs = new BlogService();

        CategorieService cs = new CategorieService();
        if (id_categ_a_id != null) {
            categorieA cours = cs.getCategorieById(Integer.parseInt(id_categ_a_id.getValue()));
            // Utiliser la variable cours comme prévu

            Blog h1 = new Blog(cours, titre_article.getText(), auteur_article.getText(), contenu_c.getText(), url_image.getText(), Integer.parseInt(checkbest.getText()));
            hs.ModifierBlog2(b);

            Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
            confirmation.setContentText("Product est modifié avec succès");
            confirmation.show();
        } else {
            System.out.println("ya chaima eli taamel fih ghalet");
        }
    }

    @FXML
    private void SupprimerPost(ActionEvent event) {
        delete();
        TablePosts.getItems().removeAll(TablePosts.getSelectionModel().getSelectedItem());
        System.out.println(TablePosts);
        TablePosts.refresh();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("succes");
        alert.setHeaderText(null);
        alert.setContentText("post a été supprimer Avec succ");
        alert.showAndWait();
    }

    private List<String> loadCategories() {
        /* List<String> categories = new ArrayList<>();
        categories.add("Sport");
        categories.add("Actualité");
        categories.add("healthy");
         */
        List<String> categories = new ArrayList<>();
        CategorieService categorieService = new CategorieService(); // Instancier votre service CategorieA
        List<categorieA> categorieAList = categorieService.Recuperer(); // Appeler la méthode Recuperer() pour récupérer les catégories depuis votre service

        // Convertir les objets categorieA en chaînes de caractères
        for (categorieA categorieA : categorieAList) {
            //categories.add(categorieA.toString()); // Ou utilisez la méthode appropriée pour obtenir la chaîne de caractères souhaitée
            categories.add(categorieA.getType());
        }

        return categories;

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
        posts = blogService.Recuperer();

        return posts;
    }

    private void resetFields() {
        clear();
    }

    private void clear() {
        titre_article.setText(null);
        auteur_article.setText(null);
        contenu_c.setText(null);

        CheckBox checkbest = new CheckBox();
        checkbest.setSelected(true); // Définir la valeur sélectionnée à true
        checkbest.setIndeterminate(false); // Définir l'état indéterminé à false

        CategCombox.setValue(null);
        url_image.setText(null);
    }

    public ObservableList<Blog> getBlogList() {
        ObservableList<Blog> blogList = FXCollections.observableArrayList();
        BlogService b = new BlogService();
        List<Blog> blogs = b.Recuperer();

        blogList.addAll(blogs);
        return blogList;

    }

    public void showBlog() {

        ObservableList<Blog> list = getBlogList();
        titre.setCellValueFactory(new PropertyValueFactory<>("titre_article"));
        date_a.setCellValueFactory(new PropertyValueFactory<>("date_a"));
        date_a.setText("Date : " + String.valueOf(b.getDate()));

        Bauteur.setCellValueFactory(new PropertyValueFactory<>("auteur_article"));
        //checkbest.setCellFactory(CheckBoxTableCell.forTableColumn("is_best"));

        categorie.setCellValueFactory(new PropertyValueFactory<>("id_categ_a_id"));

        TablePosts.setItems(list);
    }

    @FXML
    private void reset(ActionEvent event) {
    }

    @FXML
    private void ExporterListe(ActionEvent event) {
    }

    @FXML
    private void Trie(ActionEvent event) {
    }

    @FXML
    private void Recherche(KeyEvent event) {
    }

}
