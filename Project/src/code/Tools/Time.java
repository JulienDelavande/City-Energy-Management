package code.Tools;

import java.lang.Math;

public class Time {
    private int min;
    private int hour;
    private int dayDate; // the date (1er, 2, 3, ..., 30, 31)
    private int dayWeek; // the day (Monday, Tuesday, ..., Sunday) in binary Monday=0b1000000, ...
    private int month; // the month in binary January=0b100000000000, Februry=0b010000000000, ...

    public Time() {
        this.min = 0;
        this.hour = 0;
        this.dayDate = 1;
        this.dayWeek = 0b1000000;
        this.month = 0b100000000000;
    }

    public Time(int day) {
        this.min = 0;
        this.hour = 0;
        this.dayDate = 1;
        this.dayWeek = day;
        this.month = 0b100000000000;
    }

    public Time(int min, int hour, int dayDate, int day, int month) {
        this.min = min;
        this.hour = hour;
        this.dayDate = dayDate;
        this.dayWeek = day;
        this.month = month;
    }

    /**
     * increment the time by one minute
     */
    public void incMin() {
        int testMonth = 0;
        if (this.month == 0b010000000000) {
            testMonth = -2;
        }
        if ((this.month & 0b101010110101) != 0) {
            testMonth = 1;
        }

        // test minutes
        if (this.min == 59) {
            this.min = 0;
        } else {
            this.min++;
        }

        // test hour
        if (this.min == 0) {
            if (this.hour == 23) {
                this.hour = 0;
            } else {
                this.hour++;
            }
        }

        // test date (day)
        if (this.hour == 0 && this.min == 0) {
            if (this.dayDate == 30 + testMonth) {
                this.dayDate = 0;
            } else {
                this.dayDate++;
            }
        }

        // test month
        if (this.hour == 0 && this.min == 0 && this.dayDate == 0) {
            if (this.month == 0b000000000001) {
                this.month = 0b100000000000;
            } else {
                this.month /= 2;
            }
        }

        // test day
        if (this.hour == 0 && this.min == 0) {
            if (this.dayWeek == 0b0000001) {
                this.dayWeek = 0b1000000;
            } else {
                this.dayWeek /= 2;
            }
        }
    }

    /**
     * increment the time by one day
     */
    public void incDay() {
        int testMonth = 0;
        if (this.month == 0b010000000000) {
            testMonth = -2;
        }
        if ((this.month & 0b101010110101) != 0) {
            testMonth = 1;
        }

        // test date (day)
        if (this.dayDate == 30 + testMonth) {
            this.dayDate = 0;
        } else {
            this.dayDate++;
        }

        // test month
        if (this.dayDate == 0) {
            if (this.month == 0b000000000001) {
                this.month = 0b100000000000;
            } else {
                this.month /= 2;
            }
        }

        // test day
        if (this.dayWeek == 0b0000001) {
            this.dayWeek = 0b1000000;
        } else {
            this.dayWeek /= 2;
        }
    }

    /**
     * to get the sunshine value
     * 
     * @return the sunshine value dependending on the hour of the day
     */
    public double sunshine() {

        double sigma = 6 * Math.exp(-(Math.pow(this.convertMonthInt() - 6, 2)) / (2 * Math.pow(25, 2)));
        double mean = 12;
        return Math.exp(-(Math.pow(this.hour - mean, 2)) / (2 * Math.pow(sigma, 2)));
    }

    @Override
    public String toString() {
        return getHour() + "h" + getMin() + " " + getDayWeekString() + " " + getDayDate() + " " + getMonthString();
    }

    public int convertMonthInt() {
        int month = 0;
        if (this.month == 0b100000000000) {
            month = 1;
        }
        if (this.month == 0b010000000000) {
            month = 2;
        }
        if (this.month == 0b001000000000) {
            month = 3;
        }
        if (this.month == 0b000100000000) {
            month = 4;
        }
        if (this.month == 0b000010000000) {
            month = 5;
        }
        if (this.month == 0b000001000000) {
            month = 6;
        }
        if (this.month == 0b000000100000) {
            month = 7;
        }
        if (this.month == 0b000000010000) {
            month = 8;
        }
        if (this.month == 0b000000001000) {
            month = 9;
        }
        if (this.month == 0b000000000100) {
            month = 10;
        }
        if (this.month == 0b000000000010) {
            month = 11;
        }
        if (this.month == 0b000000000001) {
            month = 12;
        }
        return month;
    }

