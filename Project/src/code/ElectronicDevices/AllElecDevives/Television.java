package code.ElectronicDevices.AllElecDevives;

import code.ElectronicDevices.ElecDevice;

public class Television extends ElecDevice {

    public Television(String deviceName, String homeName, String cityName) {
        super(deviceName, homeName, cityName);
        this.powerMax = 15;
        this.addCycle(new double[] { 3 * 60, 45 }, new double[] { 20 * 60, 60 }, 0b1111111, 0b111111111111,
                new double[] { 1, 0.2 });
    }

}