package code.Tools;

import code.City;
import code.Consumer.Consumer;
import code.ElectronicDevices.ElecDevice;
import code.Producer.Producer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CommandPanel {

    /**
     * fonction wich return the consomation and the production of one day
     * 
     * @param time    the time which the simulation should begin
     * @param weather the weather of the city
     * @param city    the city where the simulation is running
     * @return a list of arrays: [time since the beguinning in minute, consomation
     *         by the city during the last minute, consomation since the beguinning
     *         of the day, prduction since the beguinning of the day]
     */
    public static ArrayList<double[]> getConsoAndProdOfOneDay(Time time, Weather weather, City city,
            boolean automaticSwitchProducer) {

        ArrayList<double[]> listOfConsoAndProdByMinute = new ArrayList<double[]>();
        double consoSinceBegDay = 0;
        double prodSinceBegDay = 0;
        double[] tabMinute;
        double consoThisMin = 0;
        double prodThisMin = 0;

        for (int i = 0; i < 1440; i++) {
            if (weather.isAutomatic()) {
                weather.setWeatherOnTime(time);
            }
            consoThisMin = city.getConso(time) / 3600;
            if (automaticSwitchProducer) {
                city.setProd(time, weather);
            }
            prodThisMin = city.getProd(time, weather) / 3600;
            consoSinceBegDay += consoThisMin;
            prodSinceBegDay += prodThisMin;

            tabMinute = new double[5];
            tabMinute[0] = i + 1;
            tabMinute[1] = consoThisMin;
            tabMinute[2] = prodThisMin;
            tabMinute[3] = consoSinceBegDay;
            tabMinute[4] = prodSinceBegDay;

            listOfConsoAndProdByMinute.add(tabMinute);
            time.incMin();
        }
        return listOfConsoAndProdByMinute;
    }

    /**
     * fonction wich return the consomation and the production of city for one year
     * 
     * @param time    the time which the simulation should begin
     * @param weather the weather of the city
     * @param city    the city where the simulation is running
     * @return a list of arrays: [time since the beguinning in days, consomation by
     *         the city during the last day, consomation since the beguinning of the
     *         simulation, prduction since the beguinning of the simulation]
     */
    public static ArrayList<double[]> getConsoAndProdOfOneYear(Time time, Weather weather, City city,
            boolean automaticSwitchProducerYear) {

        ArrayList<double[]> listOfConsoAndProdByDay = new ArrayList<double[]>();
        double consoSinceBegYear = 0;
        double prodSinceBegYear = 0;
        double[] tabDay;
        double consoThisDay = 0;
        double prodThisDay = 0;

        for (int i = 0; i < 365; i++) {
            if (weather.isAutomatic()) {
                weather.setWeatherOnTimeMeanDay(time);
            }
            consoThisDay = city.getConsoDay(time) / 3600;
            if (automaticSwitchProducerYear) {
                city.setProd(time, weather);
            }
            prodThisDay = city.getProdDay(time, weather) / 3600;
            consoSinceBegYear += consoThisDay;
            prodSinceBegYear += prodThisDay;

            tabDay = new double[5];
            tabDay[0] = i + 1;
            tabDay[1] = consoThisDay;
            tabDay[2] = prodThisDay;
            tabDay[3] = consoSinceBegYear;
            tabDay[4] = prodSinceBegYear;

            listOfConsoAndProdByDay.add(tabDay);
            time.incDay();
        }
        return listOfConsoAndProdByDay;
    }

    /**
     * fonction wich return the consomation and the production of a device for one
     * day
     * 
     * @param time   the time which the simulation should begin
     * @param device the electrical device for the simulation
     * @return a list of arrays: [time since the beguinning in minutes, consomation
     *         by the city during the last minutes, consomation since the beguinning
     *         of the simulation, prduction since the beguinning of the simulation]
     */
    public static ArrayList<double[]> getConsoOneDeviceDay(Time time, ElecDevice device) {
        ArrayList<double[]> listOfConsoMin = new ArrayList<double[]>();
        double consoSinceBegDay = 0;
        double[] tabMinute;
        double consoThisMin = 0;

        for (int i = 0; i < 1440; i++) {
            consoThisMin = device.getConso(time) / 3600;
            consoSinceBegDay += consoThisMin;

            tabMinute = new double[3];
            tabMinute[0] = i + 1;
            tabMinute[1] = consoThisMin;
            tabMinute[2] = consoSinceBegDay;

            listOfConsoMin.add(tabMinute);
            time.incMin();
        }
        return listOfConsoMin;

    }

    /**
     * fonction wich return the consomation of a device for one year
     * 
     * @param time   the time which the simulation should begin
     * @param device the electrical device for the simulation
     * @return a list of arrays: [time since the beguinning in days, consomation by
     *         the city during the last day, consomation since the beguinning of the
     *         simulation, prduction since the beguinning of the simulation]
     */
    public static ArrayList<double[]> getConsoOneDeviceYear(Time time, ElecDevice device) {
        ArrayList<double[]> listOfConsoDay = new ArrayList<double[]>();
        double consoSinceBegYear = 0;
        double[] tabDay;
        double consoThisDay = 0;

        for (int i = 0; i < 365; i++) {
            consoThisDay = device.getConsoDay(time) / 3600;
            consoSinceBegYear += consoThisDay;

            tabDay = new double[3];
            tabDay[0] = i + 1;
            tabDay[1] = consoThisDay;
            tabDay[2] = consoSinceBegYear;

            listOfConsoDay.add(tabDay);
            time.incDay();
        }
        return listOfConsoDay;

    }

    /**
     * fonction wich return the consomation of a consumer for one day
     * 
     * @param time the time which the simulation should begin
     * @param home the consumer for the simulation
     * @return a list of arrays: [time since the beguinning in minutes, consomation
     *         by the city during the last minute, consomation since the beguinning
     *         of the simulation, prduction since the beguinning of the simulation]
     */
    public static ArrayList<double[]> getConsoOneConsumerDay(Time time, Consumer home) {
        ArrayList<double[]> listOfConsoMin = new ArrayList<double[]>();
        double consoSinceBegDay = 0;
        double[] tabMinute;
        double consoThisMin = 0;

        for (int i = 0; i < 1440; i++) {
            consoThisMin = home.getConso(time) / 3600;
            consoSinceBegDay += consoThisMin;

            tabMinute = new double[3];
            tabMinute[0] = i + 1;
            tabMinute[1] = consoThisMin;
            tabMinute[2] = consoSinceBegDay;

            listOfConsoMin.add(tabMinute);
            time.incMin();
        }
        return listOfConsoMin;

    }

    /**
     * fonction wich return the consomation of a consumer for one year
     * 
     * @param time the time which the simulation should begin
     * @param home the consumer for the simulation
     * @return a list of arrays: [time since the beguinning in days, consomation by
     *         the city during the last day, consomation since the beguinning of the
     *         simulation, prduction since the beguinning of the simulation]
     */
    public static ArrayList<double[]> getConsoOneConsumerYear(Time time, Consumer home) {
        ArrayList<double[]> listOfConsoDay = new ArrayList<double[]>();
        double consoSinceBegYear = 0;
        double[] tabDay;
        double consoThisDay = 0;

        for (int i = 0; i < 365; i++) {
            consoThisDay = home.getConsoDay(time) / 3600;
            consoSinceBegYear += consoThisDay;

            tabDay = new double[3];
            tabDay[0] = i + 1;
            tabDay[1] = consoThisDay;
            tabDay[2] = consoSinceBegYear;

            listOfConsoDay.add(tabDay);
            time.incDay();
        }
        return listOfConsoDay;

    }

    /**
     * fonction wich return the consomation and the production of a device for one
     * day
     * 
     * @param time     the time which the simulation should begin
     * @param weather  the weather in the city
     * @param producer the consumer for the simulation
     * @return a list of arrays: [time since the beguinning in minutes, consomation
     *         by the city during the last minute, consomation since the beguinning
     *         of the simulation, prduction since the beguinning of the simulation]
     */
    public static ArrayList<double[]> getConsoOneProducerDay(Time time, Weather weather, Producer producer) {
        ArrayList<double[]> listOfProdMin = new ArrayList<double[]>();
        double prodSinceBegDay = 0;
        double[] tabMinute;
        double prodThisMin = 0;

        for (int i = 0; i < 1440; i++) {
            prodThisMin = producer.getProd(time, weather) / 3600;
            prodSinceBegDay += prodThisMin;

            tabMinute = new double[3];
            tabMinute[0] = i + 1;
            tabMinute[1] = prodThisMin;
            tabMinute[2] = prodSinceBegDay;

            listOfProdMin.add(tabMinute);
            time.incMin();
        }
        return listOfProdMin;

    }

    /**
     * fonction wich return the consomation and the production of a device for one
     * year
     * 
     * @param time     the time which the simulation should begin
     * @param weather  the weather in the city
     * @param producer the consumer for the simulation
     * @return a list of arrays: [time since the beguinning in days, consomation by
     *         the city during the last day, consomation since the beguinning of the
     *         simulation, prduction since the beguinning of the simulation]
     */
    public static ArrayList<double[]> getConsoOneProducerYear(Time time, Weather weather, Producer producer) {
        ArrayList<double[]> listOfProdDay = new ArrayList<double[]>();
        double prodSinceBegYear = 0;
        double[] tabDay;
        double prodThisDay = 0;

        for (int i = 0; i < 365; i++) {
            prodThisDay = producer.getProd(time, weather) / 3600;
            prodSinceBegYear += prodThisDay;

            tabDay = new double[3];
            tabDay[0] = i + 1;
            tabDay[1] = prodThisDay;
            tabDay[2] = prodSinceBegYear;

            listOfProdDay.add(tabDay);
            time.incDay();
        }
        return listOfProdDay;

    }

    /**
     * fonction which print in the terminal the list of conso and prod
     * 
     * @param listOfConsoAndProd the list which should be printed
     */
    public static void printConsoAndProd(ArrayList<double[]> listOfConsoAndProd) {
        for (double[] tab : listOfConsoAndProd) {
            for (double number : tab) {
                System.out.print(number + "; ");
            }
            System.out.println();
        }
    }

    /**
     * fonction which print a simulation array in a file in csv format
     * 
     * @param listOfConsoAndProd the list which should be printed
     * @param fileName           the name of the file
     */
    public static void printInFile(ArrayList<double[]> listOfConsoAndProd, String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName);
            String text = "Time; ConsInst; ProdInst; ConsSinceBeg; ProdSinceBeg;\n";
            for (double[] tab : listOfConsoAndProd) {
                for (double number : tab) {
                    text += number + "; ";
                }
                text += "\n";
            }
            text = text.replace(".", ",");
            fw.write(text);
            fw.close();
            System.out.println("data writen in the " + fileName + " file\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}