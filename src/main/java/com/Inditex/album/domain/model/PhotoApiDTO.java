package com.inditex.album.domain.model;

import lombok.*;

@Data
@Builder
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class PhotoApiDTO {

    private Integer id;
    private Integer albumId;
    private String url;
    private String thumbnailUrl;
    private String title;
}
