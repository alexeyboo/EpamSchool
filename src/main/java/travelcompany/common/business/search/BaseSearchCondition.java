package travelcompany.common.business.search;

public abstract class BaseSearchCondition <ID>{
    protected ID id;
    protected SortDirection sortDirection = SortDirection.ASC;
    protected SortType sortType = SortType.SIMPLE;

    public ID getId() {
        return id;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(SortDirection sortDirection) {
        this.sortDirection = sortDirection;
    }

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public boolean needSorting() {
        return sortDirection != null && sortType != null;
    }
}
