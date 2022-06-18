package chao.wonderland.bo;

//import com.sun.istack.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name= "user_info")
@Entity
public class User {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
    @Id
    @JsonIgnore
    @Column(name="user_id", updatable = false)
    private String userId;

    @Column(name="last_name")
    private String lastName;

    @Column(name="first_name")
    private String firstName;

    @Column(name="email_address")
    private String emailAddress;


}
