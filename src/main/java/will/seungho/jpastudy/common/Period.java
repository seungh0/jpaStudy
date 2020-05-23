package will.seungho.jpastudy.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Embeddable
public class Period {

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	public Period(LocalDateTime startDate, LocalDateTime endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public boolean hasWorkTime() {
		LocalDateTime now = LocalDateTime.now();
		return now.isBefore(endDate) && now.isAfter(startDate);
	}

}
