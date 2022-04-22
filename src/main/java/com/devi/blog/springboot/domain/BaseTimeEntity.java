package com.devi.blog.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
// MappedSuperclass : JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우
// 필드들(createdDate, modifiedDate)도 Column으로 인식
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
// Entity들의 createdDate, modifiedDate를 관리하기 위한 BaseTimeEntity
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
