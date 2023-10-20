package code;

import java.util.ArrayList;

import code.Consumer.*;
import code.Producer.*;
import code.Producer.AllTypeOfProducer.*;

import code.Tools.*;

public class City {

    // info
    private String cityName;
    // Conssumer
    private int numConsumer;
    // Producer
    private int numProducer;
    private int numNuclearReactor;
    private int numThermicCentral;
    private int numHydrolicCentral;
    private int numWindTurbine;
    private int numSolarPanel;

    // Constitution of the city
    private ArrayList<Consumer> listOfConsumer;
    private ArrayList<Producer> listOfProducer;

    // constructors
    public City(String name) {
        this.cityName = name;
        this.numConsumer = 1;

        this.numProducer = 0;
        this.numNuclearReactor = 0;
        this.numThermicCentral = 0;
        this.numHydrolicCentral = 0;
        this.numWindTurbine = 0;
        this.numSolarPanel = 0;

        listOfConsumer = new ArrayList<Consumer>();
        listOfConsumer.add(new Consumer("Home_1", this.cityName));

        listOfProducer = new ArrayList<Producer>();

    }

    public City(String name, int numConsumer) {
        this.cityName = name;
        this.numConsumer = numConsumer;

        this.numProducer = 0;
        this.numNuclearReactor = 0;
        this.numThermicCentral = 0;
        this.numHydrolicCentral = 0;
        this.numWindTurbine = 0;
        this.numSolarPanel = 0;

        listOfConsumer = new ArrayList<Consumer>();
        for (int i = 0; i < this.numConsumer; i++) {
            listOfConsumer.add(new Consumer("Home_" + (i + 1), this.cityName));
        }

        listOfProducer = new ArrayList<Producer>();
    }

    /**
     * to get the conso of the city during one minute at a given time
     * 
     * @param time the time which the conso will be calculated for the next minute
     * @return the conso for one minute in Wh
     */
    public double getConso(Time time) {
        double conso_t = 0;
        for (Consumer consumer : listOfConsumer) {
            conso_t += consumer.getConso(time);
        }
        return conso_t;
    }

    /**
     * to give the consomation of the city during one day at an given date
     * 
     * @param time the time which the conso will be calculated for the next day
     * @return the conso for one minute in Wh
     */
    public double getConsoDay(Time time) {
        double conso_d = 0;
        for (Consumer consumer : listOfConsumer) {
            conso_d += consumer.getConsoDay(time);
        }
        return conso_d;
    }

    /**
     * to give the production of the city during one minute at an given date
     * 
     * @param time the time which the conso will be calculated for the next minute
     * @return the conso for one minute in Wh
     */
    public double getProd(Time time, Weather weather) {
        double prod_t = 0;
        for (Producer producer : listOfProducer) {
            prod_t += producer.getProd(time, weather);
        }
        return prod_t;
    }

    public double getProdDay(Time time, Weather weather) {
        double prod_d = 0;
        for (int i = 0; i < 1440; i++) {
            prod_d += getProd(time, weather);
        }
        return prod_d;
    }

    public void setProd(Time time, Weather weather) {
        for (Producer producer : this.listOfProducer) {
            producer.switchOff();
        }

        // SolarPanel
        for (Producer producer : this.listOfProducer) {
            if (producer.getProdName() == "SolarPanel")
                producer.switchOn();
        }
        if (getProd(time, weather) > 1.1 * this.getConso(time)) {
            return;
        }

        // WindTurbine
        for (Producer producer : this.listOfProducer) {
            if (producer.getProdName() == "WindTurbine")
                producer.switchOn();
        }
        if (getProd(time, weather) > 1.1 * this.getConso(time)) {
            return;
        }

        // HydrolicCentral
        for (Producer producer : this.listOfProducer) {
            if (producer.getProdName() == "HydrolicCentral")
                producer.switchOn();
        }
        if (getProd(time, weather) > 1.1 * this.getConso(time)) {
            return;
        }

        // NuclearReactor
        for (Producer producer : this.listOfProducer) {
            if (producer.getProdName() == "NuclearReactor")
                producer.switchOn();
        }
        if (getProd(time, weather) > 1.1 * this.getConso(time)) {
            return;
        }

        // ThermicCentral
        for (Producer producer : this.listOfProducer) {
            if (producer.getProdName() == "ThermicCentral")
                producer.switchOn();
        }
        return;

    }

