package com.directory.regions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "region not found")
public class RegionNotFoundException extends RuntimeException {
}
