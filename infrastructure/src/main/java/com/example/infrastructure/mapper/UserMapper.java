package com.example.infrastructure.mapper;

import com.example.domain.entity.user;
import com.example.infrastructure.jpa.UserJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static user toDomain(UserJpaEntity entity) {
        if (entity == null) {
            return null;
        }

        user d = new user();
        d.setId(entity.getId());
        d.setFullname(entity.getFullName()); // Ánh xạ từ fullName (JPA) sang fullname (Domain)
        d.setEmail(entity.getEmail());
        d.setPhone(entity.getPhone());
        d.setCreatedAt(entity.getCreatedAt());
        return d;
    }

    public static UserJpaEntity toJpa(user domain) {
        if (domain == null) {
            return null;
        }

        UserJpaEntity d = new UserJpaEntity();
        d.setId(domain.getId());
        d.setFullName(domain.getFullname());
        d.setEmail(domain.getEmail());
        d.setPhone(domain.getPhone());
        d.setCreatedAt(domain.getCreatedAt());
        return d;
    }
}
