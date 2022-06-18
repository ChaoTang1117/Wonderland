package chao.wonderland.bo;

//import com.sun.istack.NotNull;
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
    @Column(name="user_id", updatable = false)
//    @NotNull
    private String userId;

//    @NotNull
    @Column(name="last_name")
    private String lastName;

//    @NotNull
    @Column(name="first_name")
    private String firstName;

    @Column(name="email_address")
    private String emailAddress;


}
