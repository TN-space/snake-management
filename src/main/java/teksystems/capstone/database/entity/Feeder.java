package teksystems.capstone.database.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feeders")
public class Feeder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private String size;

    @Column(name = "status")
    private String status;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "image_url")
    private String imgUrl;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "feeder", fetch = FetchType.LAZY)
    private List<FeederSnake> feederSnakes;
}
