package mitfahrboerse.model.presentation.beans;

import mitfahrboerse.global.dto.RequestDTO;
import mitfahrboerse.global.dto.SearchCriteriaDTO;
import mitfahrboerse.model.business.service.FindService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Backing bean for the requests.xhtml overview page.
 * Inherits from AbstractPagedTableBean.
 * To manage the search criteria and display the resulting list
 * of available ride requests in a paginated table.
 *
 * @author Anton Hollube
 */
@ViewScoped
@Named
public class RequestsBean extends AbstractPagedTableBean<RequestDTO> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * DTO to hold the search criteria from the filter fields in the table header.
     */
    private SearchCriteriaDTO searchCriteria;

    /**
     * Initializes the bean.
     * Creates a new SearchCriteriaDTO and loads the initial data.
     */
    @PostConstruct
    @Override
    public void init() {
        super.init(); 
        this.searchCriteria = new SearchCriteriaDTO();
        filter();
    }

    /**
     * Action method for the 'cb_search' button.
     * Overrides the abstract method from the parent class.
     * To execute a search using the FindService based on the
     * criteria in the DTOs and update the table.
     */
    @Override
    public void filter() {
     
    }


    /**
     * Provides the SearchCriteriaDTO to the Facelet for filter bindings.
     * @return The SearchCriteriaDTO.
     */
    public SearchCriteriaDTO getSearchCriteria() {
        return searchCriteria;
    }

    /**
     * Sets the SearchCriteriaDTO (used by JSF).
     * @param searchCriteria The SearchCriteriaDTO.
     */
    public void setSearchCriteria(SearchCriteriaDTO searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}
}