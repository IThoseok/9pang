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
public class OrderDetailVo {	
	private int orderDetailNum;
	private int orderNum;
	private int pNum;
	private int pCount;
	private int pSellprc;
	private String orderStat;
	private int refund;
	
	
	
	
}
