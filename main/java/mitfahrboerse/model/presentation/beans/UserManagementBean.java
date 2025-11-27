package mitfahrboerse.model.presentation.beans;

import mitfahrboerse.global.dto.UserDTO;
import mitfahrboerse.model.business.service.FindService;
import mitfahrboerse.model.business.service.ProfileService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 * Backing bean for the user_management.xhtml page.
 * Inherits from AbstractPagedTableBean.
 * This is an Admin-only bean used to display a searchable,
 * paginated list of all users in the system.
 *
 * @author Anton Hollube
 */
@ViewScoped
@Named
public class UserManagementBean extends AbstractPagedTableBean<UserDTO> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * Initializes the bean and
     * initializes the parent table and loads the first page of data.
     */
    @PostConstruct
    @Override
    public void init() {
        super.init();
        filter();
    }

    /**
     * Action method called by the paged table.
     * To load the list of all users based on the admin's
     * search/filter criteria.
     */
    @Override
    public void filter() {
    }

    /**
     * Navigation method for 'l_toCreateUser'.
     * Navigates the admin to the registration page to create a new user.
     * @return The navigation outcome (redirect to register.xhtml).
     */
    public String goToCreateUser() {
        //TODO: Remove hardcoded string
        return "/view/anonymous/register.xhtml?faces-redirect=true";
    }

    /**
     * Navigation method for clicking on a table row.
     *  Navigates the admin to the profile page for the selected user.
     * @param userDto The user to edit.
     * @return The navigation outcome (redirect to profile.xhtml).
     */
    public String goToUserProfile(UserDTO userDto) {
        //TODO: Remove hardcoded string
        return "/view/registered/profile.xhtml?faces-redirect=true&userId=" + userDto.getUserId();
    }

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}
}