$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/FeatureFiles/test.feature");
formatter.feature({
  "name": "Title of your feature",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "Title of your scenario outline",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@testui1"
    }
  ]
});
formatter.step({
  "name": "User hit the URL \"\u003cURL\u003e\" from Excel",
  "keyword": "Given "
});
formatter.step({
  "name": "User click on anthem button",
  "keyword": "And "
});
formatter.step({
  "name": "User click on member",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "URL"
      ]
    },
    {
      "cells": [
        "urlFromExcel"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Title of your scenario outline",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@testui1"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "User hit the URL \"urlFromExcel\" from Excel",
  "keyword": "Given "
});
formatter.match({
  "location": "HomePageStep.user_hit_the_URL_From_Excel(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User click on anthem button",
  "keyword": "And "
});
formatter.match({
  "location": "HomePageStep.User_click_on_anthem_button()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User click on member",
  "keyword": "And "
});
formatter.match({
  "location": "HomePageStep.User_click_on_memeber()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});