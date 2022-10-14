package com.pds.smartUs.BackEnd.appback.helpers;

import java.time.Instant;
import java.util.*;

public class Helper {

    public static String getDateNow(){
        long unixTime = Instant.now().getEpochSecond();
        Instant instant = Instant.ofEpochSecond(unixTime);
        String withoutT = instant.toString().replace('T', ' ');
        String datenow = withoutT.replace('Z', ' ');
        return datenow;
    }


    public static Map<String, Float> sortMapFloat(Map<String, Float> map) {
        List<Map.Entry<String, Float>> list = new LinkedList<Map.Entry<String, Float>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Float>>() {
            public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        Map<String, Float> sortedMap = new LinkedHashMap<String, Float>();
        for (Iterator<Map.Entry<String, Float>> it = list.iterator(); it.hasNext();) {
            Map.Entry<String, Float> entry = (Map.Entry<String, Float>) it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static Map<String, Integer> sortMapInt(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();) {
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}
