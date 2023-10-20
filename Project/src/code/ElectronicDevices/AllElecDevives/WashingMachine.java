package code.ElectronicDevices.AllElecDevives;

import code.ElectronicDevices.ElecDevice;

public class WashingMachine extends ElecDevice {

    public WashingMachine(String deviceName, String homeName, String cityName) {
        super(deviceName, homeName, cityName);
        this.powerMax = 250;
        this.addCycle(new double[] { 60, 10 }, new double[] { 12, 60 * 6 }, 0b0000001, 0b111111111111,
                new double[] { 1, 0.2 });
    }

}