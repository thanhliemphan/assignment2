package entity;

import javax.persistence.*;

@Entity
@Table(name = "orderdetails")
public class OrderDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "productname")
    private String productName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unitprice")
    private double unitPrice;

    @ManyToOne
    @JoinColumn(name = "ordersId")
    private OrdersEntity orders;

    public OrderDetailsEntity() {
    }

    @Override
    public String toString(){
        return "OrderDetailsEntity(" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ')';
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public OrdersEntity getOrders() {
        return orders;
    }

    public void setOrders(OrdersEntity orders) {
        this.orders = orders;
    }
}
