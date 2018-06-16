package be.solodoukhin.model;

/**
 * @author SOLODOUKHIN VIKTOR
 *
 * date 16/06/18
 */
public class RoundTable extends Table{

    private Double xCenter;
    private Double yCenter;
    private Double radius;

    public RoundTable(String code, int personsNumber, Double xCenter, Double yCenter, Double radius) {
        super(code, personsNumber);
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.radius = radius;
    }

    public Double getxCenter() {
        return xCenter;
    }

    public void setxCenter(Double xCenter) {
        this.xCenter = xCenter;
    }

    public Double getyCenter() {
        return yCenter;
    }

    public void setyCenter(Double yCenter) {
        this.yCenter = yCenter;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoundTable)) return false;
        if (!super.equals(o)) return false;

        RoundTable that = (RoundTable) o;

        if (xCenter != null ? !xCenter.equals(that.xCenter) : that.xCenter != null) return false;
        if (yCenter != null ? !yCenter.equals(that.yCenter) : that.yCenter != null) return false;
        return radius != null ? radius.equals(that.radius) : that.radius == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (xCenter != null ? xCenter.hashCode() : 0);
        result = 31 * result + (yCenter != null ? yCenter.hashCode() : 0);
        result = 31 * result + (radius != null ? radius.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoundTable{" +
                "xCenter=" + xCenter +
                ", yCenter=" + yCenter +
                ", radius=" + radius +
                ", code='" + code + '\'' +
                ", personsNumber=" + personsNumber +
                ", order=" + order +
                '}';
    }
}
