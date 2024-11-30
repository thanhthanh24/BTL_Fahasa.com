package MainDetail;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static java.lang.Double.parseDouble;

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

    @FindBy(id = "btn-save-account-info")
    private WebElement btnSave;

    @FindBy(xpath = "//div[@class='my-account user-detail']//li[@class='success-msg']")
    private WebElement textSaveSuccess;

    @FindBy(xpath = "//span[@class='icon_menu']")
    private WebElement icon;

    @FindBy(xpath = "(//li[@class='parent dropdown aligned-left']//span[@class='menu-title'])[1]")
    private WebElement menuSachTrongNuoc;

    @FindBy(xpath = "(//div[@class='ves-widget']//ul[@class='nav-links']//a)[1]")
    //@FindBy(xpath = "//a[@href='https://www.fahasa.com/sach-trong-nuoc/van-hoc-trong-nuoc/tieu-thuyet.html']")
    private WebElement menuTieuThuyet;

    @FindBy(xpath = "//dd[@class='even']//ol[@class='m-filter-css-checkboxes']/li/a")
    private List<WebElement> listPrice;

    @FindBy(xpath = "//li//span[@class='label']")
    private WebElement filterBy;

    @FindBy(xpath = "(//span[@class='price']//span)[1]")
    private WebElement firstProduct;

    @FindBy(xpath = "//a[@class='btn-remove']")
    private WebElement iconX;

    @FindBy(xpath = "//div[@class='icon_cart_gray']")
    private WebElement iconCart;

    @FindBy(xpath = "//div[@class='page-title-container']//h1")
    private WebElement verifyCart;

    @FindBy(xpath = "//span[@class='num-items-checkbox']")
    private WebElement totalQuantity;

    @FindBy(xpath = "(//div[@class='products clearfix'])[1]")
    private WebElement clickProductsAdd;

    @FindBy(xpath = "(//input[@class='input-text qty'])[1]")
    private WebElement quantityProducts;

    @FindBy(xpath = "//span[@class='fhs_icon_cart']")
    private WebElement cartAdd;

    @FindBy(xpath = "//a[@class='btn-add-qty']")
    private WebElement addFirstProducts;

    @FindBy(xpath = "(//span[@class='cart-price']//span)[1]")
    private WebElement detailMoneyProductFirst;

    @FindBy(xpath = "(//div[@class='cart-fhsItem-price']//span[@class='price'])[1]")
    private WebElement originalMoney;

    @FindBy(xpath = "(//div[@class='number-cart-page-right']//span[@class='price'])[2]")
    private WebElement totalMoney;

    @FindBy(id = "checkbox-all-products")
    private WebElement checkboxAll;

    public void openBrowser() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().window().maximize();
        WebDriverManager.chromedriver().setup();
        PageFactory.initElements(webDriver, this);
        webDriver.get(urlHome);
        txtLogin.click();
    }

    //Thông tin đăng nhập
    public void inputInfoLogin(String username, String password) {
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
        if (radiobuttonMale.isSelected() && !radiobuttonFeMale.isSelected()) {
            System.out.println("Giới tính Nam đang được chọn , tui chọn lại giới tính Nữ nhaaa");
            radiobuttonFeMale.click();
        } else if (radiobuttonFeMale.isSelected() && !radiobuttonMale.isSelected()) {
            System.out.println("Giới tính Nữ đang được chọn , tui chọn lại giới tính Nam nhaaa");
            radiobuttonMale.click();
        } else {
            //???KHum hỉu saoo không chọn dc radiobutton
            System.out.println("Trạng thái checkboxNam: " + radiobuttonMale.isSelected());
            System.out.println("Trạng thái checkboxNữ: " + radiobuttonFeMale.isSelected());
            System.out.println("Cả hai checkbox đều chưa được chọn.");
        }

        //Click "Lưu thay đổi"
        btnSave.click();

        //Verify message thành công "Thông tin tài khoản đã được lưu lại."
        Assert.assertEquals("Thông tin khum có khớp", "Thông tin tài khoản đã được lưu lại.", textSaveSuccess.getText());

        // Reload lại trang sau khi lưu
        webDriver.navigate().refresh();

        //Kiểm tra phần tử radio button đã được chọn chưa

        if (radiobuttonMale.isSelected()) {
            System.out.println("Đã chọn giời tính Nam");
            Assert.assertTrue(radiobuttonMale.isSelected());
        } else if (radiobuttonFeMale.isSelected()) {
            System.out.println("Đã chọn giới tính Nữ");
            Assert.assertTrue(radiobuttonFeMale.isSelected());
        }
    }

    //Kiểm tra giá
    public void checkFilterPrice() throws InterruptedException {
        Actions actions = new Actions(webDriver);
        Thread.sleep(3000);
        actions.moveToElement(icon).perform();
        Thread.sleep(3000);
        //actions.moveToElement(menuTieuThuyet).click().perform();

        actions.moveToElement(menuSachTrongNuoc);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", menuTieuThuyet);

        List<WebElement> priceList = webDriver.findElements(By.cssSelector("ol.m-filter-css-checkboxes a"));

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(1000));

        // Duyệt qua từng phần tử trong danh sách và click nếu chưa chọn
        for (WebElement priceItem : listPrice) {
            // Kiểm tra nếu phần tử chưa được chọn
            if (priceItem.getAttribute("class").contains("m-checkbox-unchecked")) {
                // In ra thông tin khoảng giá
                System.out.println("Chọn khoảng giá: " + priceItem.getAttribute("title"));
                // Chờ cho phần tử có thể click được và click vào phần tử
                wait.until(ExpectedConditions.elementToBeClickable(priceItem));
                priceItem.click();
                Thread.sleep(3000);
                String resultWithoutVND = priceItem.getAttribute("title").replace("đ", "").replace(",", ".").trim();
                System.out.println("Giá trị khoảng giá : " + resultWithoutVND);

                verifyPrice(resultWithoutVND);
                verifyProduct(resultWithoutVND);
                // verifyClickIconX();
            }
        }
    }

    public void verifyPrice(String checkboxFilter) {
        String priceText = filterBy.getText();
       /* System.out.println(priceText);
        String cleanedPrice = priceText.replace("Giá :", "").trim();
        System.out.println(cleanedPrice);*/
        System.out.println("Giá trị Lọc Theo là: '" + priceText + "'");

        // In ra mã ASCII của từng ký tự trong chuỗi
       /* for (int i = 0; i < priceText.length(); i++) {
            System.out.println("Ký tự: " + priceText.charAt(i) + " ASCII: " + (int) priceText.charAt(i));
        }*/
        String cleanedPrice = priceText.replace("Giá: ", "").trim();
        // System.out.println("Kết quả sau khi xử lý: '" + cleanedPrice + "'");
        if (checkboxFilter.contains("700.000 - trở lên")) {
            checkboxFilter = checkboxFilter.replace("700.000 - trở lên", "700.000 - 9.999.999");
        }
        Assert.assertEquals("Không trùng rùiiii", cleanedPrice, checkboxFilter);

    }

    //So sánh giá sản phẩm đầu tiên có nằm trong khoảng giá hay không ?
    public void verifyProduct(String verifyCheckboxFilter) {
        String productPriceText = firstProduct.getText();
        System.out.println("Giá của sản phẩm đầu tiên là: " + productPriceText);
        //Loại bỏ phần "đ" và chuyển đổi giá trị thành số
        String productPriceString = productPriceText.replace(" đ", "").replace(".", "").trim(); // Loại bỏ "đ" và dấu chấm
        double productPrice = parseDouble(productPriceString);

        //Lấy khoảng giá từ verifyCheckboxFilter
        //tách chuỗi thành 2 phần : cao và thấp
        //Ví dụ "300.000 - 500.000" sẽ được tách thành một mảng có hai phần: ["300.000", "500.000"]
        // priceRange[0] = "300.000"
        //priceRange[1] = "500.000".
        String[] priceRange = verifyCheckboxFilter.split(" - ");
        //gán cho giá trị  priceRange[0] là giá trị thấp và chuyển đổi dấu . thành dấu " "
        double lowerPrice = parseDouble(priceRange[0].replace(".", ""));
        //gán cho giá trị priceRange[1] là giá trị cao
        //Sử dụng toán tử 3 ngôi để cho trường hợp trở lên gán bằng giá trị vô hạn
        double upperPrice = (priceRange[1].equals("trở lên")) ? Double.MAX_VALUE : parseDouble(priceRange[1].replace(".", ""));

        //  Kiểm tra xem giá sản phẩm có nằm trong khoảng giá không?????
        if (productPrice >= lowerPrice && productPrice <= upperPrice) {
            System.out.println("Sản phẩm nằm trong khoảng giá.");
        } else {
            System.out.println("Sản phẩm không nằm trong khoảng giá.");
        }
    }

    public void verifyClickIconX() throws InterruptedException {
        //khong hỉu sao không click đc
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", icon);
        Thread.sleep(5000);
        Assert.assertFalse("Phần tử filterBy không nên tồn tại sau khi click.", filterBy.isDisplayed());
        Thread.sleep(5000);
        Assert.assertFalse("Vẫn còn tích chọn giá ", listPrice.isEmpty());
        System.out.println("Loại bỏ Lọc theo rùi đóoo");
        System.out.println("------------------------------------------");


    }

    // Màn hình giỏ hàng:
    public void checkCart() throws InterruptedException {
        //WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(1000));
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", iconCart);
        //iconCart.click();
        // WebElement cartTextElement = wait.until(ExpectedConditions.visibilityOf(verifyCart));
        //System.out.println(verifyCart.getText());
        //Vào màn hình giỏ hàng -> Verify vào màn hình thành công
        Assert.assertEquals("Không trùng rùiiii", "GIỎ HÀNG", verifyCart.getText());
        //Gọi tới hàm verify Số lượng sản phẩm trong giỏ hàng
        verifyQuantity();
        js.executeScript("arguments[0].click();", iconCart);
        //Gọi tới hàm verify số tiền của sản phẩm trong giỏ hàng
        verifyMoney();

    }

    public void verifyQuantity() throws InterruptedException {
        //System.out.println(totalQuantity.getText());
        String quantityText = totalQuantity.getText();
        System.out.println(quantityText);

        // Chuyển chuỗi số lượng thành số nguyên (int)
        int price = Integer.parseInt(quantityText);

        if (price < 5) {
            // System.out.println("Ít hơn 5 sản phẩm rồi ");
            int productsToAdd = 5 - price;
            System.out.println("Cần thêm " + productsToAdd + " sản phẩm để đạt 5 sản phẩm");

            Actions actions = new Actions(webDriver);
            Thread.sleep(3000);
            actions.moveToElement(icon).perform();
            Thread.sleep(3000);
            //actions.moveToElement(menuTieuThuyet).click().perform();

            actions.moveToElement(menuSachTrongNuoc);
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            js.executeScript("arguments[0].click();", menuTieuThuyet);
            clickProductsAdd.click();
            Thread.sleep(3000);
            // quantityProducts.clear();
            JavascriptExecutor js1 = (JavascriptExecutor) webDriver;
            js1.executeScript("arguments[0].value = '';", quantityProducts);
            Thread.sleep(3000);
            quantityProducts.sendKeys(String.valueOf(productsToAdd));
            Thread.sleep(3000);
            cartAdd.click();
        } else {
            System.out.println("Đủ 5 sản phầm gòi má ơiiii");
        }
    }

    public void verifyMoney() throws InterruptedException {
        // Lấy giá trị tiền trước khi thêm sản phẩm
        String initialPriceText = detailMoneyProductFirst.getText();
        // Chuyển giá trị tiền sang kiểu số
        double initialPrice = parseDouble(initialPriceText.replaceAll("[^0-9]", ""));
        System.out.println("Giá sản phẩm trước khi thêm sản phẩm là :" + initialPrice);

        // Click vào nút addFirstProducts để thêm sản phẩm
        Thread.sleep(3000);
        addFirstProducts.click();
        Thread.sleep(5000);

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(detailMoneyProductFirst));

        // Lấy giá trị tiền sau khi thêm sản phẩm
        String updatedPriceText = detailMoneyProductFirst.getText();
        double updatedPrice = parseDouble(updatedPriceText.replaceAll("[^0-9]", ""));

        System.out.println("Giá sản phẩm sau khi thêm sản phẩm là :" + updatedPrice);
        String originalMoneyText = originalMoney.getText();
        String originalWithoutVND = originalMoneyText.replace(" đ", "").trim(); // Loại bỏ "đ" và dấu chấm
        double productPrice = parseDouble(originalWithoutVND);
        System.out.println(productPrice);
        Thread.sleep(5000);

        double expectedPrice = initialPrice + productPrice * 1000;
        System.out.println("Giá trị sau khi thêm là : " + expectedPrice);

        Assert.assertEquals("Giá sản phẩm không tăng đúng", expectedPrice, updatedPrice, 0.01);
    }

    public void checkTotalCartMoney() throws InterruptedException {
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", iconCart);
        String totalMoneyText = totalMoney.getText();
        String totalMoneyWithoutVND = totalMoneyText.replace(" đ", "").trim();
        int resulttotalMoney = Integer.parseInt(totalMoneyWithoutVND);
        Assert.assertEquals("Không bằng không rùi", 0, resulttotalMoney);
        Assert.assertFalse("Úi đang check rùi", checkboxAll.isSelected());
        selectCheckbox();

    }

    public void selectCheckbox() throws InterruptedException {
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", iconCart);
        checkboxAll.click();
        Thread.sleep(8000);

        //Total Tổng Số Tiền (gồm VAT)
        String totalMoneyText = totalMoney.getText();
        //System.out.println(totalMoneyText);
        String totalMoneyWithoutVND = totalMoneyText.replace(" đ", "").replace(",", ".").trim();
        //System.out.println(totalMoneyWithoutVND);
        double resultTotalMoney = parseDouble(totalMoneyWithoutVND.replaceAll("[^0-9]", ""));

        List<WebElement> checkboxes = webDriver.findElements(By.xpath("//span[@class='cart-price']//span"));
        double total = 0.0;

        // Duyệt qua tất cả các checkbox
        for (WebElement checkbox : checkboxes) {
            String priceText = checkbox.getText().replace(" đ", "").replace(".", "");
            double price = parseDouble(priceText);
            total += price;
            System.out.println("Tổng giá trị các sản phẩm đã chọn: " + price + " đ");
            System.out.println("Tổng giá trị các sản phẩm đã chọn: " + total + " đ");
        }

        Assert.assertEquals("Không bằng nhau ", total, resultTotalMoney, 0.01);
    }

    public void closeBrower() {
        webDriver.quit();
    }

}
