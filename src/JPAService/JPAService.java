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

        TypedQuery<Movie> tquery = em.createQuery("select m from Movie m order by m.mId asc", Movie.class);

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

    public void allUsers() {
        em.getTransaction().begin();

        TypedQuery<MovieUser> tquery = em.createQuery("select mu from MovieUser mu order by mu.muId asc", MovieUser.class);

        List<MovieUser> mulist = tquery.getResultList();

        if (mulist.isEmpty()) {
            System.out.println("sorry, no users were found");
        } else {
            System.out.println("Registered users: ");

            for (MovieUser e : mulist) {
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

        Movie m = em.find(Movie.class, mId);

        profit = m.getBoxOffice() - m.getBudget();

        if (profit >= 0) {
            return profit;
        } else if (profit < 0) {
            profit = 0.0;
        }

        return profit;

        //System.out.println("The profit for movie id " + mId +"("+ m.getmName() + ") " + profit);
    }

    public boolean passCheck(String name, String pass) {

        MovieUser mu = em.find(MovieUser.class, name);

        if (mu.getuPass().equals(pass)) {
            return true;

        }

        return false;

    }

    public int getGroup(String name) {
        int returnValue = 0;

        MovieUser mu = em.find(MovieUser.class, name);

        if ((mu) instanceof UserAdmin) {
            UserAdmin m = (UserAdmin) mu;
            //m.getUSER_GROUP();
            returnValue = 1;
        } else if ((mu) instanceof UserRegular) {
            UserRegular ur = (UserRegular) mu;
            returnValue = 2;
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
        //System.out.println("Your user id = " + ur.getuId() + " please remember this as you'll need it to login");
        em.getTransaction().commit();
    }

    public void removeUser(int uid) {
        em.getTransaction().begin();
        MovieUser d = em.find(MovieUser.class, uid);
        em.remove(d);

        em.getTransaction().commit();
    }

    public String passEncrypt(String pass) {

        String newPass = "";

        String[] password = new String[pass.length()];

        for (int i = 0; i < password.length; i++) {

            char c = pass.charAt(i);

            if (c >= 'a' && c <= 'z') {
                c = (char) (c + 1);

                if (c > 'z') {
                    c = (char) (c - 'z' + 'a' - 1);
                }

                newPass += c;
            }

        }

        return newPass;
    }

    public void removeMovie(int id) {

        em.getTransaction().begin();
        Movie m = em.find(Movie.class, id);
        em.remove(m);

        Studio s = m.getStudio();
        if (s.getMlist().isEmpty()) {

            em.getTransaction().commit();
        } else {
            s.getMlist().remove(m);
            em.remove(m);
            em.getTransaction().commit();
        }

    }

    public void changeMovieTitle(int id, String name) {
        em.getTransaction().begin();
        Movie m = em.find(Movie.class, id);
        m.setmName(name);
        em.getTransaction().commit();

    }

    public void updateMovieRating(int id, int rating) {
        em.getTransaction().begin();
        Movie m = em.find(Movie.class, id);
        if (rating == 1) {
            m.setmRating(m.getmRating() + 1);
        } else if (rating == 0) {
            m.setmRating(m.getmRating() - 1);

        } else {
            m.setmRating(m.getmRating() + 0);
        }

        em.getTransaction().commit();

    }

    /*public String getUsername(String name) {
     String rname = null;
     if (findUser(name) != -1) {
     MovieUser u = em.find(MovieUser.class, findUser(name));
     rname = u.getuName();
     }
    
     return rname;
     }*/
    public int findUser(String name) {

        TypedQuery<MovieUser> tquery = em.createQuery("select u from MovieUser u", MovieUser.class);

        List<MovieUser> ulist = tquery.getResultList();

        for (int i = 0; i < ulist.size(); i++) {

            if (ulist.get(i).getuName().equals(name)) {
                return ulist.get(i).getuId();

            }

        }

        return -1;
    }

    public void addStudio(Studio s) {
        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();
    }

    public void addMovieStudio(int mid, int st) {
        em.getTransaction().begin();
        Movie m = em.find(Movie.class, mid);
        Studio s = em.find(Studio.class, st);
        s.addMovie(m);
        em.getTransaction().commit();
    }

    public void viewStudios() {
        em.getTransaction().begin();
        TypedQuery<Studio> q = em.createQuery("select s from Studio s", Studio.class);
        List<Studio> results = q.getResultList();
        if (results.isEmpty()) {
            System.out.println("No Studios found");
        } else {
            System.out.println("Studio List");
            for (int i = 0; i < results.size(); i++) {
                System.out.println(results.get(i).getsName());
                int size = results.get(i).getMlist().size();
                for (int j = 0; j < size; j++) {
                    System.out.println("\t" + results.get(i).getMlist().get(j).getmName());
                }
            }
        }
        em.getTransaction().commit();
    }

    public void removeStudio(int did) {

        em.getTransaction().begin();
        Studio s = em.find(Studio.class, did);
        em.remove(s);

        em.getTransaction().commit();

    }

    public int getLatestMovie() {
        int id = -1;

        TypedQuery<Movie> mquery = em.createQuery("select m from Movie m order by m.mId asc", Movie.class);
        List<Movie> mlist = mquery.getResultList();

        //return the last id
        for (int i = 0; i < mlist.size(); i++) {
            id = mlist.get(i).getmId();

        }
        return id;

    }
}
