package com.etnetera.hr.service;

import com.etnetera.hr.model.JavaScriptFramework;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JavaScriptFrameworkServiceImpl implements JavaScriptFrameworkService {

    private final JavaScriptFrameworkRepository repository;

    @Autowired
    public JavaScriptFrameworkServiceImpl(JavaScriptFrameworkRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addJavaScriptFramework(JavaScriptFramework item) {
        if (item != null) {
            this.repository.save(item);
        }
    }

    @Override
    public JavaScriptFramework getJavaScriptFramework(long id) {
        Optional<JavaScriptFramework> optionalJavaScriptFramework = this.repository.findById(id);
        return optionalJavaScriptFramework.orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public void updateJavaScriptFramework(long id, JavaScriptFramework newItem) {
        // exists with not found exception
        getJavaScriptFramework(id);

        newItem.setId(id);

        this.repository.save(newItem);
    }

    @Override
    public void deleteJavaScriptFramework(long id) {
        // exists with not found exception
        getJavaScriptFramework(id);

        this.repository.deleteById(id);
    }

    @Override
    public List<JavaScriptFramework> getJavaScriptFrameworks() {
        List<JavaScriptFramework> javaScriptFrameworks = new ArrayList<>();
        this.repository.findAll().forEach(javaScriptFrameworks::add);
        return javaScriptFrameworks;
    }
}
