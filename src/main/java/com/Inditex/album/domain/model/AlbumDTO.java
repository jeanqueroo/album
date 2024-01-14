package com.Inditex.album.domain.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDTO {

    private Integer id;
    // Album title.
    private String title;
    private Integer userId;
    // when is created.
    @JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    private LocalDateTime createAt;
    // when is created.
    private String createby;

    private List<PhotoDTO> photos = new ArrayList<>();

}
