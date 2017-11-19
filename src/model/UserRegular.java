/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author carl
 */
@Entity
@Table(name = "UserRegular")
@DiscriminatorValue(value = "r")
@PrimaryKeyJoinColumn(referencedColumnName = "muId")
public class UserRegular extends MovieUser {

    private static int USER_GROUP = 2;

    public UserRegular() {

    }

    public UserRegular(String uName, String uPass) {
        super(uName, uPass);
    }

    public int getUSER_GROUP() {
        return USER_GROUP;
    }

}
