/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsShcherbuk;

import entitysLishtvan.Users;
import javax.ejb.Local;

/**
 *
 * @author KravcovaSherbukLishtvan
 */
@Local
public interface AuthUserLocalShcherbuk {
    public Users getCurrentUser();
    public void setCurrentUser(Users currentUser);
}
