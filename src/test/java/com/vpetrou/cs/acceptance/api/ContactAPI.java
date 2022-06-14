package com.vpetrou.cs.acceptance.api;

import com.vpetrou.cs.acceptance.utils.BaseTest;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

public class ContactAPI extends BaseTest {

    public static String contactID;

    public void searchContactById(String email) {
        response = RestAssured
                .given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .when()
                .get(API_URL + "/contacts/" + contactID)
                .then()
                .extract()
                .response();

        response.then().statusCode(200);
        Assertions.assertTrue(response.asString().contains(email));
    }

    public void addNewContact(DataTable testData) {
        String name = getValue(testData, "Name");
        String email = getValue(testData, "Email");
        String phone = getValue(testData, "Phone");

        JSONObject contactObject = new JSONObject()
                .put("name", name)
                .put("email", email)
                .put("phone", phone);

        System.out.println(contactObject.toString());
        response = RestAssured
                .given()
                .baseUri(API_URL + "/contacts")
                .contentType(ContentType.JSON)
                .body(contactObject.toString())
                .post()
                .then()
                .extract()
                .response();
        response.then().statusCode(200);

        contactID = JsonPath.from(response.asString()).get("id").toString();
        System.out.println("ID of the New Contact: " + contactID);
    }

    public static void cleanup() {
        setURL();
        response = RestAssured
                .given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .when()
                .delete(API_URL + "/contacts/delete")
                .then()
                .extract()
                .response();

        response.then().statusCode(200);
    }

}
