package krishikalyan.aupadhyay.myapp.farmersfield;

/**
 * Created by aupadhyay on 4/2/17.
 */

public class SoilDataSet {

    private String Light;
    private String Temp;
    private String mois;
    private String time;


    public SoilDataSet(String light, String temp, String mois, String time) {
        Light = light;
        Temp = temp;
        this.mois = mois;
        this.time = time;
    }

    public SoilDataSet() {
    }

    public String getTime()
    {
        return time;
    }

    public String getLight() {
        return Light;
    }

    public void setLight(String light) {
        Light = light;
    }

    public String getTemp() {
        return Temp;
    }

    public void setTemp(String temp) {
        Temp = temp;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }
}
