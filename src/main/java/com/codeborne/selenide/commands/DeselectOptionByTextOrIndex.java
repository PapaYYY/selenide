package com.codeborne.selenide.commands;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.impl.WebElementSource;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;

import static com.codeborne.selenide.Condition.exist;

public class DeselectOptionByTextOrIndex implements Command<Void> {
  @Override
  public Void execute(SelenideElement proxy, WebElementSource selectField, Object[] args) {
    if (args[0] instanceof String[]) {
      deselectOptionsByTexts(selectField, (String[]) args[0]);
    }
    else if (args[0] instanceof int[]) {
      deselectOptionsByIndexes(selectField, (int[]) args[0]);
    }
    return null;
  }

  private void deselectOptionsByTexts(WebElementSource selectField, String[] texts) {
    Select select = new Select(selectField.getWebElement());
    for (String text : texts) {
      try {
        select.deselectByVisibleText(text);
      }
      catch (NoSuchElementException e) {
        throw new ElementNotFound(selectField.driver(), selectField.getSearchCriteria() + "/option[text:" + text + ']', exist, e);
      }
    }
  }

  private void deselectOptionsByIndexes(WebElementSource selectField, int[] indexes) {
    Select select = new Select(selectField.getWebElement());
    for (int index : indexes) {
      try {
        select.deselectByIndex(index);
      }
      catch (NoSuchElementException e) {
        throw new ElementNotFound(selectField.driver(), selectField.getSearchCriteria() + "/option[index:" + index + ']', exist, e);
      }
    }
  }
}
