package teksystems.capstone.database.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "snakes")
public class Snake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "species")
    private String species;

    @Column(name = "sex")
    private String sex;

    @Column(name = "note")
    private String note;

//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    @Column(name = "birth_date")
    private LocalDate birthDate = LocalDate.now();

    @Column(name = "age")
    private String age;
//    Period.between(birthDate, LocalDate.now()).getYears() > 0
//            ? (Period.between(birthDate, LocalDate.now()).getYears()) + " years"
//            : (Period.between(birthDate, LocalDate.now()).getMonths()) + " months"

    @Column(name = "image_url")
    private String imgUrl;
}
