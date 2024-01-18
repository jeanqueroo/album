package com.inditex.album.infrastructure.entities;

import lombok.*;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Table("PHOTO")
public class PhotoEntity implements Persistable<Integer> {

    // PHOTO identifier.
    @Column("ID")
    private Integer id;
    @Column("ALBUM_ID")
    private Integer albumId;
    @Column("URL")
    private String url;
    @Column("THUMBAIL_URL")
    private String thumbnailUrl;
    @Column("TITLE")
    private String title;
    // when is created.
    @Column("CREATE_AT")
    private LocalDateTime createAt;

    // when is created.
    @Column("CREATE_BY")
    private String createBy;

    @Override
    public boolean isNew() {
        return createAt == null;
    }
}
