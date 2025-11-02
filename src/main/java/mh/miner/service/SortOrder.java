package mh.miner.service;

public abstract class SortOrder {
    public enum SortType {
        ASC,
        DESC
    }

    private String column;
    private SortType type;

    public SortOrder(String column, SortType type) {
        super();
        this.column = column;
        this.type = type;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public SortType getType() {
        return type;
    }

    public void setType(SortType type) {
        this.type = type;
    }
}
