package com.inditex.album.domain.mapper;

import com.inditex.album.domain.model.PhotoApiDTO;
import com.inditex.album.domain.model.PhotoDTO;
import com.inditex.album.infrastructure.config.MapStructConfig;
import com.inditex.album.infrastructure.entities.PhotoEntity;
import com.inditex.album.infrastructure.model.PhotoApi;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface PhotoMapper {


    PhotoApiDTO apiTophotoApiDTO(PhotoApi photoApi);

    @Mapping(target = "createBy", source = "createBy", qualifiedByName = "setPhotoCreateBy")
    PhotoEntity dtoToPhotoEntity(PhotoDTO photoDTO);

    List<PhotoEntity> dtoToPhotoEntity(List<PhotoDTO> photoDTO);

    List<PhotoDTO> photoEntityToDto(List<PhotoEntity> photoEntities);
    @Named("setPhotoCreateBy")
    default String setPhotoCreateBy(String value) {
        // TODO BUSCAR EL USUARIO EN JWT
        return "jquero";
    }


}
