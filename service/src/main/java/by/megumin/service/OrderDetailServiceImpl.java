package by.megumin.service;

import by.megumin.dao.OrderDetailDao;
import by.megumin.dao.common.BaseDao;
import by.megumin.entity.orderEntity.OrderDetail;
import by.megumin.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail> implements OrderDetailService {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Override
    protected BaseDao<OrderDetail> getDao() {
        return orderDetailDao;
    }
}
