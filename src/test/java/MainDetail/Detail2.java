//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package MainDetail;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Double.parseDouble;
import static org.junit.Assert.assertEquals;

public class Detail2 {
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
    private WebElement menuTieuThuyet;
    @FindBy(xpath = "//dd[@class='even']//ol[@class='m-filter-css-checkboxes']/li/a")
    private List<WebElement> listPrice;
    @FindBy(xpath = "//li//span[@class='label']")
    private WebElement filterBy;
    @FindBy(xpath = "(//span[@class='product-image'])[1]")
    private WebElement firstProduct;
    @FindBy(xpath = "//h1[@class='fhs_name_product_desktop']")
    private WebElement firstProductName;
    String tenSanPham = "";

    @FindBy(xpath = "//p[@class='special-price']")
    private WebElement firstProductPrice;
    @FindBy(xpath = "//input[@name='qty']")
    private WebElement defaultAmount;
    @FindBy(xpath = "//a[@class='btn-subtract-qty']")
    private WebElement decreaseAmountButton;
    @FindBy(xpath = "//a[@class='btn-add-qty']")
    private WebElement increaseAmountButton;
    @FindBy(xpath = "//span[@class='price']")
    private WebElement firstProductDetailPrice;
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
    @FindBy(xpath = "//input[@id='checkbox-all-products']")
    private WebElement checkboxAll;

    public Detail2() {
    }

