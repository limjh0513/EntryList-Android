package com.example.entrylistServer.utils;

import com.example.entrylistServer.response.VisitantResponse;

import java.util.Comparator;

public class VisitantComparator implements Comparator<VisitantResponse> {
    @Override
    public int compare(VisitantResponse o1, VisitantResponse o2) {
        if(o1.getId() < o2.getId()) return 1;
        if(o1.getId() > o2.getId()) return -1;
        return 0;
    }
}
