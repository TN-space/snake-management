package teksystems.capstone.formbean.feeder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class AddFeederFormBean {

    private Integer id;

    //Error checking seems to behave like if/else, check one by one in order
    // then show the first error message it encounters in browser, but show all error messages in console
    @NotBlank(message = "feeder name is required")
    private String name;

    private String size;

    private String status;

    private Integer quantity;
}