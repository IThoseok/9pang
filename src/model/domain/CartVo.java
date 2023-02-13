package model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY) 
public class CartVo {
	private int cartNum;
	private String uId;
	private int pNum;
	private int pCount;
	private int pSellprc;
	private String pName;
//	ArrayList<CartVo> all;
//	public CartVo(int cartNum, String uId, int pNum, int pCount, int pSellprc, String pName){
//		this.cartNum = cartNum;
//		this.uId = uId;
//		this.pNum = pNum;
//		this.pCount = pCount;
//		this.pSellprc = pSellprc;
//		this.pName = pName;
//	}
	
}
