/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsShcherbuk;

import entitysLishtvan.Users;
import javax.ejb.Singleton;
import javax.ejb.Stateful;

/**
 *
 * @author KravcovaSherbukLishtvan
 */
@Singleton
public class AuthUser implements AuthUserLocalShcherbuk {
    private Users currentUser = null;

    @Override
    public Users getCurrentUser() {
        return currentUser;
    }

    @Override
    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }

}
