package com.Inditex.album.domain.model;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class PhotoDTO {

    private Integer id;
    private Integer albumId;
    private String url;
    private String thumbnailUrl;
    private String title;
    private LocalDateTime createAt;
    private String createby;
}
