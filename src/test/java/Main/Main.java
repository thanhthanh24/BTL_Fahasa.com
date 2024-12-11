package Main;

import MainDetail.Detail;
import MainDetail.Detail2;
import org.junit.Test;

public class Main {
    @Test
    public void updateInfo() throws InterruptedException {
        Detail2 detail = new Detail2();
        detail.openBrowser();
        detail.inputInfoLogin("0964639446", "Thanhtran123");
        detail.updateInfo();
        detail.closeBrower();
    }
    @Test
    public void checkFilterPrice() throws InterruptedException {
        Detail2 detail = new Detail2();
        detail.openBrowser();
        detail.inputInfoLogin("0964639446", "Thanhtran123");
        detail.checkFilterPrice();
        detail.closeBrower();
    }
    @Test
    public void checkProduct() throws InterruptedException {
        Detail2 detail = new Detail2();
        detail.openBrowser();
        detail.inputInfoLogin("0964639446", "Thanhtran123");
        detail.checkProduct();
        detail.closeBrower();
    }
    @Test
    public void checkProductAmount() throws InterruptedException {
        Detail2 detail = new Detail2();
        detail.openBrowser();
        detail.inputInfoLogin("0964639446", "Thanhtran123");
        detail.checkProduct();
        detail.checkProductAmount();;
        detail.closeBrower();
    }

    @Test
    public void addToCart() throws InterruptedException {
        Detail2 detail = new Detail2();
        detail.openBrowser();
        detail.inputInfoLogin("0964639446", "Thanhtran123");
        detail.checkProduct();
        detail.checkProductAmount();
        detail.addToCart();
        detail.closeBrower();
    }
    @Test
    public void checkCart() throws InterruptedException {
        Detail2 detail = new Detail2();
        detail.openBrowser();
        detail.inputInfoLogin("0964639446", "Thanhtran123");
        detail.checkCart();
        detail.closeBrower();
    }
    @Test
    public void verifyQuantity() throws InterruptedException {
        Detail2 detail = new Detail2();
        detail.openBrowser();
        detail.inputInfoLogin("0964639446", "Thanhtran123");
        detail.checkCart();
        detail.verifyQuantity();
        detail.closeBrower();
    }
    @Test
    public void checkCartMoney() throws InterruptedException {
        Detail2 detail = new Detail2();
        detail.openBrowser();
        detail.inputInfoLogin("0964639446", "Thanhtran123");
        detail.selectCheckbox();
        detail.closeBrower();
    }
    @Test
    public void deleteProductFromCart() throws InterruptedException {
        Detail2 detail = new Detail2();
        detail.openBrowser();
        detail.inputInfoLogin("0964639446", "Thanhtran123");
        detail.checkProduct();
        detail.checkProductAmount();
        detail.addToCart();
        detail.deleteProductFromCart();
        detail.closeBrower();
    }
}

