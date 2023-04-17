# DealHistory 저장

## 개요

거래내역을 저장하는 API 입니다.

내부적으로 실거래 API 를 호출해옵니다.

## 명세

{% swagger method="post" path="/api/v1/research" baseUrl="{{domain}}" summary="거래내역 저장을 위한 API 입니다." expanded="true" %}
{% swagger-description %}

{% endswagger-description %}

{% swagger-parameter in="body" name="pageNo" type="Int" required="true" %}
페이지번호
{% endswagger-parameter %}

{% swagger-parameter in="body" name="numOfRows" type="Int" required="true" %}
한 페이지당 데이터 갯수
{% endswagger-parameter %}

{% swagger-parameter in="body" name="lawdCd" required="true" type="String" %}
법정코드
{% endswagger-parameter %}

{% swagger-parameter in="body" name="dearYmd" required="true" type="String" %}
날짜
{% endswagger-parameter %}

{% swagger-response status="201: Created" description="거래내역 저장 성공" %}
{% code lineNumbers="true" %}
```json
{
    "id":$research_id
}
```
{% endcode %}
{% endswagger-response %}
{% endswagger %}

### Request 예시

{% tabs %}
{% tab title="curl" %}
```powershell
curl --location 'ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/dealHistories' \
--header 'Content-Type: application/json' \
--data '{
    "pageNo":1,
    "numOfRows":10,
    "lawdCd":"11110",
    "dearYmd":"202304"
} '
```
{% endtab %}

{% tab title="http" %}
```
POST /api/v1/dealHistories HTTP/1.1
Host: ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com
Content-Type: application/json
Content-Length: 90

{
    "pageNo":1,
    "numOfRows":10,
    "lawdCd":"11110",
    "dearYmd":"202304"
} 
```
{% endtab %}

{% tab title="JavaScript(Fetch)" %}
```javascript
var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

var raw = JSON.stringify({
  "pageNo": 1,
  "numOfRows": 10,
  "lawdCd": "11110",
  "dearYmd": "202304"
});

var requestOptions = {
  method: 'POST',
  headers: myHeaders,
  body: raw,
  redirect: 'follow'
};

fetch("ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/dealHistories", requestOptions)
  .then(response => response.text())
  .then(result => console.log(result))
  .catch(error => console.log('error', error));
```
{% endtab %}

{% tab title="NodeJs(Axios)" %}
```javascript
const axios = require('axios');
let data = JSON.stringify({
  "pageNo": 1,
  "numOfRows": 10,
  "lawdCd": "11110",
  "dearYmd": "202304"
});

let config = {
  method: 'post',
  maxBodyLength: Infinity,
  url: 'ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/dealHistories',
  headers: { 
    'Content-Type': 'application/json'
  },
  data : data
};

axios.request(config)
.then((response) => {
  console.log(JSON.stringify(response.data));
})
.catch((error) => {
  console.log(error);
});

```
{% endtab %}
{% endtabs %}
