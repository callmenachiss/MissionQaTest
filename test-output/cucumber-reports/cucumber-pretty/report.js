$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/java/API-Test.feature");
formatter.feature({
  "line": 2,
  "name": "API test",
  "description": "",
  "id": "api-test",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@API"
    }
  ]
});
formatter.scenario({
  "comments": [
    {
      "line": 3,
      "value": "# Please visit https://reqres.in/"
    }
  ],
  "line": 5,
  "name": "Should see LIST USERS of all existing users",
  "description": "",
  "id": "api-test;should-see-list-users-of-all-existing-users",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 6,
  "name": "I get the default list of users for on 1st page",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "I get the list of all users within every page",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "I should see total users count equals the number of user ids",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 39
    }
  ],
  "location": "ApiSteps.getDefaultListOfUsers(int)"
});
formatter.result({
  "duration": 1590003167,
  "status": "passed"
});
formatter.match({
  "location": "ApiSteps.getUsersFromEveryPage()"
});
formatter.result({
  "duration": 396766458,
  "status": "passed"
});
formatter.match({
  "location": "ApiSteps.totalUsersShouldMatchCollectedIds()"
});
formatter.result({
  "duration": 151375,
  "status": "passed"
});
formatter.scenario({
  "line": 11,
  "name": "Should see SINGLE USER data",
  "description": "",
  "id": "api-test;should-see-single-user-data",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 12,
  "name": "I make a search for user 3",
  "keyword": "Given "
});
formatter.step({
  "line": 13,
  "name": "I should see the following user data",
  "rows": [
    {
      "cells": [
        "first_name",
        "email"
      ],
      "line": 14
    },
    {
      "cells": [
        "Emma",
        "emma.wong@reqres.in"
      ],
      "line": 15
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "3",
      "offset": 25
    }
  ],
  "location": "ApiSteps.searchForUser(String)"
});
formatter.result({
  "duration": 389943375,
  "status": "passed"
});
formatter.match({
  "location": "ApiSteps.userDataShouldMatch(DataTable)"
});
formatter.result({
  "duration": 28915542,
  "status": "passed"
});
formatter.scenario({
  "line": 18,
  "name": "Should see SINGLE USER NOT FOUND error code",
  "description": "",
  "id": "api-test;should-see-single-user-not-found-error-code",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 19,
  "name": "I make a search for user 55",
  "keyword": "Given "
});
formatter.step({
  "line": 20,
  "name": "I receive error code 404 in response",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "55",
      "offset": 25
    }
  ],
  "location": "ApiSteps.searchForUser(String)"
});
formatter.result({
  "duration": 380742958,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "404",
      "offset": 21
    }
  ],
  "location": "ApiSteps.responseCodeShouldBe(int)"
});
formatter.result({
  "duration": 451125,
  "status": "passed"
});
formatter.scenarioOutline({
  "line": 23,
  "name": "CREATE a user",
  "description": "",
  "id": "api-test;create-a-user",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 24,
  "name": "I create a user with following \u003cName\u003e \u003cJob\u003e",
  "keyword": "Given "
});
formatter.step({
  "line": 25,
  "name": "response should contain the following data",
  "rows": [
    {
      "cells": [
        "name",
        "job",
        "id",
        "createdAt"
      ],
      "line": 26
    }
  ],
  "keyword": "Then "
});
formatter.examples({
  "line": 28,
  "name": "",
  "description": "",
  "id": "api-test;create-a-user;",
  "rows": [
    {
      "cells": [
        "Name",
        "Job"
      ],
      "line": 29,
      "id": "api-test;create-a-user;;1"
    },
    {
      "cells": [
        "Peter",
        "Manager"
      ],
      "line": 30,
      "id": "api-test;create-a-user;;2"
    },
    {
      "cells": [
        "Liza",
        "Sales"
      ],
      "line": 31,
      "id": "api-test;create-a-user;;3"
    }
  ],
  "keyword": "Examples"
});
formatter.scenario({
  "line": 30,
  "name": "CREATE a user",
  "description": "",
  "id": "api-test;create-a-user;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@API"
    }
  ]
});
formatter.step({
  "line": 24,
  "name": "I create a user with following Peter Manager",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 25,
  "name": "response should contain the following data",
  "rows": [
    {
      "cells": [
        "name",
        "job",
        "id",
        "createdAt"
      ],
      "line": 26
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "Peter",
      "offset": 31
    },
    {
      "val": "Manager",
      "offset": 37
    }
  ],
  "location": "ApiSteps.createUser(String,String)"
});
formatter.result({
  "duration": 389661792,
  "status": "passed"
});
formatter.match({
  "location": "ApiSteps.createdResponseShouldContainExpectedData(DataTable)"
});
formatter.result({
  "duration": 41258042,
  "status": "passed"
});
formatter.scenario({
  "line": 31,
  "name": "CREATE a user",
  "description": "",
  "id": "api-test;create-a-user;;3",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@API"
    }
  ]
});
formatter.step({
  "line": 24,
  "name": "I create a user with following Liza Sales",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 25,
  "name": "response should contain the following data",
  "rows": [
    {
      "cells": [
        "name",
        "job",
        "id",
        "createdAt"
      ],
      "line": 26
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "Liza",
      "offset": 31
    },
    {
      "val": "Sales",
      "offset": 36
    }
  ],
  "location": "ApiSteps.createUser(String,String)"
});
formatter.result({
  "duration": 234362625,
  "status": "passed"
});
formatter.match({
  "location": "ApiSteps.createdResponseShouldContainExpectedData(DataTable)"
});
formatter.result({
  "duration": 34351583,
  "status": "passed"
});
formatter.scenario({
  "line": 34,
  "name": "LOGIN - SUCCESSFUL by a user",
  "description": "",
  "id": "api-test;login---successful-by-a-user",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 35,
  "name": "I login unsuccessfully with the following data",
  "rows": [
    {
      "cells": [
        "Email",
        "Password"
      ],
      "line": 36
    },
    {
      "cells": [
        "eve.holt@reqres.in",
        "cityslicka"
      ],
      "line": 37
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 38,
  "name": "I should get a response code of 200",
  "keyword": "Then "
});
formatter.match({
  "location": "ApiSteps.loginWithData(DataTable)"
});
formatter.result({
  "duration": 249222750,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 32
    }
  ],
  "location": "ApiSteps.loginResponseCodeShouldBe(int)"
});
formatter.result({
  "duration": 571916,
  "status": "passed"
});
formatter.scenario({
  "line": 40,
  "name": "LOGIN - UNSUCCESSFUL by a user",
  "description": "",
  "id": "api-test;login---unsuccessful-by-a-user",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 41,
  "name": "I login unsuccessfully with the following data",
  "rows": [
    {
      "cells": [
        "Email",
        "Password"
      ],
      "line": 42
    },
    {
      "cells": [
        "eve.holt@reqres.in",
        ""
      ],
      "line": 43
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 44,
  "name": "I should get a response code of 400",
  "keyword": "Then "
});
formatter.step({
  "line": 45,
  "name": "I should see the following response message:",
  "rows": [
    {
      "cells": [
        "\"error\": \"Missing password\""
      ],
      "line": 46
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "ApiSteps.loginWithData(DataTable)"
});
formatter.result({
  "duration": 233881708,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "400",
      "offset": 32
    }
  ],
  "location": "ApiSteps.loginResponseCodeShouldBe(int)"
});
formatter.result({
  "duration": 303875,
  "status": "passed"
});
formatter.match({
  "location": "ApiSteps.responseMessageShouldMatch(DataTable)"
});
formatter.result({
  "duration": 20085625,
  "status": "passed"
});
formatter.scenario({
  "line": 48,
  "name": "Should see the list of users with DELAYED RESPONSE",
  "description": "",
  "id": "api-test;should-see-the-list-of-users-with-delayed-response",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 49,
  "name": "I wait for the user list to load",
  "keyword": "Given "
});
formatter.step({
  "line": 50,
  "name": "I should see that every user has a unique id",
  "keyword": "Then "
});
formatter.match({
  "location": "ApiSteps.waitForUserListToLoad()"
});
formatter.result({
  "duration": 3228597167,
  "status": "passed"
});
formatter.match({
  "location": "ApiSteps.everyUserShouldHaveUniqueId()"
});
formatter.result({
  "duration": 10043250,
  "status": "passed"
});
formatter.uri("src/test/java/UI-Test.feature");
formatter.feature({
  "line": 2,
  "name": "Checkout items in the basket",
  "description": "Please use home page of https://www.saucedemo.com/",
  "id": "checkout-items-in-the-basket",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@UI"
    }
  ]
});
formatter.before({
  "duration": 1771164583,
  "status": "passed"
});
formatter.scenario({
  "line": 5,
  "name": "Check item total cost and tax",
  "description": "",
  "id": "checkout-items-in-the-basket;check-item-total-cost-and-tax",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 6,
  "name": "I am on the home page",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "I login in with the following details",
  "rows": [
    {
      "cells": [
        "userName",
        "Password"
      ],
      "line": 8
    },
    {
      "cells": [
        "standard_user",
        "secret_sauce"
      ],
      "line": 9
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "I add the following items to the basket",
  "rows": [
    {
      "cells": [
        "Sauce Labs Backpack"
      ],
      "line": 12
    },
    {
      "cells": [
        "Sauce Labs Fleece Jacket"
      ],
      "line": 13
    },
    {
      "cells": [
        "Sauce Labs Bolt T-Shirt"
      ],
      "line": 14
    },
    {
      "cells": [
        "Sauce Labs Onesie"
      ],
      "line": 15
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 17,
  "name": "I  should see 4 items added to the shopping cart",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "I click on the shopping cart",
  "keyword": "And "
});
formatter.step({
  "line": 19,
  "name": "I verify that the QTY count for each item should be 1",
  "keyword": "And "
});
formatter.step({
  "line": 20,
  "name": "I remove the following item:",
  "rows": [
    {
      "cells": [
        "Sauce Labs Fleece Jacket"
      ],
      "line": 21
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 22,
  "name": "I  should see 3 items added to the shopping cart",
  "keyword": "And "
});
formatter.step({
  "line": 23,
  "name": "I click on the CHECKOUT button",
  "keyword": "And "
});
formatter.step({
  "line": 24,
  "name": "I type \"FirstName\" for First Name",
  "keyword": "And "
});
formatter.step({
  "line": 25,
  "name": "I type \"LastName\" for Last Name",
  "keyword": "And "
});
formatter.step({
  "line": 26,
  "name": "I type \"EC1A 9JU\" for ZIP/Postal Code",
  "keyword": "And "
});
formatter.step({
  "line": 28,
  "name": "I click on the CONTINUE button",
  "keyword": "When "
});
formatter.step({
  "line": 29,
  "name": "Item total will be equal to the total of items on the list",
  "keyword": "Then "
});
formatter.step({
  "line": 30,
  "name": "a Tax rate of 8 % is applied to the total",
  "keyword": "And "
});
formatter.match({
  "location": "UiSteps.openHomePage()"
});
formatter.result({
  "duration": 490932042,
  "status": "passed"
});
formatter.match({
  "location": "UiSteps.loginWithDetails(DataTable)"
});
formatter.result({
  "duration": 548188625,
  "status": "passed"
});
formatter.match({
  "location": "UiSteps.addItemsToBasket(DataTable)"
});
formatter.result({
  "duration": 20617345542,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "4",
      "offset": 14
    }
  ],
  "location": "UiSteps.shoppingCartCountShouldBe(int)"
});
formatter.result({
  "duration": 8680084,
  "status": "passed"
});
formatter.match({
  "location": "UiSteps.openShoppingCart()"
});
formatter.result({
  "duration": 55446333,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 52
    }
  ],
  "location": "UiSteps.quantityForEachItemShouldBe(int)"
});
formatter.result({
  "duration": 94661250,
  "status": "passed"
});
formatter.match({
  "location": "UiSteps.removeItems(DataTable)"
});
formatter.result({
  "duration": 95172958,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "3",
      "offset": 14
    }
  ],
  "location": "UiSteps.shoppingCartCountShouldBe(int)"
});
formatter.result({
  "duration": 9858250,
  "status": "passed"
});
formatter.match({
  "location": "UiSteps.clickCheckout()"
});
formatter.result({
  "duration": 49823542,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "FirstName",
      "offset": 8
    }
  ],
  "location": "UiSteps.typeFirstName(String)"
});
formatter.result({
  "duration": 86813375,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "LastName",
      "offset": 8
    }
  ],
  "location": "UiSteps.typeLastName(String)"
});
formatter.result({
  "duration": 85283250,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "EC1A 9JU",
      "offset": 8
    }
  ],
  "location": "UiSteps.typePostalCode(String)"
});
formatter.result({
  "duration": 87518583,
  "status": "passed"
});
formatter.match({
  "location": "UiSteps.continueToOverview()"
});
formatter.result({
  "duration": 67031541,
  "status": "passed"
});
formatter.match({
  "location": "UiSteps.itemTotalShouldMatchListedItems()"
});
formatter.result({
  "duration": 92125666,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "8",
      "offset": 14
    }
  ],
  "location": "UiSteps.taxRateShouldBeApplied(int)"
});
formatter.result({
  "duration": 51234000,
  "status": "passed"
});
formatter.after({
  "duration": 13569695334,
  "status": "passed"
});
});