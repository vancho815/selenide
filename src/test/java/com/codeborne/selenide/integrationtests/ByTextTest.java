package com.codeborne.selenide.integrationtests;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.Thread.currentThread;
import static org.junit.Assert.assertEquals;

public class ByTextTest {
  @Before
  public void openTestPage() {
    open(currentThread().getContextClassLoader().getResource("page_with_selects_without_jquery.html"));
  }

  @Test
  public void userCanFindElementByText() {
    $(byText("Page without JQuery")).shouldHave(text("Page without JQuery"));
    $(byText("Dropdown list")).shouldHave(text("Dropdown list"));
    $(byText("@livemail.ru")).shouldHave(text("@livemail.ru"));
  }

  @Test
  public void spacesInTextAreIgnored() {
    $(byText("Lä Baskerville")).shouldHave(text("Lä Baskerville"));
    $(withText("Lä Baskerville")).shouldHave(text("Lä Baskerville"));
  }

  @Test
  public void canFindElementByTextInsideParentElement() {
    assertEquals(2, $$($("#multirowTable"), byText("Chack")).size());

    assertEquals(1, $$($("#multirowTable tr"), byText("Chack")).size());
    assertEquals("first_row", $("#multirowTable tr").find(byText("Chack")).getAttribute("class"));
  }

  @Test
  public void canFindElementContainingText() {
    $(withText("without")).shouldHave(text("Page without JQuery"));
    $(withText("Dropdown")).shouldHave(text("Dropdown list"));
    $(withText("@livemail.r")).shouldHave(text("@livemail.ru"));
  }

  @Test
  public void canFindElementContainingTextInsideParentElement() {
    assertEquals(2, $$($("#multirowTable"), withText("Cha")).size());

    assertEquals(1, $$($("#multirowTable tr"), withText("ack")).size());
    assertEquals("second_row", $("#multirowTable tr", 1).find(withText("hac")).getAttribute("class"));
  }

  @Test @Ignore
  public void canFindElementsByI18nText() {
    System.out.println($(By.xpath("//input[@value='draft']")));
    System.out.println($(By.xpath(".//*[normalize-space(text()) = 'Я тупица']")));
    $(byText("Я тупица")).shouldHave(text("Я тупица"));
    $(withText("Я туп")).shouldHave(text("Я тупица"));

    assertEquals(1, $$($("#radioButtons"), withText("I don't speak Russian")).size());

    assertEquals(3, $$($("#radioButtons"), withText("Я")).size());
    assertEquals(1, $$($("#radioButtons input"), withText("Я ")).size());
  }
}
