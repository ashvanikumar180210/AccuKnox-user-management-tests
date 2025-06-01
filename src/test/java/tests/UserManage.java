package tests;

import org.testng.annotations.Test;

import Pages.AddNewUserPage;
import Pages.DeleteUserPage;
import Pages.EditUserPage;
import Pages.LoginPage;
import Pages.SearchUserPage;
import utilities.DriverFactory;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


public class UserManage {

    WebDriver driver;
    LoginPage loginPage;
    SearchUserPage searchPage;
    EditUserPage editPage;
    DeleteUserPage deletePage;
    AddNewUserPage addingPage;

    String oldUsername = "demo122";
    String role = "Admin";
    String prefix = "p";
    String fullName = "Peter Mac Anderson";
    String status = "Enabled";
    String password="Secure@123";
    String confrimpasword="Secure@123";
    
    String newUsername = "demo122change";
    
    @BeforeClass
	@Parameters("browser")
    
    public void setUp(@Optional("firefox") String browser) throws Exception {
        driver = DriverFactory.initDriver(browser);
        loginPage = new LoginPage(driver);
        searchPage = new SearchUserPage(driver);
        editPage = new EditUserPage(driver);
        deletePage = new DeleteUserPage(driver);
        addingPage= new AddNewUserPage(driver);
    }

    @Test(priority = 1)
    public void loginTest() {
        loginPage.login("Admin", "admin123");
    }
    @Test(priority = 2)
    
    public void AddingUser() 
    {
    	addingPage.addUser(oldUsername, role, prefix, fullName, status,password, confrimpasword);
    }
    
    @Test(priority = 3)
    public void searchAndEditUser() throws InterruptedException {
        searchPage.navigateToAdminModule();
        searchPage.searchUser(oldUsername, role, prefix, fullName, status);
        editPage.editUser(oldUsername, newUsername);
    }

    @Test(priority = 4)
    public void searchAndDeleteEditedUser() {
        searchPage.navigateToAdminModule();
        searchPage.searchUser(newUsername, role, prefix, fullName, status);
        deletePage.deleteUser(newUsername);
    }

    
    @AfterClass

    public void tearDown() {
        driver.quit();
    }
}
