package teksystems.capstone.database.entity;

import lombok.*;

import javax.persistence.*;
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
    @Column(name = "add_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addDate = new Date();

}
