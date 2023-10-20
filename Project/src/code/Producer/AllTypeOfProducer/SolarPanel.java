package code.Producer.AllTypeOfProducer;

import code.Tools.*;
import code.Producer.*;

public class SolarPanel extends Producer {
    public SolarPanel(String cityName, String prodName) {
        super(cityName, prodName);
        this.powerMax = 400;
    }

    public void check(Time time, Weather weather) {
        if (this.isOn = true) {
            this.power = this.powerMax * (1 - weather.getCloud()) * time.sunshine();
        }
    }
}