    public int getMin() {
        return this.min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getHour() {
        return this.hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getDayDate() {
        return this.dayDate;
    }

    public void setDayDate(int dayDate) {
        this.dayDate = dayDate;
    }

    public String getDayWeekString() {
        String day = "day";
        if (this.dayWeek == 0b1000000) {
            day = "Monday";
        }
        if (this.dayWeek == 0b0100000) {
            day = "Tuesday";
        }
        if (this.dayWeek == 0b0010000) {
            day = "Wednesday";
        }
        if (this.dayWeek == 0b0001000) {
            day = "Thursday";
        }
        if (this.dayWeek == 0b0000100) {
            day = "Friday";
        }
        if (this.dayWeek == 0b0000010) {
            day = "Saturday";
        }
        if (this.dayWeek == 0b0000001) {
            day = "Sunday";
        }
        return day;
    }

    public void setDayWeek(int dayWeek) {
        this.dayWeek = dayWeek;
    }

    public int getDayWeek() {
        return this.dayWeek;
    }

    public String getMonthString() {
        String month = "month";
        if (this.month == 0b100000000000) {
            month = "January";
        }
        if (this.month == 0b010000000000) {
            month = "February";
        }
        if (this.month == 0b001000000000) {
            month = "March";
        }
        if (this.month == 0b000100000000) {
            month = "April";
        }
        if (this.month == 0b000010000000) {
            month = "May";
        }
        if (this.month == 0b000001000000) {
            month = "June";
        }
        if (this.month == 0b000000100000) {
            month = "Jully";
        }
        if (this.month == 0b000000010000) {
            month = "August";
        }
        if (this.month == 0b000000001000) {
            month = "September";
        }
        if (this.month == 0b000000000100) {
            month = "October";
        }
        if (this.month == 0b000000000010) {
            month = "November";
        }
        if (this.month == 0b000000000001) {
            month = "December";
        }
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMonth() {
        return this.month;
    }

    public static int monthIntToBinary(int month) {
        if (month == 1) {
            return 0b100000000000;
        }
        if (month == 2) {
            return 0b010000000000;
        }
        if (month == 3) {
            return 0b001000000000;
        }
        if (month == 4) {
            return 0b000100000000;
        }
        if (month == 5) {
            return 0b000010000000;
        }
        if (month == 6) {
            return 0b000001000000;
        }
        if (month == 7) {
            return 0b000000100000;
        }
        if (month == 8) {
            return 0b000000010000;
        }
        if (month == 9) {
            return 0b000000001000;
        }
        if (month == 10) {
            return 0b000000000100;
        }
        if (month == 11) {
            return 0b000000000010;
        }
        if (month == 12) {
            return 0b000000000001;
        }
        System.out.println("please select a valid month -> 1<=month<=12");
        System.out.println("the month selected will be January by default");
        return 0b100000000000;
    }

    public static int dayStringToBinary(String day) {
        if (day == "Monday" || day == "monday") {
            return 0b1000000;
        }
        if (day == "Tuesday" || day == "tuesday") {
            return 0b0100000;
        }
        if (day == "Wednesday" || day == "wednesday") {
            return 0b0010000;
        }
        if (day == "Thursday" || day == "thursday") {
            return 0b0001000;
        }
        if (day == "Friday" || day == "friday") {
            return 0b0000100;
        }
        if (day == "Saturday" || day == "saturday") {
            return 0b0000010;
        }
        if (day == "Sunday" || day == "sunday") {
            return 0b0000001;
        }
        System.out.println(
                "please select a valid day ->\n dayWeek={\"Monday\",\"Tuesday\",\"Wednesday\",\"Thursday\",\"Friday\",\"Saturday\",\"Sunday\"");
        System.out.println("the day selected will be Monday by default");
        return 0b1000000;
    }

}
