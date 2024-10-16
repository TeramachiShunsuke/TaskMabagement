package com.example.taskmanagement.adapter.inbound.web.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable; // このインポートを追加
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // バリデーションエラーのハンドリング
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @Nullable HttpHeaders headers, // @Nullable を追加
            @Nullable HttpStatusCode status,
            @Nullable WebRequest request) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        // status が null の場合は 500 Internal Server Error を使用
        HttpStatusCode responseStatus = (status != null) ? status : HttpStatusCode.valueOf(500);

        return new ResponseEntity<>(errors, headers, responseStatus);
    }

    // その他の例外ハンドリング（必要に応じて追加）
}