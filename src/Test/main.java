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
        int counter = 0;

        System.out.println("Please press 1 to create an admin account");
        System.out.println("Please press 2 to create an regular user account");
        System.out.println("Please press 3 to sign in using an exisiting account");

        int choice = in.nextInt();

        switch (choice) {
            case 1:

                System.out.println("Please enter user username");
                name = in.nextLine();
                name = in.nextLine();

                while (jpa.findUser(name) != -1) {
                    System.out.println("Please enter a unique user username");
                    name = in.nextLine();
                    counter++;

                    if (counter > 2) {
                        System.out.println("Please try again later");
                        System.exit(0);
                    }
                }
                System.out.println("Please enter password");
                pass = in.nextLine();

                jpa.addAdmin(new UserAdmin(name, jpa.passEncrypt(pass)));

                break;
            case 2:

                System.out.println("Please enter user username");
                name = in.nextLine();
                name = in.nextLine();

                while (jpa.findUser(name) != -1) {
                    System.out.println("Please enter a unique user username ");
                    name = in.nextLine();
                    counter++;

                    if (counter > 2) {
                        System.out.println("Please try again later");
                        System.exit(0);
                    }
                }

                System.out.println("Please enter password");
                pass = in.nextLine();

                jpa.addUserRegular(new UserRegular(name, jpa.passEncrypt(pass)));

                break;
        }

        System.out.println("Press 1 to login");
        System.out.println("Press 2 to exit");

        choice = in.nextInt();

        switch (choice) {
            case 1:
                in.nextLine();
                System.out.println("Please enter user username");
                name = in.nextLine();

                while (jpa.findUser(name) == -1) {
                    System.out.println("Username not found, please enter a valid user username " + jpa.findUser(name));
                    name = in.nextLine();
                    counter++;

                    if (counter > 2) {
                        System.out.println("Please try again later");
                        System.exit(0);
                    }
                }

                System.out.println("Please enter password");

                pass = in.nextLine();
                if (jpa.passCheck(name, jpa.passEncrypt(pass)) == true) {

                    if (jpa.getGroup(name) == 1) {
                        //admin

                        while (true) {
                            System.out.println("=====Wecome " + name + " to the admin page======");

                            System.out.println("Please press 1 to view all movies");
                            System.out.println("Please press 2 to view all movie studios");
                            System.out.println("Please press 3 to view a movie's profit");
                            System.out.println("Please press 4 to add a movie rating");
                            System.out.println("=====Admin only permissions======");
                            System.out.println("Please press 5 to add movie");
                            System.out.println("Please press 6 to add a new movie studio");
                            System.out.println("Please press 7 to add a movie to it's studio");

                            System.out.println("Please press 8 to view all user accounts");
                            System.out.println("Please press 9 to delete a user account");

                            System.out.println("Please press 10 to delete a movie");
                            System.out.println("Please press 11 to change title of a movie");
                            System.out.println("Press 12 to delete a movie studio");
                            System.out.println("Please press 13 to view exit");
                            choice = in.nextInt();
                            switch (choice) {
                                case 1:
                                    jpa.allMovies();
                                    break;

                                case 2:
                                    jpa.viewStudios();
                                    break;

                                case 3:
                                    System.out.println("Please enter the movie id ");
                                    int id = in.nextInt();
                                    System.out.println("The profit was " + jpa.calcProfit(id));
                                    break;
                                case 4:
                                    System.out.println("Please enter id you would like to add rating to");
                                    id = in.nextInt();
                                    in.nextLine();
                                    System.out.println("Please add your rating -- Press 1 like like or press 0 to add dislike");

                                    int rating = 0;
                                    rating = in.nextInt();
                                    jpa.updateMovieRating(id, rating);
                                    break;

                                case 5:
                                    System.out.println("Please enter the name of the movie you wish to add");
                                    String mName = in.nextLine();
                                    mName = in.nextLine();
                                    System.out.println("Please enter the rating");

                                    int rate = in.nextInt();
                                    System.out.println("Please enter the run time");
                                    int run = in.nextInt();
                                    System.out.println("Please enter the budget");
                                    double budget = in.nextDouble();
                                    System.out.println("Please enter the box office");
                                    double box = in.nextDouble();

                                    jpa.addMovie(new Movie(mName, rate, run, budget, box));

                                    int mId = jpa.getLatestMovie();

                                    System.out.println("Please enter movie studio id");
                                    int sId = in.nextInt();

                                    jpa.addMovieStudio(mId, sId);

                                    break;

                                case 6:
                                    System.out.println("Please enter studio name");
                                    String sName = in.nextLine();
                                    sName = in.nextLine();
                                    jpa.addStudio(new Studio(sName));

                                    break;
                                case 7:
                                    System.out.println("Please enter movie id");
                                    mId = in.nextInt();

                                    System.out.println("Please enter movie studio id");
                                    sId = in.nextInt();

                                    jpa.addMovieStudio(mId, sId);
                                    break;

                                case 8:
                                    jpa.allUsers();
                                    break;
                                case 9:
                                    System.out.println("Please enter the user id to delete");
                                    id = in.nextInt();
                                    jpa.removeUser(id);
                                    break;

                                case 10:
                                    System.out.println("Please enter the id of the movie you wish to delete");
                                    id = in.nextInt();
                                    jpa.removeMovie(id);
                                    break;
                                case 11:
                                    System.out.println("please enter the id of the movie you wish to change the title");
                                    id = in.nextInt();
                                    in.nextLine();
                                    System.out.println("please enter the new title");
                                    name = in.nextLine();
                                    jpa.changeMovieTitle(id, name);
                                    break;
                                case 12:
                                    System.out.println("please enter the id of the movie stuido");
                                    id = in.nextInt();
                                    jpa.removeStudio(id);
                                    break;
                                case 13:
                                    System.exit(0);
                                    break;
                            }
                        }
                    } else {

                        while (true) {
                            //normal user
                            System.out.println("=====Wecome " + name + "======");
                            System.out.println("Please press 1 to view all movies");
                            System.out.println("Please press 2 to view all movie studios");
                            System.out.println("Please press 3 to view a movie's profit");
                            System.out.println("Please press 4 to add a movie rating");
                            System.out.println("Please press 5 to view exit");

                            choice = in.nextInt();

                            switch (choice) {

                                case 1:
                                    jpa.allMovies();
                                    break;

                                case 2:
                                    jpa.viewStudios();
                                    break;

                                case 3:
                                    System.out.println("Please enter the movie id ");
                                    int id = in.nextInt();
                                    System.out.println("The profit was " + jpa.calcProfit(id));
                                    break;

                                case 4:
                                    System.out.println("Please enter id you would like to add rating to");
                                    id = in.nextInt();
                                    in.nextLine();
                                    System.out.println("Please add your rating -- Press 1 like like or press 0 to add dislike");

                                    int rating = 0;
                                    rating = in.nextInt();
                                    jpa.updateMovieRating(id, rating);

                                    break;

                                case 5:
                                    System.exit(0);
                                    break;

                            }

                        }
                    }
                } else {
                    System.out.println("wrong password or user id please try again later");
                }
        }

    }
}
