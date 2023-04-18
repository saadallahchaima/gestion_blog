/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.ByteArrayInputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author saada
 */
public class Blog {

    private int ID;
    private int id_categ_a_id;
    private String titre_article;
    private String contenu_article;
    private String auteur_article;
    private String image;
    private Date date;
    private int is_best;
    private List<comment> comments; // Ajoutez cet attribut pour stocker les catégories associées au blog

    private List<categorieA> categories; // Ajoutez cet attribut pour stocker les catégories associées au blog

    public Blog(int ID, int id_categ_a_id, String titre_article, String contenu_article, String auteur_article, String image, Date date, boolean is_best) {
        this.ID = ID;
        this.id_categ_a_id = id_categ_a_id;
      
            this.titre_article = titre_article;
      
        this.contenu_article = contenu_article;
        this.auteur_article = auteur_article;
        this.image = image;
        this.date = date;
        this.is_best = is_best ? 1 : 0; // Convertir la valeur booléenne en un entier
    }

    public Blog() {
    }

    public Blog(int ID, int id_categ_a_id, String titre_article, String contenu_article, String auteur_article, String image, boolean is_best) {
        this.ID = ID;
        this.id_categ_a_id = id_categ_a_id;
       
            this.titre_article = titre_article;
       
        this.contenu_article = contenu_article;
        this.auteur_article = auteur_article;
        this.image = image;
        this.is_best = is_best ? 1 : 0; // Convertir la valeur booléenne en un entier
    }

    public Blog(int ID, int id_categ_a_id, String titre_article, String contenu_article, String auteur_article, String image, LocalDate date, int is_best) {
        this.ID = ID;
        this.id_categ_a_id = id_categ_a_id;
      
            this.titre_article = titre_article;
      
        this.contenu_article = contenu_article;
        this.auteur_article = auteur_article;
        this.image = image;
        this.date = Date.valueOf(date); // convert LocalDate to Date
        this.is_best = is_best;
    }

    public Blog(int categb, String titreb, String contenub, String auteurb, Image imageb, int _bestb) {

        this.id_categ_a_id = id_categ_a_id;

        this.titre_article = titre_article;

        this.contenu_article = contenu_article;
        this.auteur_article = auteur_article;
        this.image = image;
        this.is_best = is_best;
        this.comments = new ArrayList<>();

        this.categories = new ArrayList<>();

    }

    public Blog(String titreb, String auteurb, String contenub, String text, Integer n, LocalDate MAX, int Idc) {
        this.titre_article = titreb;
        this.auteur_article = auteurb;
        this.contenu_article = contenub;
        this.image = text;
        this.is_best = n;
        this.id_categ_a_id = Idc;
    }

    public Blog(String titreb, String auteurb, String contenub, String text, LocalDate MAX) {
        this.titre_article = titreb;
        this.auteur_article = auteurb;
        this.contenu_article = contenub;
        this.image = text;

    }

    public Blog(int id, String titre, String auteur, String contenu, String categorieA, String imageUrl, int best) {
        this.titre_article = titre;
        this.auteur_article = auteur;
        this.contenu_article = contenu;
        this.image = imageUrl;
        this.is_best = best;
    }

    public Blog(int id, String titre, String auteur, String contenu, String categorieA, String imageUrl) {
        this.titre_article = titre;
        this.auteur_article = auteur;
        this.contenu_article = contenu;
        this.image = imageUrl;

    }

    public Blog(int id, TableColumn<Blog, String> titre, Label auteur, String contenub, String categorieA, Image imageb) {
        this.ID = id;
        this.titre_article = titre.getText(); // Supposant que titre.getText() renvoie une chaîne de caractères
        this.auteur_article = auteur.getText(); // Supposant que auteur.getText() renvoie une chaîne de caractères
        this.contenu_article = contenub;
        this.id_categ_a_id = Integer.parseInt(categorieA); // Supposant que categorieA est une chaîne de caractères représentant un entier
//this.image = new ImageView(imageb);
    }

