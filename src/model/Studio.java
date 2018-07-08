
package model;

import javax.persistence.*;
import java.util.*;

/**
 *
 * @author carl
 */
@Entity
@SequenceGenerator(name = "sId_seq", initialValue = 1, allocationSize = 1)
public class Studio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sId_seq")
    private int sId;
    private String sName;

    @OneToMany(mappedBy = "studio", cascade = CascadeType.REMOVE) // change this to include .REMOVE
    private List<Movie> mlist = new ArrayList<>();

    public Studio() {

    }

    public Studio(String name) {
        this.sName = name;

    }

    public int getsId() {
        return sId;
    }

    public String getsName() {
        return sName;
    }

    public List<Movie> getMlist() {
        return mlist;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public void setMlist(List<Movie> mlist) {
        this.mlist = mlist;
    }

    public void addMovie(Movie m) {
        mlist.add(m);
        m.setStudio(this);
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < mlist.size(); i++) {
            s += mlist.get(i);
        }
        return s;
    }

}
