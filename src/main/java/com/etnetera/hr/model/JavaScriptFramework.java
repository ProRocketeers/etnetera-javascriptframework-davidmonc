package com.etnetera.hr.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class JavaScriptFramework {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Please provide a name")
    private String name;
    @NotEmpty(message = "Please provide a version")
    private String version;
    @NotNull(message = "Please provide a deprecationDate as string in format 'yyyy-MM-dd'")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate deprecationDate;
    @NotNull(message = "Please provide a hypeLevel")
    private int hypeLevel;

    private JavaScriptFramework() {}

    private JavaScriptFramework(Builder b) {
        this.id = b.id;
        this.name = b.name;
        this.version = b.version;
        this.deprecationDate = b.deprecationDate;
        this.hypeLevel = b.hypeLevel;
    }

    public Long getId() {
        return id;
    }

    // for update case only
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public LocalDate getDeprecationDate() {
        return deprecationDate;
    }

    public int getHypeLevel() {
        return hypeLevel;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String version;
        private LocalDate deprecationDate;
        private int hypeLevel;

        private Builder() {}

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withVersion(String version) {
            this.version = version;
            return this;
        }

        public Builder withDeprecationDate(LocalDate deprecationDate) {
            this.deprecationDate = deprecationDate;
            return this;
        }

        public Builder withHypeLevel(int hypeLevel) {
            this.hypeLevel = hypeLevel;
            return this;
        }

        public JavaScriptFramework build() {
            return new JavaScriptFramework(this);
        }
    }

    @Override
    public String toString() {
        return String.format("JavaScriptFramework[id=%d, name='%s', version='%s', deprecationDate='%s', hypeLevel=%d]",
                id, name, version, deprecationDate, hypeLevel);
    }
}
