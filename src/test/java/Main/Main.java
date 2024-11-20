package Main;

import MainDetail.Detail;
import org.junit.Test;

public class Main {
    @Test
    public void updateInfo() throws InterruptedException {
        Detail detail = new Detail();
        detail.openBrowser();
        detail.inputInfoLogin("0964639446","Thanhtran123");
        detail.updateInfo();
        detail.closeBrower();
    }
}
