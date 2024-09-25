package tests.books;

import api.books.BooksApi;
import config.DataConfig;
import helpers.WithLogin;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;
import tests.TestBase;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayName("Проверяет удаление книги из коллекции профиля")
public class CheckDeleteBookFromCollectionsTest extends TestBase {

    private static final DataConfig DATA_CONFIG = ConfigFactory.create(DataConfig.class, System.getProperties());

    @Test
    @Tag("smoke")
    @WithLogin
    @DisplayName("Проверяем, что книга удаляется из коллекции профиля")
    void checkDeleteBookFromCollections() {
        new BooksApi().addBookToCollections();
        ProfilePage profilePage = new ProfilePage();
        profilePage.openProfilePage();
        step("Проверяем отображение имени в профиле и книги в списке коллекции", () ->
                assertSoftly(softly -> {
                    assertThat(profilePage.getUserName())
                            .contains(DATA_CONFIG.getUserName())
                            .as("Получили невалидное имя в профиле пользователя");

                    assertThat(profilePage.getAuthorBook())
                            .contains(DATA_CONFIG.getAuthorBook())
                            .as("Получили невалидное имя книги автора");
                })
        );

        profilePage.deleteBook();
        step("Проверяем отсутствие книги в коллекции после удаления её из списка", () ->
                assertThat(profilePage.getAuthorBook())
                        .isNotEqualTo(DATA_CONFIG.getAuthorBook())
                        .as("Книга не удалилась из списка коллекции")
        );
    }
}