package green.petmate.user.dto;

import green.petmate.user.entity.Pet;
import green.petmate.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private Integer age;
    private String loginId;
    private String profileImage;
    private String address;
    private Double latitude;
    private Double longitude;

    // Pet info
    private String petImage;
    private String petType;
    private String petBreed;
    private String personality;
    private String grade;

    public static UserResponse from(User user) {
        Pet pet = user.getPet();
        UserResponseBuilder builder = UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .loginId(user.getLoginId())
                .profileImage(user.getProfileImage())
                .address(user.getAddress())
                .latitude(user.getLatitude())
                .longitude(user.getLongitude())
                .grade(user.getGrade().name());

        if (pet != null) {
            builder.petImage(pet.getPetImage())
                    .petType(pet.getPetType())
                    .petBreed(pet.getPetBreed())
                    .personality(pet.getPersonality());
        }

        return builder.build();
    }
}
