package csu.web.mypetstore.persistence;

import csu.web.mypetstore.domain.CartItem;

import java.util.List;

public interface CartDao {
    void insertCartItem(CartItem cartItem, String userid);

    List<CartItem> getCartItemListByUserid(String userid);

    void incrementQuantity(CartItem cartItem);

    void removeItemById(CartItem cartItem);

    void updateQuantityByItemId(CartItem cartItem, int quantity);

    void removeAllCartItemsByUserid(String userid);
}
