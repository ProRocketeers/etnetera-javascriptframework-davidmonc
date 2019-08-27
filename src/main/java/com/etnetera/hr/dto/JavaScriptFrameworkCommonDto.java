package com.etnetera.hr.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Holds the transfer object. Parent of inbound and outbound dtos.
 */
public class JavaScriptFrameworkCommonDto {

    @NotEmpty(message = "Please provide a name")
    private String name;
    @NotEmpty(message = "Please provide a version")
    private String version;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate deprecationDate;
    @NotNull(message = "Please provide a hypeLevel")
    private int hypeLevel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LocalDate getDeprecationDate() {
        return deprecationDate;
    }

    public void setDeprecationDate(LocalDate deprecationDate) {
        this.deprecationDate = deprecationDate;
    }

    public int getHypeLevel() {
        return hypeLevel;
    }

    public void setHypeLevel(int hypeLevel) {
        this.hypeLevel = hypeLevel;
    }

    @Override
    public String toString() {
        return String.format("JavaScriptFrameworkCommonDto[name='%s', version='%s', deprecationDate='%s', hypeLevel=%d",
                name, version, deprecationDate, hypeLevel);
    }
}
