package com.init.sopa.rest;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.init.sopa.dao.SopaDAO;
import com.init.sopa.entitys.Sopa;

@RestController
@RequestMapping("products")
public class SopaREST {
	
	@Autowired
	private SopaDAO sopaDAO;
	
	@GetMapping
	public ResponseEntity<List<Sopa>> getProduct(){
		List<Sopa> products=sopaDAO.findAll();	
		return ResponseEntity.ok(products);	
	}
	
	@RequestMapping(value="{sopaId}") // /sopa/{sopatId} -> /sopa/1
	public ResponseEntity<Sopa> getProductById(@PathVariable("sopaId") Long sopaId){	
		Optional<Sopa> optionalProduct = sopaDAO.findById(sopaId);
		if(optionalProduct.isPresent()) {
			return ResponseEntity.ok(optionalProduct.get());
		} else {			
			return ResponseEntity.noContent().build();
		}			
	}	
	
	@PostMapping(value="{/baz/sopa-numeros/validate}")
	public ResponseEntity<Sopa> createProduct(@RequestBody Sopa sopa){
		Sopa validation = sopaDAO.save(sopa);
		return ResponseEntity.ok(validation);
	}
	
	@DeleteMapping(value="{sopaId}") // /products (DELET)
	public ResponseEntity<Void> deleteProduct(@PathVariable("sopaId") Long sopaId){
		sopaDAO.deleteById(sopaId);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping
	public ResponseEntity<Sopa> updateProduct(@RequestBody Sopa sopa){
		Optional<Sopa> optionalProduct = sopaDAO.findById(sopa.getId());
		if(optionalProduct.isPresent()) {
			Sopa updateProduct = optionalProduct.get();
			updateProduct.setName(sopa.getName());
			sopaDAO.save(updateProduct);
			return ResponseEntity.ok(updateProduct);
		} else {			
			return ResponseEntity.noContent().build();
		}
	}
	
	//@GetMapping //localhost:8080
	//@RequestMapping(value="hello", method=RequestMethod.GET)
	/*public String hello() {
		return "Hello word";*/
	}
	

