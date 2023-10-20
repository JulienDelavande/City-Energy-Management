package code.ElectronicDevices.AllElecDevives;

import code.ElectronicDevices.ElecDevice;

public class Smartphone extends ElecDevice {

    public Smartphone(String deviceName, String homeName, String cityName) {
        super(deviceName, homeName, cityName);
        this.powerMax = 35;
        this.addCycle(new double[] { 60, 10 }, new double[] { 16, 10 }, 0b1111111, 0b111111111111,
                new double[] { 1.0, 0.1 });
    }

}