package com.bajaj.bfhl.service;

import com.bajaj.bfhl.dto.RequestDto;
import com.bajaj.bfhl.dto.ResponseDto;

public interface BfhlService {
    ResponseDto processData(RequestDto requestDto);
}
