package com.miskowskij.app.service.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.miskowskij.app.persistence.user.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

final class UserMapper {

    static List<UserDTO> mapEntitiesIntoDTOs(List<User> entities) {
        return entities.stream()
                .map(UserMapper::mapEntityIntoDTO)
                .collect(toList());
    }

    static UserDTO mapEntityIntoDTO(User entity) {
    	UserDTO dto = new UserDTO();

    	dto.setId(entity.getId());
    	dto.setCreationTime(entity.getCreationTime());
        dto.setUsername(entity.getUsername());
        dto.setFirstname(entity.getFirstname());
        dto.setLastname(entity.getLastname());
        dto.setModificationTime(entity.getModificationTime());
        
        return dto;
    }

    static Page<UserDTO> mapEntityPageIntoDTOPage(Pageable page, Page<User> source) {
        List<UserDTO> dtos = mapEntitiesIntoDTOs(source.getContent());
        return new PageImpl<>(dtos, page, source.getTotalElements());
    }
}