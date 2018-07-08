
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
    @OrderBy("mId asc")
    private int mId;
    private String mName;
    private int mRating;

    private int runTimeMin;
    private double budget;
    private double boxOffice;
    
    @ManyToOne()
    @JoinColumn(name = "sId")
    private Studio studio;
    
    
    
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

    public int getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public int getmRating() {
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

    public void setmRating(int rate) {
        this.mRating = rate;
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

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }
    
    
    
    
    @Override
    public String toString() {
        return "movie id: " + mId + " title: " + mName + " movie rating: " + mRating + " runTimeMin: "+runTimeMin + " box office " + boxOffice + " budget " + budget;
        
    }
    
    
    
    

}
