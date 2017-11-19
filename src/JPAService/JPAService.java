/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPAService;

import java.util.*;
import javax.persistence.*;
import model.*;

/**
 *
 * @author carl
 */
public class JPAService {

    EntityManagerFactory emf;
    EntityManager em;

    public JPAService() {
        emf = Persistence.createEntityManagerFactory("ca2PU");
        em = emf.createEntityManager();
    }

    public void allMovies() {
        em.getTransaction().begin();

        TypedQuery<Movie> tquery = em.createQuery("select m from Movie m order by m.mRating desc", Movie.class);

        List<Movie> elist = tquery.getResultList();

        if (elist.isEmpty()) {
            System.out.println("sorry, no movies were found");
        } else {
            System.out.println("Movie list: ");

            for (Movie e : elist) {
                System.out.println(e);
            }
        }

        em.getTransaction().commit();

    }

    public void addMovie(Movie m) {
        em.getTransaction().begin();
        em.persist(m);
        em.getTransaction().commit();
    }

    public double calcProfit(int mId) {
        double profit = 0;
        em.getTransaction().begin();
        Movie m = em.find(Movie.class, mId);

        profit = m.getBoxOffice() - m.getBudget();

        em.getTransaction().commit();
        return profit;

    }

    public boolean passCheck(int uid, String pass) {
        /*em.getTransaction().begin();
         MovieUser mu = em.find(MovieUser.class, uid);
        
         if (mu.getuPass().equals(pass)) {
        
         System.out.println(1);
        
         } else {
        
         System.out.println(0);
        
         }
        
        
         em.getTransaction().commit();*/

        em.getTransaction().begin();

        TypedQuery<MovieUser> tquery = em.createQuery("select p from MovieUser p", MovieUser.class);
        em.getTransaction().commit();

        List<MovieUser> plist = tquery.getResultList();

        for (int i = 0; i < plist.size(); i++) {
            if (plist.get(i).getuId() == uid) {
                if (plist.get(i).getuPass().equals(pass)) {
                    return true;

                }

            }

        }

        return false;

    }

    public int getGroup(int uid) {
        int returnValue = 0;
        em.getTransaction().begin();

        TypedQuery<MovieUser> tquery = em.createQuery("select p from MovieUser p", MovieUser.class);
        em.getTransaction().commit();

        List<MovieUser> plist = tquery.getResultList();

        for (int i = 0; i < plist.size(); i++) {
            if (plist.get(i).getuId() == uid){
            if ((plist.get(i)) instanceof UserAdmin) {
                UserAdmin m = (UserAdmin) plist.get(i);
                //m.getUSER_GROUP();
                returnValue = 1;
            } else if ((plist.get(i)) instanceof UserRegular) {
                UserRegular ur = (UserRegular) plist.get(i);
                returnValue = 2;
            }

        }
        }

        return returnValue;

    }

    public void addAdmin(UserAdmin a) {
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
    }

    public void addUserRegular(UserRegular ur) {
        em.getTransaction().begin();
        em.persist(ur);
        em.getTransaction().commit();
    }
}
