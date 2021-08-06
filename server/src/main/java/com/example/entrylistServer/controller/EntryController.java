package com.example.entrylistServer.controller;

import com.example.entrylistServer.request.VisitantRequest;
import com.example.entrylistServer.response.BaseResponse;
import com.example.entrylistServer.response.VisitantResponse;
import com.example.entrylistServer.service.EntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class EntryController {
    final private EntryService entryService;

    @GetMapping("inquire")
    public BaseResponse<List<VisitantResponse>> inquireVisitant() {
        try {
            List<VisitantResponse> visitants = entryService.inquireVisitant();
            return new BaseResponse<>(200, "성공적으로 조회했습니다.", visitants);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse<>(500, "조회에 실패했습니다.", null);
        }
    }

    @PutMapping("insert")
    public BaseResponse<Boolean> insertVisitant(@RequestBody VisitantRequest visitantRequest) {
        if(entryService.insertVisitant(visitantRequest)){
            return new BaseResponse<>(200, "성공적으로 등록했습니다.", true);
        } else{
            return new BaseResponse<>(500, "등록에 실패했습니다..", false);
        }

    }

    @DeleteMapping("delete/{id}")
    public BaseResponse<Boolean> deleteVisitant(@PathVariable int id) {
        System.out.println(id);
        if (entryService.deleteVisitant(id)) {
            return new BaseResponse<>(200, "성공적으로 삭제했습니다.", true);
        } else {
            return new BaseResponse<>(500, "삭제를 실패했습니다.", false);
        }

    }

    @GetMapping("manager")
    public BaseResponse<List<VisitantResponse>> inquireManagerVisitant() {
        List<VisitantResponse> visitants = entryService.inquireMangerVisitant();
        return new BaseResponse<>(200, "성공적으로 조회했습니다.", visitants);
    }
}