    public Blog(int id, String titreb, String auteurb, String contenub, String text, Integer n, LocalDate MAX, int Idc) {
        this.ID = id;
        this.auteur_article = auteurb;
        this.titre_article = titreb;
        this.contenu_article = contenub;
        this.id_categ_a_id = Idc;
        this.is_best = n;
        this.image = text;
    }

    public Blog(String titreb, String auteurb, String contenub, String text, boolean n, LocalDate MAX, int Idc) {
    
    
        this.auteur_article = auteurb;
        this.titre_article = titreb;
        this.contenu_article = contenub;
        this.id_categ_a_id = Idc;
    this.is_best = n ? 1 : 0; // Convertir la valeur booléenne en entier (0 ou 1)
        this.image = text;
    }

     public Blog(categorieA categorie, String titre, String auteur, String contenu, String url, int isBest) {
categorieA categ = new categorieA(); // Remplacez cette ligne avec l'instanciation réelle de votre objet categorieA
int id_categorie = categorie.getId(); // Utiliser la méthode getId() pour obtenir la valeur int de l'objet categorieA        this.titre = titre;
        this.auteur_article = auteur;
        this.contenu_article = contenu;
        this.image = url;
        this.is_best = isBest;
        this.titre_article=titre;
    }
    

    public List<comment> getComments() {
        return comments;
    }

    public void setComments(List<comment> comments) {
        this.comments = comments;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getId_categ_a_id() {
        return id_categ_a_id;
    }

    public void setId_categ_a_id(int id_categ_a_id) {
        this.id_categ_a_id = id_categ_a_id;
    }

    public String getTitre_article() {
        return titre_article;
    }

    public void setTitre_article(String titre_article) {
        this.titre_article = titre_article;
    }

    public String getContenu_article() {
        return contenu_article;
    }

    public void setContenu_article(String contenu_article) {
        this.contenu_article = contenu_article;
    }

    public String getAuteur_article() {
        return auteur_article;
    }

    public void setAuteur_article(String auteur_article) {
        this.auteur_article = auteur_article;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIs_best() {
        return is_best;
    }

    public void setIs_best(int is_best) {
        this.is_best = is_best;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.ID;
        hash = 17 * hash + this.id_categ_a_id;
        hash = 17 * hash + Objects.hashCode(this.titre_article);
        hash = 17 * hash + Objects.hashCode(this.contenu_article);
        hash = 17 * hash + Objects.hashCode(this.auteur_article);
        hash = 17 * hash + Objects.hashCode(this.image);
        hash = 17 * hash + Objects.hashCode(this.date);
        hash = 17 * hash + this.is_best;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Blog other = (Blog) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (this.id_categ_a_id != other.id_categ_a_id) {
            return false;
        }
        if (this.is_best != other.is_best) {
            return false;
        }
        if (!Objects.equals(this.titre_article, other.titre_article)) {
            return false;
        }
        if (!Objects.equals(this.contenu_article, other.contenu_article)) {
            return false;
        }
        if (!Objects.equals(this.auteur_article, other.auteur_article)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Blog{" + "ID=" + ID + ", id_categ_a_id=" + id_categ_a_id + ", titre_article=" + titre_article + ", contenu_article=" + contenu_article + ", auteur_article=" + auteur_article + ", image=" + image + ", date=" + date + ", is_best=" + is_best + '}';
    }

    public boolean isIs_best() {
        return (is_best == 1);
    }

    public void setDate(LocalDate localDate) {
        this.date = Date.valueOf(localDate);
    }

    private static boolean commenceParMajuscule(String nom_article) {
        if (nom_article == null || nom_article.isEmpty()) {
            return false;
        }
        char premiereLettre = nom_article.charAt(0);
        return Character.isUpperCase(premiereLettre);
    }
    // Méthode pour ajouter une catégorie à la liste de catégories

    public void addCategorie(categorieA categorie) {
        this.categories.add(categorie);
    }

    // Méthode pour définir la liste complète des catégories associées au blog
    public void setCategories(List<categorieA> categories) {
        this.categories = categories;
    }

    // Méthode pour récupérer la liste des catégories associées au blog
    public List<categorieA> getCategories() {
        return this.categories;
    }

    public List<comment> setCommentaires(List<comment> commentaires) {
        return this.comments;
    }


   }


