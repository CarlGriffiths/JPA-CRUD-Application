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
@SequenceGenerator(name="uId_seq", initialValue=1, allocationSize=1)
@Table (name="MovieUser")
@Inheritance( strategy = InheritanceType.JOINED )
@DiscriminatorColumn( name = "type" )
public class MovieUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uId_seq")
    private int muId;
    private String uName;
    private String uPass;
    

    public MovieUser() {

    }

    MovieUser(String uName, String uPass) {
        this.uName = uName;
        this.uPass = uPass;
        
    }

    public int getuId() {
        return muId;
    }

    public String getuName() {
        return uName;
    }

    public String getuPass() {
        return uPass;
    }

    public void setuId(int uId) {
        this.muId = uId;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public void setuPass(String uPass) {
        this.uPass = uPass;
    }

    /* public int getUsergroup() {
    return usergroup;
    }*/

    /* public void setUsergroup(int usergroup) {
    this.usergroup = usergroup;
    }*/
    
}
