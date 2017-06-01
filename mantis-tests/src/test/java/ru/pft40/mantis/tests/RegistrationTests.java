package ru.pft40.mantis.tests;

import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {
    @Test
    public void testRegisterNewUser() {
        appMngr.registration().start("user1", "user1@mail.localhost");
    }
}
