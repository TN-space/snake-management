package teksystems.capstone.formbean.snake;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class AddSnakeFormBean {
    // These variable names have to be the same as the name="email"... in the register.jsp
    // this id will be null in case of a create, and will have a value in case of an update
    private Integer id;

    //Error checking seems to behave like if/else, check one by one in order
    // then show the first error message it encounters in browser, but show all error messages in console
    @NotBlank(message = "species name is required")
    private String species;

    private String sex;

    // 500 error if submit form without birthday - have to regex that or something later
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate birthDate;

    private String note;

    // age is not working as expected
//    private String age = Period.between(birthDate, LocalDate.now()).getYears() > 0
//            ? (Period.between(birthDate, LocalDate.now()).getYears()) + " years"
//            : (Period.between(birthDate, LocalDate.now()).getMonths()) + " months";

    private String imgUrl;

}
