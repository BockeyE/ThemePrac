package com.fluxdemo.fluxdemo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author bockey
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
    private String msg;

}
