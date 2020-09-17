package com.ymtechstudio.week13homework.service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymtechstudio.week13homework.domain.Recipe;
import com.ymtechstudio.week13homework.repository.RecipeRepository;

@Service
public class FileService {
	@Autowired
	private RecipeRepository recipeRepo;

	public enum recipeHeader {
		cookingMinutes, dairyFree, glutenFree, instructions, preparationMinutes, pricePerServing, readyInMinutes,
		servings, spoonacularScore, title, vegan, vegetarian
	}

	private List<Recipe> getAllRecipeFromCsv() {

//		if (recipeRepo.getAllRecipes().size() == 0) {

		CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(',')
				.withEscape('\\')
				.withQuote('"')
				.withIgnoreSurroundingSpaces()
				.withHeader(recipeHeader.class)
				.withSkipHeaderRecord();

		try (Reader in = new FileReader("recipe.txt")) {
			Iterable<CSVRecord> records = csvFormat.parse(in);
			for (CSVRecord record : records) {
				Integer cookingMinutes = Integer.parseInt(record.get("cookingMinutes"));
				Boolean dairyFree = Boolean.parseBoolean(record.get("dairyFree"));
				Boolean glutenFree = Boolean.parseBoolean(record.get("glutenFree"));
				String instructions = record.get("instructions");
				Double preparationMinutes = Double.parseDouble(record.get("preparationMinutes"));
				Double pricePerServing = Double.parseDouble(record.get("pricePerServing"));
				Integer readyInMinutes = Integer.parseInt(record.get("readyInMinutes"));
				Integer servings = Integer.parseInt(record.get("servings"));
				Double spoonacularScore = Double.parseDouble(record.get("spoonacularScore"));
				String title = record.get("title");
				Boolean vegan = Boolean.parseBoolean(record.get("vegan"));
				Boolean vegetarian = Boolean.parseBoolean(record.get("vegetarian"));

				Recipe recipe = new Recipe();
				recipe.setCookingMinutes(cookingMinutes);
				recipe.setDairyFree(dairyFree);
				recipe.setPreparationMinutes(preparationMinutes);
				recipe.setTitle(title);
				recipe.setInstructions(instructions);
				recipe.setVegan(vegan);
				recipe.setVegetarian(vegetarian);
				recipe.setSpoonacularScore(spoonacularScore);
				recipe.setServings(servings);
				recipe.setGlutenFree(glutenFree);
				recipe.setPricePerServing(pricePerServing);
				recipe.setReadyInMinutes(readyInMinutes);

				recipeRepo.getRecipes().add(recipe);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return recipeRepo.getRecipes();

	}

	public List<Recipe> getRecipes() {
		if (recipeRepo.getRecipes().size() == 0) {
			return getAllRecipeFromCsv();
		}
		return recipeRepo.getRecipes();
	}
}
