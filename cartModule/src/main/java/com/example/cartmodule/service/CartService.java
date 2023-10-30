package com.example.cartmodule.service;

import com.example.cartmodule.Integration.CustomerProductDTO;
import com.example.cartmodule.Integration.CustomerProductQualityDTO;
import com.example.cartmodule.Integration.Message;
import com.example.cartmodule.Integration.Sender;
import com.example.cartmodule.model.domains.Product;
import com.example.cartmodule.model.entity.Cart;
import com.example.cartmodule.model.entity.ProductOnCart;
import com.example.cartmodule.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private Sender sender;


    public Cart addShoppingCart(String customerId) {
        Message<String> message = new Message<String>(
                "addCart",
                customerId
        );
        Cart shoppingCart = cartRepository.save(new Cart(customerId));

        sender.send(message);
        return shoppingCart;
    }

    public Product addProductToAShoppingCart(String customerId, Product product, Integer quantity) {
        Message<CustomerProductQualityDTO> customerProductQualityDTOMessage =
                new Message<CustomerProductQualityDTO>(
                        "addProductAndQuantity",
                        new CustomerProductQualityDTO(
                                customerId,
                                product,
                                quantity)
                );
        Cart shoppingCart = cartRepository.findByCustomerId(customerId).get();

        shoppingCart.addProduct(product, quantity);
        cartRepository.save(shoppingCart);
        sender.send(customerProductQualityDTOMessage);
        return product;

    }

    public void removeProductWithQuantity(String customerId, String productId, Integer quantity) {

        Cart shoppingCart = cartRepository.findByCustomerId(customerId).get();
        Product product = null;
        for (ProductOnCart cartLine : shoppingCart.getCartLineList()) {

            if (cartLine.getProduct().getProductId().equals(productId)) {
                product = cartLine.getProduct();
                shoppingCart.removeProduct(cartLine.getProduct(), quantity);
            }

        }
        Message<CustomerProductQualityDTO> customerProductQualityDTOMessage =
                new Message<CustomerProductQualityDTO>(
                        "removeProductWithQuality",
                        new CustomerProductQualityDTO(
                                customerId,
                                product,
                                quantity)
                );

        cartRepository.save(shoppingCart);
        sender.send(customerProductQualityDTOMessage);

    }

    public void removeAllProduct(String customerId, String productId) {
        Cart shoppingCart = cartRepository.findByCustomerId(customerId).get();
        Product product;
        for (ProductOnCart cartLine : shoppingCart.getCartLineList()) {

            if (cartLine.getProduct().getProductId().equals(productId)) {
                product = cartLine.getProduct();
                System.out.println();

                shoppingCart.getCartLineList().remove(cartLine);
                System.out.println("Shopping cart" + shoppingCart);
                Message<CustomerProductDTO> customerProductQualityDTOMessage =
                        new Message<CustomerProductDTO>(
                                "removeAllProduct",
                                new CustomerProductDTO(
                                        customerId,
                                        product
                                )
                        );
                cartRepository.save(shoppingCart);
                sender.send(customerProductQualityDTOMessage);
                return;
            }
        }
    }

    public List<ProductOnCart> checkoutCart(String customerId) {
        Message<String> message = new Message<String>(
                "checkout",
                customerId
        );
        Cart cart = cartRepository.findByCustomerId(customerId).get();
        System.out.println(111111);
        System.out.println(cart.getCartLineList().get(0));
        System.out.println(cart.getCartLineList().size());
        sender.send(message);
        System.out.println("This is the list of CartLines : " + cart.getCartLineList());

        return new ArrayList<>(cart.getCartLineList());
    }

    public void removeCartLine(String customerId) {
        Cart shoppingCart = cartRepository.findByCustomerId(customerId).get();
        shoppingCart.removeCartLineList();
        cartRepository.save(shoppingCart);
    }

}
