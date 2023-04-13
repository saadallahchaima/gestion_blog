/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.TextField;

/**
 *
 * @author saada
 */
public class comment {

    private int ID;
    private String nom_c;
    private String email;
    private int id_article;
    private String contenu_c;
    private Timestamp date_com;
    private int approved;
    private Blog article; // Ajout de la propriété "article"

    public comment(int ID, String nom_c, String email, int id_article, String contenu_c, Timestamp date_com, int approved) {
        this.ID = ID;
        if (estValideNom(nom_c)) {
            this.nom_c = nom_c;
        } else {
            throw new IllegalArgumentException("Le nom doit être composé uniquement de caractères.");
        }
        if (estValideEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("L'email doit être valide.");
        }

        this.id_article = id_article;
        if (estValideCommentaire((contenu_c))) {
            this.contenu_c = contenu_c;
        } else {
            throw new IllegalArgumentException("le contenu du commentaire doit etre superieur a 80");
        }

        this.date_com = date_com;
        this.approved = approved;
    }

    public comment(int ID, String nom_c, String email, int id_article, String contenu_c, int approved, Blog article) {
        this.ID = ID;
        if (estValideNom(nom_c)) {
            this.nom_c = nom_c;
        } else {
            throw new IllegalArgumentException("Le nom doit être composé uniquement de caractères.");
        }
        if (estValideEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("L'email doit être valide.");
        }
        this.id_article = id_article;
        if (estValideCommentaire((contenu_c))) {
            this.contenu_c = contenu_c;
        } else {
            throw new IllegalArgumentException("le contenu du commentaire doit etre superieur a 80");
        }
        this.approved = approved;
        this.article = article;
    }

    public comment(int ID, String nom_c, String email, int id_article, String contenu_c, int approved) {
        this.ID = ID;
        if (estValideNom(nom_c)) {
            this.nom_c = nom_c;
        } else {
            throw new IllegalArgumentException("Le nom doit être composé uniquement de caractères.");
        }
        if (estValideEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("L'email doit être valide.");
        }
        this.id_article = id_article;
        if (estValideCommentaire((contenu_c))) {
            this.contenu_c = contenu_c;
        } else {
            throw new IllegalArgumentException("le contenu du commentaire doit etre superieur a 80");
        }
        this.approved = approved;
    }

    public comment() {
    }

    public comment(int ID, int id_article, String contenu_c, Date date_com, String nom_c, String email, int approved) {

        this.ID = ID;

        if (estValideNom(nom_c)) {
            this.nom_c = nom_c;
        } else {
            throw new IllegalArgumentException("Le nom doit être composé uniquement de caractères.");
        }
        if (estValideEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("L'email doit être valide.");
        }

        this.id_article = id_article;

        if (estValideCommentaire((contenu_c))) {
            this.contenu_c = contenu_c;
        } else {
            throw new IllegalArgumentException("le contenu du commentaire doit etre superieur a 80");
        }

        this.approved = approved;
    }

    public comment(int ID, String nom_c, String email, int id_article, String contenu_c, Timestamp date_com, int approved, Blog article) {
        this.ID = ID;
        if (estValideNom(nom_c)) {
            this.nom_c = nom_c;
        } else {
            throw new IllegalArgumentException("Le nom doit être composé uniquement de caractères.");
        }
        if (estValideEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("L'email doit être valide.");
        }
        this.id_article = id_article;
        if (estValideCommentaire((contenu_c))) {
            this.contenu_c = contenu_c;
        } else {
            throw new IllegalArgumentException("le contenu du commentaire doit etre superieur a 80");
        }
        this.date_com = date_com;
        this.approved = approved;
        this.article = article;
    }

    public comment(int ID, int id_article, String nom_c, Timestamp timestamp, String email, String contenu_c, int approved) {
        this.ID = ID;
        if (estValideNom(nom_c)) {
            this.nom_c = nom_c;
        } else {
            throw new IllegalArgumentException("Le nom doit être composé uniquement de caractères.");
        }
        if (estValideEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("L'email doit être valide.");
        }
        this.id_article = id_article;
        this.date_com = timestamp;
        if (estValideCommentaire((contenu_c))) {
            this.contenu_c = contenu_c;
        } else {
            throw new IllegalArgumentException("le contenu du commentaire doit etre superieur a 80");
        }
        this.approved = approved;
    }

    public comment(String nomText, String commentaireText, String emailText) {
        this.nom_c = nomText;
        this.email = emailText;
        this.contenu_c = commentaireText;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom_c() {
        return nom_c;
    }

    public void setNom_c(String nom_c) {
        this.nom_c = nom_c;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public String getContenu_c() {
        return contenu_c;
    }

    public void setContenu_c(String contenu_c) {
        this.contenu_c = contenu_c;
    }

    public Timestamp getDate_com() {
        return date_com;
    }

    public void setDate_com(Timestamp date_com) {
        this.date_com = date_com;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public Blog getArticle() {
        return article;
    }

    public void setArticle(Blog article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "comment{" + "ID=" + ID + ", nom_c=" + nom_c + ", email=" + email + ", id_article=" + id_article + ", contenu_c=" + contenu_c + ", date_com=" + date_com + ", approved=" + approved + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.ID;
        hash = 47 * hash + Objects.hashCode(this.nom_c);
        hash = 47 * hash + Objects.hashCode(this.email);
        hash = 47 * hash + this.id_article;
        hash = 47 * hash + Objects.hashCode(this.contenu_c);
        hash = 47 * hash + Objects.hashCode(this.date_com);
        hash = 47 * hash + this.approved;
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
        final comment other = (comment) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (this.id_article != other.id_article) {
            return false;
        }
        if (this.approved != other.approved) {
            return false;
        }
        if (!Objects.equals(this.nom_c, other.nom_c)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.contenu_c, other.contenu_c)) {
            return false;
        }
        if (!Objects.equals(this.date_com, other.date_com)) {
            return false;
        }
        return true;
    }

    public void setDate(LocalDate localDate) {
        LocalDateTime localDateTime = localDate.atStartOfDay();
        this.date_com = Timestamp.valueOf(localDateTime);
    }

    private static boolean estValideNom(String nom_c) {
        return nom_c != null && !nom_c.isEmpty() && nom_c.matches("[a-zA-Z]+");
    }

    private static boolean estValideEmail(String email) {
        return email != null && !email.isEmpty() && email.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
    }

    private static boolean estValideCommentaire(String commentaire) {
        return commentaire.length() >= 16;
    }

}
