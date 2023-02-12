const product2 = [
 { name: '라면', price: 2000 }, 
  { name: '아이스크림', price: 1500 }, 
  { name: '과자', price: 2000 },
  { name: '맥주', price: 4000 }, 
  { name: '물', price: 1500 }
];

let three = "";
let product = "";

$(document).ready(function ReqResPostAxios1(){
			
		axios.get('step03')
		.then(function (resData){
			
			product = resData.data;
			console.log('그냥2', product);
		})
		.catch(function (error){
			document.getElementById("view").innerHTML ="잠시후 재요청"
		}).then(function () {

		//console.log('그냥2', json2);
		//three = product.filter(list => list.price <= 3000);
		//json2.map(list => saleProduct.name = list.name);
		//product.forEach(sale);
		//console.log('최종', salePrice);
		   	
		});
});
//일단 10일자 수업내용중 체크할점 이거 지금 포스트로했네? get으로도 바꿔보고 map이 왜 맛이간건지 또 즉시실행으로 더 깔끔하게 할 방법 없었는지
//config 말고 스샷찍어둔거처럼 url에 데이터 넣어서 보내는걸로도 해보고 
//아 그리고 jsp단에서 제이슨 배열을 좀 바꿔서 보내면 어떤식으로 또 변경이 되는지도 체크 ㄱㄱ
//그리고 contentType 그냥 텍스트로하면 어떻게되는지도 보삼

function drawTable(list) {
  
  document.getElementById("view").innerHTML = ``;

  let table = document.createElement("table");  //<table></table> 테이블생성
  table.classList.add('w3-table-all');//<table class='w3-table-all'> 테이블에 속성부여
  

  let tr = document.createElement("tr");  //<tr></tr> tr생성
  let th = document.createElement("th"); //<th> th생성
  th.style.textAlign = 'center';//<th style="textAlign:center"></th> th속성부여 가운데 정렬
  th.innerHTML = '상품명';//<th style="textAlign:center">상품명</th> th에 text 추가
  tr.appendChild(th);  //<tr><th style="textAlign:center">상품명</th></tr> tr의 자식개체로 th지정
 
  th = document.createElement("th");//같은 변수에 다시 새로운 th개체 생성
  th.style.textAlign = 'center';//속성부여 가운데정렬
  th.innerHTML = '가격';//text추가
  tr.appendChild(th);//자식개체 지정

  table.appendChild(tr);  //<table class='w3-table-all'><tr>...</tr></table> tr은 테이블의 자식으로

  let totalPrice = [];

  list.forEach(function (item) {
    let tr = document.createElement("tr");
    tr.classList.add('w3-hover-green');

    totalPrice.push(item.price);//일단 가격만 뽑아서 숫자로 바꿔가지고 배열에 넣고
    
    for (let key in item) {
      let td = document.createElement("td");
      td.style.textAlign = 'center';
      td.innerHTML = item[key];
      //console.log(key, item[key]);
      //console.log(item);
      
      tr.appendChild(td);
    };
    table.appendChild(tr);
  });

    totalPrice = totalPrice.reduce(reduceFun);//매핑한 배열 리듀스로 합쳐서 다시 변수에 저장
  
    tr = document.createElement("tr");
    tr.classList.add('w3-hover-green');
    td = document.createElement("td");
    td.style.textAlign = 'center'
    td.innerHTML = '총합';
    tr.appendChild(td);
    td = document.createElement("td");
    td.style.textAlign = 'center'
    td.innerHTML = `${totalPrice}`;
    tr.appendChild(td);
    table.appendChild(tr);
  
  return document.getElementById("view").appendChild(table);
}

function mapFun(no){//list 받아서 숫자만 뽑아줄거임
  return no.price;
}

function reduceFun(no1, no2){// 리듀스
  return no1 + no2;
}

let saleProduct = [];
function sale(item) {//그냥 위에서 불러서 작업하고 나중에 손봐주자고잉
	saleProduct.push({name: item.name, price: item.price*0.5});
};


document.querySelector('#btn1').addEventListener('click', function (item) {
  return drawTable(product);
});

document.querySelector('#btn2').addEventListener('click', function (item) {
  return drawTable(saleProduct);
});

document.querySelector('#btn3').addEventListener('click', function (item) {
  return drawTable(three);
});



