package org.example;

import config.SpringConfig;
import entity.OrderDetailsEntity;
import entity.OrdersEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.OrderDetailsRepository;
import repository.OrdersRepository;

import java.time.LocalDate;
import java.util.List;

public class Main {
    static ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    static OrdersRepository ordersRepository = (OrdersRepository) context.getBean("ordersRepository");
    static OrderDetailsRepository orderDetailsRepository = (OrderDetailsRepository) context.getBean("orderDetailsRepository");

    public static void main(String[] args) {
//        createNewOrderDetailWithNewOrder();
//        listAllOrdersAndOrderDetails();
//        findById(2);
//        createNewOrderDetailsWithOrder();
//        getAllOrdersInCurrentMonth();
//        findOrderTotalAmountMoreThan(35000);
        listAllOrderByProductName("Javaa");
    }
    public static OrdersEntity createNewOrder(){
        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setOrderDate(LocalDate.parse("2023-06-20"));
        ordersEntity.setCustomerName("Nguyen Van A");
        ordersEntity.setCustomerAddress("Da Nang");
        return ordersEntity;
    }

    public static OrderDetailsEntity createNewOrderDetail(){
        OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
        orderDetailsEntity.setProductName("Java");
        orderDetailsEntity.setQuantity(30);
        orderDetailsEntity.setUnitPrice(1000);
        return  orderDetailsEntity;
    }
    public static void createNewOrderDetailWithNewOrder(){
        OrdersEntity ordersEntity = createNewOrder();
        ordersRepository.save(ordersEntity);
        System.out.println("A new order saved successfully,order ID = "+ordersEntity.getId());

        OrderDetailsEntity orderDetailsEntity = createNewOrderDetail();
        orderDetailsEntity.setOrders(ordersEntity);
        orderDetailsRepository.save(orderDetailsEntity);
        System.out.println("A new order details saved successfully,order details ID = "+orderDetailsEntity.getId());
    }
    private static void createNewOrderDetailsWithOrder(){
        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setId(1);

        OrderDetailsEntity orderDetailsEntity = createNewOrderDetail();
        orderDetailsEntity.setOrders(ordersEntity);
        orderDetailsRepository.save(orderDetailsEntity);
    }
    public static void listAllOrdersAndOrderDetails(){
        List<OrdersEntity> ordersEntityList = (List<OrdersEntity>) ordersRepository.getAllOrders();
        if (ordersEntityList.size()!=0){
            System.out.println("Found " + ordersEntityList.size() + " order(s) in table order");
            System.out.println("They are: ");
            for (OrdersEntity order:ordersEntityList) {
                System.out.println(order.toString());
            }
        }
    }

    public static void findById(int ordersId){
        if (ordersRepository.existsById(ordersId)){
            OrdersEntity ordersEntity = ordersRepository.findById(ordersId).get();
            System.out.println("Found an order with order id = " + ordersId);
            System.out.println(ordersEntity.toString());
        }
    }
    public static void getAllOrdersInCurrentMonth(){
        List<OrdersEntity> ordersEntityList = ordersRepository.findByCurrentMonthAndYear();
        if (ordersEntityList.size()!=0){
            System.out.println("Found " + ordersEntityList.size() + " order(s) in the current month");
            System.out.println("They are: ");
            for (OrdersEntity order:ordersEntityList) {
                System.out.println(order.toString());
            }
        }
        else System.out.println("Not found an order in the current month");
    }
    public static void findOrderTotalAmountMoreThan(double amount){
        List<OrdersEntity> ordersEntityList = ordersRepository.findTotalAmountMoreThan(amount);
        if (ordersEntityList.size()!=0){
            System.out.println("Found " + ordersEntityList.size() + " order(s) have total amount more than "+ amount);
            System.out.println("They are: ");
            for (OrdersEntity order:ordersEntityList) {
                System.out.println(order.toString());
            }
        }
        else System.out.println("Not found an order have total amount more than "+ amount);
    }
    public static void listAllOrderByProductName(String productName){
        List<OrdersEntity> ordersEntityList = ordersRepository.findByProductName(productName);
        if (ordersEntityList.size()!=0){
            System.out.println("Found " + ordersEntityList.size() + " order(s) buy " + productName);
            System.out.println("They are: ");
            for (OrdersEntity order:ordersEntityList) {
                System.out.println(order.toString());
            }
        }
        else System.out.println("Not found an order buy " + productName);
    }
}