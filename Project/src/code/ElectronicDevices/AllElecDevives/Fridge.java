package code.ElectronicDevices.AllElecDevives;

import code.ElectronicDevices.ElecDevice;

public class Fridge extends ElecDevice {

    public Fridge(String deviceName, String homeName, String cityName) {
        super(deviceName, homeName, cityName);
        this.powerMax = 700;
        this.addCycle(new double[] { 24 * 60, 0.0005 }, new double[] { 0, 0.0001 }, 0b1111111, 0b111111111111,
                new double[] { 1, 0.1 });
    }

}
