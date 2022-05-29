package org.pizzaRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="C:\\Users\\Admin\\eclipse-workspace\\PizzaCucumber\\src\\test\\resources\\pizza.feature", glue="org.pizzaSteps")
public class PizzaRunner {
	

}
