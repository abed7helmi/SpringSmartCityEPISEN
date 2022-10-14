package com.pds.smartUs.BackEnd.appback.simulator.config;

public class SmartGridVariables {
    public static long PRODUCTION_INSERTS_STATS     = 3600 * 1000;
    public static long SIMULATOR_REFRESH_FREQUENCY  = 60 * 10;
    public static long SMART_GRID_TIME_UNIT         = 1500; // 100 milliseconds
    public static long SMART_GRID_SIMULATOR_REFRESH = 1000;
    public static long SMART_GRID_BALANCE_REFRESH   = 1000;
    public static long PROD_CONS_FREQUENCY          = 750;
    public static long SMART_GRID_ONE_HOUR          = SMART_GRID_TIME_UNIT * 60;
    public static long UPDATE_OBSERVERS_FREQUENCY   = 60 * 1000;
}
