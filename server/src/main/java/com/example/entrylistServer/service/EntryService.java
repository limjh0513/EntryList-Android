package com.example.entrylistServer.service;

import com.example.entrylistServer.entity.VisitantEntity;
import com.example.entrylistServer.repository.EntryRepository;
import com.example.entrylistServer.request.VisitantRequest;
import com.example.entrylistServer.response.VisitantResponse;
import com.example.entrylistServer.utils.VisitantComparator;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EntryService {
    final EntryRepository entryRepository;

    public List<VisitantResponse> inquireVisitant() {
        System.out.println(LocalDateTime.now());
        List<VisitantEntity> entities = entryRepository.findAll();

        ArrayList<VisitantResponse> responseArray = new ArrayList<>();

        entities.forEach(it -> {
            VisitantResponse response = new VisitantResponse();
            response.setId(it.getId());
            String[] phone = it.getPhone_number().split("-");
            response.setPhone_number(phone[0] + "-****-" + phone[2]);
            response.setResidence(it.getResidence());
            String date = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(it.getVisit_time());
            response.setVisit_date(date.toString());

            responseArray.add(response);
        });

        Collections.sort(responseArray, new VisitantComparator());
        return responseArray;
    }

    public boolean insertVisitant(VisitantRequest visitantRequest) {
        System.out.println(visitantRequest.getResidence() + " " + visitantRequest.getPhone_number());

        if(visitantRequest.getPhone_number() == null || visitantRequest.getResidence() == null){
            return false;
        } else {
            VisitantEntity entity = new VisitantEntity(visitantRequest.getPhone_number(), visitantRequest.getResidence());
            entryRepository.save(entity);
        }
        return true;
    }

    public Boolean deleteVisitant(int id) {

        try {
            entryRepository.findById(id);
            entryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<VisitantResponse> inquireMangerVisitant() {
        List<VisitantEntity> entities = entryRepository.findAll();

        ArrayList<VisitantResponse> responseArray = new ArrayList<>();

        entities.forEach(it -> {
            VisitantResponse response = new VisitantResponse();
            response.setId(it.getId());
            response.setPhone_number(it.getPhone_number());
            response.setResidence(it.getResidence());
            String date = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(it.getVisit_time());
            response.setVisit_date(date);

            responseArray.add(response);
        });

        return responseArray;
    }
}
