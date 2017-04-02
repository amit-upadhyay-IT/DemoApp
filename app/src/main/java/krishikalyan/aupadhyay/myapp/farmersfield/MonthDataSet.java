package krishikalyan.aupadhyay.myapp.farmersfield;

/**
 * Created by aupadhyay on 4/2/17.
 */

public class MonthDataSet {

    private double max_germ_temp, max_ph, max_rainfall, max_rip_temp, min_germ_temp, min_ph, min_rainfall, min_rip_temp;
    private String name;

    public MonthDataSet(double max_germ_temp, double max_ph, double max_rainfall, double max_rip_temp, double min_germ_temp, double min_ph, double min_rainfall, double min_rip_temp, String name) {
        this.max_germ_temp = max_germ_temp;
        this.max_ph = max_ph;
        this.max_rainfall = max_rainfall;
        this.max_rip_temp = max_rip_temp;
        this.min_germ_temp = min_germ_temp;
        this.min_ph = min_ph;
        this.min_rainfall = min_rainfall;
        this.min_rip_temp = min_rip_temp;
        this.name = name;
    }

    public MonthDataSet() {
    }

    public double getMax_germ_temp() {
        return max_germ_temp;
    }

    public void setMax_germ_temp(double max_germ_temp) {
        this.max_germ_temp = max_germ_temp;
    }

    public double getMax_ph() {
        return max_ph;
    }

    public void setMax_ph(double max_ph) {
        this.max_ph = max_ph;
    }

    public double getMax_rainfall() {
        return max_rainfall;
    }

    public void setMax_rainfall(double max_rainfall) {
        this.max_rainfall = max_rainfall;
    }

    public double getMax_rip_temp() {
        return max_rip_temp;
    }

    public void setMax_rip_temp(double max_rip_temp) {
        this.max_rip_temp = max_rip_temp;
    }

    public double getMin_germ_temp() {
        return min_germ_temp;
    }

    public void setMin_germ_temp(double min_germ_temp) {
        this.min_germ_temp = min_germ_temp;
    }

    public double getMin_ph() {
        return min_ph;
    }

    public void setMin_ph(double min_ph) {
        this.min_ph = min_ph;
    }

    public double getMin_rainfall() {
        return min_rainfall;
    }

    public void setMin_rainfall(double min_rainfall) {
        this.min_rainfall = min_rainfall;
    }

    public double getMin_rip_temp() {
        return min_rip_temp;
    }

    public void setMin_rip_temp(double min_rip_temp) {
        this.min_rip_temp = min_rip_temp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
