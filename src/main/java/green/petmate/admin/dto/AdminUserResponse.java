package green.petmate.admin.dto;

import green.petmate.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class AdminUserResponse {
    private Long id;
    private String name;
    private Integer age;
    private String loginId;
    private String profileImage;
    private String address;
    private String grade;
    private String petType;
    private String petBreed;
    private String createdAt;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static AdminUserResponse from(User user) {
        AdminUserResponseBuilder builder = AdminUserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .loginId(user.getLoginId())
                .profileImage(user.getProfileImage())
                .address(user.getAddress())
                .grade(user.getGrade().name())
                .createdAt(user.getCreatedAt() != null ? user.getCreatedAt().format(FMT) : "-");

        if (user.getPet() != null) {
            builder.petType(user.getPet().getPetType())
                    .petBreed(user.getPet().getPetBreed());
        }
        return builder.build();
    }
}
