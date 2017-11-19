/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import javax.persistence.*;

/**
 *
 * @author carl
 */

@Entity
@SequenceGenerator(name="mId_seq", initialValue=1, allocationSize=1)
@Table (name="Movie")
public class Movie {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="mId_seq")
    private int mId;
    private String mName;
    private int mRating;

    private int runTimeMin;
    private double budget;
    private double boxOffice;
    
    
    
    //possibe inheritence
    //staff --> actor and director
    public Movie() {

    }

    public Movie(String mName, int mRating, int runTimeMin, double budget, double boxOffice) {
        this.mName = mName;
        this.mRating = mRating;
        this.runTimeMin = runTimeMin;
        this.budget = budget;
        this.boxOffice = boxOffice;
    }

    public String getmName() {
        return mName;
    }

    public int getmAgeRating() {
        return mRating;
    }

    public int getRunTimeMin() {
        return runTimeMin;
    }

    public double getBudget() {
        return budget;
    }

    public double getBoxOffice() {
        return boxOffice;
    }
    
    

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmAgeRating(int mAgeRating) {
        this.mRating = mAgeRating;
    }

    public void setRunTimeMin(int runTimeMin) {
        this.runTimeMin = runTimeMin;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setBoxOffice(double boxOffice) {
        this.boxOffice = boxOffice;
    }
    
    
    @Override
    public String toString() {
        return "movie id: " + mId + " title: " + mName + " age rating: " + mRating + " runTimeMin: "+runTimeMin + " box office " + boxOffice + " budget " + budget;
    }
    
    

}
