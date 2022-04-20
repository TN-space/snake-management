package teksystems.capstone.formbean.feederSnake;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class AddFeederSnakeFormBean {

    private Integer id;

    @NotNull(message = "feeder may not be blank")
    private Integer feederId;

    @NotNull(message = "snake may not be blank")
    private Integer snakeId;

    private Integer quantity;

}
