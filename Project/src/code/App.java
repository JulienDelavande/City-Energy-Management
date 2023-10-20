package code;

import code.Consumer.Consumer;
import code.ElectronicDevices.ElecDevice;
import code.ElectronicDevices.AllElecDevives.*;
import code.Producer.Producer;
import code.Producer.AllTypeOfProducer.SolarPanel;
import code.Tools.*;
import java.util.ArrayList;


public class App {


//******************** Parameters to choose **************************


// ############### Simulation on one city ###############

    //Construction of the City

    private static String cityName = "Toulouse";
    private static int numConsumer = 10_000;

    private static int numNuclearReactor = 1;
    private static int numThermicCentral = 1;
    private static int numHydrolicCentral = 1;
    private static int numWindTurbine = 10;
    private static int numSolarPanel = 100;




    // ***** simulation on one day ***** 

        private static boolean simulationOneDay = true;

        //-> choose the date:
        private static int day = 1;
        private static String dayWeek = "Monday";
        private static int month = 1;
        private static int hour = 0;
        private static int min = 0;


        //-> automatic weather?:
        private static boolean automaticWeather = false;
        //-> if no, choose the weather:
        private static double temperature = 20;
        private static double cloud = 0.5; //0<cloud<1
        private static double windForce = 0.5; //0<windForce<1

        //-> automatic switch-ON/OFF the producer according to the simulation?
        private static boolean automaticSwitchProducer = true;

        //-> where should it be printed?
        private static boolean printedInTheTerminal = false;
        private static boolean printedInAFile = true;



    // ***** Simulation on one year ***** 

        private static boolean simulationOneYear = true;

        //-> choose the date:
        private static int dayYear = 1;
        private static String dayWeekYear = "Monday";
        private static int monthYear = 1;
        private static int hourYear = 0;
        private static int minYear = 0;
    
        //-> automatic weather?:
        private static boolean automaticWeatherYear = true;
        //-> if no, choose the weather:
        private static double temperatureYear = 20;
        private static double cloudYear = 0.5; //0<cloud<1
        private static double windForceYear = 0.5; //0<windForce<1

        //-> automatic switch-ON/OFF the producer according to the simulation?
        private static boolean automaticSwitchProducerYear = true;

        //-> where should it be printed?
        private static boolean printedInTheTerminalYear = false;
        private static boolean printedInAFileYear = true;



// ############### Simulation of one device ###############

        private static boolean simOneDevice = true;
        private static ElecDevice device = new Light("cityName", "homeName", "deviceName");
        private static boolean simulationOneDayDevice = true;
        private static boolean simulationOneYearDevice = true;

                
        //-> choose the date:
        private static int dayDevice = 1;
        private static String dayWeekDevice = "Monday";
        private static int monthDevice = 1;
        private static int hourDevice = 0;
        private static int minDevice = 0;

        //-> where should it be printed?
        private static boolean printedInTheTerminalDevice = false;
        private static boolean printedInAFileDevice = true;




// ############### Simulation of one Consumer ###############

        private static boolean simOneConsumer = true;
        private static boolean simulationOneDayConsumer = true;
        private static boolean simulationOneYearConsumer = true;

        //-> choose the date:
        private static int dayConsumer = 1;
        private static String dayWeekConsumer = "Monday";
        private static int monthConsumer = 1;
        private static int hourConsumer = 0;
        private static int minConsumer = 0;

        //-> where should it be printed?
        private static boolean printedInTheTerminalConsumer = false;
        private static boolean printedInAFileConsumer = true;




// ############### Simulation of one Producer ###############

        private static boolean simOneProducer = true;
        private static Producer producer = new SolarPanel("cityName", "prodName");
        private static boolean simulationOneDayProducer = true;
        private static boolean simulationOneYearProducer = true;

        //-> choose the date:
        private static int dayProducer = 1;
        private static String dayWeekProducer = "Monday";
        private static int monthProducer = 1;
        private static int hourProducer = 0;
        private static int minProducer = 0;

        //-> automatic weather?:
        private static boolean automaticWeatherProducer = false;
        //-> if no, choose the weather:
        private static double temperatureProducer = 20;
        private static double cloudProducer = 0.5; //0<cloud<1
        private static double windForceProducer = 0.5; //0<windForce<1

        //-> where should it be printed?
        private static boolean printedInTheTerminalProducer = false;
        private static boolean printedInAFileProducer = true;





//********************* MAIN ***************************** 

