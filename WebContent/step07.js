let pName= "";
let pSellprc = 0;
let pCount = 0;
let totPrc = 0;
let before = `"<table id="cart" class="table table-hover table-condensed"><thead><tr><thead><tr><th style="width:50%" >상품명</th><th style="width:10%; text-align: center">가격</th><th style="width:8%; text-align: center">수량</th><th style="width:10%"></th></tr></thead>"`;
let loop = "";
let after = "";
let product = "";
let product2 = "";
let index = 0;
let cartnum = 0;
$(document).ready(function reqResAxios1(){
		axios({
			  method: 'post',
			  url:'cccc/cart',
			  withCredentials: true
			}) .catch(function (error){
				document.getElementById(".container").innerHTML ="잠시후 재요청하세요";
			})
			.then(function(resData){
				//product2 = resData;
				product = resData.data;
				product2 = resData.data;
				console.log(product);
				for(index in product){
					pName = product[index].pname;
					pSellprc = product[index].psellprc;
					pCount = product[index].pcount;
					totPrc += pSellprc*pCount;
					cartnum = index;
					loop += `"<tbody class="${cartnum}"><tr><td data-th="Product"><div class="row"><div class="col-sm-2 hidden-xs"><img src="http://placehold.it/100x100" alt="..." class="img-responsive"/></div><div class="col-sm-10"><h4 class="nomargin">${pName}</h4></div></div></td><td data-th="Price" id="price${index}">${pSellprc}원</td><td data-th="Quantity"><input type="number" id = "count${index}"class="form-control text-center" value="${pCount}"min="1"max="99"onclick="reqResAxios2(product2)"></td><td class="actions" data-th=""><button class="btn btn-danger btn-sm"onclick="deleteCart(this)"><i class="fa fa-trash-o"></i></button></td></tr></tbody>"`;
					after = `"<tfoot><tr><td><a href="#" class="btn btn-warning"><i class="fa fa-angle-left"></i> 쇼핑계속하기--메인홈으로보내</a></td><td class="hidden-xs text-center"><strong id="tot">${totPrc}원</strong></td><td><a href="#" class="btn btn-success btn-block">주문하기주문페이지보내<i class="fa fa-angle-right"></i></a></td></tr></tfoot></table>"`;
				}
				document.querySelector(".container").innerHTML = before+loop+after;
		});
});	
	

function reqResAxios2(c){
	let price = "";
	let tot = 0;
	let p = c
	for (let a =0; a<=index; a++){
		price = document.querySelector(`#price${a}`).innerHTML;
		tot += Number(price.slice(0, -1)) * document.querySelector(`#count${a}`).value;
		document.querySelector("#tot").innerHTML = tot+"원";
		p[a].pcount = document.querySelector(`#count${a}`).value;
	}
	ReqResPostAxios1(p);
}	

function ReqResPostAxios1(product2){
	axios.post('cccc/updatesess',  JSON.stringify(product2), { // json을 json타입의 text로 변환
		  headers: {
		    "Content-Type": `application/json`, // application/json 타입 선언
		  },
		})
		.then((res) => {
		  console.log(res);
		});	
	
	
}

function deleteCart(obj){
let data = 0;	
	console.log(obj.parentNode.parentNode.parentNode.className); //.className
	console.log(product2[Number(obj.parentNode.parentNode.parentNode.className)]); //.className
	console.log(JSON.stringify(product2[Number(obj.parentNode.parentNode.parentNode.className)]), "이건파싱한거"); //.className
	data = product2[Number(obj.parentNode.parentNode.parentNode.className)].cartNum;//카트넘버 변수넣기
	console.log(data, "data찍어보기");
	obj.parentNode.parentNode.parentNode.innerHTML = "";
	axios.delete(`cccc/${data}`)
		.then((res) => {
		  console.log(res);
		});	
//	axios.delete(`cccc/deleteCart`, {data:{cartNum: data}, headers:{Authorization: "token"}})
//	axios.delete('cccc/deleteCart',  JSON.stringify(product2[Number(obj.parentNode.parentNode.parentNode.className)]), { // json을 json타입의 text로 변환
//		  headers: {
//		    "Content-Type": `application/json`, // application/json 타입 선언
//		  },
//		})
//		.then((res) => {
//		  console.log(res);
//		});	
	
}

