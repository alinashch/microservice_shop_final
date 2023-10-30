package com.example.cartmodule.controller;

import com.example.cartmodule.model.domains.Product;
import com.example.cartmodule.model.entity.Cart;
import com.example.cartmodule.model.entity.Order;
import com.example.cartmodule.model.entity.ProductOnCart;
import com.example.cartmodule.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    private final ShoppingFeignClient shoppingFeignClient;

    private final ProductFeignClient productFeignClient;

    @PostMapping("/addCartForACustomer/{customerId}")
    public ResponseEntity<?> addShoppingCartForCustomer(@PathVariable String customerId){
        Cart shoppingCart =  cartService.addShoppingCart(customerId);
        return new ResponseEntity<Cart>(shoppingCart, HttpStatus.OK);
    }

    @PostMapping("/addProductToCartWithQuantity/{customerId}/quantity/{quantity}")
    public ResponseEntity<?> addProductToShoppingCart(@PathVariable String customerId ,
                                                      @PathVariable Integer quantity,
                                                      @RequestBody Product product){
        if(productFeignClient.checkProductInStock(product.getProductId())){
            Product product1 = cartService.addProductToAShoppingCart(customerId,product,quantity);
            return new ResponseEntity<Product>(product1,HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/removeProductFromCartWithQuantity/{customerId}/product/{productId}/quantity/{quantity}")
    public ResponseEntity<?> removeProductWithQuantity(@PathVariable("customerId") String customerId ,
                                                       @PathVariable("quantity") Integer quantity,
                                                       @PathVariable("productId") String productId){
        cartService.removeProductWithQuantity(customerId,productId,quantity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/removeProductFromCart/{customerId}/product/{productId}")
    public ResponseEntity<?> removeAllProduct(@PathVariable("customerId") String customerId ,
                                              @PathVariable("productId") String productId){

        cartService.removeAllProduct(customerId,productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/checkout/{customerId}")
    public ResponseEntity<?> checkoutCart(@PathVariable String customerId){
        List<ProductOnCart> cartLines =  cartService.checkoutCart(customerId);
        Order order = shoppingFeignClient.createOrder(cartLines);
        cartService.removeCartLine(customerId);

        return new ResponseEntity<>(order,HttpStatus.OK);

    }

    @FeignClient("orderModule")
    interface ShoppingFeignClient{
        @PostMapping("/order")
        Order createOrder(@RequestBody List<ProductOnCart> cartLines);
    }


    @FeignClient("ProductService")
    interface ProductFeignClient{
        @GetMapping("/products/{productNumber}/isInStock")
        boolean checkProductInStock(@PathVariable("productNumber") String productNumber);
    }

}
