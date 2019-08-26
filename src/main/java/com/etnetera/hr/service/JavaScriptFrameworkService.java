package com.etnetera.hr.service;

import com.etnetera.hr.model.JavaScriptFramework;

import java.util.List;

public interface JavaScriptFrameworkService {

    void addJavaScriptFramework(JavaScriptFramework item);

    JavaScriptFramework getJavaScriptFramework(long id);

    void updateJavaScriptFramework(long id, JavaScriptFramework newItem);

    void deleteJavaScriptFramework(long id);

    List<JavaScriptFramework> getJavaScriptFrameworks();
}
