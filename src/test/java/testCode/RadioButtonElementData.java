package testCode;

import org.testng.annotations.DataProvider;

public class RadioButtonElementData {

    @DataProvider(name = "testingRadioButtonsElements")
    public static Object[][] radioButtonElementTest() {
        return new Object[][] {{"Gold"},
                {"Silver"},
                {"Bronze"},
                {"Selen"}};
    }

}
