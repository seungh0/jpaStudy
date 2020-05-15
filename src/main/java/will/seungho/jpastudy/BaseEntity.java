package will.seungho.jpastudy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

	private String createdBy;

	private LocalDateTime createdDateTime;

	private String lastModifiedBy;

	private LocalDateTime lastModifiedDateTime;

	/**
	 * 참고로 JPA Audinting으로 자동화 할 수 있음.
	 */

}

/**
 * 상속관계 매핑 X
 * 엔티티 X, 테이블과 매핑X
 * 부모 클래스를 상속받는 자식 클래스에게 매핑 정보만 제공
 * 조회, 검색 불가
 * 추상클래스 권장!
 */