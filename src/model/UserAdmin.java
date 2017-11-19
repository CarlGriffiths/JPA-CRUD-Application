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
@Table (name="UserAdmin")
@DiscriminatorValue( value="a" )
@PrimaryKeyJoinColumn(referencedColumnName="muId")
public class UserAdmin extends MovieUser {
    
    
    private static int USER_GROUP = 1;
    
    public UserAdmin(){
        
    }
    
    public UserAdmin(String uName, String uPass){
        super(uName,uPass);
        //this.USER_GROUP = USER_GROUP;
    }

    public  int getUSER_GROUP() {
        return USER_GROUP;
    }

    
}
