package com.inditex.album.domain.mapper;

import com.inditex.album.domain.model.AlbumApiDTO;
import com.inditex.album.domain.model.AlbumDTO;
import com.inditex.album.infrastructure.config.MapStructConfig;
import com.inditex.album.infrastructure.entities.AlbumEntity;
import com.inditex.album.infrastructure.model.AlbumApi;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface AlbumMapper {

    @Mapping(target = "photos.createAt", ignore = true)
    @Mapping(target = "photos.createBy", ignore = true)
    AlbumDTO albumApiToAlbumDto(AlbumApiDTO albumApiDTO);

    @Mapping(target = "createby", source = "createby", qualifiedByName = "setCreateBy")
    @Mapping(target = "photoEntities", ignore = true)
    AlbumEntity albumDTOToEntity(AlbumDTO albumDTO);

    @Mapping(target = "photos", ignore = true)
    AlbumDTO albumEntityToDTO(AlbumEntity albumEntity);

    @Mapping(target = "photos", ignore = true)
    AlbumApiDTO apiToApiDTO(AlbumApi albumApi);

    @Named("setCreateBy")
    default String setCreateBy(String value) {
        // TODO BUSCAR EL USUARIO EN JWT
        return "jquero";
    }

}
