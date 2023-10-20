package code.ElectronicDevices;

import code.Tools.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class ElecDevice {

    // infos for each device
    protected String deviceName;
    protected double powerMax;
    protected String cityName;
    protected String homeName;
    protected int numCycle;
    protected double consoPerc;

    // state of the device a each minute
    protected boolean on;
    protected double power;
    protected double beguining;
    protected double duration;

    // different periods
    protected ArrayList<Cycle> listOfCycles;

    public ElecDevice(String deviceName, String homeName, String cityName) {
        this.cityName = cityName;
        this.homeName = homeName;
        this.deviceName = deviceName;
        this.on = false;
        this.power = 0;
        this.listOfCycles = new ArrayList<Cycle>();
        this.consoPerc = 1;
    }

    /**
     * add an new cycle
     * 
     * @param duration  the duration of the use in minutes (0<duration<=3600)
     * @param beguining the time of the beguining in minutes in the day
     * @param dayWeek   the days in the week when the device is used
     * @param month     the month when the device is used
     * @param powerPerc the power percentage of the use
     */
    public void addCycle(double[] duration, double[] beguining, int dayWeek, int month, double[] powerPerc) {
        Cycle cycle = new Cycle(duration, beguining, dayWeek, month, powerPerc);
        this.listOfCycles.add(cycle);
        this.numCycle++;
    }

    /**
     * switch of/on the on value if the device is beeing use at the date_t
     * 
     * @param time the current time
     */
    public void check(Time time) {

        for (Cycle cycle : listOfCycles) {

            if (cycle.isOn(time, this)) {
                this.power = this.powerMax * this.normalPowerPerc(cycle.getPowerPerc()[0], cycle.getPowerPerc()[1]);
                this.on = true;
                return;
            }
        }
        this.on = false;
        return;
    }

    /**
     * consomation of energie in one minute a the date_t
     * 
     * @param time the current time
     * @return the consomation of the device during the last minute
     */
    public double getConso(Time time) {
        double conso_t = 0;
        this.check(time);
        if (on) {
            conso_t += this.power * 60;
        }
        return conso_t;
    }

    /**
     * get the consommation of the device for one day at a given date
     * 
     * @param time the current time
     * @return the consomation of the device during the last day
     */
    public double getConsoDay(Time time) {
        double conso_d = 0;
        for (Cycle cycle : listOfCycles) {
            if (cycle.isOnThisDay(time)) {
                conso_d += this.normalBegOrDur(cycle.getDuration()[0], cycle.getDuration()[1]) * 60
                        * this.normalPowerPerc(cycle.getPowerPerc()[0], cycle.getPowerPerc()[0]) * powerMax;
            }
        }
        return conso_d;
    }

    /**
     * to get a percentage of the power max with a normal law
     * 
     * @param mean the mean of the power percentage (0<mean<1)
     * @param var  the variance of the power percentage
     * @return the percentage of the power max
     */
    public double normalPowerPerc(double mean, double var) {
        double consoPerc = 0;
        Random rand = new Random();
        consoPerc = mean + var * rand.nextGaussian();
        if (consoPerc > 1) {
            return 1;
        }
        if (consoPerc < 0.2) {
            return 0.2;
        }
        return consoPerc;
    }

    /**
     * to get the beguining or the duration of the use of the device with a normal
     * law
     * 
     * @param mean the mean of the duration or beguining (in minute)
     * @param var  the variance of the duration or beguining
     * @return the beguining or the duration of the use of the device
     */
    public double normalBegOrDur(double mean, double var) {
        double beguining = 0;
        Random rand = new Random();
        beguining = mean + var * rand.nextGaussian();
        if (beguining < 0) {
            return 0;
        }
        if (beguining > 24 * 60) {
            return 24 * 60;
        }
        return beguining;
    }

    @Override
    public String toString() {
        return "{" + " deviceName='" + getDeviceName() + "'" + ", powerMax='" + getPowerMax() + "'" + ", cityName='"
                + getCityName() + "'" + ", homeName='" + getHomeName() + "'" + ", numCycle='" + getNumCycle() + "'"
                + ", on='" + getOn() + "'" + ", power='" + getPower() + "'" + "}";
    }

    // setters and getters

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public double getPowerMax() {
        return this.powerMax;
    }

    public void setPowerMax(double powerMax) {
        this.powerMax = powerMax;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getHomeName() {
        return this.homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public int getNumCycle() {
        return this.numCycle;
    }

    public void setNumCycle(int numCycle) {
        this.numCycle = numCycle;
    }

    public boolean getOn() {
        return this.on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public double getPower() {
        return this.power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public ArrayList<Cycle> getListOfCycles() {
        return this.listOfCycles;
    }

    public double getConsoPerc() {
        return this.consoPerc;
    }

    public void setConsoPerc(double consoPerc) {
        this.consoPerc = consoPerc;
    }

    public boolean isOn() {
        return this.on;
    }

    public double getBeguining() {
        return this.beguining;
    }

    public void setBeguining(double beguining) {
        this.beguining = beguining;
    }

    public double getDuration() {
        return this.duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setListOfCycles(ArrayList<Cycle> listOfCycles) {
        this.listOfCycles = listOfCycles;
    }

}
