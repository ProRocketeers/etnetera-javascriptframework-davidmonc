package com.etnetera.hr.mapper;

import com.etnetera.hr.dto.JavaScriptFrameworkInboundDto;
import com.etnetera.hr.model.JavaScriptFramework;
import com.etnetera.hr.dto.JavaScriptFrameworkOutboundDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Component
public class JavaScriptFrameworkMapper {

    public JavaScriptFramework toJavaScriptFramework(JavaScriptFrameworkInboundDto dto) throws DateTimeParseException {
        return JavaScriptFramework.builder()
                .withName(dto.getName())
                .withVersion(dto.getVersion())
                .withDeprecationDate(LocalDate.now())
                .withHypeLevel(dto.getHypeLevel())
                .build();
    }

    public JavaScriptFrameworkOutboundDto fromJavaScriptFramework(JavaScriptFramework javaScriptFramework) {
        JavaScriptFrameworkOutboundDto dto = new JavaScriptFrameworkOutboundDto();
        dto.setId(javaScriptFramework.getId().intValue());
        dto.setName(javaScriptFramework.getName());
        dto.setVersion(javaScriptFramework.getVersion());
        dto.setDeprecationDate1(javaScriptFramework.getDeprecationDate());
        dto.setHypeLevel(javaScriptFramework.getHypeLevel());
        return dto;
    }
}
