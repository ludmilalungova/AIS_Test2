package com.company;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liduska on 18.06.21.
 */
/*Tady by se initializovala cela stranka, pro ucely testu jsem pracovala pouze s potrebnymi poli,
  bylo by potreba otestovat kompletni zobrazeni vcetne labelu, plus dalsi odkazy
  Bohuzel se mi nepodarila spravne implementovat metody na overeni,
  ze jsou pole napred prazdna a pote spravne vyplnena a obsanuji to co maji
*/

public class HlasenkySkodPage extends PageObject {

    @FindBy(xpath = "//label[@id='vstupInfo:PanelVstupniInfo:VI_HLASIL:inpVI_HLASIL_label']")
    private WebElement skoduHlasiComboBox;

    @FindBy(xpath = "//*[@id=\"vstupInfo:PanelVstupniInfo:VI_HLASIL:inpVI_HLASIL_input\"]/option")
    private List<WebElement> skoduHlasiSeznam;

    @FindBy(xpath = "//input[@id='vstupInfo:PanelVstupniInfo:VI_JMENO:inpVI_JMENO']")
    private WebElement jmenoField;

    @FindBy(xpath = "//input[@id='vstupInfo:PanelVstupniInfo:VI_PRIJMENI:inpVI_PRIJMENI']")
    private WebElement prijmeniField;

    @FindBy(xpath = "//input[@id='vstupInfo:PanelVstupniInfo:VI_DATUM_VZNIKU_NEBO_ZJISTENI_SKODY:inpVI_DATUM_VZNIKU_NEBO_ZJISTENI_SKODY_input']")
    private WebElement datumVznikuSkodyField;

    @FindBy(xpath = "//span[@id='vstupInfo:PanelVstupniInfo:VI_DATUM_VZNIKU_NEBO_ZJISTENI_SKODY:inpVI_DATUM_VZNIKU_NEBO_ZJISTENI_SKODY']/button/span")
    private WebElement datumVznikuSkodyCalendar;

    @FindBy(xpath = "//input[@id='vstupInfo:PanelVstupniInfo:VI_CPS:inpVI_CPS']")
    private WebElement cisloPojistneSmlouvyField;

    @FindBy(xpath = "//button[@id='vstupInfo:j_idt197']/span")
    private WebElement potvrditButton;

    public HlasenkySkodPage(WebDriver driver) {
        super(driver);
    }

    public void OdesliFormular() {
        potvrditButton.isEnabled();
        potvrditButton.click();
    }

    public void VepisCisloPojistneSmlouvy(String cislo) {
        cisloPojistneSmlouvyField.isEnabled();
        cisloPojistneSmlouvyField.sendKeys(cislo);
        //tady by to chtelo testy na min/max size pole, zadani ruznych formatu napr. se znaky, pismeny...
        //plus jeste dopracovat variantu rodneho cisla
    }

    public void VepisDatum(String datum) {
        datumVznikuSkodyField.isEnabled();
        datumVznikuSkodyField.click();
        datumVznikuSkodyField.sendKeys(datum);
        //Tohle neni dokoncene, vepsani datumu ve forme stringu neni dobry napad, bylo by treba si pohrat s formaty
        //Dalsi cast je datepicker, ktery uz take nestiham zpracovat, mam-li to odevzdat v patek
    }

    public void ChooseSkoduHlasi() {
        skoduHlasiComboBox.isEnabled();
        skoduHlasiComboBox.click();
    }

    public void VyberZeSeznamuSkoduHlasi(String skoduHlasi, int index) {
        ArrayList<String> arrayOfSkoduHlasi = ziskejVyber(skoduHlasiSeznam);
        //idealni jeste otestovat, ze arrayOfSkoduHlasi obsahuje ocekavane hodnoty - nestihla jsem
        skoduHlasiSeznam.get(index).click();
        /* zkontrolovat jestli byl combobox vybran
           vybirani podle indexu, jsem zvolila jako prozatimni rychle reseni, lepe by bylo vyhledavat podle ziskane value,
           jeste lepe random vybirani z listu, kde by byli ulozene values s propojenymi indexy*/
    }

    public void FieldControl(String jmeno, String prijmeni) {
        KontrolaPole(jmenoField, jmeno);
        KontrolaPole(prijmeniField, prijmeni);
    }

    //samostatna metoda vhodna pro snadne pouziti v pripade pridani novych comboboxu
    private ArrayList<String> ziskejVyber(List<WebElement> list) {
        ArrayList<String> array = new ArrayList<>();
        for (WebElement polozka : list) {
            String vypisuje = polozka.getAttribute("value");
            array.add(vypisuje);
            //System.out.println(vypisuje);
        }
        return array;
    }

    //samostatna metoda vhodna pro snadne pouziti v pripade pridani novych poli
    private void KontrolaPole(WebElement pole, String value) {
        String textInsideInputBox;
        pole.isEnabled();
        textInsideInputBox = pole.getAttribute("value");
        if (textInsideInputBox.isEmpty()) {
            pole.sendKeys(value);
        } else {
            System.out.println("Pole bylo predvyplnene");
        }
    }
}
