package green.petmate.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignupRequest {
    private String name;
    private Integer age;
    private String loginId;
    private String password;
    private String address;
    private Double latitude;
    private Double longitude;
    private String petType;
    private String petBreed;
    private String personality;
}
