package code.ElectronicDevices.AllElecDevives;

import code.ElectronicDevices.ElecDevice;

public class MicroWave extends ElecDevice {

    public MicroWave(String deviceName, String homeName, String cityName) {
        super(deviceName, homeName, cityName);
        this.powerMax = 250;
        this.addCycle(new double[] { 10, 3 }, new double[] { 7 * 60 + 30, 30 }, 0b1111111, 0b111111111111,
                new double[] { 1, 0.2 });// morning
        this.addCycle(new double[] { 10, 3 }, new double[] { 20 * 60, 60 }, 0b1010111, 0b111111111111,
                new double[] { 1, 0.2 });// night
    }

}