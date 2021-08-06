package com.example.entrylistServer.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class VisitantResponse{
    private int id;
    private String phone_number;
    private String residence;
    private String visit_date;

    public VisitantResponse() {

    }


}
