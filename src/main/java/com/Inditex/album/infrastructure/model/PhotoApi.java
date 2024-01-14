package com.Inditex.album.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class PhotoApi {

    private Integer id;
    private Integer albumId;
    private String url;
    private String thumbnailUrl;
    private String title;
}
