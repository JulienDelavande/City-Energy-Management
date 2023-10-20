package code.Tools;

public class Weather {

    /*
     * info ->Toulouse
     * https://fr.climate-data.org/europe/france/midi-pyrenees/toulouse-341/#climate
     * -table https://fr.windfinder.com/windstatistics/toulouse_blagnac
     */

    // {average, min, max}
    private double[][] listOfTemperatureByMonth = { { 5.8, 2.7, 9.4 }, { 6.2, 2.4, 10.5 }, { 9.5, 5.1, 14.3 },
            { 12.4, 7.8, 17.3 }, { 15.9, 11.2, 20.7 }, { 20.2, 15.2, 25.3 }, { 22.4, 17.2, 27.5 }, { 22.4, 17.4, 27.7 },
            { 19.3, 14.5, 24.5 }, { 15.4, 11.5, 20 }, { 9.7, 6.6, 13.3 }, { 6.6, 3.5, 10.2 } };

    // {max, min}
    private int[][] listOfWindForceByMonth = { { 22, 8 }, { 24, 9 }, { 25, 9 }, { 23, 9 }, { 22, 9 }, { 17, 8 },
            { 18, 8 }, { 18, 7 }, { 17, 7 }, { 24, 8 }, { 23, 8 }, { 20, 8 } };

    // {average}
    private double[] listOfNumHourOfSun = { 4.3, 5.8, 7.2, 8.2, 8.7, 9.9, 9.8, 9.5, 8.7, 7.1, 5.1, 4.8 };

    private double temperature;
    private double cloud; // 0<cloud<1
    private double windForce; // 0<windForce<1
    private boolean automatic; // true if the weather is set automatically depending on the time

    public Weather(double temperature, double cloud, double windForce) {
        this.temperature = temperature;
        this.cloud = cloud;
        this.windForce = windForce;
        this.automatic = false;
    }

    public Weather() {
        this.temperature = 20;
        this.cloud = 0;
        this.windForce = 0;
        this.automatic = false;
    }

    /**
     * set the weather automatically in a given date and hour
     * 
     * @param time the current time
     */
    public void setWeatherOnTime(Time time) {
        double meanHour = 12;
        double sigmaHour = 14 * Math.exp(-(Math.pow(time.convertMonthInt() - 6, 2)) / (2 * Math.pow(25, 2)));

        // temperature
        // double meanTemp = listOfTemperatureByMonth[time.convertMonthInt()-1][0];
        double minTemp = listOfTemperatureByMonth[time.convertMonthInt() - 1][1];
        double maxTemp = listOfTemperatureByMonth[time.convertMonthInt() - 1][2];
        this.temperature = maxTemp * Math.exp(-(Math.pow(time.getHour() - meanHour, 2)) / (2 * Math.pow(sigmaHour, 2)))
                + minTemp;

        // wind force
        int maxWindForce = listOfWindForceByMonth[time.convertMonthInt() - 1][0];
        // int minWindForce = listOfWindForceByMonth[time.convertMonthInt()-1][1];
        this.windForce = maxWindForce / 25;

        // cloud
        double hourOfSun = listOfNumHourOfSun[time.convertMonthInt() - 1];
        double randomCloud = Math.random();
        if (13 * randomCloud > 11 - hourOfSun) {
            this.cloud = 0;
        } else {
            this.cloud = 0.6;
        }
    }

    /**
     * set the weather automatically in a given date
     * 
     * @param time the current time
     */
    public void setWeatherOnTimeMeanDay(Time time) {
        // temperature
        double meanTemp = listOfTemperatureByMonth[time.convertMonthInt() - 1][0];
        this.temperature = meanTemp;

        // wind force
        int maxWindForce = listOfWindForceByMonth[time.convertMonthInt() - 1][0];
        // int minWindForce = listOfWindForceByMonth[time.convertMonthInt()-1][1];
        this.windForce = maxWindForce / 25;

        // cloud
        double hourOfSun = listOfNumHourOfSun[time.convertMonthInt() - 1];
        double randomCloud = Math.random();
        if (13 * randomCloud > 11 - hourOfSun) {
            this.cloud = 0;
        } else {
            this.cloud = 0.6;
        }
    }

    @Override
    public String toString() {
        return "{" + " temperature='" + getTemperature() + "'" + ", cloud='" + getCloud() + "'" + ", windForce='"
                + getWindForce() + "'" + "}";
    }

    public boolean isAutomatic() {
        return this.automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getCloud() {
        return this.cloud;
    }

    public void setCloud(double cloud) {
        this.cloud = cloud;
    }

    public double getWindForce() {
        return this.windForce;
    }

    public void setWindForce(double windForce) {
        this.windForce = windForce;
    }

}
