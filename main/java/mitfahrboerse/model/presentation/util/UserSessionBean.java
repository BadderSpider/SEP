package mitfahrboerse.model.presentation.util;

import jakarta.faces.context.FacesContext;
import mitfahrboerse.global.dto.UserDTO;
import mitfahrboerse.global.dto.UserRole;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

/**
 *
 *
 * @author René Schmaderer
 */

//TODO Rene: JavDocs
@SessionScoped
@Named
public class UserSessionBean implements Serializable {


    private static final long serialVersionUID = 1L;
    private UserDTO userDTO;
    private int sessionID;

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public String invalidateSession() {
        this.userDTO = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        return "/view/facelets/anonymous/rides.xhtml?faces-redirect=true";
    }

    public UserDTO getCurrentUser() {
        return this.userDTO;
    }


    public boolean isLoggedIn() {
        return true;
        //return this.userDTO != null; toDO
    }


    public boolean isAdmin() {
        return true;
        /*if (this.userDTO == null) {
            return false;
        }
        return userDTO.getUserRole().equals(UserRole.ADMIN);*/ //todo
    }


    public boolean isAnonymous() {
        return userDTO == null;
    }

    public long getUserId() {
        if (this.userDTO == null) {
            return 0;
        }
        return this.userDTO.getUserId();
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}