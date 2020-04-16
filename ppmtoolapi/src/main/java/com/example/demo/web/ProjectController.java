package com.example.demo.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Project;
import com.example.demo.service.MapValidationErrorService;
import com.example.demo.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	
//	@PostMapping("/addProject")
//	public ResponseEntity<Project> saveProject(@RequestBody Project project) {
//		Project pro = projectservice.saveOrUpdate(project);
//		return new ResponseEntity<Project>(pro, HttpStatus.CREATED);
//	}
	
	
//	@PostMapping("")
//	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project,BindingResult result){
//		if(result.hasErrors()) {
//			return new ResponseEntity<String>("Invalid Project Object",HttpStatus.BAD_REQUEST);
//		}
//		Project proj = projectService.saveOrUpdate(project);
//		return new ResponseEntity<Project>(proj, HttpStatus.CREATED);
//	}
	
	
//	@PostMapping("")
//	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project,BindingResult result){
//		if(result.hasErrors()) {
//			Map<String, String> errorMap = new HashMap<>();
//			for(FieldError fieldError : result.getFieldErrors()) {
//				errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
//			}
//			return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.BAD_REQUEST);
//		}
//		Project proj = projectService.saveOrUpdate(project);
//		return new ResponseEntity<Project>(proj, HttpStatus.CREATED);
//	}
	
	
	
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project,BindingResult result){
		
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) return errorMap;
		
		Project proj = projectService.saveOrUpdate(project);
		return new ResponseEntity<Project>(proj, HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProjectById(@PathVariable String projectId){
		Project project = projectService.findProjectByIdentifier(projectId);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<Project> getAllProjects(){
		return projectService.findAllProjcts();
	}

}
