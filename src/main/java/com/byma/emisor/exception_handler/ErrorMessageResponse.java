package com.byma.emisor.exception_handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessageResponse {
    private final String exception;
    private final int status;
    private final String message;
    private final String path;
    private final String method;
    @Builder.Default
    private final LocalDateTime timestamp = LocalDateTime.now();
    private List<String> details;
}