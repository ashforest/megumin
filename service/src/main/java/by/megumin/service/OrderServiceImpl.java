package by.megumin.service;

import by.megumin.dao.OrderContentDao;
import by.megumin.dao.OrderDao;
import by.megumin.dao.OrderDetailDao;
import by.megumin.dao.PaymentDetailsDao;
import by.megumin.dao.common.BaseDao;
import by.megumin.entity.orderEntity.*;
import by.megumin.entity.userEntity.User;
import by.megumin.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderContentDao orderContentDao;

    @Autowired
    private PaymentDetailsDao paymentDetailsDao;

    @Override
    public List<Order> getByUser(User owner) {
        return orderDao.getByUser(owner);
    }

    @Override
    public Order createOrder(User user, PaymentDetails paymentDetails) {
        List<Cart> carts = cartService.getByUser(user);
        OrderDetail orderDetail = new OrderDetail(LocalDateTime.now(), null);
        orderDetailDao.save(orderDetail);
        Order order = new Order();
        order.setOwner(user);
        order.setStatus(OrderStatus.FORMED);
        order.setDetail(orderDetail);
        orderDao.save(order);

        if (paymentDetails.getPaymentType() == PaymentType.ONE_TIME) {
            paymentDetails = new PaymentDetails();
            paymentDetails.setPaymentType(PaymentType.ONE_TIME);
        } else {
            paymentDetails.setInterestRate(getInterestRate(paymentDetails));
        }

        paymentDetailsDao.save(paymentDetails);
        final PaymentDetails pd = paymentDetails;

        List<OrderContent> contents = carts
                .stream()
                .map(c -> new OrderContent(c.getProduct(), c.getAmount(), pd, order))
                .collect(Collectors.toList());
        contents.forEach(c -> orderContentDao.save(c));
        cartService.cleanByUser(user);
        return order;
    }

    @Override
    protected BaseDao<Order> getDao() {
        return orderDao;
    }

    private double getInterestRate(PaymentDetails paymentDetails) {
        switch (paymentDetails.getRepaymentMonthTerm()) {
            case 2:
                return 0;
            case 4:
                return 3;
            case 6:
                return 5;
            default:
                throw new IllegalArgumentException("Not possible term");
        }
    }
}
