/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import JPAService.JPAService;
import model.*;
import java.util.*;

/**
 *
 * @author carl
 */
public class main {

    static JPAService jpa = new JPAService();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String name = null;
        String pass = null;

        System.out.println("Please press 1 to view register an admin account");
        System.out.println("Please press 2 to view register an regular user account");
        System.out.println("Please press 3 to sign in using an exisiting account");

        int choice = in.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Please enter user username");
                name = in.nextLine();
                name = in.nextLine();
                System.out.println("Please enter password");
                pass = in.nextLine();
                jpa.addAdmin(new UserAdmin(name, pass));

                break;
            case 2:
                System.out.println("Please enter user username");
                name = in.nextLine();
                name = in.nextLine();
                System.out.println("Please enter password");
                pass = in.nextLine();
                jpa.addUserRegular(new UserRegular(name, pass));
                break;
        }

        System.out.println("Press 1 to login");
        System.out.println("Press 2 to exit");

        choice = in.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Please enter user id");
                int id = in.nextInt();
                in.nextLine();
                System.out.println("Please enter password");
                pass = in.nextLine();
                if (jpa.passCheck(id, pass) == true) {

                    if (jpa.getGroup(id) == 1) {
                        //admin
                        while (true) {
                            System.out.println("welcome to admin page");
                            System.out.println("Please press 1 to view all movies");
                            System.out.println("Please press 2 to view exit");
                            choice = in.nextInt();
                            switch (choice) {
                                case 1:
                                    jpa.allMovies();
                                    break;
                                    case 2:
                                    System.exit(0);
                                        break;
                            }
                        }
                    } else {

                        //normal user
                        System.out.println("normal user");

                    }
                } else {
                    System.out.println("wrong password or user id please try again later");
                }
        }

    }
}
/*
        
 System.out.println("Please enter the name of the movie you wish to add");
 String mName = in.nextLine();
 System.out.println("Please enter the rating");
 int rate = in.nextInt();
 System.out.println("Please enter the run time");
 int run = in.nextInt();
 jpa.addMovie(new Movie(mName, rate, run ));*/
/*System.out.println("Please enter id");
 int id = in.nextInt();
 in.nextLine();
 System.out.println("Please enter password");*/
                //String pass = in.nextLine();
        /*if  (jpa.passCheck(3, "pass") == true){
 //system here
 jpa.allMovies();
        
 }*/

                //jpa.addMovie(new Movie("test", 100, 40, 90, 90 ));
//jpa.addAdmin(new UserAdmin("carla", "newpass"));
//jpa.addUserRegular(new UserRegular ("george", "pass"));

