package com.Inditex.album.domain.mapper;

import com.Inditex.album.domain.model.PhotoApiDTO;
import com.Inditex.album.domain.model.PhotoDTO;
import com.Inditex.album.infrastructure.config.MapStructConfig;
import com.Inditex.album.infrastructure.entities.PhotoEntity;
import com.Inditex.album.infrastructure.model.PhotoApi;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface PhotoMapper {


    PhotoApiDTO apiTophotoApiDTO(PhotoApi photoApi);

    @Mapping(target = "createby", source = "createby", qualifiedByName = "setPhotoCreateBy")
    PhotoEntity dtoToPhotoEntity(PhotoDTO photoDTO);

    List<PhotoEntity> dtoToPhotoEntity(List<PhotoDTO> photoDTO);

    List<PhotoDTO> photoEntityToDto(List<PhotoEntity> photoEntities);
    @Named("setPhotoCreateBy")
    default String setPhotoCreateBy(String value) {
        // TODO BUSCAR EL USUARIO EN JWT
        return "jquero";
    }


}
