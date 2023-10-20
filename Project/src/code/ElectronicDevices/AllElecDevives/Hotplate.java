package code.ElectronicDevices.AllElecDevives;

import code.ElectronicDevices.ElecDevice;

public class Hotplate extends ElecDevice {

    public Hotplate(String deviceName, String homeName, String cityName) {
        super(deviceName, homeName, cityName);
        this.powerMax = 250;
        this.addCycle(new double[] { 30, 10 }, new double[] { 12, 45 }, 0b0000011, 0b111111111111,
                new double[] { 0.6, 0.3 }); // weekend
        this.addCycle(new double[] { 30, 10 }, new double[] { 20, 45 }, 0b1111111, 0b111111111111,
                new double[] { 0.6, 0.3 }); // every night
    }

}
