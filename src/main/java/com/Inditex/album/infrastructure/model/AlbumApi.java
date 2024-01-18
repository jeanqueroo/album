package com.inditex.album.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class AlbumApi {

    private Integer id;
    // Album title.
    private String title;

    private Integer userId;
}
