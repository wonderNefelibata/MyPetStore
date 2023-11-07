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

    public OrderService(){
        itemDao = new ItemDaoImpl();
        orderDao = new OrderDaoImpl();
        sequenceDao = new SequenceDaoImpl();
    }

    public int getNextId(String name){
        Sequence sequence = new Sequence(name,-1);
        sequence = sequenceDao.getSequence(sequence);
        if(sequence == null){
            throw new RuntimeException("Error: A null sequence was returned from the database (could not get next  " + name +
                    " sequence).");
        }
        Sequence parameterObject = new Sequence(name,sequence.getNextId()+1);
        sequenceDao.updateSequence(parameterObject);
        return sequence.getNextId();
    }

    public void insertOrder(Order order) {
        order.setOrderId(getNextId("ordernum"));
        orderDao.insertOrder(order);
        orderDao.insertOrderStatus(order);
    }

    public Order getOrder(int orderId){
        Order order = orderDao.getOrder(orderId);
        return order;
    }

    public List<Order> getOrdersByUsername(String username){
        return orderDao.getOrdersByUsername(username);
    }

}
