package code.ElectronicDevices.AllElecDevives;

import code.ElectronicDevices.ElecDevice;

public class Furnace extends ElecDevice {

    public Furnace(String deviceName, String homeName, String cityName) {
        super(deviceName, homeName, cityName);
        this.powerMax = 2_000;

        this.addCycle(new double[] { 60, 20 }, new double[] { 19, 45 }, 0b0100101, 0b111111111111,
                new double[] { 0.8, 0.2 });
    }

}
