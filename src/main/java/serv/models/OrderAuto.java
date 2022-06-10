package serv.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "order_auto")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderAuto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "order_id")
    private int orderId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "auto_id", referencedColumnName = "id")
    private Auto auto;

    @Column(name = "price")
    private int price;

    @Override
    public String toString() {
        return "{"+auto +
                ", цена=" + price + " руб."
                +"}";
    }
}

