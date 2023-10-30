package com.example.cartmodule.model.entity;

import com.example.cartmodule.model.domains.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    private String customerId;
    private List<ProductOnCart> cartLineList = new ArrayList<>();

    public Cart(String customerId) {
        this.customerId = customerId;
    }

    public boolean addProduct(Product product, Integer quantity){
        for(ProductOnCart cartLine  : cartLineList){
            if(cartLine.getProduct().equals(product)){
                cartLine.changeQuantity(cartLine.getQuantity() + quantity);
                return true;
            }
        }
        cartLineList.add(new ProductOnCart(product,quantity));
        return true;
    }

    public boolean removeProduct(Product product,Integer quantity){
        ProductOnCart cartLine2= null;

        for(ProductOnCart cartLine : cartLineList){
            if(cartLine.getProduct().equals(product) && cartLine.getQuantity() > quantity ){
                cartLine.changeQuantity(cartLine.getQuantity() - quantity);
                return true;
            }
            else if(cartLine.getProduct().equals(product) && cartLine.getQuantity() <= quantity ){
                cartLineList.remove(cartLine);
                return false;
            }
            else{
                cartLine2=cartLine;
            }
        }
        cartLineList.remove(cartLine2);
        return true;
    }

    public List<ProductOnCart> checkout(){
        return this.cartLineList;
    }

    public void removeCartLineList(){
        this.cartLineList = new ArrayList<>();
    }

}
