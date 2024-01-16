package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void start() {
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/users")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("size()", equalTo(20));
    }

    //2. Verify the if the name of id = 5914154 is equal to ”Dandapaani Agarwal”
    @Test
    public void test002() {
        response.body("find{it.id == 5914154}.name", equalTo("Dandapaani Agarwal"));
    }

    //3. Check the single ‘Name’ in the Array list (Aanjaneya Iyer)
    @Test
    public void test003() {
        response.body("name", hasItem("Aanjaneya Iyer"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Dandapaani Agarwal, Aanjaneya Iyer, Chandini Khatri )
    @Test
    public void test004() {
        response.body("name", hasItems("Dandapaani Agarwal", "Aanjaneya Iyer", "Chandini Khatri"));
    }

    //5. Verify the email of userid = 5914141 is equal “pilla_dandapaani_amb@goyette.test”
    @Test
    public void test005() {
        response.body("find{it.id == 5914141}.email", equalTo("pilla_dandapaani_amb@goyette.test"));
    }

    //6. Verify the status is “Active” of user name is “Dandapaani Agarwal”
    @Test
    public void test006() {
        response.body("find{it.status == 'active'}.name", equalTo("Dandapaani Agarwal"));
    }

    //7. Verify the Gender = male
    @Test
    public void test007() {
        response.body("find{it.name == 'Dinesh Mehrotra'}.gender", equalTo("male"));
    }
}
