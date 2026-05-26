package com.bajaj.bfhl.controller;

import com.bajaj.bfhl.dto.RequestDto;
import com.bajaj.bfhl.dto.ResponseDto;
import com.bajaj.bfhl.service.BfhlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bfhl")
@CrossOrigin("*")
public class BfhlController {

    @Autowired
    private BfhlService bfhlService;

    @PostMapping
    public ResponseEntity<ResponseDto> processData(@RequestBody RequestDto requestDto) {
        try {
            ResponseDto response = bfhlService.processData(requestDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseDto errorResponse = new ResponseDto();
            errorResponse.setIsSuccess(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