    public static void main(String[] args) throws Exception {
        

        long lStartTime = System.nanoTime();
        System.out.println(" \n --- ****** Begining ****** --- \n \n");




//############### SIMULATION Of one City ###############

    //Creation of the City:
        City city = new City(cityName, numConsumer);
        city.setNumNuclearReactor(numNuclearReactor);
        city.setNumThermicCentral(numThermicCentral);
        city.setNumHydrolicCentral(numHydrolicCentral);
        city.setNumWindTurbine(numWindTurbine);
        city.setNumSolarPanel(numSolarPanel);
        System.out.println("City created !  ->");
        System.out.println(city + "\n");
        long lDurationCityCreationTime = System.nanoTime()-lStartTime;



//************* Simulation one Day: ******************

        if(simulationOneDay){
        System.out.println("\n*** Begining of the simulation for one day... ***\n");

    //set of time
        Time time = new Time();
        time.setHour(hour);
        time.setMin(min);
        time.setDayDate(day);
        time.setMonth(Time.monthIntToBinary(month));
        time.setDayWeek(Time.dayStringToBinary(dayWeek));
        System.out.println("Time set !  ->");
        System.out.println(time + "\n");

    //set of weather
        Weather weather = new Weather();
        if (automaticWeather){ weather.setAutomatic(true); System.out.println("Automatic weather set ! \n");}
        else{
            weather.setTemperature(temperature);
            weather.setCloud(cloud);
            weather.setWindForce(windForce);
            System.out.println("Weather set !  ->");
            System.out.println(weather + "\n");
        }

        
    //Simulation
        ArrayList<double[]> listConsoAndProdOneDay = CommandPanel.getConsoAndProdOfOneDay(time, weather, city, automaticSwitchProducer);

    //Display of the result
        if (printedInTheTerminal) {CommandPanel.printConsoAndProd(listConsoAndProdOneDay);}
        if (printedInAFile) {CommandPanel.printInFile(listConsoAndProdOneDay, "ConsoAndProdOneDay.csv");}

    //Display of the cost of the simulation (time spend)
        long lEndTime = System.nanoTime();
        System.out.println("time spend for the simulation of one day in ms : "
        + (lEndTime - lStartTime)/1000000 + "\n Simulation ended\n");
    }



 //************* Simulation one Year: ******************

        if(simulationOneYear){
        long lStartTimeYear = System.nanoTime();
        System.out.println("\n*** Begining of the simulation for one year... ***\n");


    //set of time
        Time time_2 = new Time();
        time_2.setHour(hourYear);
        time_2.setMin(minYear);
        time_2.setDayDate(dayYear);
        time_2.setMonth(Time.monthIntToBinary(monthYear));
        time_2.setDayWeek(Time.dayStringToBinary(dayWeekYear));
        System.out.println("Time set !  ->");
        System.out.println(time_2 + "\n");

    //set of weather
        Weather weather_2 = new Weather();
        if (automaticWeatherYear){ weather_2.setAutomatic(true); System.out.println("Automatic weather set !\n");}
        else{
            weather_2.setTemperature(temperatureYear);
            weather_2.setCloud(cloudYear);
            weather_2.setWindForce(windForceYear);
            System.out.println("Weather set !  ->");
            System.out.println(weather_2 + "\n");
        }

        
    //Simulation
        ArrayList<double[]> listConsoAndProdOneYear = CommandPanel.getConsoAndProdOfOneYear(time_2, weather_2, city, automaticSwitchProducerYear);

    //Display of the result
        if (printedInTheTerminalYear) {CommandPanel.printConsoAndProd(listConsoAndProdOneYear);}
        if (printedInAFileYear) {CommandPanel.printInFile(listConsoAndProdOneYear, "ConsoAndProdOneYear.csv");}

    //Display of the cost of the simulation (time spend)
        long lEndTimeYear = System.nanoTime();
        System.out.println("Time spend for the one year simulation in ms : "
        + (lEndTimeYear - lStartTimeYear + lDurationCityCreationTime)/1000000 + "\n Simulation on year ended\n");
        
    }


//############### SIMULATION Of one Device ###############

    if (simOneDevice){

        //set of time
        Time timeDevice = new Time();
        timeDevice.setHour(hourDevice);
        timeDevice.setMin(minDevice);
        timeDevice.setDayDate(dayDevice);
        timeDevice.setMonth(Time.monthIntToBinary(monthDevice));
        timeDevice.setDayWeek(Time.dayStringToBinary(dayWeekDevice));
        System.out.println("Time set !  ->");
        System.out.println(timeDevice + "\n");
        

//************* Simulation one Day: ******************
        if(simulationOneDayDevice){
        
        //Simulation
        ArrayList<double[]> listConsoOneDayDevice = CommandPanel.getConsoOneDeviceDay(timeDevice, device);

        //Display of the result
        if (printedInTheTerminalDevice) {CommandPanel.printConsoAndProd(listConsoOneDayDevice);}
        if (printedInAFileDevice) {CommandPanel.printInFile(listConsoOneDayDevice, "ConsoOneDayDevice.csv");}
        }


//************* Simulation one Year: ******************
        if(simulationOneYearDevice){
        
        //Simulation
        ArrayList<double[]> listConsoOneYearDevice = CommandPanel.getConsoOneDeviceYear(timeDevice, device);
    
        //Display of the result
        if (printedInTheTerminalDevice) {CommandPanel.printConsoAndProd(listConsoOneYearDevice);}
        if (printedInAFileDevice) {CommandPanel.printInFile(listConsoOneYearDevice, "ConsoOneYearDevice.csv");}
    
            }     
        }

//############### SIMULATION Of one Consumer ###############

if (simOneConsumer){

    //creation of the consumer
    Consumer home = new Consumer("homeName", "cityName");

    //set of time
    Time timeConsumer = new Time();
    timeConsumer.setHour(hourConsumer);
    timeConsumer.setMin(minConsumer);
    timeConsumer.setDayDate(dayConsumer);
    timeConsumer.setMonth(Time.monthIntToBinary(monthConsumer));
    timeConsumer.setDayWeek(Time.dayStringToBinary(dayWeekConsumer));
    System.out.println("Time set !  ->");
    System.out.println(timeConsumer + "\n");
    

//************* Simulation one Day: ******************
    if(simulationOneDayConsumer){
    
    //Simulation
    ArrayList<double[]> listConsoOneDayConsumer = CommandPanel.getConsoOneConsumerDay(timeConsumer, home);

    //Display of the result
    if (printedInTheTerminalConsumer) {CommandPanel.printConsoAndProd(listConsoOneDayConsumer);}
    if (printedInAFileConsumer) {CommandPanel.printInFile(listConsoOneDayConsumer, "ConsoOneDayHome.csv");}
    }


//************* Simulation one Year: ******************
    if(simulationOneYearConsumer){
    
    //Simulation
    ArrayList<double[]> listConsoOneYearConsumer = CommandPanel.getConsoOneConsumerYear(timeConsumer, home);

    //Display of the result
    if (printedInTheTerminalConsumer) {CommandPanel.printConsoAndProd(listConsoOneYearConsumer);}
    if (printedInAFileConsumer) {CommandPanel.printInFile(listConsoOneYearConsumer, "ConsoOneYearHome.csv");}

        }     
    }


//############### SIMULATION Of one Producer ###############

if (simOneProducer){

    //set of time
    Time timeProducer = new Time();
    timeProducer.setHour(hourProducer);
    timeProducer.setMin(minProducer);
    timeProducer.setDayDate(dayProducer);
    timeProducer.setMonth(Time.monthIntToBinary(monthProducer));
    timeProducer.setDayWeek(Time.dayStringToBinary(dayWeekProducer));
    System.out.println("Time set !  ->");
    System.out.println(timeProducer + "\n");

    //set of the weather
    Weather weatherProducer = new Weather();
    if (automaticWeatherProducer){ weatherProducer.setAutomatic(true); System.out.println("Automatic weather set ! \n");}
    else{
        weatherProducer.setTemperature(temperatureProducer);
        weatherProducer.setCloud(cloudProducer);
        weatherProducer.setWindForce(windForceProducer);
        System.out.println("Weather set !  ->");
        System.out.println(weatherProducer + "\n");
    }

//************* Simulation one Day: ******************
    if(simulationOneDayProducer){
    
    //Simulation
    ArrayList<double[]> listConsoOneDayProducer = CommandPanel.getConsoOneProducerDay(timeProducer, weatherProducer, producer);

    //Display of the result
    if (printedInTheTerminalProducer) {CommandPanel.printConsoAndProd(listConsoOneDayProducer);}
    if (printedInAFileProducer) {CommandPanel.printInFile(listConsoOneDayProducer, "ConsoOneDayProducer.csv");}
    }


//************* Simulation one Year: ******************
    if(simulationOneYearProducer){
    
    //Simulation
    ArrayList<double[]> listConsoOneYearProducer = CommandPanel.getConsoOneProducerYear(timeProducer, weatherProducer, producer);

    //Display of the result
    if (printedInTheTerminalProducer) {CommandPanel.printConsoAndProd(listConsoOneYearProducer);}
    if (printedInAFileProducer) {CommandPanel.printInFile(listConsoOneYearProducer, "ConsoOneYearProducer.csv");}

        }     
    }

}

}
