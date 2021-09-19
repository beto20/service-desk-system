package com.example.ms.spring.users.business;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.ms.spring.users.model.Role;
import com.example.ms.spring.users.model.User;
import com.example.ms.spring.users.repository.DepartmentRepository;
import com.example.ms.spring.users.repository.RoleRepository;
import com.example.ms.spring.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.cloudinary.json.JSONObject;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;


@Service
@AllArgsConstructor
public class UserBusinessImpl implements UserBusiness{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public Flux<User> getAll() {
        return userRepository.findAll()
                .flatMap(user -> Mono.just(user)
                        .zipWith(roleRepository.findById(user.getRoleId().getId()), (userRoleDetail, roleData) -> {
                            userRoleDetail.setRoleId(roleData);
                            return userRoleDetail;
                        }))
                .flatMap(userWithRole -> Mono.just(userWithRole).zipWith(departmentRepository.findById(userWithRole.getDepartmentId().getId()), (userDepartmentDetail, departmentData) -> {
                    userDepartmentDetail.setDepartmentId(departmentData);
                    return userDepartmentDetail;
                }));
    }

    public Mono<User> getById(String userId) {
        return userRepository.findById(userId)
                .flatMap(user -> Mono.just(user)
                            .zipWith(roleRepository.findById(user.getRoleId().getId()), (userRoleDetail, roleData) -> {
                                userRoleDetail.setRoleId(roleData);
                                return userRoleDetail;
                            }))
                .flatMap(userWithRole -> Mono.just(userWithRole).zipWith(departmentRepository.findById(userWithRole.getDepartmentId().getId()), (userDepartmentDetail, departmentData) -> {
                    userDepartmentDetail.setDepartmentId(departmentData);
                    return userDepartmentDetail;
                }));
    }

/////////
    public Mono<User> upload(String id, FilePart filePart) {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "albertocloud",
                "api_key","313269915668122",
                "api_secret","vqQQLx2QxLYj5E0z73OjGCK74lo"));
        return getById(id).flatMap(user -> {
            try {
                File file = Files.createTempFile("temp", filePart.filename()).toFile();
                filePart.transferTo(file).block();

                Map response = cloudinary.uploader().upload(file, ObjectUtils.asMap("resource_type", "auto"));

                JSONObject jsonObject = new JSONObject(response);
                String url = jsonObject.getString("url");

                user.setPhoto(url);
                return update(id, user);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return Mono.just(user);
        });
    }

/////////
    public Flux<Role> getAllRole() {
        return roleRepository.findAll();
    }
    public Mono<Role> insertRole(Role role) {
        return roleRepository.save(role);
    }
////////////

    @Override
    public Mono<User> insert(User user) {
        return userRepository.save(user);
    }

    @Override
    public Mono<User> update(String id, User user) {
        return userRepository.findById(id).map(updateUser -> User.builder()
                .id(id)
                .name(user.getName())
                .lastname(user.getLastname())
                .photo(user.getPhoto())
                .password(user.getPassword())
                .jobTitle(user.getJobTitle())
                .roleId(user.getRoleId())
                .departmentId(user.getDepartmentId())
                .build()
        ).flatMap(userRepository::save);
    }

    @Override
    public Mono<Void> delete(String id) {
        return userRepository.deleteById(id);
    }
}
