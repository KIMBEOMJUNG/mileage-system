package com.triple.mileage.common.util;

import java.util.ArrayList;

public class util {

    public static ArrayList<String> stringToArrayList (String entity) {
        String temp= entity.replace("[","").replace("]","");
        ArrayList<String> arrayList = new ArrayList<>();
        if(temp.isEmpty() != true){
            String[] arr = temp.split(",");
            for(String i : arr) {
                arrayList.add(i);
            }
        }
        return arrayList;
    }
    
}
