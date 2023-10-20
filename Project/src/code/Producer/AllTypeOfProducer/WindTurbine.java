package code.Producer.AllTypeOfProducer;

import code.Tools.*;
import code.Producer.*;

public class WindTurbine extends Producer {
    public WindTurbine(String cityName, String prodName) {
        super(cityName, prodName);
        this.powerMax = 350_000;
    }

    public void check(Time time, Weather weather) {
        if (this.isOn = true) {
            this.power = this.powerMax * weather.getWindForce();
        }
    }
}
