package repository;

import entity.OrderDetailsEntity;
import entity.OrdersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends CrudRepository<OrderDetailsEntity,Integer> {

}
