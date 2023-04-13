/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Blog;
import Entity.PostLike;
import java.util.List;


public interface IServiceA<B> {

    public void AjouterBlog(Blog article);

    public void ModifierBlog(int ID, B v);

    public void SupprimerBlog(int ID);

    public List<B> Recuperer();

    public List getCommentsWithArticleTitles();

    public List likes(int id);

    public boolean ajouterlike(int idp, String email);
  public List islikedbyuser(int idp, int idc);
   public void Supprimerlike(int id, int idc);
     public void afficherAuteursPlusActifs() ;
}
