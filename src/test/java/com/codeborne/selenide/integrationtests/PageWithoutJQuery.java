package com.codeborne.selenide.integrationtests;

import com.codeborne.selenide.DOM;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static java.lang.Thread.currentThread;
import static org.junit.Assert.assertFalse;

public class PageWithoutJQuery {
  @Before
  public void openTestPageWithJQuery() {
    open(currentThread().getContextClassLoader().getResource("page_with_selects_without_jquery.html"));
  }

  @Test
  public void worksForPagesWithoutJQuery() {
    assertFalse(DOM.isJQueryAvailable());
  }
}
