package com.Inditex.album.domain.mapper;

import com.Inditex.album.domain.model.AlbumApiDTO;
import com.Inditex.album.domain.model.AlbumDTO;
import com.Inditex.album.infrastructure.config.MapStructConfig;
import com.Inditex.album.infrastructure.entities.AlbumEntity;
import com.Inditex.album.infrastructure.model.AlbumApi;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface AlbumMapper {

    AlbumDTO AlbumApiToAlbumDto(AlbumApiDTO albumApiDTO);

    @Mapping(target = "createby", source = "createby", qualifiedByName = "setCreateBy")
    AlbumEntity AlbumDTOToEntity(AlbumDTO albumDTO);

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
