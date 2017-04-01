package krishikalyan.aupadhyay.myapp.whether_model;

/**
 * Created by aupadhyay on 4/1/17.
 */

public class Rainfall_Model_Dataset {

    private double average_Temperature;
    private double cloud_Cover;
    private double diurnal_TemperatureRange;
    private double evatranspiration;
    private double ground_Frost;
    private double maximum_Temperature;
    private double minimum_Temperature;
    private String month;
    private double precipitation;
    private double vapour_Pressure;
    private double wet_Days_Frequency;

    public Rainfall_Model_Dataset(double average_Temperature, double cloud_Cover, double diurnal_TemperatureRange, double evatranspiration, double ground_Frost, double maximum_Temperature, double minimum_Temperature, String month, double precipitation, double vapour_Pressure, double wet_Days_Frequency) {
        this.average_Temperature = average_Temperature;
        this.cloud_Cover = cloud_Cover;
        this.diurnal_TemperatureRange = diurnal_TemperatureRange;
        this.evatranspiration = evatranspiration;
        this.ground_Frost = ground_Frost;
        this.maximum_Temperature = maximum_Temperature;
        this.minimum_Temperature = minimum_Temperature;
        this.month = month;
        this.precipitation = precipitation;
        this.vapour_Pressure = vapour_Pressure;
        this.wet_Days_Frequency = wet_Days_Frequency;
    }

    public Rainfall_Model_Dataset() {
    }

    public double getAverage_Temperature() {
        return average_Temperature;
    }

    public void setAverage_Temperature(double average_Temperature) {
        this.average_Temperature = average_Temperature;
    }

    public double getCloud_Cover() {
        return cloud_Cover;
    }

    public void setCloud_Cover(double cloud_Cover) {
        this.cloud_Cover = cloud_Cover;
    }

    public double getDiurnal_TemperatureRange() {
        return diurnal_TemperatureRange;
    }

    public void setDiurnal_TemperatureRange(double diurnal_TemperatureRange) {
        this.diurnal_TemperatureRange = diurnal_TemperatureRange;
    }

    public double getEvatranspiration() {
        return evatranspiration;
    }

    public void setEvatranspiration(double evatranspiration) {
        this.evatranspiration = evatranspiration;
    }

    public double getGround_Frost() {
        return ground_Frost;
    }

    public void setGround_Frost(double ground_Frost) {
        this.ground_Frost = ground_Frost;
    }

    public double getMaximum_Temperature() {
        return maximum_Temperature;
    }

    public void setMaximum_Temperature(double maximum_Temperature) {
        this.maximum_Temperature = maximum_Temperature;
    }

    public double getMinimum_Temperature() {
        return minimum_Temperature;
    }

    public void setMinimum_Temperature(double minimum_Temperature) {
        this.minimum_Temperature = minimum_Temperature;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public double getVapour_Pressure() {
        return vapour_Pressure;
    }

    public void setVapour_Pressure(double vapour_Pressure) {
        this.vapour_Pressure = vapour_Pressure;
    }

    public double getWet_Days_Frequency() {
        return wet_Days_Frequency;
    }

    public void setWet_Days_Frequency(double wet_Days_Frequency) {
        this.wet_Days_Frequency = wet_Days_Frequency;
    }
}
