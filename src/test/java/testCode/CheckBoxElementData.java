package testCode;

import org.testng.annotations.DataProvider;

public class CheckBoxElementData {

    @DataProvider(name = "testingCheckBoxElements")
    public static Object[][] checkBoxElementTest() {
        return new Object[][] {{"Water"},
                              {"Earth"},
                              {"Wind"},
                                {"Fire"}};
    }
}


