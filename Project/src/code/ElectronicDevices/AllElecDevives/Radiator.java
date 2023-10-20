package code.ElectronicDevices.AllElecDevives;

import java.util.Random;

import code.ElectronicDevices.ElecDevice;

public class Radiator extends ElecDevice {

    private int numberOfElecRadiator;
    Random rand;

    public Radiator(String deviceName, String homeName, String cityName) {
        super(deviceName, homeName, cityName);

        rand = new Random();
        numberOfElecRadiator = 5 + rand.nextInt(15);
        this.powerMax = 250 * numberOfElecRadiator;
        this.addCycle(new double[] { 60 * 24, 0.001 }, new double[] { 0, 0.001 }, 0b1111111, 0b111000000011,
                new double[] { 1, 0.2 });
    }

}
