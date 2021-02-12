package com.directory.regions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason = "region creation error")
public class RegionCreationException extends RuntimeException {
}
