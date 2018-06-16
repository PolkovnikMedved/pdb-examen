package be.solodoukhin.model;

/**
 * @author SOLODOUKHIN VIKTOR
 */
public abstract class Table {

    protected final String code;

    protected final int personsNumber;

    protected Order order;

    public Table(String code, int personsNumber) {
        this.code = code;
        this.personsNumber = personsNumber;
    }

    public String getCode() {
        return code;
    }

    public int getPersonsNumber() {
        return personsNumber;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Table)) return false;

        Table table = (Table) o;

        if (personsNumber != table.personsNumber) return false;
        if (code != null ? !code.equals(table.code) : table.code != null) return false;
        return order != null ? order.equals(table.order) : table.order == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + personsNumber;
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }
}
