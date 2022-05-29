package org.steps;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="C:\\Users\\Admin\\eclipse-workspace\\PizzaCucumber\\src\\test\\resources\\login.feature",glue="org.steps")
public class TestRunner {

}
