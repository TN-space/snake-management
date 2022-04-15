package teksystems.capstone.formbean.feederSnake;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class AddFeederSnakeFormBean {

    private Integer id;

    private Integer feederId;

    private Integer snakeId;

    private Integer quantity;

}
