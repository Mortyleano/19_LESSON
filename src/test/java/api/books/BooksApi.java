package api.books;

import config.DataConfig;
import io.qameta.allure.Step;
import models.BookModel;
import models.IsbnDataModel;
import org.aeonbits.owner.ConfigFactory;

import java.util.List;

import static data.UserSessionData.getToken;
import static data.UserSessionData.getUserId;
import static io.restassured.RestAssured.given;
import static specs.AddBookSpec.addBookRequestSpec;
import static specs.AddBookSpec.addBookResponseSpec;

public class BooksApi {

    @Step("Добавляем книгу в коллекцию")
    public void addBookToCollections() {
        DataConfig isbnId = ConfigFactory.create(DataConfig.class, System.getProperties());
        List<IsbnDataModel> books = List.of(new IsbnDataModel(isbnId.getDefaultIsbnId()));
        BookModel bookData = new BookModel(getUserId(), books);

        given(addBookRequestSpec)
                .header("Authorization", "Bearer " + getToken())
                .body(bookData)
                .when()
                .post()
                .then()
                .spec(addBookResponseSpec)
                .extract().response();
    }
}