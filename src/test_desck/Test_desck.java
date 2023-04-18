/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_desck;

import Entity.Blog;
import Entity.PostLike;
import Entity.categorieA;
import Entity.comment;
import Service.BlogService;
import Service.CategorieService;
import Service.CommentService;
import Util.MyDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author saada
 */
public class Test_desck {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Date date = Date.valueOf("2022-10-10");
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(localDateTime);

        Connection cnx;
        cnx = MyDB.getInsatnce().getConnection();

        Blog v = new Blog(9, 1, "Hey w tawa?", "hi", "chaima", "image", true);
        comment c = new comment(7, "chaimaaaaaaa", "chaima.saadallah@espir.tn", 8, "hirtyretrdetrdrertdftrdrtrtrdrtrdrs'sderszeqzesredtueyfyt", 1);
        comment chaima = new comment(14, "chaimaaaaaaaaaapp", "chaimaaaa.saadallah@espir.tn", 8, "chaimaaaaaaa bo jaajhhsfhEGYUzegyAGTYAFyeugZQHGDVztyfveZGRSTRETRETFGDFGZRTRZTesfsdfvdxfezrfdcxxset", timestamp, 1, v);

        categorieA cat = new categorieA(4, "Actualité");

        {
            ;
            BlogService ps = new BlogService();
            CommentService p = new CommentService();
            CategorieService pp = new CategorieService();
            //ps.ModifierBlog2(v);
            //p.Recuperer();
            //p.ModifierCo(chaima);
            // PostLike like = new PostLike(1,42,
           //ps.likes(4);
            //ps.islikedbyuser(4,1);
            //ps.Supprimerlike(1,1);
            // System.out.println(ps.rechercheAvancee("chaima", "chaima"));
            //System.out.println(ps.recherche("Yoga"));
            //ps.ajouterlike(3,"ayedi.malek@esprit.tn");
            //ps.afficherAuteursPlusActifs();
            ps.getLikeStats(cnx);
            ps.getCommentStats(cnx);

            // System.out.println(  p.detail(1));
            //System.out.println( ps.getCommentsWithArticleTitles());
            //get commentaire by article
            //System.out.println(ps.getComments(8));
            //System.out.println( p.getCommentsByArticle(1));
           /*List<comment> commentHistory = p.getCommentHistory(1);
            if (commentHistory.isEmpty()) {
                System.out.println("Aucun commentaire trouvé");
            } else {
                for (Iterator<comment> it = commentHistory.iterator(); it.hasNext();) {
                    comment cc = it.next();
                    System.out.println(cc.toString());
                }
            }*/
            //p.AjouterCo(chaima);
            //pp.AjouterCategorie(cat);
            // pp.Recuperer();
            //Affichage            
            //pp.ModifierCo(cat);
            //pp.SupprimerCat(7);
            //p.Recuperer(1);
            // p.SupprimerCo(2);
           // ps.AjouterBlog(v);
            //ps.ModifierBlog(5,v);
            // ps.Recuperer();
            // ps.SupprimerBlog(9);
            //System.out.println( ps.AfficherArticlesByCategoryId(4));
            //System.out.println(ps.filterArticles(4,"Actualité"));
            //System.out.println( ps.getAllArticlesByCategory(1));
            // System.out.println( ps.getAllArticlesSortedByDate("date_a", "Desc"));
            //System.out.println( ps.getFavoriteArticles());
            //System.out.println(ps.compterNbCommentaires());
            //System.out.println( ps.getArticlesPopulairesParCommentaires(1));
            // System.out.println(ps.getArticlesPopulairesParCommentaires(3));
        }
    }
}
