package mitfahrboerse.global.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * A generic DTO for handling complex search, pagination, and sorting requests.
 * It combines a flexible filter map with structured pagination and sorting objects.
 * @author Jonathan Schilcher
 */
public class SearchCriteriaDTO {

    private final Map<String, Object> filters = new HashMap<>();
    private Pagination pagination = new Pagination();
    private Sorting sorting = new Sorting();

    // --- Fluent API to easily generate search requests ---

    public SearchCriteriaDTO addFilter(String key, Object value) {
        if (key != null) {
            if (value != null && !value.toString().isEmpty()) {
                this.filters.put(key, value);
            } else {
                this.filters.remove(key);
            }
        }
        return this;
    }

    public SearchCriteriaDTO withPagination(int pageNumber, int pageSize) {
        this.pagination = new Pagination(pageNumber, pageSize);
        return this;
    }

    public SearchCriteriaDTO withSorting(String sortBy, SortDirection direction) {
        this.sorting = new Sorting(sortBy, direction);
        return this;
    }

    // --- Getter ---

    public Map<String, Object> getFilters() {
        return filters;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public Sorting getSorting() {
        return sorting;
    }

    public void setSorting(Sorting sorting) {
        this.sorting = sorting;
    }

    // --- Inner classes for pagination and sorting ---

    public static class Pagination {
        private int pageNumber = 1;
        private int pageSize = 10;

        /**
         * The total number of elements found in the database matching the criteria.
         */
        private long totalElements = 0;

        public Pagination() {}

        public Pagination(int pageNumber, int pageSize) {
            this.pageNumber = pageNumber;
            this.pageSize = pageSize;
        }

        public int getPageNumber() { return pageNumber; }
        public void setPageNumber(int pageNumber) { this.pageNumber = pageNumber; }

        public int getPageSize() { return pageSize; }
        public void setPageSize(int pageSize) { this.pageSize = pageSize; }

        public long getTotalElements() { return totalElements; }
        public void setTotalElements(long totalElements) { this.totalElements = totalElements; }

        public int getOffset() { return (pageNumber - 1) * pageSize; }

        /**
         * Calculates the total number of pages based on totalElements and pageSize.
         * @return The total page count.
         */
        public int getTotalPages() {
            if (pageSize <= 0) return 1;
            int pages = (int) Math.ceil((double) totalElements / pageSize);
            return pages == 0 ? 1 : pages;
        }
    }

    public static class Sorting {
        private String sortBy = "id";
        private SortDirection direction = SortDirection.ASC;

        public Sorting() {}
        public Sorting(String sortBy, SortDirection direction) {
            this.sortBy = sortBy;
            this.direction = direction;
        }

        public String getSortBy() { return sortBy; }
        public void setSortBy(String sortBy) { this.sortBy = sortBy; }

        public SortDirection getDirection() { return direction; }
        public void setDirection(SortDirection direction) { this.direction = direction; }
    }

    public enum SortDirection {
        ASC, DESC;
    }
}