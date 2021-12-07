package com.stepDefinitions;

import com.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class HomePageStep {

	@Given("User hit the URL {string}")
	public void user_hit_the_URL(String url) {
		HomePage.hitURL(url);
	}

	@And("User click on anthem button")
	public void User_click_on_anthem_button() {
		HomePage.clickOnAnthemButton();
	}

	@And("User click on member")
	public void User_click_on_memeber() {
		HomePage.clickOnMember();
	}
	
	@Given("User hit the URL {string} from Excel")
	public void user_hit_the_URL_From_Excel(String url) {
		HomePage.hitURLFromExcel(url);
	}
}
