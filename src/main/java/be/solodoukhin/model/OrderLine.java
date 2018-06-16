package be.solodoukhin.model;

import be.solodoukhin.model.embedded.OrderLineId;
import be.solodoukhin.model.enumeration.OrderLineState;

/**
 * @author SOLODOUKHIN VIKTOR
 *
 * date 16/06/18
 *
 * Classe qui représente la table TLIGNECMD
 */
public class OrderLine {

    /*
        ID = FKCOMMANDE_LIG, NUM_LIG
     */
    private OrderLineId id;
    /*
        FK_ARTICLE_LIG
     */
    private final Article article;
    /*
        ETAT_LIG
     */
    private OrderLineState state;
    /*
        PAYE_LIG
     */
    private Boolean paid;
    /*
        PRIX_LIG
     */
    private Double price;

    public OrderLine(OrderLineId id, Article article, Double price) {
        this.id = id;
        this.article = article;
        this.price = price;
    }

    public OrderLine(OrderLineId id, Article article, OrderLineState state, Boolean paid, Double price) {
        this.id = id;
        this.article = article;
        this.state = state;
        this.paid = paid;
        this.price = price;
    }

    public OrderLineId getId() {
        return id;
    }

    public void setId(OrderLineId id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public OrderLineState getState() {
        return state;
    }

    public void setState(OrderLineState state) {
        this.state = state;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderLine orderLine = (OrderLine) o;

        if (id != null ? !id.equals(orderLine.id) : orderLine.id != null) return false;
        if (article != null ? !article.equals(orderLine.article) : orderLine.article != null) return false;
        if (state != orderLine.state) return false;
        if (paid != null ? !paid.equals(orderLine.paid) : orderLine.paid != null) return false;
        return price != null ? price.equals(orderLine.price) : orderLine.price == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (article != null ? article.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (paid != null ? paid.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + id +
                ", article=" + article +
                ", state=" + state +
                ", paid=" + paid +
                ", price=" + price +
                '}';
    }
}
