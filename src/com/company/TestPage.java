package com.company;


import org.testng.annotations.Test;

public class TestPage extends FunctionalTest{

    @Test
    public void vyplnHlasenkuHappyPath(){

        //Odsouhlas DGPR
            driver.get("https://insure.koop.cz/hlasenky-web/faces/vstup_info.xhtml");

            SouhlasGDPRPage souhlasGDPRPage = new SouhlasGDPRPage(driver);
            souhlasGDPRPage.submit();

        //Vypln formular

            HlasenkySkodPage hlasenkySkodPage = new HlasenkySkodPage(driver);

            hlasenkySkodPage.FieldControl("Ludmila", "Lungova");

            hlasenkySkodPage.ChooseSkoduHlasi();

            hlasenkySkodPage.VyberZeSeznamuSkoduHlasi("POSKOZENY", 6);

            hlasenkySkodPage.VepisDatum("12122020");

            hlasenkySkodPage.VepisCisloPojistneSmlouvy("1234567890");

        //Odesli formular
            hlasenkySkodPage.OdesliFormular();

    }

}
