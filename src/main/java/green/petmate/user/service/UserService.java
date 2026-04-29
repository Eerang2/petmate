package green.petmate.user.service;

import green.petmate.user.dto.SignupRequest;
import green.petmate.user.dto.UserResponse;
import green.petmate.user.entity.Pet;
import green.petmate.user.entity.User;
import green.petmate.user.entity.repositoey.PetRepository;
import green.petmate.user.entity.repositoey.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PetRepository petRepository;

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    @Transactional
    public Long signup(SignupRequest request, MultipartFile profileImage, MultipartFile petImage) throws IOException {
        if (userRepository.existsByLoginId(request.getLoginId())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        String profileImagePath = saveFile(profileImage);
        String petImagePath = saveFile(petImage);

        User user = User.builder()
                .name(request.getName())
                .age(request.getAge())
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .profileImage(profileImagePath)
                .address(request.getAddress())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build();

        userRepository.save(user);

        Pet pet = Pet.builder()
                .user(user)
                .petImage(petImagePath)
                .petType(request.getPetType())
                .petBreed(request.getPetBreed())
                .personality(request.getPersonality())
                .build();

        petRepository.save(pet);

        return user.getId();
    }

    public Long login(String loginId, String password) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return user.getId();
    }

    @Transactional(readOnly = true)
    public UserResponse getUserInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        return UserResponse.from(user);
    }

    @Transactional
    public void updateUserInfo(Long userId, String name, String address,
                               Double latitude, Double longitude,
                               MultipartFile profileImage) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        if (name != null) user.setName(name);
        if (address != null) user.setAddress(address);
        if (latitude != null) user.setLatitude(latitude);
        if (longitude != null) user.setLongitude(longitude);

        if (profileImage != null && !profileImage.isEmpty()) {
            String path = saveFile(profileImage);
            user.setProfileImage(path);
        }

        userRepository.save(user);
    }

    @Transactional
    public void updatePetInfo(Long userId, String petType, String petBreed,
                              String personality, MultipartFile petImage) throws IOException {
        Pet pet = petRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("펫 정보가 없습니다."));

        if (petType != null) pet.setPetType(petType);
        if (petBreed != null) pet.setPetBreed(petBreed);
        if (personality != null) pet.setPersonality(personality);

        if (petImage != null && !petImage.isEmpty()) {
            String path = saveFile(petImage);
            pet.setPetImage(path);
        }

        petRepository.save(pet);
    }

    private String saveFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) return null;

        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File dest = new File(UPLOAD_DIR + fileName);
        file.transferTo(dest);

        return "/uploads/" + fileName;
    }
}
