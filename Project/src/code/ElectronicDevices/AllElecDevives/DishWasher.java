package code.ElectronicDevices.AllElecDevives;

import code.ElectronicDevices.ElecDevice;

public class DishWasher extends ElecDevice {

    public DishWasher(String deviceName, String homeName, String cityName) {
        super(deviceName, homeName, cityName);
        this.powerMax = 2200;
        this.addCycle(new double[] { 60, 10 }, new double[] { 60 * 21, 60 }, 0b1111111, 0b111111111111,
                new double[] { 0.9, 0.2 });
    }

}
