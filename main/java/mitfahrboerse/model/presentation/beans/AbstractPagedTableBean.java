package mitfahrboerse.model.presentation.beans;

import mitfahrboerse.global.dto.SearchCriteriaDTO;
import mitfahrboerse.global.dto.SearchCriteriaDTO.SortDirection;
import mitfahrboerse.global.dto.SearchCriteriaDTO.Pagination;
import mitfahrboerse.global.dto.SearchCriteriaDTO.Sorting;

import java.util.Collections;
import java.util.List;

/**
 * Abstract base bean for tables supporting pagination, sorting, and filtering.
 * Referenced in composite components.
 *
 * @author Matthias Schmitt
 */
public abstract class AbstractPagedTableBean<T> extends SafeActionBean {

    private List<T> pagedItems = Collections.emptyList();

    private SearchCriteriaDTO searchCriteriaDto = new SearchCriteriaDTO();

    /**
     * Loads the data based on the current searchCriteriaDto.
     * Must be implemented by concrete beans.
     * Implementation must:
     * 1. Call the service with searchCriteriaDto.
     * 2. Set the result list via setPagedItems().
     * 3. Set the total count via setTotalElements().
     */
    protected abstract void load();

    public AbstractPagedTableBean() {
    }

    /**
     * Initializes the bean. (loads the initial data list).
     */
    public void init() {
        this.searchCriteriaDto = new SearchCriteriaDTO();
        this.searchCriteriaDto.setPagination(new Pagination(1, 10));
        load();
    }

    /**
     * Applies the current filter criteria to the data list.
     * Resets to page 1.
     */
    public void filter() {
        this.searchCriteriaDto.getPagination().setPageNumber(1);
        load();
    }

    /**
     * Navigates directly to a specific page number.
     * @param pageNumber The page to navigate to.
     */
    public void goToPage(int pageNumber) {
        int total = getTotalPages();

        if (pageNumber < 1) {
            pageNumber = 1;
        } else if (pageNumber > total && total > 0) {
            pageNumber = total;
        }

        this.searchCriteriaDto.getPagination().setPageNumber(pageNumber);
        load();
    }

    /**
     * Navigates to the previous page.
     */
    public void prevPage() {
        int current = getCurrentPage();
        if (current > 1) {
            goToPage(current - 1);
        }
    }

    /**
     * Navigates to the page number currently set in the DTO (via input field binding).
     */
    public void goToPage() {
        // The input field has already updated the DTO via setCurrentPage
        load();
    }

    /**
     * Navigates to the next page.
     */
    public void nextPage() {
        int current = getCurrentPage();
        if (current < getTotalPages()) {
            goToPage(current + 1);
        }
    }

    /**
     * Sorts the data list based on a specific column and direction.
     * @param columnId The ID of the column to sort by.
     * @param direction The sort direction (ASC or DESC).
     */
    public void sort(String columnId, SortDirection direction) {
        this.searchCriteriaDto.setSorting(new Sorting(columnId, direction));
        load();
    }

    // --- Computed Properties for View (Delegating to DTO) ---

    /**
     * Checks if the table is currently on the first page.
     * @return true if on the first page, false otherwise.
     */
    public boolean isFirstPage() {
        return getCurrentPage() <= 1;
    }

    /**
     * Checks if the table is currently on the last page.
     * @return true if on the last page, false otherwise.
     */
    public boolean isLastPage() {
        return getCurrentPage() >= getTotalPages();
    }

    /**
     * Gets the current page number.
     * @return The current page.
     */
    public int getCurrentPage() {
        return this.searchCriteriaDto.getPagination().getPageNumber();
    }

    /**
     * Sets the current page number (e.g., from user input).
     * @param currentPage The page number.
     */
    public void setCurrentPage(int currentPage) {
        this.searchCriteriaDto.getPagination().setPageNumber(currentPage);
    }

    /**
     * Gets the total number of pages available.
     * @return The total page count.
     */
    public int getTotalPages() {
        return this.searchCriteriaDto.getPagination().getTotalPages();
    }

    // --- Helper for Concrete Beans ---

    /**
     * Helper method to update the total element count in the DTO.
     * @param count The total number of elements in the database.
     */
    protected void setTotalElements(long count) {
        this.searchCriteriaDto.getPagination().setTotalElements(count);
    }

    // --- Standard Getters & Enums ---

    /**
     * Gets the list of items for the current page.
     * @return The list of paged items.
     */
    public List<T> getPagedItems() {
        return pagedItems;
    }

    /**
     * Sets the list of items for the current page.
     * @param pagedItems The list of paged items.
     */
   public void setPagedItems(List<T> pagedItems) {
        this.pagedItems = pagedItems;
    }

    /**
     * Gets the search criteria DTO.
     * @return The search criteria DTO.
     */
    public SearchCriteriaDTO getSearchCriteriaDto() {
        return searchCriteriaDto;
    }

    /**
     * Sets the search criteria DTO.
     * @param searchCriteriaDto The search criteria DTO.
     */
    public void setSearchCriteriaDto(SearchCriteriaDTO searchCriteriaDto) {
        this.searchCriteriaDto = searchCriteriaDto;
    }

    /**
     * Exposes the SortDirection.ASC enum to the XHTML page.
     * @return SortDirection.ASC
     */
    public SortDirection getSortDirAsc() {
        return SortDirection.ASC;
    }

    /**
     * Exposes the SortDirection.DESC enum to the XHTML page.
     * @return SortDirection.DESC
     */
    public SortDirection getSortDirDesc() {
        return SortDirection.DESC;
    }
}