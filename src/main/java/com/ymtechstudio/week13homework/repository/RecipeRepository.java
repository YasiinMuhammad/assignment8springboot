package com.ymtechstudio.week13homework.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ymtechstudio.week13homework.domain.Recipe;

@Repository
public class RecipeRepository {

	private List<Recipe> recipes = new ArrayList<>(100);
	
	public List<Recipe> getRecipes() {
		return recipes;
		
	}
	
} 