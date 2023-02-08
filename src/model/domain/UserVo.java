package model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserVo {
	private String uId;
	private String uPw;
	private String uName;
	private String uMail;
	private String uPhone;
	private String uGrade;
	private String uRdate;
	private int uReward;
	
	public UserVo(String uId, String uPw) {
		this.uId = uId;
		this.uPw = uPw;
	}
}
