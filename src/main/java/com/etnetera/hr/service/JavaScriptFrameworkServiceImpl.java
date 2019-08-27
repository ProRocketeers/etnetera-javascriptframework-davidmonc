package com.etnetera.hr.service;

import com.etnetera.hr.model.JavaScriptFramework;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of service methods above java script framework domain object.
 */
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
    public List<JavaScriptFramework> getJavaScriptFrameworks(String name, String version) {
        List<JavaScriptFramework> javaScriptFrameworks = new ArrayList<>();

        Iterable<JavaScriptFramework> javaScriptFrameworkIterable;
        switch (findByRecognition(name, version)) {

            case NO_FILTER:
                javaScriptFrameworkIterable = this.repository.findAll();
                break;

            case BY_NAME:
                javaScriptFrameworkIterable = this.repository.findByName(name);
                break;

            case BY_VERSION:
                javaScriptFrameworkIterable = this.repository.findByVersion(version);
                break;

            case BY_NAME_AND_VERSION:
                javaScriptFrameworkIterable = this.repository.findByNameAndVersion(name, version);
                break;

            default:
                throw new IllegalArgumentException("Not reachable point.");
        }

        javaScriptFrameworkIterable.forEach(javaScriptFrameworks::add);
        return javaScriptFrameworks;
    }

    // TODO definitely point for refactoring
    FindBy findByRecognition(String name, String version) {
        if (StringUtils.isEmpty(name) && StringUtils.isEmpty(version)) {
            return FindBy.NO_FILTER;
        } else if (StringUtils.isEmpty(name)) {
            return FindBy.BY_VERSION;
        } else if (StringUtils.isEmpty(version)) {
            return FindBy.BY_NAME;
        } else {
            return FindBy.BY_NAME_AND_VERSION;
        }
    }
}
