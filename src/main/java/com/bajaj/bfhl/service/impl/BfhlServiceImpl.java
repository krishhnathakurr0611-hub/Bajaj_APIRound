package com.bajaj.bfhl.service.impl;

import com.bajaj.bfhl.dto.RequestDto;
import com.bajaj.bfhl.dto.ResponseDto;
import com.bajaj.bfhl.service.BfhlService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Override
    public ResponseDto processData(RequestDto requestDto) {
        ResponseDto response = new ResponseDto();
        
        // "krishna_thakur_06112005"
        response.setUserId("krishna_thakur_06112005");
        response.setEmail("krishnathakur230625@acropolis.in");
        response.setRollNumber("0827CI231069");
        
        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        BigInteger sum = BigInteger.ZERO;
        
        StringBuilder concatBuilder = new StringBuilder();
        
        if (requestDto.getData() != null) {
            for (String item : requestDto.getData()) {
                if (item == null || item.isEmpty()) continue;
                
                // Process characters for concat_string
                for (char c : item.toCharArray()) {
                    if (Character.isLetter(c)) {
                        concatBuilder.append(c);
                    }
                }
                
                // Classification
                if (item.matches("^-?\\d+$")) {
                    BigInteger number = new BigInteger(item);
                    sum = sum.add(number);
                    if (number.remainder(new BigInteger("2")).equals(BigInteger.ZERO)) {
                        evenNumbers.add(item);
                    } else {
                        oddNumbers.add(item);
                    }
                } else if (item.matches("^[a-zA-Z]+$")) {
                    alphabets.add(item.toUpperCase());
                } else if (item.matches("^[^a-zA-Z0-9]+$")) {
                    specialCharacters.add(item);
                }
            }
        }
        
        // Generate concat_string
        String reversed = concatBuilder.reverse().toString();
        StringBuilder finalConcat = new StringBuilder();
        boolean upper = true;
        for (char c : reversed.toCharArray()) {
            if (upper) {
                finalConcat.append(Character.toUpperCase(c));
            } else {
                finalConcat.append(Character.toLowerCase(c));
            }
            upper = !upper;
        }
        
        response.setOddNumbers(oddNumbers);
        response.setEvenNumbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialCharacters);
        response.setSum(sum.toString());
        response.setConcatString(finalConcat.toString());
        response.setIsSuccess(true);
        
        return response;
    }
}
