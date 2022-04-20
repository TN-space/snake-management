package teksystems.capstone.formbean.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@ToString
public class RegisterFormBean {
    // These variable names have to be the same as the name="email"... in the register.jsp
    // this id will be null in case of a create, and will have a value in case of an update
    private Integer id;


    //Error checking seems to behave like if/else, check one by one in order
    // then show the first error message it encounters in browser, but show all error messages in console
    @NotBlank(message = "email is required")
    @Pattern(regexp = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}", message = "email format invalid, regex")
//    @EmailUnique(message = "Email already existed in database")
    @Email(message = "email is invalid, @Email")
    private String email;

    @NotBlank(message = "first name is required")
    private String firstName;

    @NotBlank(message = "last name is required")
    private String lastName;

    @NotBlank(message = "password is required")
    @Length(min = 3, max = 15, message = "Password must be between 3 and 15 characters")
    private String password;

    @Length(min = 3, max = 15, message = "Password must be between 3 and 15 characters")
    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;

    // checkbox variable name has to match with the `name` in input tag
//    @AssertTrue(message = "Checkbox is required")
//    private boolean checkbox;

}
