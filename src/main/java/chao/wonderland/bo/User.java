package chao.wonderland.bo;

//import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(updatable = false)
//    @NotNull
    private String userId;

//    @NotNull
    private String lastName;

//    @NotNull
    private String firstName;


}
