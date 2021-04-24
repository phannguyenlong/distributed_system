public class Advertising {
    private double tv, radio, newspaper, sales;

    public double getTv() {
        return tv;
    }

    public void setTv(double tv) {
        this.tv = tv;
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public double getNewspaper() {
        return newspaper;
    }

    public void setNewspaper(double newspaper) {
        this.newspaper = newspaper;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return tv + "," + radio + "," + newspaper + "," + sales;
    }

}
