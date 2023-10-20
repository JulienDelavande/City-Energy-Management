package code.Tools;

import code.ElectronicDevices.ElecDevice;

public class Cycle {

    // represention of a cycle
    private double[] duration; // the time of use in minutes (0<duration[0]<=3600) : mean and variance (normal
                               // law)
    private double[] beguining; // the moment of the beguinning of the use in minutes : (in a day) mean and
                                // variance (normal law)
    private int dayWeek; // the days of use in binary monday=0b1000000, Tuesday 0b0100000, ...
    private int month; // the month of use in binary January=0b100000000000, Februry=0b010000000000,
                       // ...
    private double[] powerPerc; // the percentage of the power max used for the cycle : mean and variance
                                // (normal law)

    public Cycle(double[] duration, double[] beguining, int dayWeek, int month, double[] powerPerc) {
        this.duration = duration;
        this.beguining = beguining;
        this.dayWeek = dayWeek;
        this.month = month;
        this.powerPerc = powerPerc;
    }

    /**
     * verify if the current time correspond to a use in a cycle
     * 
     * @param time the current time
     * @return true if the the cycle is on for this given time
     */
    public boolean isOn(Time time, ElecDevice elecDevice) {
        double beguining = elecDevice.normalBegOrDur(this.beguining[0], this.beguining[1]);
        double duration = elecDevice.normalBegOrDur(this.duration[0], this.duration[1]);
        boolean rightTime = time.getMin() + time.getHour() * 60 >= beguining
                && time.getMin() + time.getHour() * 60 < beguining + duration;
        boolean rightDay = (this.dayWeek & time.getDayWeek()) != 0;
        boolean rightmonth = (this.getMonth() & time.getMonth()) != 0;
        if (rightTime & rightDay & rightmonth) {
            elecDevice.setBeguining(beguining);
            elecDevice.setDuration(duration);
            return true;
        }
        return false;
    }

    /**
     * verify if the current time correspond to a use in a cycle
     * 
     * @param time the current time
     * @return true if the the cycle is on for this day
     */
    public boolean isOnThisDay(Time time) {
        boolean rightDay = (this.dayWeek & time.getDayWeek()) != 0;
        boolean rightmonth = (this.month & time.getMonth()) != 0;
        return (rightDay & rightmonth);
    }

    // display of a cycle
    @Override
    public String toString() {
        return "{" + " duration='" + getDuration() + "'" + ", beguinning='" + getBeguinning() + "'" + ", dayWeek='"
                + getDayWeek() + "'" + ", month='" + getMonth() + "'" + ", powerPerc='" + getPowerPerc() + "'" + "}";
    }

    // getters and setters
    public double[] getDuration() {
        return this.duration;
    }

    public void setDuration(double[] duration) {
        this.duration = duration;
    }

    public double[] getBeguinning() {
        return this.beguining;
    }

    public void setBeguinning(double[] beguinning) {
        this.beguining = beguinning;
    }

    public int getDayWeek() {
        return this.dayWeek;
    }

    public void setDayWeek(int dayWeek) {
        this.dayWeek = dayWeek;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double[] getPowerPerc() {
        return this.powerPerc;
    }

    public void setPowerPerc(double[] powerPerc) {
        this.powerPerc = powerPerc;
    }

}
