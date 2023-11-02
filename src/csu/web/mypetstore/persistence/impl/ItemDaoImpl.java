package csu.web.mypetstore.persistence.impl;


import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.persistence.ItemDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemDaoImpl implements ItemDao {

    private static final String GET_ITEM_LIST_BY_PRODUCT=
            "SELECT I.ITEMID," +
                    "LISTPRICE," +
                    "UNITCOST," +
                    "SUPPLIER AS supplierId," +
                    "I.PRODUCTID AS \"product.productId\"," +
                    "NAME AS \"product.name\"," +
                    "DESCN AS \"product.description\"," +
                    "CATEGORY AS \"product.categoryId\"," +
                    "STATUS," +
                    "ATTR1 AS attribute1," +
                    "ATTR2 AS attribute2," +
                    "ATTR3 AS attribute3," +
                    "ATTR4 AS attribute4," +
                    "ATTR5 AS attribute5" +
                    "FROM ITEM I, PRODUCT P" +
                    "WHERE P.PRODUCTID = I.PRODUCTID" +
                    "AND I.PRODUCTID = ?";

    private static final String GET_ITEM=
            "select I.ITEMID," +
                    "LISTPRICE," +
                    "UNITCOST," +
                    "SUPPLIER AS supplierId," +
                    "I.PRODUCTID AS \"product.productId\"," +
                    "NAME AS \"product.name\"," +
                    "DESCN AS \"product.description\"," +
                    "CATEGORY AS \"product.categoryId\"," +
                    "STATUS," +
                    "ATTR1 AS attribute1," +
                    "ATTR2 AS attribute2," +
                    "ATTR3 AS attribute3," +
                    "ATTR4 AS attribute4," +
                    "ATTR5 AS attribute5," +
                    "QTY AS quantity" +
                    "from ITEM I, INVENTORY V, PRODUCT P" +
                    "where P.PRODUCTID = I.PRODUCTID" +
                    "and I.ITEMID = V.ITEMID" +
                    "and I.ITEMID = ?";

    private static final String GET_INVENTORY_QUANTITY=
            "SELECT QTY AS value FROM INVENTORY WHERE ITEMID = ?";
    private static final String UPDATE_INVENTORY_QUANTITY=
            "UPDATE INVENTORY SET QTY = QTY - ? WHERE ITEMID = ?";

    @Override
    public List<Product> searchProductList(String keywords) {
        ArrayList<Product> productList = new ArrayList<>();
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SEARCH_PRODUCT_LIST);
            preparedStatement.setString(1,keywords);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Product product = new Product();
                product.setProductId(resultSet.getString("PRODUCTID"));
                product.setName(resultSet.getString("NAME"));
                product.setDescription(resultSet.getString("description"));
                product.setCategoryId(resultSet.getString("categoryId"));
                productList.add(product);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public void updateInventoryQuantity(Map<String, Object> param) {
        int quantity= (int) param.get("quantity");
        String itemId = (String) param.get("itemId");
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INVENTORY_QUANTITY);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setString(2,itemId);
            preparedStatement.executeUpdate();
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public int getInventoryQuantity(String itemId) {
        int inventoryQuantity=-1;
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(GET_INVENTORY_QUANTITY);
            preparedStatement.setString(1,itemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                inventoryQuantity=resultSet.getInt("value");
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return inventoryQuantity;
    }

    @Override
    public List<Item> getItemListByProduct(String productId) {
        ArrayList<Item> itemList = new ArrayList<>();
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(GET_ITEM_LIST_BY_PRODUCT);
            preparedStatement.setString(1,productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Item item = new Item();
                Product product=new Product();

                item.setItemId(resultSet.getString("I.ITEMID"));
                item.setListPrice(resultSet.getBigDecimal("LISTPRICE"));
                item.setUnitCost(resultSet.getBigDecimal("UNITCOST"));
                item.setSupplierId(resultSet.getInt("supplierId"));
                item.setProductId(resultSet.getString("product.productId"));

                product.setName(resultSet.getString("product.name"));
                product.setDescription(resultSet.getString("product.description"));
                product.setCategoryId(resultSet.getString("product.categoryId"));

                item.setStatus(resultSet.getString("STATUS"));
                item.setAttribute1(resultSet.getString("attribute1"));
                item.setAttribute2(resultSet.getString("attribute2"));
                item.setAttribute3(resultSet.getString("attribute3"));
                item.setAttribute4(resultSet.getString("attribute4"));
                item.setAttribute5(resultSet.getString("attribute5"));

                itemList.add(item);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public Item getItem(String itemId) {
        Item item=null;
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(GET_ITEM);
            preparedStatement.setString(1,itemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                item = new Item();
                Product product=new Product();

                item.setItemId(resultSet.getString("I.ITEMID"));
                item.setListPrice(resultSet.getBigDecimal("LISTPRICE"));
                item.setUnitCost(resultSet.getBigDecimal("UNITCOST"));
                item.setSupplierId(resultSet.getInt("supplierId"));
                item.setProductId(resultSet.getString("product.productId"));

                product.setName(resultSet.getString("product.name"));
                product.setDescription(resultSet.getString("product.description"));
                product.setCategoryId(resultSet.getString("product.categoryId"));

                item.setStatus(resultSet.getString("STATUS"));
                item.setAttribute1(resultSet.getString("attribute1"));
                item.setAttribute2(resultSet.getString("attribute2"));
                item.setAttribute3(resultSet.getString("attribute3"));
                item.setAttribute4(resultSet.getString("attribute4"));
                item.setAttribute5(resultSet.getString("attribute5"));
                item.setQuantity(resultSet.getInt("quantity"));
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return item;
    }
}
