package com.adaptris.samples;
import com.adaptris.annotation.InputFieldHint;

public enum TimeUnit {

    //DAYS, HOURS, MINUTES, SECONDS, MILLISECONDS, MICROSECONDS, NANOSECONDS
    @InputFieldHint(friendly = "DAYS") DAYS,        //input hint field gives you an option to display a value but have it linked to another value eg. compare by days translates to DAYS
    @InputFieldHint(friendly = "HOURS") HOURS,
    @InputFieldHint(friendly = "MINUTES") MINUTES,
    @InputFieldHint(friendly = "SECONDS") SECONDS;

}