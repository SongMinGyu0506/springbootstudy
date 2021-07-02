package com.songmingyu.admin;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/*
* 생성시간/수정시간 자동화
* 보통 엔티티에는 해당 데이터의 생성시간과 수정시간을 포함, 유지보수에 있어 굉장히 중요한 정보이기 때문
* 매번 이비에 삽입하기전 갱신하기전에 수정 등록하는 코드가 여기저기 반복적으로 들어가게 된다.
* 코드가 지저분해지고 효율성이 낮으므로 JPA Auditing을 사용
*/

@Getter
@MappedSuperclass //JPA Entity Class들이 BaseTimeEntity를 상속할 경우 필드들도 칼럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class) // BaseTimeEntity Class에 Auditing 기능을 포함시킨다.
public abstract class BaseTimeEntity {
    @CreatedDate //엔티티 생성 저장시 시간이 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate // 수정시 시간 자동 저장
    private LocalDateTime modifiedDate;
}
