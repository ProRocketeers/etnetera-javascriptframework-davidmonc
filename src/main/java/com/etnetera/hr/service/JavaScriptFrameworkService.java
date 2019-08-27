package com.etnetera.hr.service;

import com.etnetera.hr.model.JavaScriptFramework;

import java.util.List;

/**
 * Interface for service methods above java script framework domain object.
 */
public interface JavaScriptFrameworkService {

    void addJavaScriptFramework(JavaScriptFramework item);

    JavaScriptFramework getJavaScriptFramework(long id);

    void updateJavaScriptFramework(long id, JavaScriptFramework newItem);

    void deleteJavaScriptFramework(long id);

    List<JavaScriptFramework> getJavaScriptFrameworks(String name, String version);
}
