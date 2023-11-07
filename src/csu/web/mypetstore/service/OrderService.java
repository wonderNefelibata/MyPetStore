package csu.web.mypetstore.service;

import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.domain.LineItem;
import csu.web.mypetstore.domain.Order;
import csu.web.mypetstore.domain.Sequence;
import csu.web.mypetstore.persistence.ItemDao;
import csu.web.mypetstore.persistence.LineItemDao;
import csu.web.mypetstore.persistence.OrderDao;
import csu.web.mypetstore.persistence.SequenceDao;
import csu.web.mypetstore.persistence.impl.ItemDaoImpl;
import csu.web.mypetstore.persistence.impl.LineItemDaoImpl;
import csu.web.mypetstore.persistence.impl.OrderDaoImpl;
import csu.web.mypetstore.persistence.impl.SequenceDaoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    private ItemDao itemDao;
    private OrderDao orderDao;
    private SequenceDao sequenceDao;
//    private LineItemDao lineItemDao;

    public OrderService(){
        itemDao = new ItemDaoImpl();
        orderDao = new OrderDaoImpl();
        sequenceDao = new SequenceDaoImpl();
//        lineItemDao = new LineItemDaoImpl();
    }

    public int getNextId(String name){//name为line/ordernum
        Sequence sequence = new Sequence(name,-1);
        sequence = sequenceDao.getSequence(sequence);//去数据库查询，看是否存在订单，不存在新建，存在则继续计数
        if(sequence == null){
            throw new RuntimeException("Error: A null sequence was returned from the database (could not get next  " + name +
                    " sequence).");//抛出异常显示在浏览器错误页面中
        }
        Sequence parameterObject = new Sequence(name,sequence.getNextId()+1);
        sequenceDao.updateSequence(parameterObject);
        return sequence.getNextId();
    }

    public void insertOrder(Order order) {
        order.setOrderId(getNextId("ordernum"));//注明是更新订单列表的
        //更新数据库中的库存inventory
//        for(int i=0;i<order.getLineItems().size();i++){
//            LineItem lineItem = order.getLineItems().get(i);
//            String itemId = lineItem.getItemId();
//            Integer increment = Integer.valueOf(lineItem.getQuantity());
//            Map<String,Object> param = new HashMap<String,Object>(2);
//            param.put("itemId",itemId);//键值对
//            param.put("increment",increment);
//            itemDao.updateInventoryQuantity(param);
//        }
        orderDao.insertOrder(order);//订单信息存入数据库中
        orderDao.insertOrderStatus(order);//修改订单状态
//        for(int i=0;i<order.getLineItems().size();i++){
//            LineItem lineItem = order.getLineItems().get(i);
//            lineItem.setOrderId(order.getOrderId());
//            lineItemDao.insertLineItem(lineItem);//把订单商品加入数据库
//        }
    }

    public Order getOrder(int orderId){
        Order order = orderDao.getOrder(orderId);//获取订单信息
//        order.setLineItems(lineItemDao.getLineItemsByOrderId(orderId));//获取该订单的商品属性
//        for(int i=0;i<order.getLineItems().size();i++){
//            LineItem lineItem = (LineItem) order.getLineItems().get(i);
//            Item item = itemDao.getItem(lineItem.getItemId());
//            item.setQuantity(itemDao.getInventoryQuantity(lineItem.getItemId()));
//            lineItem.setItem(item);
//        }
        return order;
    }

    public List<Order> getOrdersByUsername(String username){
        return orderDao.getOrdersByUsername(username);
    }

}
