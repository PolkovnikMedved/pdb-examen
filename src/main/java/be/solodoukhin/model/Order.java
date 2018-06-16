package be.solodoukhin.model;

import be.solodoukhin.model.enumeration.OrderState;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author SOLODOUKHIN VIKTOR
 *
 * date 16/06/18
 *
 * Cette classe représente la table TCOMMANDE
 */
public class Order {

    /*
        ID = NUM_COM
     */
    private Integer number = null;
    /*
        MOMENTA_COM
     */
    private LocalDateTime arrivedMoment = null;
    /*
        MOMENTS_COM
     */
    private LocalDateTime outMoment = null;
    /*
        ETAT_COM
     */
    private OrderState state;
    /*
        TOTAL_COM
     */
    private Double total;
    /*
        FKSERVEUR_COM
     */
    private final Waiter waiter;

    private List<OrderLine> articles;

    public Order(Waiter waiter) {
        this.waiter = waiter;
        this.state = OrderState.EN_COURS;
        this.total = 0.0;
        this.articles = new ArrayList<>();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public LocalDateTime getArrivedMoment() {
        return arrivedMoment;
    }

    public void setArrivedMoment(LocalDateTime arrivedMoment) {
        this.arrivedMoment = arrivedMoment;
    }

    public Optional<LocalDateTime> getOutMoment() {
        return Optional.ofNullable(outMoment);
    }

    public void setOutMoment(LocalDateTime outMoment) {
        this.outMoment = outMoment;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public List<OrderLine> getArticles() {
        return articles;
    }

    public void setArticles(List<OrderLine> articles) {
        this.articles = articles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (number != null ? !number.equals(order.number) : order.number != null) return false;
        if (arrivedMoment != null ? !arrivedMoment.equals(order.arrivedMoment) : order.arrivedMoment != null)
            return false;
        if (outMoment != null ? !outMoment.equals(order.outMoment) : order.outMoment != null) return false;
        if (state != order.state) return false;
        if (total != null ? !total.equals(order.total) : order.total != null) return false;
        if (waiter != null ? !waiter.equals(order.waiter) : order.waiter != null) return false;
        return articles != null ? articles.equals(order.articles) : order.articles == null;
    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (arrivedMoment != null ? arrivedMoment.hashCode() : 0);
        result = 31 * result + (outMoment != null ? outMoment.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (waiter != null ? waiter.hashCode() : 0);
        result = 31 * result + (articles != null ? articles.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "number=" + number +
                ", arrivedMoment=" + arrivedMoment +
                ", outMoment=" + outMoment +
                ", state=" + state +
                ", total=" + total +
                ", waiter=" + waiter +
                ", articles=" + articles +
                '}';
    }
}
