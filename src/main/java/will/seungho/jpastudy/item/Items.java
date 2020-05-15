package will.seungho.jpastudy.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import will.seungho.jpastudy.BaseEntity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public class Items extends BaseEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private int price;

}

/**
 * JPA 상속관계 매핑
 *
 * 관계형 데이터베이스는 상속관계가 없다.
 * 슈퍼 타입 서브타입 관계라는 모델링 기법이 객체 상속과 유사.
 * 상속 관계 매핑: 객체의 상속과 구조와 DB의 슈퍼타입 서브타입 관게를 매핑
 *
 *
 * A. 조인 전략
 * 장점)
 * 1. 데이터가 정규화 되어있음.
 * 2. FK 참조 무결성 제약조건 활용 가능 (ITEM_ID) = 설계 굿
 * 3. 저장 공간 효율화
 *
 * 단점)
 * 1. 조회시 조인을 많이 이용해 성능 저하
 * 2. 조회 쿼리가 복잡함
 * 3. 데이터 저장시 INSERT SQL 2번 호출
 *
 * => 별 그렇게 까지 성능이 나빠지진 않음 (조인 전략이 정석이라고 생각하면 된다)
 *
 * B. 단일 테이블 전략
 * 장점)
 * 1. 조인이 필요없어, 조회 성능이 빠름
 * 2. 조회 쿼리 단순
 *
 * 단점)
 * 1. 자식 엔티티가 매핑한 컬럼은 모두 null 허용 (name, price 뺴고 다 null 허용해야함)
 * 2. 단일 테이블에 모든 것을 저장하므로, 테이블이 커질 수 있어 상황에 따라 조회 성능이 오히려 느려질 수 있다.
 *
 * C. 구현 클래스마다 테이블 전략 => 추천하지 않는 전략
 * 장점)
 * 1. 서브 타입을 명확하게 구분해서 처리할떄 효과적
 * 2. NOT NULL 제약 조건 사용 가능
 *
 * 단점)
 * 1. 여러 자식 테이블을 함께 조회할때 성능이 느림(UNION SQL)
 * 2. 자식 테이블을 통합해서 쿼리하기 어려움
 * 3. 시스템 변경시 매우 비효율적임
 */