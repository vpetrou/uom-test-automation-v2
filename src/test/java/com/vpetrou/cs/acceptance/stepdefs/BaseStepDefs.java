package com.vpetrou.cs.acceptance.stepdefs;

import com.vpetrou.cs.acceptance.utils.BaseTest;
import io.cucumber.java.Before;

public class BaseStepDefs extends BaseTest {

    @Before
    public void beforeTest() {
        setURL();
    }

}
