package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {

    static String name = TestUtils.getRandomValue() + "Mary";
    static String email = TestUtils.getRandomValue() + "Maryjohn@gmail.com";
    static String gender = "female";
    static String status = "active";
    static int userID;

    //==================Create a user and find the user ID========================//
    @Test
    public void verifyUserCreatedSuccessfully() {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 4eafe56bd20cc3d06cca1b4a0669425f4c8c3a6586977b8671351f020f9304b7")
                .when()
                .body(userPojo)
                .post("https://gorest.co.in/public/v2/users");
        response.then().log().all().statusCode(201);
        response.prettyPrint();
    }

    //=============================Find the new user by ID==========================//

    @Test
    public void verifyNewUserInfo() {

        Response response = given()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer 4eafe56bd20cc3d06cca1b4a0669425f4c8c3a6586977b8671351f020f9304b7")
                .queryParams("id", 5969617)
                .when()
                .get("/posts");
        response.then().statusCode(200);
        response.prettyPrint();
    }

//    // ======================Update the new user===============================//

    @Test
    public void verifyUserUpdateSuccessfully() {

        UserPojo userPojo = new UserPojo();
        String email = TestUtils.getRandomValue() + "disha123@gmail.com";
        userPojo.setEmail(email);

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 4eafe56bd20cc3d06cca1b4a0669425f4c8c3a6586977b8671351f020f9304b7")
                .pathParam("id", 5)
                .when()
                .body(userPojo)
                .when().patch("/public/v2/users/{id}");
        response.prettyPrint();

    }
    //    //======================Delete the new user===============================//
    @Test
    public void verifyUserDeleteSuccessfully() {
        UserPojo userPojo = new UserPojo();
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 4eafe56bd20cc3d06cca1b4a0669425f4c8c3a6586977b8671351f020f9304b7")
                .pathParam("id", 8)
                .when()
                .body(userPojo)
                .when().delete("/public/v2/users/{id}");
        response.prettyPrint();
    }
}