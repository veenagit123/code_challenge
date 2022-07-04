package com.example.kubeServicesNew;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.example.kubeServicesNew.KubeServices;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class KubeServicesJpaResource {
	
	
	@Autowired
	private KubeServicesJPAData kubeServicesJPAData;

	
	@GetMapping("/jpa/courses/{coursename}/todos")
	public List<KubeServices> getAllTodos(@PathVariable String coursename){
		return kubeServicesJPAData.findByCoursename(coursename);
		
	}

	@GetMapping("/jpa/courses/{coursename}/todos/{id}")
	public KubeServices getTodo(@PathVariable String coursename, @PathVariable long id){
		return kubeServicesJPAData.findById(id).get();
		
	}

	
	@DeleteMapping("/jpa/courses/{coursename}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(
			@PathVariable String coursename, @PathVariable long id){
		
		
		kubeServicesJPAData.deleteById(id);
		
		return ResponseEntity.noContent().build();
		
	}
	

	
	@PutMapping("/jpa/courses/{coursename}/todos/{id}")
	public ResponseEntity<KubeServices> updateTodo(
			@PathVariable String coursename,
			@PathVariable long id, @RequestBody KubeServices todo){
		
		//Todo todoUpdated = todoService.save(todo);
		KubeServices todoUpdated = kubeServicesJPAData.save(todo);
		
		return new ResponseEntity<KubeServices>(todo, HttpStatus.OK);
	}
	
	@PostMapping("/jpa/courses/{coursename}/todos")
	public ResponseEntity<Void> createTodo(
			@PathVariable String coursename, @RequestBody KubeServices todo){
		
		
		todo.setCoursename(coursename);
		KubeServices createdTodo = kubeServicesJPAData.save(todo);
		
		//Location
		//Get current resource url
		///{id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
		
}