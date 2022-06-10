package serv.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne()
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Owner owner;

    @Column(name = "date")
    private Date date = new Date();

    @OneToOne()
    @JoinColumn(name = "auto_id", referencedColumnName = "id")
    private Auto auto;

    @Column(name = "days")
    private int days;

    @Column(name = "userId")
    private int userId;

}
