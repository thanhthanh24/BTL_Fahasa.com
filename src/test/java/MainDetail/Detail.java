package MainDetail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Detail {
    WebDriver webDriver;
    private static final String urlHome = "https://www.fahasa.com/";

    @FindBy(xpath = "//div[@class='icon_account_gray']")
    private WebElement txtLogin;
    @FindBy(xpath = "//input[@id='login_username']")
    private WebElement inputUser;
    @FindBy(xpath = "//input[@id='login_password']")
    private WebElement inputPass;
    @FindBy(xpath = "//button[@class='fhs-btn-login']")
    private WebElement buttonLogin;
    @FindBy(xpath = "//span[text()='Tài khoản']")
    private WebElement textAcc;

    public void openBrowser() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        PageFactory.initElements(webDriver, this);
        webDriver.get(urlHome);
        txtLogin.click();
    }
    //Thông tin đăng nhập
    public void inputInfoLogin(String username, String password){
        inputUser.sendKeys(username);
        inputPass.sendKeys(password);
        buttonLogin.click();

    }
    //Cập nhật thông tin
    public void updateInfo(){

    }
    //Kiểm tra giá
    public void checkPrice(){

    }
    //Kiểm tra sản phẩm
    public void checkProduct(){

    }



}
