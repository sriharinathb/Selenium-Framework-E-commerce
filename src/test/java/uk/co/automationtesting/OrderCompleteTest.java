package uk.co.automationtesting;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.Hooks;
import pageObjects.Homepage;
import pageObjects.OrderFormDelivery;
import pageObjects.OrderFormPayment;
import pageObjects.OrderFormPersInfo;
import pageObjects.OrderFormShippingMethod;
import pageObjects.ShopContentPanel;
import pageObjects.ShopHomepage;
import pageObjects.ShopProductPage;
import pageObjects.ShoppingCart;

@Listeners(resources.Listeners.class)

public class OrderCompleteTest extends Hooks {
	private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String DOMAIN = "@example.com";
    private static final int EMAIL_LENGTH = 10; // Length of the local part of the email
	public OrderCompleteTest() throws IOException {
		super();
	}

	@Test
	public void endToEndTest() throws IOException, Exception {

		// creating an object of the automationtesting.co.uk webpage
		Homepage home = new Homepage();

		// handles cookie prompt
		home.getCookie().click();
		home.getToggle().click();
	home.getStoreLink().click();

		// home.getTestStoreLink().click();

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
		cPanel.getCheckoutBtn().click();

		// creating an object of the shopping cart page (all items selected)
		ShoppingCart cart = new ShoppingCart();
		cart.getHavePromo().click();
		cart.getPromoTextbox().sendKeys("20OFF");
		cart.getPromoAddBtn().click();
		cart.getProceedCheckoutBtn().click();

		// creating an object of the order personal information page
		OrderFormPersInfo pInfo = new OrderFormPersInfo();
		pInfo.getGenderMr().click();
		pInfo.getFirstNameField().sendKeys("John");
		pInfo.getLastnameField().sendKeys("Smith");
		String email=generateRandomEmail();
		pInfo.getEmailField().sendKeys(email);
		pInfo.getTermsConditionsCheckbox().click();
		pInfo.getContinueBtn().click();

		// creating an object of the order delivery info page
		OrderFormDelivery orderDelivery = new OrderFormDelivery();
		orderDelivery.getAddressField().sendKeys("123 Main Street");
		orderDelivery.getCityField().sendKeys("Houston");
		Select state = new Select(orderDelivery.getStateDropdown());
		state.selectByVisibleText("Texas");
		orderDelivery.getPostcodeField().sendKeys("77021");
		orderDelivery.getContinueBtn().click();

		// creating an object of the shipping method page
		OrderFormShippingMethod shipMethod = new OrderFormShippingMethod();
		shipMethod.getDeliveryMsgTextbox().sendKeys("If I am not in, please leave my delivery on my porch.");
		shipMethod.getContinueBtn().click();

		// creating an object of the payment options page
		OrderFormPayment orderPay = new OrderFormPayment();
		orderPay.getPayByCheckRadioBtn().click();
		orderPay.getTermsConditionsCheckbox().click();
		orderPay.getOrderBtn().click();
	}
	public static String generateRandomEmail() {
        StringBuilder localPart = new StringBuilder(EMAIL_LENGTH);
        Random random = new Random();

        // Generate random characters for the local part of the email
        for (int i = 0; i < EMAIL_LENGTH; i++) {
            localPart.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        // Combine local part with domain to form the email address
        return localPart.toString() + DOMAIN;
    }
}

