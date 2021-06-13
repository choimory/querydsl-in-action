package com.choimory.querydslinaction.common.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class CommonEntity {
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime registedDateTime;
    @Column
    @LastModifiedDate
    private LocalDateTime updatedDateTime;
    @Column
    private LocalDateTime deletedDateTime;
}
