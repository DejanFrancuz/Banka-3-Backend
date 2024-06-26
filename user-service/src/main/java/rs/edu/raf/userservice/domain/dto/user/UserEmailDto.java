package rs.edu.raf.userservice.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEmailDto implements Serializable {
    //za komunikaciju sa Bank Service-om
    private Long userId;
    private String email;
}
