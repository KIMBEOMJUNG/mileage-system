package com.triple.mileage.common.util;

import java.util.ArrayList;

public class util {

    public static ArrayList<String> stringToArrayList (String entity) {
        String temp= entity.replace("[","").replace("]","");
        String[] arr = temp.split(",");
        ArrayList<String> arrayList = new ArrayList<>();
        for(String i : arr) {
            arrayList.add(i);
        }

        return arrayList;
    }
    
}
