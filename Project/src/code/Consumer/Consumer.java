package code.Consumer;

import java.util.ArrayList;
import java.util.Random;

import code.ElectronicDevices.*;
import code.ElectronicDevices.AllElecDevives.*;
import code.Tools.*;

public class Consumer {

    // infos
    protected String homeName;
    protected String cityName;

    private int numElecDevices;
    private ArrayList<ElecDevice> listOfElecDevice;

    // constructors
    public Consumer(String homeName, String cityName) {

        this.cityName = cityName;
        this.homeName = homeName;
        numElecDevices = 0;
        this.listOfElecDevice = new ArrayList<ElecDevice>();

        // https://domotique-news.com/100-appareils-electriques-par-foyer/

        // fridge
        if (isInTheHome(0.98)) {
            listOfElecDevice.add(new Fridge("Fridge", this.homeName, this.cityName));
            numElecDevices++;
        }

        // light
        if (isInTheHome(1)) {
            listOfElecDevice.add(new Light("Light", this.homeName, this.cityName));
            numElecDevices++;
        }

        // Radiator
        if (isInTheHome(0.47)) {
            listOfElecDevice.add(new Radiator("Radiator", this.homeName, this.cityName));
            numElecDevices++;
        }

        // Dish Washer
        if (isInTheHome(0.93)) {
            listOfElecDevice.add(new DishWasher("DishWasher", this.homeName, this.cityName));
            numElecDevices++;
        }

        // Washing Machine
        if (isInTheHome(0.93)) {
            listOfElecDevice.add(new WashingMachine("WashingMachine", this.homeName, this.cityName));
            numElecDevices++;
        }

        // Furnace
        if (isInTheHome(0.81)) {
            listOfElecDevice.add(new Furnace("Furnace", this.homeName, this.cityName));
            numElecDevices++;
        }

        // Hotplate
        if (isInTheHome(0.81)) {
            listOfElecDevice.add(new Hotplate("Hotplate", this.homeName, this.cityName));
            numElecDevices++;
        }

        // MicroWave
        if (isInTheHome(0.88)) {
            listOfElecDevice.add(new MicroWave("MicroWave", this.homeName, this.cityName));
            numElecDevices++;
        }

        // Computer
        if (isInTheHome(0.6)) {
            listOfElecDevice.add(new Computer("Computer", this.homeName, this.cityName));
            numElecDevices++;
        }

        // Smartphone
        if (isInTheHome(0.9)) {
            listOfElecDevice.add(new Smartphone("Smartphone", this.homeName, this.cityName));
            numElecDevices++;
        }

        // Television
        if (isInTheHome(0.89)) {
            listOfElecDevice.add(new Television("Television", this.homeName, this.cityName));
            numElecDevices++;
        }

    }

    /**
     * to get the consomation of the consummer during one minute at a given time
     * 
     * @param time the current time
     * @return the consomation of the the consumer during the last minute
     */
    public double getConso(Time time) {
        double conso_t = 0;
        for (ElecDevice device : listOfElecDevice) {
            conso_t += device.getConso(time);
        }
        return conso_t;
    }

    /**
     * to get the consommation of the consummer during one day
     * 
     * @param time the current time
     * @return the consomation of the consuler during the last minute
     */
    public double getConsoDay(Time time) {
        double conso_d = 0;
        for (ElecDevice device : listOfElecDevice) {
            conso_d += device.getConsoDay(time);
        }
        return conso_d;
    }

    /**
     * to know if the house will have this device (based on the percentage of this
     * device in homes in France)
     * 
     * @param perc percentage of home having this device
     * @return true if the house will have this device flase instead
     */
    public static boolean isInTheHome(double perc) {
        Random rand = new Random();
        return (rand.nextInt(1) < perc);
    }

    // representation of a consummer
    @Override
    public String toString() {
        return "{" + " homeName='" + getHomeName() + "'" + ", numElecDevices='" + getNumElecDevices() + "'" + "}";
    }

    // getters and setters
    public String getHomeName() {
        return this.homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public int getNumElecDevices() {
        return this.numElecDevices;
    }

    public void setNumElecDevices(int numElecDevices) {
        this.numElecDevices = numElecDevices;
    }

    public ArrayList<ElecDevice> getListOfAppElec() {
        return this.listOfElecDevice;
    }

    public void setListOfAppElec(ArrayList<ElecDevice> listOfAppElec) {
        this.listOfElecDevice = listOfAppElec;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public ArrayList<ElecDevice> getListOfElecDevice() {
        return this.listOfElecDevice;
    }

    public void setListOfElecDevice(ArrayList<ElecDevice> listOfElecDevice) {
        this.listOfElecDevice = listOfElecDevice;
    }

}