    // reprsentation of the city
    @Override
    public String toString() {
        return "{" + " cityName='" + getCityName() + "'" + ", numConsumer='" + getNumConsumer() + "'"
                + ", numProducer='" + getNumProducer() + "'" + "}";
    }

    // setter and getter
    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getNumConsumer() {
        return this.numConsumer;
    }
    // public void setNumConsumer(int numConsumer) { this.numConsumer = numConsumer;
    // }

    public int getNumProducer() {
        return this.numProducer;
    }
    // public void setNumProducer(int numProducer) { this.numProducer = numProducer;
    // }

    public int getNumNuclearReactor() {
        return this.numNuclearReactor;
    }

    public void setNumNuclearReactor(int numNuclearReactor) {
        this.numProducer -= this.numNuclearReactor;
        this.numNuclearReactor = numNuclearReactor;
        this.numProducer += this.numNuclearReactor;
        Producer Test = new NuclearReactor(cityName, "test");
        for (Producer producer : listOfProducer) {
            if (producer.getClass() == Test.getClass()) {
                this.listOfProducer.remove(producer);
            }
        }
        for (int i = 0; i < numNuclearReactor; i++) {
            this.listOfProducer.add(new NuclearReactor(cityName, "NuclearReactor"));
        }
    }

    public int getNumThermicCentral() {
        return this.numThermicCentral;
    }

    public void setNumThermicCentral(int numThermicCentral) {
        this.numProducer -= this.numThermicCentral;
        this.numThermicCentral = numThermicCentral;
        this.numProducer += this.numThermicCentral;
        Producer Test = new ThermicCentral(cityName, "test");
        for (Producer producer : listOfProducer) {
            if (producer.getClass() == Test.getClass()) {
                this.listOfProducer.remove(producer);
            }
        }
        for (int i = 0; i < numThermicCentral; i++) {
            this.listOfProducer.add(new ThermicCentral(cityName, "ThermicCentral"));
        }
    }

    public int getNumHydrolicCentral() {
        return this.numHydrolicCentral;
    }

    public void setNumHydrolicCentral(int numHydrolicCentral) {
        this.numProducer -= this.numHydrolicCentral;
        this.numHydrolicCentral = numHydrolicCentral;
        this.numProducer += this.numHydrolicCentral;
        Producer Test = new HydrolicCentral(cityName, "test");
        for (Producer producer : listOfProducer) {
            if (producer.getClass() == Test.getClass()) {
                this.listOfProducer.remove(producer);
            }
        }
        for (int i = 0; i < numHydrolicCentral; i++) {
            this.listOfProducer.add(new HydrolicCentral(cityName, "HydrolicCentral"));
        }
    }

    public int getNumWindTurbine() {
        return this.numWindTurbine;
    }

    public void setNumWindTurbine(int numWindTurbine) {
        this.numProducer -= this.numWindTurbine;
        this.numWindTurbine = numWindTurbine;
        this.numProducer += this.numWindTurbine;
        Producer Test = new WindTurbine(cityName, "test");
        for (Producer producer : listOfProducer) {
            if (producer.getClass() == Test.getClass()) {
                this.listOfProducer.remove(producer);
            }
        }
        for (int i = 0; i < numWindTurbine; i++) {
            this.listOfProducer.add(new WindTurbine(cityName, "WindTurbine"));
        }
    }

    public int getNumSolarPanel() {
        return this.numSolarPanel;
    }

    public void setNumSolarPanel(int numSolarPanel) {
        this.numProducer -= this.numSolarPanel;
        this.numSolarPanel = numSolarPanel;
        this.numProducer += this.numSolarPanel;
        Producer Test = new SolarPanel(cityName, "test");
        for (Producer producer : listOfProducer) {
            if (producer.getClass() == Test.getClass()) {
                this.listOfProducer.remove(producer);
            }
        }
        for (int i = 0; i < numSolarPanel; i++) {
            this.listOfProducer.add(new SolarPanel(cityName, "SolarPanel"));
        }
    }

    public ArrayList<Consumer> getListOfConsumer() {
        return this.listOfConsumer;
    }

    public void setListOfConsumer(ArrayList<Consumer> listOfConsumer) {
        this.listOfConsumer = listOfConsumer;
    }

    public ArrayList<Producer> getListOfProducer() {
        return this.listOfProducer;
    }

    public void setListOfProducer(ArrayList<Producer> listOfProducer) {
        this.listOfProducer = listOfProducer;
    }

}
