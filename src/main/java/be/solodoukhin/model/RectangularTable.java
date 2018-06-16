package be.solodoukhin.model;

/**
 * @author SOLODOUKHIN VIKTOR
 *
 * date 16/06/18
 */
public class RectangularTable extends Table{

    /**
     * x,y désigne le point supérieur haut
     */
    private final Double x;
    private final Double y;
    private final Double width;
    private final Double height;

    public RectangularTable(String code, int personsNumber, Double x, Double y, Double width, Double height) {
        super(code, personsNumber);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getWidth() {
        return width;
    }

    public Double getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RectangularTable)) return false;
        if (!super.equals(o)) return false;

        RectangularTable that = (RectangularTable) o;

        if (x != null ? !x.equals(that.x) : that.x != null) return false;
        if (y != null ? !y.equals(that.y) : that.y != null) return false;
        if (width != null ? !width.equals(that.width) : that.width != null) return false;
        return height != null ? height.equals(that.height) : that.height == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (x != null ? x.hashCode() : 0);
        result = 31 * result + (y != null ? y.hashCode() : 0);
        result = 31 * result + (width != null ? width.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RectangularTable{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", code='" + code + '\'' +
                ", personsNumber=" + personsNumber +
                ", order=" + order +
                '}';
    }
}
