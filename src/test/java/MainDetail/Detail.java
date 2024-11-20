package MainDetail;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

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

    @FindBy(xpath = "(//span[@class='radiomark'])[1]")
    private WebElement radiobuttonMale;
    @FindBy(xpath = "(//span[@class='radiomark'])[2]")
    private WebElement radiobuttonFeMale;
    @FindBy(id ="btn-save-account-info")
    private WebElement btnSave;
    @FindBy(xpath = "//div[@class='my-account user-detail']//li[@class='success-msg']")
    private WebElement textSaveSuccess;




    public void openBrowser() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
    /* Cập nhập thông tin cá nhân:
+ Đổi giới tính nếu đang là Nam thì sẽ chọn lại là Nữ và ngược lại.
+ Click "Lưu thay đổi"
+ Verify message thành công "Thông tin tài khoản đã được lưu lại."
+ Reload trang check xem data thực sự được cập nhật chưa
*/
    //Cập nhật thông tin
    public void updateInfo() throws InterruptedException {



      /*  JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0, 1000)");*/



        // Cuộn đến phần tử đó
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", radiobuttonMale);
       
        // Đổi giới tính nếu đang là Nam thì sẽ chọn lại là Nữ và ngược lại.
        if(radiobuttonMale.isSelected() && !radiobuttonFeMale.isSelected()){
            System.out.println("Giới tính Nam đang được chọn , tui chọn lại giới tính Nữ nhaaa");
            radiobuttonFeMale.click();
        }else if(radiobuttonFeMale.isSelected() && !radiobuttonMale.isSelected()) {
            System.out.println("Giới tính Nữ đang được chọn , tui chọn lại giới tính Nam nhaaa");
            radiobuttonMale.click();
        }else {
            //???KHum hỉu saoo không chọn dc radiobutton
            System.out.println("Trạng thái checkboxNam: " + radiobuttonMale.isSelected());
            System.out.println("Trạng thái checkboxNữ: " + radiobuttonFeMale.isSelected());
            System.out.println("Cả hai checkbox đều chưa được chọn.");
        }
        //Click "Lưu thay đổi"
        btnSave.click();

        //Verify message thành công "Thông tin tài khoản đã được lưu lại."
        Assert.assertEquals("Thông tin khum có khớp","Thông tin tài khoản đã được lưu lại.",textSaveSuccess.getText());

        // Reload lại trang sau khi lưu
        webDriver.navigate().refresh();

        //Kiểm tra phần tử radio button đã được chọn chưa

        if(radiobuttonMale.isSelected()){
            System.out.println("Đã chọn giời tính Nam");
            Assert.assertTrue(radiobuttonMale.isSelected());
        }else if(radiobuttonFeMale.isSelected()){
            System.out.println("Đã chọn giới tính Nữ");
            Assert.assertTrue(radiobuttonFeMale.isSelected());
        }


    }
    //Kiểm tra giá
    public void checkFilterPrice(){

    }
    //Kiểm tra sản phẩm
    public void checkProduct(){

    }
    public void closeBrower() {
        webDriver.quit();
    }



}
