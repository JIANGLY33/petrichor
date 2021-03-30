package com.jalinyiel.petrichor.core.util;

import com.jalinyiel.petrichor.core.ObjectEncoding;
import com.jalinyiel.petrichor.core.ObjectType;

import java.time.LocalTime;

public class PetrichorUtil {

    public static final ObjectType KEY_TYPE = ObjectType.PETRICHOR_STRING;

    public static final ObjectEncoding KEY_ENCODING = ObjectEncoding.RAW_STRING;

    public static int timeCompare(String t1, String t2) {
        LocalTime localTime1 = LocalTime.parse(t1);
        LocalTime localTime2 = LocalTime.parse(t2);
        return localTime1.compareTo(localTime2);
    }


}
