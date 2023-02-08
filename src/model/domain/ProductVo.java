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
public class ProductVo {
	private int pNum;
	private String pCate;
	private String pName;
	private int pSellprc;
	private int pBuyprc;
	private int pStock;
	private String pRdate;
	private int pSell;
	private String pDetail;
	
}
