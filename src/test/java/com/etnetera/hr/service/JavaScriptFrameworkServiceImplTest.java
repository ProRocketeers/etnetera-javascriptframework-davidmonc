package com.etnetera.hr.service;

import com.etnetera.hr.model.JavaScriptFramework;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class JavaScriptFrameworkServiceImplTest {

    private static final Long JAVASCRIPT_FRAMEWORK_ID = Long.valueOf(1);
    private static final String JAVASCRIPT_FRAMEWORK_NAME = "ABCD";
    private static final String JAVASCRIPT_FRAMEWORK_VERSION = "1.2.3";
    private static final LocalDate JAVASCRIPT_FRAMEWORK_DEPRECATION_DATE = LocalDate.of(2019, 8, 26);
    private static final int JAVASCRIPT_FRAMEWORK_HYPE_LEVEL = 99;
    private static final String JAVASCRIPT_FRAMEWORK_NEW_NAME = "ASDF";
    private static final String JAVASCRIPT_FRAMEWORK_NEW_VERSION = "9.1.3";
    private static final LocalDate JAVASCRIPT_FRAMEWORK_NEW_DEPRECATION_DATE = LocalDate.of(2020, 9, 11);
    private static final int JAVASCRIPT_FRAMEWORK_NEW_HYPE_LEVEL = 66;

    private JavaScriptFrameworkServiceImpl javaScriptFrameworkService;

    @Mock
    private JavaScriptFrameworkRepository javaScriptFrameworkRepositoryMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        javaScriptFrameworkService = new JavaScriptFrameworkServiceImpl(javaScriptFrameworkRepositoryMock);
    }

    @Test
    public void addJavaScriptFramework_withNull_shouldNotSaveJavaScriptFramework() {
        javaScriptFrameworkService.addJavaScriptFramework(null);

        Mockito.verify(javaScriptFrameworkRepositoryMock, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void addJavaScriptFramework_withJavaScriptFramework_shouldSaveJavaScriptFramework() {
        JavaScriptFramework javaScriptFramework = createJavaScriptFramework();
        javaScriptFrameworkService.addJavaScriptFramework(javaScriptFramework);

        Mockito.verify(javaScriptFrameworkRepositoryMock, Mockito.times(1)).save(javaScriptFramework);
    }

    @Test(expected = EntityNotFoundException.class)
    public void getJavaScriptFramework_withNotExistingId_shouldThrowEntityNotFoundException() {
        Mockito.when(javaScriptFrameworkRepositoryMock.findById(JAVASCRIPT_FRAMEWORK_ID)).thenThrow(new EntityNotFoundException(JAVASCRIPT_FRAMEWORK_ID));

        javaScriptFrameworkService.getJavaScriptFramework(JAVASCRIPT_FRAMEWORK_ID);
    }

    @Test
    public void getJavaScriptFramework_withExistingId_shouldReturnJavascriptFramework() {
        JavaScriptFramework javaScriptFramework = createJavaScriptFramework();
        Mockito.when(javaScriptFrameworkRepositoryMock.findById(JAVASCRIPT_FRAMEWORK_ID)).thenReturn(Optional.of(javaScriptFramework));

        JavaScriptFramework result = javaScriptFrameworkService.getJavaScriptFramework(JAVASCRIPT_FRAMEWORK_ID);

        assertThat(result, is(notNullValue()));
        assertThat(result.getName(), is(JAVASCRIPT_FRAMEWORK_NAME));
    }

    @Test(expected = EntityNotFoundException.class)
    public void updateJavaScriptFramework_withNotExistingId_shouldThrowEntityNotFoundException() {
        Mockito.when(javaScriptFrameworkRepositoryMock.findById(JAVASCRIPT_FRAMEWORK_ID)).thenThrow(new EntityNotFoundException(JAVASCRIPT_FRAMEWORK_ID));

        javaScriptFrameworkService.updateJavaScriptFramework(JAVASCRIPT_FRAMEWORK_ID, Mockito.any());
    }

    @Test
    public void updateJavaScriptFramework_withExistingId_shouldSaveNewJavascriptFramework() {
        JavaScriptFramework oldJsf = createJavaScriptFramework();
        JavaScriptFramework newJsf = createNewJavaScriptFramework();
        Mockito.when(javaScriptFrameworkRepositoryMock.findById(JAVASCRIPT_FRAMEWORK_ID)).thenReturn(Optional.of(oldJsf));

        javaScriptFrameworkService.updateJavaScriptFramework(JAVASCRIPT_FRAMEWORK_ID, newJsf);

        Mockito.verify(javaScriptFrameworkRepositoryMock, Mockito.times(1)).save(newJsf);
        assertThat(newJsf.getId(), is(JAVASCRIPT_FRAMEWORK_ID));
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteJavaScriptFramework_withNotExistingId_shouldThrowEntityNotFoundException() {
        Mockito.when(javaScriptFrameworkRepositoryMock.findById(JAVASCRIPT_FRAMEWORK_ID)).thenThrow(new EntityNotFoundException(JAVASCRIPT_FRAMEWORK_ID));

        javaScriptFrameworkService.deleteJavaScriptFramework(JAVASCRIPT_FRAMEWORK_ID);
    }

    @Test
    public void deleteJavaScriptFramework_withExistingId_shouldDeleteExistingJavascriptFramework() {
        JavaScriptFramework javaScriptFramework = createJavaScriptFramework();
        Mockito.when(javaScriptFrameworkRepositoryMock.findById(JAVASCRIPT_FRAMEWORK_ID)).thenReturn(Optional.of(javaScriptFramework));

        javaScriptFrameworkService.deleteJavaScriptFramework(JAVASCRIPT_FRAMEWORK_ID);

        Mockito.verify(javaScriptFrameworkRepositoryMock, Mockito.times(1)).deleteById(JAVASCRIPT_FRAMEWORK_ID);
    }

    @Test
    public void getJavaScriptFrameworks_withEmptyRepository_shouldReturnEmptyList() {
        Mockito.when(javaScriptFrameworkRepositoryMock.findAll()).thenReturn(new ArrayList<>());

        List<JavaScriptFramework> javaScriptFrameworks = javaScriptFrameworkService.getJavaScriptFrameworks();

        assertThat(javaScriptFrameworks, is(notNullValue()));
        assertThat(javaScriptFrameworks.size(), is(0));
    }

    @Test
    public void getJavaScriptFrameworks_withItemsInRepository_shouldReturnList() {
        List<JavaScriptFramework> javaScriptFrameworks = new ArrayList<>();
        javaScriptFrameworks.add(createJavaScriptFramework());
        javaScriptFrameworks.add(createNewJavaScriptFramework());

        Mockito.when(javaScriptFrameworkRepositoryMock.findAll()).thenReturn(javaScriptFrameworks);

        List<JavaScriptFramework> result = javaScriptFrameworkService.getJavaScriptFrameworks();

        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(2));
    }

    private JavaScriptFramework createJavaScriptFramework() {
        return JavaScriptFramework.builder()
                .withName(JAVASCRIPT_FRAMEWORK_NAME)
                .withVersion(JAVASCRIPT_FRAMEWORK_VERSION)
                .withDeprecationDate(JAVASCRIPT_FRAMEWORK_DEPRECATION_DATE)
                .withHypeLevel(JAVASCRIPT_FRAMEWORK_HYPE_LEVEL)
                .build();
    }

    private JavaScriptFramework createNewJavaScriptFramework() {
        return JavaScriptFramework.builder()
                .withName(JAVASCRIPT_FRAMEWORK_NEW_NAME)
                .withVersion(JAVASCRIPT_FRAMEWORK_NEW_VERSION)
                .withDeprecationDate(JAVASCRIPT_FRAMEWORK_NEW_DEPRECATION_DATE)
                .withHypeLevel(JAVASCRIPT_FRAMEWORK_NEW_HYPE_LEVEL)
                .build();
    }
}