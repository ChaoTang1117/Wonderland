package chao.wonderland.bo;

//import com.sun.istack.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Data
@Table(name= "user_info")
@Entity
public class User {

    @Id
    @JsonIgnore
    @Column(name="user_id", updatable = false)
    private String userId;

    @Column(name="last_name")
    private String lastName;

    @Column(name="first_name")
    private String firstName;

    @Column(name="email_address")
    @Email(message="invalid email format")
    private String emailAddress;


}
