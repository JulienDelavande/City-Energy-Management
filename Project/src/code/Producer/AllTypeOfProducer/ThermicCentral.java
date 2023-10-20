package code.Producer.AllTypeOfProducer;

import code.Tools.*;
import code.Producer.*;

public class ThermicCentral extends Producer {
    public ThermicCentral(String cityName, String prodName) {
        super(cityName, prodName);
        this.powerMax = 500_000_000;
    }

    public void check(Time time, Weather weather) {
        if (this.isOn = true) {
            this.power = this.powerMax;
        }
    }
}