    public void openBrowser() {
        this.webDriver = new ChromeDriver();
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));
        this.webDriver.manage().window().maximize();
        WebDriverManager.chromedriver().setup();
        PageFactory.initElements(this.webDriver, this);
        this.webDriver.get("https://www.fahasa.com/");
        this.txtLogin.click();
    }

    public void inputInfoLogin(String username, String password) {
        this.inputUser.sendKeys(new CharSequence[]{username});
        this.inputPass.sendKeys(new CharSequence[]{password});
        this.buttonLogin.click();
    }

    public void updateInfo() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) this.webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", new Object[]{this.radiobuttonMale});
        if (this.radiobuttonMale.isSelected() && !this.radiobuttonFeMale.isSelected()) {
            System.out.println("Giới tính Nam đang được chọn , tui chọn lại giới tính Nữ nhaaa");
            this.radiobuttonFeMale.click();
        } else if (this.radiobuttonFeMale.isSelected() && !this.radiobuttonMale.isSelected()) {
            System.out.println("Giới tính Nữ đang được chọn , tui chọn lại giới tính Nam nhaaa");
            this.radiobuttonMale.click();
        } else {
            System.out.println("Trạng thái checkboxNam: " + this.radiobuttonMale.isSelected());
            System.out.println("Trạng thái checkboxNữ: " + this.radiobuttonFeMale.isSelected());
            System.out.println("Cả hai checkbox đều chưa được chọn.");
        }

        this.btnSave.click();
        Assert.assertEquals("Thông tin khum có khớp", "Thông tin tài khoản đã được lưu lại.", this.textSaveSuccess.getText());
        this.webDriver.navigate().refresh();
        if (this.radiobuttonMale.isSelected()) {
            System.out.println("Đã chọn giời tính Nam");
            Assert.assertTrue(this.radiobuttonMale.isSelected());
        } else if (this.radiobuttonFeMale.isSelected()) {
            System.out.println("Đã chọn giới tính Nữ");
            Assert.assertTrue(this.radiobuttonFeMale.isSelected());
        }

    }

    public void checkFilterPrice() throws InterruptedException {
        Actions actions = new Actions(this.webDriver);
        Thread.sleep(3000L);
        actions.moveToElement(this.icon).perform();
        Thread.sleep(3000L);
        actions.moveToElement(this.menuSachTrongNuoc);
        JavascriptExecutor js = (JavascriptExecutor) this.webDriver;
        js.executeScript("arguments[0].click();", new Object[]{this.menuTieuThuyet});
        List<WebElement> priceList = this.webDriver.findElements(By.cssSelector("ol.m-filter-css-checkboxes a"));
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(1000L));
        Iterator var5 = this.listPrice.iterator();

        while (var5.hasNext()) {
            WebElement priceItem = (WebElement) var5.next();
            if (priceItem.getAttribute("class").contains("m-checkbox-unchecked")) {
                System.out.println("Chọn khoảng giá: " + priceItem.getAttribute("title"));
                wait.until(ExpectedConditions.elementToBeClickable(priceItem));
                priceItem.click();
                Thread.sleep(3000L);
                String resultWithoutVND = priceItem.getAttribute("title").replace("đ", "").replace(",", ".").trim();
                System.out.println("Giá trị khoảng giá : " + resultWithoutVND);
                this.verifyPrice(resultWithoutVND);
                this.verifyProduct(resultWithoutVND);
            }
        }

    }

    public void checkProduct() throws InterruptedException {
        Actions actions = new Actions(this.webDriver);
        Thread.sleep(2000L);
        actions.moveToElement(icon);
        Thread.sleep(2000L);
        actions.moveToElement(menuSachTrongNuoc);

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", menuTieuThuyet);
        js.executeScript("arguments[0].click();", firstProduct);
        WebDriverWait wait2 = new WebDriverWait(webDriver, Duration.ofSeconds(5));

        tenSanPham = firstProductName.getAttribute("textContent").replaceAll("[\\n\\t]", "");
        String giaSanPham = firstProductDetailPrice.getAttribute("innerText");
        System.out.println("Tên sản phẩm đầu tiên là: " + tenSanPham);
        System.out.println("Sản phẩm có giá là: " + giaSanPham);
        //verify giá và tên sản phẩm đầu tiên
    }

    private void clickMultipleTimes(WebElement element, int times) {
        for (int i = 0; i < times; i++) {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            js.executeScript("arguments[0].click();", element);
            // Thread.sleep(100); // Nếu cần, có thể thêm thời gian chờ
        }
    }

    //Hàm để verify số lượng khi thêm - bớt sản phẩm trong giỏ hàng
    public void checkProductAmount() throws InterruptedException {
        int soLuongMacDinh = Integer.parseInt(defaultAmount.getAttribute("defaultValue"));
        int soLuongHienTai = Integer.parseInt(defaultAmount.getAttribute("value"));
        clickMultipleTimes(decreaseAmountButton, 1);
        assertEquals(soLuongHienTai, soLuongMacDinh);
        System.out.println("số lượng hiện tại = số lượng mặc định = 1");

        clickMultipleTimes(increaseAmountButton, 3);
        int soLuongHienTai2 = Integer.parseInt(defaultAmount.getAttribute("value"));
        assertEquals(soLuongHienTai + 3, soLuongHienTai2);
        System.out.println("số lượng hiện tại tăng lên = " + (1 + 3));

        clickMultipleTimes(decreaseAmountButton, 3);
        int soLuongHienTai3 = Integer.parseInt(defaultAmount.getAttribute("value"));
        assertEquals(soLuongHienTai2 - 3, soLuongHienTai3);
        System.out.println("số lượng hiện tại giảm đi = " + (1 + 3 - 3));
    }

    public void addToCart() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", cartAdd);
        js.executeScript("arguments[0].click();", iconCart);

        List<WebElement> firstProductInCart = webDriver.findElements(By.xpath("//h2[@class='product-name-full-text']"));

        // Lặp qua tất cả các phần tử và kiểm tra văn bản
        for (WebElement element : firstProductInCart) {
            String tenSanPhamDauTien = element.getAttribute("textContent");
            if (tenSanPham.equals(tenSanPhamDauTien)) {
                return;
            }
        }
        Assert.fail("them san pham ko thanh cong");
    }

    public void verifyPrice(String checkboxFilter) {
        String priceText = this.filterBy.getText();
        System.out.println("Giá trị Lọc Theo là: '" + priceText + "'");
        String cleanedPrice = priceText.replace("Giá: ", "").trim();
        if (checkboxFilter.contains("700.000 - trở lên")) {
            checkboxFilter = checkboxFilter.replace("700.000 - trở lên", "700.000 - 9.999.999");
        }

        Assert.assertEquals("Không trùng rùiiii", cleanedPrice, checkboxFilter);
    }

    public void verifyProduct(String verifyCheckboxFilter) {
        String productPriceText = this.firstProductPrice.getText();
        System.out.println("Giá của sản phẩm đầu tiên là: " + productPriceText);
        String productPriceString = productPriceText.replace(" đ", "").replace(".", "").trim();
        double productPrice = parseDouble(productPriceString);
        String[] priceRange = verifyCheckboxFilter.split(" - ");
        double lowerPrice = parseDouble(priceRange[0].replace(".", "").replace(".", "").trim());
        double upperPrice = priceRange[1].equals("trở lên") ? Double.MAX_VALUE : parseDouble(priceRange[1].replace(".", "").replace(".", "").trim());
        if (productPrice >= lowerPrice && productPrice <= upperPrice) {
            System.out.println("Sản phẩm nằm trong khoảng giá.");
        } else {
            System.out.println("Sản phẩm không nằm trong khoảng giá.");
        }

    }

    public void verifyClickIconX() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) this.webDriver;
        js.executeScript("arguments[0].click();", new Object[]{this.icon});
        Thread.sleep(5000L);
        Assert.assertFalse("Phần tử filterBy không nên tồn tại sau khi click.", this.filterBy.isDisplayed());
        Thread.sleep(5000L);
        Assert.assertFalse("Vẫn còn tích chọn giá ", this.listPrice.isEmpty());
        System.out.println("Loại bỏ Lọc theo rùi đóoo");
        System.out.println("------------------------------------------");
    }

    public void checkCart() throws InterruptedException {
        Thread.sleep(3000L);
        JavascriptExecutor js = (JavascriptExecutor) this.webDriver;
        js.executeScript("arguments[0].click();", new Object[]{this.iconCart});
        Assert.assertEquals("Không trùng rùiiii", "GIỎ HÀNG", this.verifyCart.getText());
        this.verifyQuantity();
        js.executeScript("arguments[0].click();", new Object[]{this.iconCart});
        this.verifyMoney();
    }

    public void verifyQuantity() throws InterruptedException {
        String quantityText = this.totalQuantity.getText();
        System.out.println(quantityText);
        int price = Integer.parseInt(quantityText);
        if (price < 5) {
            int productsToAdd = 5 - price;
            System.out.println("Cần thêm " + productsToAdd + " sản phẩm để đạt 5 sản phẩm");
            Actions actions = new Actions(this.webDriver);
            Thread.sleep(3000L);
            actions.moveToElement(this.icon).perform();
            Thread.sleep(3000L);
            actions.moveToElement(this.menuSachTrongNuoc);
            JavascriptExecutor js = (JavascriptExecutor) this.webDriver;
            js.executeScript("arguments[0].click();", new Object[]{this.menuTieuThuyet});
            this.clickProductsAdd.click();
            Thread.sleep(3000L);
            JavascriptExecutor js1 = (JavascriptExecutor) this.webDriver;
            js1.executeScript("arguments[0].value = '';", new Object[]{this.quantityProducts});
            Thread.sleep(3000L);
            this.quantityProducts.sendKeys(new CharSequence[]{String.valueOf(productsToAdd)});
            Thread.sleep(3000L);
            this.cartAdd.click();
        } else {
            System.out.println("Đủ 5 sản phầm gòi má ơiiii");
        }

    }

    public void verifyMoney() throws InterruptedException {
        String initialPriceText = this.detailMoneyProductFirst.getText();
        double initialPrice = parseDouble(initialPriceText.replaceAll("[^0-9]", ""));
        System.out.println("Giá sản phẩm trước khi thêm sản phẩm là :" + initialPrice);
        Thread.sleep(3000L);
        this.addFirstProducts.click();
        Thread.sleep(5000L);
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10L));
        wait.until(ExpectedConditions.visibilityOf(this.detailMoneyProductFirst));
        String updatedPriceText = this.detailMoneyProductFirst.getText();
        double updatedPrice = parseDouble(updatedPriceText.replaceAll("[^0-9]", ""));
        System.out.println("Giá sản phẩm sau khi thêm sản phẩm là :" + updatedPrice);
        String originalMoneyText = this.originalMoney.getText();
        String originalWithoutVND = originalMoneyText.replace(" đ", "").trim();
        double productPrice = parseDouble(originalWithoutVND);
        System.out.println(productPrice);
        Thread.sleep(5000L);
        double expectedPrice = initialPrice + productPrice * 1000.0;
        System.out.println("Giá trị sau khi thêm là : " + expectedPrice);
        Assert.assertEquals("Giá sản phẩm không tăng đúng", expectedPrice, updatedPrice, 0.01);
    }

    public void checkTotalCartMoney() throws InterruptedException {
        Thread.sleep(3000L);
        JavascriptExecutor js = (JavascriptExecutor) this.webDriver;
        js.executeScript("arguments[0].click();", new Object[]{this.iconCart});
        String totalMoneyText = this.totalMoney.getText();
        String totalMoneyWithoutVND = totalMoneyText.replace(" đ", "").trim();
        int resulttotalMoney = Integer.parseInt(totalMoneyWithoutVND);
        Assert.assertEquals("Không bằng không rùi ", 0L, (long) resulttotalMoney);
        Assert.assertFalse("Úi đang check rùi", this.checkboxAll.isSelected());
        this.selectCheckbox();
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
        String totalMoneyWithoutVND = totalMoneyText.replace(" đ", "").replace(",", ".").trim(); //System.out.println(totalMoneyWithoutVND);
        double resultTotalMoney = parseDouble(totalMoneyWithoutVND.replaceAll("[^0-9]", ""));
//Total Products
        List<WebElement> checkboxes = webDriver.findElements(By.xpath("//span[@class='cart-price']//span"));
        double total = 0.0;

        // Duyệt qua tất cả các checkbox
        for (WebElement checkbox : checkboxes) {
            String priceText = checkbox.getText().replace(" đ", "").replace(".", "");
            System.out.println(priceText);
            double price = parseDouble(priceText);
            total += price;
            System.out.println("Tổng giá trị các sản phẩm đã chọn: " + price + " đ");
            System.out.println("Tổng giá trị các sản phẩm đã chọn: " + total + " đ");
        }
        Assert.assertEquals("Không bằng nhau ", total, resultTotalMoney, 0.01);

    }


    public void deleteProductFromCart() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        while (true) {
            try {
                // Tìm phần tử cần nhấp
                WebElement element = webDriver.findElement(By.xpath("(//i[@class='fa fa-trash-o'])[1]"));

                // Nhấp vào phần tử
                js.executeScript("arguments[0].click();", element);

                // Thêm một chút chậm giữa các lần nhấp (tùy chọn)
                Thread.sleep(200);
                // 500ms
            } catch (Exception e) {
                // Nếu phần tử không còn tồn tại, thoát khỏi vòng lặp
                System.out.println("Phần tử không còn xuất hiện trên màn hình.");
                break;
            }
        }
        WebElement element = webDriver.findElement(By.xpath("//p[text()='Chưa có sản phẩm trong giỏ hàng của bạn.']"));
        String verifyMessage = element.getAttribute("textContent");
        System.out.println("Đã hiện lên text: " + verifyMessage);

        WebElement element2 = webDriver.findElement(By.xpath("//button[@title='Mua sắm ngay']"));
        js.executeScript("arguments[0].click();", element2);

        WebElement element3 = webDriver.findElement(By.xpath("//a[@title='Tới trang chủ']"));
        System.out.println("Đã tới trang chủ thành công");
    }

    public void closeBrower() {
        this.webDriver.quit();
    }
}
