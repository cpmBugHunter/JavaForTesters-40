package ru.pft40.mantis.appmanager;

import org.openqa.selenium.By;
import ru.pft40.mantis.model.AccountData;

public class AccountHelper extends HelperBase {

    public AccountHelper(ApplicationManager appMngr) {
        super(appMngr);
    }

    public void create(AccountData account) {
        initCreation();
        fillCreationForm(account);
    }

    private void fillCreationForm(AccountData account) {
        type(By.name("username"), account.getUsername());
        type(By.name("realname"), account.getRealName());
        type(By.name("email"), account.getEmail());
        click(By.xpath("//input[@value=\"Create User\"]"));
    }

    private void initCreation() {
        click(By.xpath("//input[@value=\"Create New Account\"]"));
    }

    public void resetPassword(AccountData account) {
        initModification(account);
        click(By.xpath("//input[@value=\"Reset Password\"]"));
    }

    private void initModification(AccountData account) {
        wd.findElement(By.xpath(String.format("//a[contains(@href, 'manage_user_edit_page.php?user_id=%d')]",
                account.getId()))).click();
    }
}
