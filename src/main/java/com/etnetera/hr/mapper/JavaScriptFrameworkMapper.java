package com.etnetera.hr.mapper;

import com.etnetera.hr.dto.JavaScriptFrameworkInboundDto;
import com.etnetera.hr.model.JavaScriptFramework;
import com.etnetera.hr.dto.JavaScriptFrameworkOutboundDto;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeParseException;

/**
 * Mapper for mapping in and out dto &lt;-&gt; domain java script framework
 */
@Component
public class JavaScriptFrameworkMapper {

    public JavaScriptFramework toJavaScriptFramework(JavaScriptFrameworkInboundDto dto) throws DateTimeParseException {
        return dto == null ? null : JavaScriptFramework.builder()
                .withName(dto.getName())
                .withVersion(dto.getVersion())
                .withDeprecationDate(dto.getDeprecationDate())
                .withHypeLevel(dto.getHypeLevel())
                .build();
    }

    public JavaScriptFrameworkOutboundDto fromJavaScriptFramework(JavaScriptFramework javaScriptFramework) {
        if (javaScriptFramework == null) {
            return null;
        }
        JavaScriptFrameworkOutboundDto dto = new JavaScriptFrameworkOutboundDto();
        dto.setId(javaScriptFramework.getId().intValue());
        dto.setName(javaScriptFramework.getName());
        dto.setVersion(javaScriptFramework.getVersion());
        dto.setDeprecationDate(javaScriptFramework.getDeprecationDate());
        dto.setHypeLevel(javaScriptFramework.getHypeLevel());
        return dto;
    }
}
