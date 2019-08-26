package com.etnetera.hr.repository;

import com.etnetera.hr.model.JavaScriptFramework;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JavaScriptFrameworkRepository extends CrudRepository<JavaScriptFramework, Long> {
}
