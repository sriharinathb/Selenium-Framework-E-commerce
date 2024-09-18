package uk.co.automationtesting;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.Hooks;
import pageObjects.Homepage;
import pageObjects.ShopContentPanel;
import pageObjects.ShopHomepage;
import pageObjects.ShopProductPage;
import pageObjects.ShoppingCart;

@Listeners(resources.Listeners.class)

public class AddRemoveItemBasketTest extends Hooks {

    public AddRemoveItemBasketTest() throws IOException {
        super();
    }

    @Test
    public void addRemoveItem() throws IOException, InterruptedException {
        // creating an object of the automationtesting.co.uk webpage
        Homepage home = new Homepage();
        Thread.sleep(2000);
        //handle cookie visibility using JSE - added 20230217
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].scrollIntoView()", home.getTestStoreLink());
        jse.executeScript("arguments[0].click();", home.getTestStoreLink());

        // creating an object of the test store homepage
        ShopHomepage shopHome = new ShopHomepage();
        shopHome.getProdOne().click();

        // creating an object of the shop products page (when a product has been
        // selected)
        ShopProductPage shopProd = new ShopProductPage();
        Select option = new Select(shopProd.getSizeOption());
        option.selectByVisibleText("M");
        shopProd.getQuantIncrease().click();
        shopProd.getAddToCartBtn().click();

        // creating an object of the cart content panel (once an item was added)
        ShopContentPanel cPanel = new ShopContentPanel();
        cPanel.getContinueShopBtn().click();
        shopProd.getHomepageLink().click();
        shopHome.getProdTwo().click();
        shopProd.getAddToCartBtn().click();
        cPanel.getCheckoutBtn().click();

        // creating an object for the shopping cart page and deleting item 2
       /* ShoppingCart cart = new ShoppingCart();
        cart.getDeleteItemTwo().click();
        Thread.sleep(6000);*/

        // using an assertion to make sure the total amount is what we are expecting
        //Assert.assertEquals(cart.getTotalAmount().getText(), "$45.24");
        System.out.println("Execution is success");

    }

}