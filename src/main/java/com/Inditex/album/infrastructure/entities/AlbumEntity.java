package com.Inditex.album.infrastructure.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@ToString
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Table("ALBUM")
public class AlbumEntity implements Persistable<Integer> {

    // Album identifier.
    @Id
    @Column("ID")
    private Integer id;

    // Album title.
    @Column("TITLE")
    private String title;

    @Column("USER_ID")
    private Integer userId;

    // when is created.
    @Column("CREATE_AT")
    private LocalDateTime createAt;

    // when is created.
    @Column("CREATE_BY")
    private String createby;

    @Transient
    private List<PhotoEntity> photoEntities;

    @Override
    public boolean isNew() {
        return createAt == null;
    }
}
