package com.example.entrylistServer.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VisitantRequest {
    private String phone_number;
    private String residence;
}
