package repository;

import entity.OrdersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends CrudRepository<OrdersEntity,Integer> {
    @Query(value = "select * from orders",nativeQuery = true)
    List<OrdersEntity> getAllOrders();

    @Query(value = "select * from orders where MONTH(orderDate)=MONTH(now()) and YEAR(orderDate)=YEAR(now())", nativeQuery = true)
    List<OrdersEntity> findByCurrentMonthAndYear();

    @Query(value = "select orders.id,orders.customeraddress,orders.customername,orders.orderdate from orders join orderdetails on orders.id=orderdetails.ordersid group by orderdetails.ordersid having sum(unitprice*quantity)>?1", nativeQuery = true)
    List<OrdersEntity> findTotalAmountMoreThan(double amount);

    @Query(value = "select * from orders join orderdetails on orders.id=orderdetails.ordersid where productname=?1",nativeQuery = true)
    List<OrdersEntity> findByProductName(String productName);
}
