package com.inditex.album.domain.model;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class AlbumApiDTO {
    private Integer id;
    // Album title.
    private String title;
    private Integer userId;
    private List<PhotoApiDTO> photos = new ArrayList<>();


}
