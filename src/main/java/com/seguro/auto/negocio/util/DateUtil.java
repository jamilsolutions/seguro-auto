package com.seguro.auto.negocio.util;

import java.util.Date;

import java.text.ParseException;

public class DateUtil {
    public static long calcular (Date inicio, Date fim) throws ParseException {
        return 
             (inicio.getTime() - fim.getTime() + 3600000L) / 86400000L;
    }    
}
