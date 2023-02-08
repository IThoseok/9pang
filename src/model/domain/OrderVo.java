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
public class OrderVo {
	private int orderNum;
	private String uId;
	private String orderDate;
	private String receiverName;
	private String receiverPhone;
	private String receiverAddr;
	private int trackingNum;
	
	
	
}
