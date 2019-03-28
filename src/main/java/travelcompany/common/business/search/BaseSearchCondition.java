package travelcompany.common.business.search;

public abstract class BaseSearchCondition <ID>{
    protected ID id;
    protected SortDirection sortDirection = SortDirection.ASC;
    protected SortType sortType = SortType.SIMPLE;
<<<<<<< HEAD
    protected Paginator paginator;

    public boolean shouldPaginate() {
        return paginator != null && paginator.getLimit() > 0 && paginator.getOffset() >= 0;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }
=======
>>>>>>> github/master

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
