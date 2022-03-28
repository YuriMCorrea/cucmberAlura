package br.com.alura.leilao.acceptance;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class) // No JUnit 5 seria extends, mas por enquanto Cucmber so esta integrado com JUnit 4
@CucumberOptions(features = "classpath:features", tags = "@leilao")
public class LeilaoCucumberRunner {
	
	
	
}
