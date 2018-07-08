
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
    private String uName;
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uId_seq")
    private int muId;
    
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

    @Override
    public String toString() {
        return " User id: " + muId + "\t" +" Username " + uName + "\t" + " Encrypted password: " + uPass;
    }

  
    
}
