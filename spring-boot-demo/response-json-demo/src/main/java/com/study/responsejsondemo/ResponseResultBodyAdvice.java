package com.study.responsejsondemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.util.WebUtils;

import java.lang.annotation.Annotation;


/*
step-5:对响应数据进行再处理，成功的--拦截注解ResponseResultBody，


    ResponseBodyAdvice是Spring4.1的新特性，其作用是在响应体写出之前做一些处理；比如，修改返回值、加密、封装等。
 */

@Slf4j
@RestControllerAdvice
public class ResponseResultBodyAdvice implements ResponseBodyAdvice<Object> {

    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseResultBody.class;

    /**
     * 拦截当前类或当前方法是否使用了 @ResponseResultBody
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ANNOTATION_TYPE) || returnType.hasMethodAnnotation(ANNOTATION_TYPE);
    }

    /**
     * 当类或者方法使用了 @ResponseResultBody 就会调用这个方法，即上面supports()方法返回值是true的情况下
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.info("");

        // 防止重复包裹的问题出现
        if (body instanceof Result) {
            return body;
        }

        return Result.success(body);//需要注意，此处的返回值类型需要与原方法的返回值类型一致，否则会出现转换异常
    }

    /**
     * 提供对标准Spring MVC异常的处理
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Result<?>> exceptionHandler(Exception ex, WebRequest request) {

        log.error("ExceptionHandler: {}", ex.getMessage());
        HttpHeaders headers = new HttpHeaders();

        //
        if (ex instanceof ResultException) {
            return this.handleResultException((ResultException) ex, headers, request);
        }
        // TODO 这里可以自定义其他的异常拦截
        return this.handleException(ex, headers, request);
    }

    /**
     * 对ResultException类返回返回结果的处理
     */
    protected ResponseEntity<Result<?>> handleResultException(ResultException ex, HttpHeaders headers, WebRequest request) {
        Result<?> body = Result.failure(ex.getResultStatus());
        HttpStatus status = ex.getResultStatus().getHttpStatus();
        return this.handleExceptionInternal(ex, body, headers, status, request);
    }

    /** Exception异常类的统一处理 */
    protected ResponseEntity<Result<?>> handleException(Exception ex, HttpHeaders headers, WebRequest request) {
        Result<?> body = Result.failure();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return this.handleExceptionInternal(ex, body, headers, status, request);
    }


    protected ResponseEntity<Result<?>> handleExceptionInternal(Exception ex, Result<?> body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}
