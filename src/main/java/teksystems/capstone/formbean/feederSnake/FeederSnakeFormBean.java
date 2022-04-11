package teksystems.capstone.formbean.feederSnake;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class FeederSnakeFormBean {

    private Integer id;

    private Integer feederId;

    private Integer snakeId;

//    @NotBlank(message = "species name is required")
//    private String species;
//
//    @NotBlank(message = "feeder name is required")
//    private String name;
}
