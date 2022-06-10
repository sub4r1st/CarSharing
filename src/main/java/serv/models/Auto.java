package serv.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "all_autos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "technical_passport", unique = true)
    private String technicalPassport;

    @Column(name = "state_number", unique = true)
    private String stateNumber;

    @Column(name = "year")
    private String year;

    @Column(name = "info")
    private String info;

    @Column(name = "price")
    private Integer price;

    @Override
    public String toString() {
        return "Марка - " + brand +
                ", Модель - " + model +
                ", Тех. паспорт - " + technicalPassport  +
                ", Номер автомобиля - " + stateNumber  +
                ", Год выпуска - " + year +
                ", Информация - " + info +
                ", Cтоимость - " + price;
    }
}
