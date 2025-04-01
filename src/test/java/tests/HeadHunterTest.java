package tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import headhunter_objects.VacanciesList;
import headhunter_objects.Vacancy;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class HeadHunterTest {

    @Test
    public void qaSearchTest() {
        String body = given()
                .when()
                .log().all()
                .get("https://api.hh.ru/vacancies?text=QA")
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().asString();

        System.out.println("***BODY***");

        System.out.println(body);

        System.out.println("***OBJECT***");

        //десериализация
        VacanciesList vacanciesList = new Gson().fromJson(body, VacanciesList.class);
        System.out.println(vacanciesList);
//        Assert.assertEquals(vacancySearchPage.getFirstVacancy(), vacanciesList.getItems().get(0).getName());
    }

    @Test
    public void qaSearchTest1() {
        String body = given()
                .when()
                .log().all()
                .get("https://api.hh.ru/vacancies?text=QA")
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().asString();

        System.out.println("***BODY***");

        System.out.println(body);

        System.out.println("***OBJECT***");

        Vacancy vacancy1 = new Vacancy();
        vacancy1.setName("Frontend Developer");
        vacancy1.setAlternateUrl("urlFront");
        vacancy1.setAdditionalInfo("info");

        Vacancy vacancy2 = new Vacancy();
        vacancy2.setName("Backend Developer");
        vacancy2.setAlternateUrl("urlBAck");

        VacanciesList vacanciesList = new VacanciesList();
        ArrayList<Vacancy> vacancies = new ArrayList<>();
        vacancies.add(vacancy2);
        vacancies.add(vacancy1);
        vacanciesList.setItems(vacancies);

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        String json = gson.toJson(vacanciesList);
        System.out.println(json);
    }
}