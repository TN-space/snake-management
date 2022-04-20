package teksystems.capstone.database.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

    // having this doesn't exclude birthDate for create method for Junit testing
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Column(name = "birth_date")
    private LocalDate birthDate;

//    @Column(name = "age")
//    private String age;
//    Period.between(birthDate, LocalDate.now()).getYears() > 0
//            ? (Period.between(birthDate, LocalDate.now()).getYears()) + " years"
//            : (Period.between(birthDate, LocalDate.now()).getMonths()) + " months"

    @Column(name = "image_url")
    private String imgUrl;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id")
    private Integer userId;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "snake", fetch = FetchType.LAZY)
    private List<FeederSnake> feederSnakes;
}
