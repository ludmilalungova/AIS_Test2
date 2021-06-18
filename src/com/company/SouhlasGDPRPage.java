package com.company;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by liduska on 18.06.21.
 */
//Tady by se initializovala cela stranka, pro tento test sem si vyziskala pouze tlacitko a praci s nim

public class SouhlasGDPRPage extends PageObject {

    @FindBy(xpath = "//button[@id='souhlasGDPRForm:j_idt39']/span")
    private WebElement souhlasGDPRForm;

    public SouhlasGDPRPage(WebDriver driver) {
        super(driver);
    }

    public HlasenkySkodPage submit() {
        souhlasGDPRForm.click();
        return new HlasenkySkodPage(driver);
    }
}
