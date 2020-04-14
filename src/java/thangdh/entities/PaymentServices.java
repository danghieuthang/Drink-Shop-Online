/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thangdh.entities;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import java.util.ArrayList;
import java.util.List;

public class PaymentServices {

    private static final String CLIENT_ID = "AUMwFobW3fubceuePyLah4xGlixPzyzpHLohCtqfWq9bLvOuqOSSZpv3JVBwHQPohkSvbUc3SyBtHFk2";
    private static final String CLIENT_SECRET = "EKSCqqehocqdWtPizyYVinqpPMUGlfaiEqrv1Hgv8AranTDpk0VWwf5G1z05DTO0OWcbknpdsqpZah2k";
    private static final String MODE = "sandbox";

    public String authorizePayment(Cart cart)
            throws PayPalRESTException {
        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(cart);
        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        Payment approvedPayment = requestPayment.create(apiContext);
        return getApprovalLink(approvedPayment);
    }

    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8084/HanaShop/checkout.jsp");
        redirectUrls.setReturnUrl("http://localhost:8084/HanaShop/ExecutePayment");
        return redirectUrls;
    }

    private Payer getPayerInformation() {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName("Hanashop")
                .setLastName("Buyer")
                .setEmail("buyer@hanashop.com");
        payer.setPayerInfo(payerInfo);
        return payer;
    }

    private List<Transaction> getTransactionInformation(Cart cart) {
        Details details = new Details();
        details.setShipping(String.format("%.2f", 0.0));
        details.setSubtotal(String.format("%.2f", cart.getTotalPrice()));
        details.setTax(String.format("%.2f", 0.0));

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(String.format("%.2f", cart.getTotalPrice()));
        amount.setDetails(details);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("Drink");

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();
        for (Object dto : cart.values()) {
            CartItem cartItemDTO = (CartItem) dto;
            Item item = new Item();
            item.setCurrency("USD");
            item.setName(cartItemDTO.getDrink().getName());
            item.setPrice(String.format("%.2f", cartItemDTO.getDrink().getPrice()));
            item.setTax("0");
            item.setQuantity(cartItemDTO.getQuantity() + "");
            items.add(item);
        }
        itemList.setItems(items);
        transaction.setItemList(itemList);
        List<Transaction> listTransaction = new ArrayList<>();
        listTransaction.add(transaction);
        return listTransaction;
    }

    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;
        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
                break;
            }
        }
        return approvalLink;
    }

    public Payment executePayment(String paymentId, String payerId)
            throws PayPalRESTException {
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        Payment payment = new Payment().setId(paymentId);

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        return payment.execute(apiContext, paymentExecution);
    }
}
