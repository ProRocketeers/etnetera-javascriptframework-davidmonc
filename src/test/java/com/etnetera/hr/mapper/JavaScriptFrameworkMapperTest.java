package com.etnetera.hr.mapper;

import com.etnetera.hr.dto.JavaScriptFrameworkInboundDto;
import com.etnetera.hr.dto.JavaScriptFrameworkOutboundDto;
import com.etnetera.hr.model.JavaScriptFramework;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class JavaScriptFrameworkMapperTest {

    private static final Long JAVASCRIPT_FRAMEWORK_ID = Long.valueOf(1);
    private static final String JAVASCRIPT_FRAMEWORK_NAME = "ABCD";
    private static final String JAVASCRIPT_FRAMEWORK_VERSION = "1.2.3";
    private static final LocalDate JAVASCRIPT_FRAMEWORK_DEPRECATION_DATE = LocalDate.of(2019, 8, 26);
    private static final int JAVASCRIPT_FRAMEWORK_HYPE_LEVEL = 99;

    private JavaScriptFrameworkMapper mapper;

    @Before
    public void setup() {
        mapper = new JavaScriptFrameworkMapper();
    }

    @Test
    public void toJavaScriptFramework_withNullDto_returnsNull() {
        JavaScriptFramework javaScriptFramework = mapper.toJavaScriptFramework(null);
        assertThat(javaScriptFramework, is(nullValue()));
    }

    @Test
    public void toJavaScriptFramework_withDto_returnsJavaScriptFramework() {
        JavaScriptFrameworkInboundDto inboundDto = createInboundDto();
        JavaScriptFramework javaScriptFramework = mapper.toJavaScriptFramework(inboundDto);

        assertThat(javaScriptFramework, is(notNullValue()));

        // just to be sure :)
        assertThat(javaScriptFramework.getName(), is(JAVASCRIPT_FRAMEWORK_NAME));
        assertThat(javaScriptFramework.getVersion(), is(JAVASCRIPT_FRAMEWORK_VERSION));
        assertThat(javaScriptFramework.getDeprecationDate(), is(JAVASCRIPT_FRAMEWORK_DEPRECATION_DATE));
        assertThat(javaScriptFramework.getHypeLevel(), is(JAVASCRIPT_FRAMEWORK_HYPE_LEVEL));
    }

    @Test
    public void fromJavaScriptFramework_withNullJavaScriptFramework_returnsNull() {
        assertThat(mapper.fromJavaScriptFramework(null), is(nullValue()));
    }

    @Test
    public void fromJavaScriptFramework_withJavaScriptFramework_returnsOutboutDto() {
        JavaScriptFrameworkOutboundDto outboundDto = mapper.fromJavaScriptFramework(
                JavaScriptFramework.builder()
                        .withId(JAVASCRIPT_FRAMEWORK_ID)
                        .withName(JAVASCRIPT_FRAMEWORK_NAME)
                        .withVersion(JAVASCRIPT_FRAMEWORK_VERSION)
                        .withDeprecationDate(JAVASCRIPT_FRAMEWORK_DEPRECATION_DATE)
                        .withHypeLevel(JAVASCRIPT_FRAMEWORK_HYPE_LEVEL)
                        .build());

        assertThat(outboundDto, is(notNullValue()));

        // just to be sure :)
        assertThat(outboundDto.getId(), is(JAVASCRIPT_FRAMEWORK_ID.intValue()));
        assertThat(outboundDto.getName(), is(JAVASCRIPT_FRAMEWORK_NAME));
        assertThat(outboundDto.getVersion(), is(JAVASCRIPT_FRAMEWORK_VERSION));
        assertThat(outboundDto.getDeprecationDate(), is(JAVASCRIPT_FRAMEWORK_DEPRECATION_DATE));
        assertThat(outboundDto.getHypeLevel(), is(JAVASCRIPT_FRAMEWORK_HYPE_LEVEL));
    }

    private JavaScriptFrameworkInboundDto createInboundDto() {
        JavaScriptFrameworkInboundDto javaScriptFrameworkInboundDto = new JavaScriptFrameworkInboundDto();
        javaScriptFrameworkInboundDto.setName(JAVASCRIPT_FRAMEWORK_NAME);
        javaScriptFrameworkInboundDto.setVersion(JAVASCRIPT_FRAMEWORK_VERSION);
        javaScriptFrameworkInboundDto.setDeprecationDate(JAVASCRIPT_FRAMEWORK_DEPRECATION_DATE);
        javaScriptFrameworkInboundDto.setHypeLevel(JAVASCRIPT_FRAMEWORK_HYPE_LEVEL);

        return javaScriptFrameworkInboundDto;
    }
}