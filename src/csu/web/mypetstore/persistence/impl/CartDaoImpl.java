package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.CartItem;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.persistence.CartDao;
import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.persistence.ItemDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {
    ItemDao itemDao = new ItemDaoImpl();

    private static final String insertCartItemString = "INSERT INTO cart (userid, cartItemItemItemId, cartItemItemProductProductId, \n" +
            "cartItemItemAttribute1, cartItemItemAttribute2, cartItemItemAttribute3,\n" +
            "cartItemItemAttribute4, cartItemItemAttribute5, cartItemItemProductName,\n" +
            "cartItemInStock, cartItemQuantity, cartItemItemListPrice, cartItemTotal)\n" +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String getCartItemListByUserid = "SELECT cartItemItemItemId, cartItemQuantity FROM cart WHERE userid = ?";

    private static final String incrementQuantityString = "UPDATE cart SET cartItemQuantity = ? WHERE cartItemItemItemId = ?";

    private static final String removeItemByIdString = "DELETE FROM cart WHERE cartItemItemItemId = ?";

    private static final String updateQuantityByItemIdString = "UPDATE cart SET cartItemQuantity = ? WHERE cartItemItemItemId = ?";

    private static final String removeAllCartItemsByUseridString = "DELETE FROM cart WHERE userid = ?";

    @Override
    public void insertCartItem(CartItem cartItem, String userid) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertCartItemString);
            preparedStatement.setString(1, userid);
            preparedStatement.setString(2, cartItem.getItem().getItemId());
            preparedStatement.setString(3, cartItem.getItem().getProduct().getProductId());
            preparedStatement.setString(4, cartItem.getItem().getAttribute1());
            preparedStatement.setString(5, cartItem.getItem().getAttribute2());
            preparedStatement.setString(6, cartItem.getItem().getAttribute3());
            preparedStatement.setString(7, cartItem.getItem().getAttribute4());
            preparedStatement.setString(8, cartItem.getItem().getAttribute5());
            preparedStatement.setString(9, cartItem.getItem().getProduct().getName());
            if (cartItem.isInStock()){
                preparedStatement.setInt(10, 1);
            } else {
                preparedStatement.setInt(10, 0);
            }
            preparedStatement.setInt(11, cartItem.getQuantity());
            preparedStatement.setBigDecimal(12, cartItem.getItem().getListPrice());
            preparedStatement.setBigDecimal(13, cartItem.getTotal());

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CartItem> getCartItemListByUserid(String userid) {
        List<CartItem> cartItemList = new ArrayList<CartItem>();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getCartItemListByUserid);
            preparedStatement.setString(1, userid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                CartItem cartItem = new CartItem();

                //获取item
                String itemId = resultSet.getString(1);
                Item item =itemDao.getItem(itemId);
                cartItem.setItem(item);

                //获取quantity
                int quantity = resultSet.getInt(2);
                cartItem.setQuantity(quantity);

                //获取total
                cartItem.calculateTotal();

                //获取inStock
                cartItem.setInStock(itemDao.getInventoryQuantity(itemId) > 0);

                cartItemList.add(cartItem);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return cartItemList;
    }

    @Override
    public void incrementQuantity(CartItem cartItem) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection
                    .prepareStatement(incrementQuantityString);

            pStatement.setInt(1, cartItem.getQuantity());
            pStatement.setString(2, cartItem.getItem().getItemId());

            int res = pStatement.executeUpdate();
            if (res == 1) {
                System.out.println("incrementQuantity成功");
            } else {
                System.out.println("incrementQuantity失败");
            }

            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeItemById(CartItem cartItem) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(removeItemByIdString);

            preparedStatement.setString(1, cartItem.getItem().getItemId());

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateQuantityByItemId(CartItem cartItem, int quantity) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection
                    .prepareStatement(updateQuantityByItemIdString);

            pStatement.setInt(1, quantity);
            pStatement.setString(2, cartItem.getItem().getItemId());

            int res = pStatement.executeUpdate();
            if (res == 1) {
                System.out.println("updateQuantityByItemId成功");
            } else {
                System.out.println("updateQuantityByItemId失败");
            }

            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeAllCartItemsByUserid(String userid) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(removeAllCartItemsByUseridString);

            preparedStatement.setString(1, userid);

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
