package com.etnetera.hr.controller;

import com.etnetera.hr.dto.JavaScriptFrameworkInboundDto;
import com.etnetera.hr.mapper.JavaScriptFrameworkMapper;
import com.etnetera.hr.dto.JavaScriptFrameworkOutboundDto;
import com.etnetera.hr.service.JavaScriptFrameworkService;
import com.etnetera.hr.service.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Simple REST controller for accessing javascript framework application logic.
 */
@RestController
@RequestMapping(path = "/frameworks")
public class JavaScriptFrameworkController {


    private final JavaScriptFrameworkService service;
    private final JavaScriptFrameworkMapper mapper;

    @Autowired
    public JavaScriptFrameworkController(JavaScriptFrameworkService service, JavaScriptFrameworkMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<Object> addJavaScriptFramework(@Valid @RequestBody JavaScriptFrameworkInboundDto dto) {
        service.addJavaScriptFramework(mapper.toJavaScriptFramework(dto));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JavaScriptFrameworkOutboundDto> getJavaScriptFramework(@PathVariable long id) {
        JavaScriptFrameworkOutboundDto dto = mapper.fromJavaScriptFramework(service.getJavaScriptFramework(id));

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateJavaScriptFramework(
            @PathVariable long id,
            @Valid @RequestBody JavaScriptFrameworkInboundDto dto) {

        // exists?
        service.getJavaScriptFramework(id);

        service.updateJavaScriptFramework(id, mapper.toJavaScriptFramework(dto));

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> updateJavaScriptFramework(@PathVariable long id) {
        service.deleteJavaScriptFramework(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<JavaScriptFrameworkOutboundDto>> getJavaScriptFrameworks(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "version", required = false) String version) {

        List<JavaScriptFrameworkOutboundDto> dtos = service.getJavaScriptFrameworks(name, version).stream()
                .map(i -> mapper.fromJavaScriptFramework(i))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

}
