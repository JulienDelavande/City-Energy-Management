package code.Producer;

import code.Tools.*;

public abstract class Producer {

    // info
    protected double powerMax;
    protected String prodName;
    protected String cityName;

    // current state at time t
    protected boolean isOn;
    protected double power;

    public Producer(String cityName, String prodName) {
        this.cityName = cityName;
        this.prodName = prodName;
        this.isOn = true;
        this.power = 0;
    }

    /**
     * switch on the producer
     */
    public void switchOn() {
        this.isOn = true;
    };

    /**
     * switch of the producer
     */
    public void switchOff() {
        this.isOn = false;
    }

    /**
     * set the power provided by the producer depending on the time and the weather
     * 
     * @param time    the current time
     * @param weather the weather of the city
     */
    public abstract void check(Time time, Weather weather);

    /**
     * to get the production of the city for one minute
     * 
     * @param time    current time
     * @param weather the weather of the city
     * @return the prod of the city for one minute
     */
    public double getProd(Time time, Weather weather) {
        double prod_t = 0;
        if (this.isOn) {
            this.check(time, weather);
            prod_t += this.power * 60;
        }
        return prod_t;
    }

    /**
     * to get the production of the city for one day
     * 
     * @param time    the current time
     * @param weather the weather of the city
     * @return the prod of the city for one day
     */
    public double getProdDay(Time time, Weather weather) {
        double prod_d = 0;
        for (int i = 0; i < 3600; i++) {
            prod_d += this.getProd(time, weather);
            time.incMin();
        }
        return prod_d;
    }

    // setters and getters
    public double getPowerMax() {
        return this.powerMax;
    }

    public void setPowerMax(double powerMax) {
        this.powerMax = powerMax;
    }

    public String getProdName() {
        return this.prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public boolean isIsOn() {
        return this.isOn;
    }

    public double getPower() {
        return this.power;
    }

    public void setPower(double power) {
        this.power = power;
    }

}
