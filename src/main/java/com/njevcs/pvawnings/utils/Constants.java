/**
 * 
 */
package com.njevcs.pvawnings.utils;

/**
 * @author patel
 *
 *         Nov 27, 2024
 */
public class Constants {
    public static final String COMMA = ",";
    public static final String SPACE = " ";
    public static final String DATA = "data";
    public static final String TOTAL = "total";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String NO_DATA = "There is no data";
    public static final String INCOME = "income";
    public static final String INCOME_LEVEL = "incomeLevel";
    public static final String ZIP_CODE = "zipCode";
    public static final String CITY = "city";
    public static final String COUNTY = "county";
    public static final String NUMBER_OF_EVS = "numberOfEvs";
    public static final String EVS_LEVEL = "evsLevel";
    public static final String STATE_NJ = "NJ";
    public static final String LOW = "Low";
    public static final String MEDIUM = "Medium";
    public static final String HIGH = "High";
    public static final String LEVEL_1 = "level1";
    public static final String LEVEL_2 = "level2";
    public static final String DC_FAST = "dc_fast";
    public static final String ZERO = "0";
    public static final int HOURS_IN_DAY = 24;

    // https://www.mdpi.com/2032-6653/14/11/308 — Section 7.6 — Fig. 12
    public static final int EV_STATION_UTILIZATION = 25;

    // kWh per EV per day
    // https://www.gencellenergy.com/resources/blog/ev-charging-power-car-electricity-usage/
    public static final int EV_ENERGY_PER_DAY = 11;

    public static final int LOW_INCOME_THRESHOLD = 60000;
    public static final int HIGH_INCOME_THRESHOLD = 100000;

    // Charging power per connector type (kW)
    // https://www.transportation.gov/rural/ev/toolkit/ev-basics/charging-speeds
    public static final int LEVEL1_POWER_KW = 1;
    public static final int LEVEL2_POWER_KW = 7;
    public static final int DC_FAST_POWER_KW = 50;

    // Utilization per day (hours)
    public static final int LEVEL1_UTILIZATION_HOURS = 4;
    public static final int LEVEL2_UTILIZATION_HOURS = 4;
    public static final int DC_FAST_UTILIZATION_HOURS = 1;

    // Utilization of number of cars per day
    public static final int LEVEL1_UTILIZATION_CARS = 2;
    public static final int LEVEL2_UTILIZATION_CARS = 2;
    public static final int DC_FAST_UTILIZATION_CARS = 8;
}
