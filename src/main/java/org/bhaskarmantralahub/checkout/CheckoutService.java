package org.bhaskarmantralahub.checkout;

import org.apache.commons.lang3.tuple.Pair;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.bhaskarmantralahub.practice.WaitUtil.stopWatch;

public class CheckoutService {

    private PriceValidatorService priceValidatorService = new PriceValidatorService();

    public Pair<Pair<CheckoutResponse, String>, List<ItemInCart>> checkout(Cart cart, boolean isParallelStream) {

        Stream<ItemInCart> stream = cart.getItemsInCart()
                .stream();

        if (isParallelStream) stream.parallel();

        List<ItemInCart> expiredItemsInCart = stream
                .peek(itemInCart -> itemInCart.setExpired(!priceValidatorService.isPriceValid(itemInCart))).filter(ItemInCart::isExpired).toList();

        if (!expiredItemsInCart.isEmpty()) {
            return Pair.of(Pair.of(CheckoutResponse.FAILURE, "Some of the items in the cart are expired"), expiredItemsInCart);
        }

        return Pair.of(Pair.of(CheckoutResponse.SUCCESS, "Checked out successfully"), cart.getItemsInCart());
    }

    @DataProvider(name = "data-provider")
    public Object[][] dpMethod() {
        return new Object[][]{{1, 211}, {10, 21}};
    }

    @DataProvider(name = "parallel-stream")
    public Object[][] parallelStream() {
        return new Object[][]{{true}, {false}};
    }

    @Test(dataProvider = "data-provider")
    public void testSuccessfulCheckout(int id1, int id2) {
        stopWatch.reset();
        stopWatch.start();
        List<ItemInCart> itemInCartList = List.of(ItemInCart.builder().id(id1).name("item1").price(100).quantity(1).build(),
                ItemInCart.builder().id(id2).name("item10").price(70).quantity(5).build());

        Pair<Pair<CheckoutResponse, String>, List<ItemInCart>> checkoutResponse = this.checkout(Cart.builder().sessionId(1).itemsInCart(itemInCartList).build(), false);

        Pair<CheckoutResponse, String> response = checkoutResponse.getLeft();
        stopWatch.stop();
        System.out.println("Response time in ms is " + stopWatch.getTime());
        if (CheckoutResponse.FAILURE == response.getLeft()) {
            throw new RuntimeException(response.getValue() + " - " + checkoutResponse.getRight().toString());
        }
        System.out.println(checkoutResponse.getRight().toString() + " items checkout response - " + response.getLeft().name());
    }

    @Test(dataProvider = "parallel-stream")
    public void testSuccessfulCheckoutWithParallelStream(boolean isParallelStream) {
        stopWatch.reset();
        stopWatch.start();
        List<ItemInCart> itemInCartList = List.of(ItemInCart.builder().id(10).name("item1").price(100).quantity(1).build(),
                ItemInCart.builder().id(12).name("item10").price(70).quantity(5).build());

        Pair<Pair<CheckoutResponse, String>, List<ItemInCart>> checkoutResponse = this.checkout(Cart.builder().sessionId(1).itemsInCart(itemInCartList).build(), isParallelStream);

        Pair<CheckoutResponse, String> response = checkoutResponse.getLeft();
        stopWatch.stop();
        System.out.println("Response time in ms is " + stopWatch.getTime() + " with parallel stream " + isParallelStream);
        if (CheckoutResponse.FAILURE == response.getLeft()) {
            throw new RuntimeException(response.getValue() + " - " + checkoutResponse.getRight().toString());
        }
        System.out.println(checkoutResponse.getRight().toString() + " items checkout response - " + response.getLeft().name());
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

}
