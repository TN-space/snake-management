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
@Table(name = "feeder_snakes")
public class FeederSnake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "feeder_id", updatable = false, insertable = false)
    private Integer feederId;

    @Column(name = "snake_id", updatable = false, insertable = false)
    private Integer snakeId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "feeding_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feedingDate = new Date();

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "feeder_id", nullable = false)
    private Feeder feeder;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "snake_id", nullable = false)
    private Snake snake;
}
