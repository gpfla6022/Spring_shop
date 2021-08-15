package com.yhr.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")
@Getter@Setter
public class Order {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; //주문상태 [ORDER, CANCEL]


    // == 연관관계 메소드== //
    // DB에 저장할 때에는 연관관계의 주인 인그턴스를 persist하면 되지만, 객체의 입장에서는
    // 양쪽 객체 모두 값을 넣어주어야 반영이 되기 때문에, 해당 사항을 돕는 메서드를 만들어 놓는다.

    public void setMember(Member member){

        /*/
        Member member = new Member();
        Order order = new Order();

        member.getOrder.add(order);
        order.setMember(member);
         */

        // 상기의 코드를 한번에 해결하기 위하여 만든 메소드
        // 이 인스턴스의 member에 주입받은 member를 할당
        this.member = member;
        // member를 호출하고, Getter로서 Order를 불러온 뒤, 해당 인스턴스를 주입한다.
        member.getOrders().add(this);

    }

    public void setOrderItem(OrderItem orderItem){

        // 마찬가지로 양방향으로 관계를 가지고 있는 OrderItem의 인스턴스에 Order를
        // 주입하기 위하여 아애의 코드를 입력한다.
        orderItems.add(orderItem);
        orderItem.setOrder(this);

    }


    public void setDelivery(Delivery delivery){

        this.delivery = delivery;
        delivery.setOrder(this);

    }

}
