package com.etnetera.hr.repository;

import com.etnetera.hr.model.JavaScriptFramework;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface defines few custom searches by fields.
 */
@Repository
public interface JavaScriptFrameworkRepository extends CrudRepository<JavaScriptFramework, Long> {

    List<JavaScriptFramework> findByName(String name);

    List<JavaScriptFramework> findByVersion(String version);

    List<JavaScriptFramework> findByNameAndVersion(String name, String version);
}
