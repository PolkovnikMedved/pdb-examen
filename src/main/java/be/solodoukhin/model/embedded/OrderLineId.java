package be.solodoukhin.model.embedded;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public class OrderLineId {

    /*
        FKCOMMANDE_LIG
     */
    private final Integer orderId;
    /*
        NUM_LIG
     */
    private final Integer orderLineId;

    public OrderLineId(Integer orderId, Integer orderLineId) {
        this.orderId = orderId;
        this.orderLineId = orderLineId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getOrderLineId() {
        return orderLineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderLineId that = (OrderLineId) o;

        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        return orderLineId != null ? orderLineId.equals(that.orderLineId) : that.orderLineId == null;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (orderLineId != null ? orderLineId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderLineId{" +
                "orderId=" + orderId +
                ", orderLineId=" + orderLineId +
                '}';
    }
}
