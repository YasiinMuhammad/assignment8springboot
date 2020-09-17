package com.ymtechstudio.week13homework.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ymtechstudio.week13homework.domain.Recipe;
import com.ymtechstudio.week13homework.service.FileService;

@RestController
public class FileController {
	@Autowired
	private FileService fileService;
	
	@GetMapping("/all-recipes")
	public List<Recipe> getAllRecipes() {
		return fileService.getRecipes();
	}
	
	@GetMapping("/vegan")
	public List<Recipe> getVegan(){
		return fileService.getRecipes()
						  .stream()
						  .filter(Recipe::getVegan)
						  .collect(Collectors.toList());
		
	}
	@GetMapping("/gluten-free")
	public List<Recipe> getGlutenFree(){
		return fileService.getRecipes()
						  .stream()
						  .filter(Recipe::getGlutenFree)
						  .collect(Collectors.toList());
		
	}
	@GetMapping("/vegan-and-gluten-free")
	public List<Recipe> getVeganAndGultenFree(){
		return fileService.getRecipes()
				  .stream()
				  .filter(Recipe::getVegan).filter(Recipe::getGlutenFree)
				  .collect(Collectors.toList());
	}
	@GetMapping("/vegetarian")
	public List<Recipe> getVegetarian(){
		return fileService.getRecipes()
				  .stream()
				  .filter(Recipe::getDairyFree)
				  .collect(Collectors.toList());
	}

}
