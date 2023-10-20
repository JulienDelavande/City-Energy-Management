package code.ElectronicDevices.AllElecDevives;

import java.util.Random;

import code.ElectronicDevices.ElecDevice;

/* INFO
    powerMAX = 400 W
    cycle = 2 cycles
     -> 1 hour in the morning at 7 am
     -> 3 hours in the night at 8 am
    */

public class Light extends ElecDevice {

    private int numberOfLight;
    private Random rand;

    public Light(String deviceName, String homeName, String cityName) {
        super(deviceName, homeName, cityName);

        rand = new Random();
        numberOfLight = 3 + rand.nextInt(20);
        this.powerMax = 30 * numberOfLight;
        this.addCycle(new double[] { 60, 15 }, new double[] { 7 * 60, 30 }, 0b1111111, 0b111111111111,
                new double[] { 0.8, 0.2 }); // morning
        this.addCycle(new double[] { 3 * 60, 45 }, new double[] { 19 * 60, 30 }, 0b1111111, 0b111111111111,
                new double[] { 0.8, 0.2 }); // night

    }
}
