# House 저장

## 명세

{% swagger method="post" path="/houses" baseUrl="{{domain}}" summary="부동산 저장을 위한 API 입니다." expanded="true" %}
{% swagger-description %}
부동산 정보를 저장합니다.

\


주로 데이터 처리를 위해 사용합니다.
{% endswagger-description %}

{% swagger-parameter in="body" name="jibunAddress" type="String" required="true" %}
지번 주소
{% endswagger-parameter %}

{% swagger-parameter in="body" name="roadAddress" type="String" required="true" %}
도로명 주소
{% endswagger-parameter %}

{% swagger-parameter in="body" name="cost" type="Long" required="true" %}
가격
{% endswagger-parameter %}

{% swagger-parameter in="body" name="latitude" type="Double" required="true" %}
위도
{% endswagger-parameter %}

{% swagger-parameter in="body" name="longitude" type="Doube" required="true" %}
경도
{% endswagger-parameter %}

{% swagger-parameter in="body" name="dealDate" required="true" %}
거래 일자
{% endswagger-parameter %}

{% swagger-parameter in="body" name="dedicatedArea" required="true" type="Long" %}
전용 면적
{% endswagger-parameter %}

{% swagger-response status="201: Created" description="부동산 삽입 성공" %}
```json
{
    // Response
    "id":$house_id
}
```
{% endswagger-response %}
{% endswagger %}

### Request 예시

{% tabs %}
{% tab title="curl" %}
```powershell
curl --location --request POST 'ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/houses' \
--header 'Content-Type: application/json' \
--data-raw '{
    "jibunAddress":"충남 천안시 서북구 성정동 1438",
    "roadAddress":"충남 천안시 서북구 성정공원3길 4",
    "hangCode":4413310200,
    "danjiName":"학산리젠다빌 3차",
    "postCode":31110,
    "cost":5500,
    "latitude":"36.8261598",
    "longitude":"127.1413382",
    "dealDate":"2023-01-15T14:53:58.333660",
    "dedicatedArea":84.0
}'
```
{% endtab %}

{% tab title="HTTP" %}
```
POST /api/v1/houses HTTP/1.1
Host: ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com
Content-Type: application/json
Content-Length: 322

{
    "jibunAddress":"충남 천안시 서북구 성정동 1438",
    "roadAddress":"충남 천안시 서북구 성정공원3길 4",
    "hangCode":4413310200,
    "danjiName":"학산리젠다빌 3차",
    "postCode":31110,
    "cost":5500,
    "latitude":"36.8261598",
    "longitude":"127.1413382",
    "dealDate":"2023-01-15T14:53:58.333660",
    "dedicatedArea":84.0
}
```
{% endtab %}

{% tab title="JavaScript(Fetch)" %}
```javascript
var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

var raw = JSON.stringify({
  "jibunAddress": "충남 천안시 서북구 성정동 1438",
  "roadAddress": "충남 천안시 서북구 성정공원3길 4",
  "hangCode": 4413310200,
  "danjiName": "학산리젠다빌 3차",
  "postCode": 31110,
  "cost": 5500,
  "latitude": "36.8261598",
  "longitude": "127.1413382",
  "dealDate": "2023-01-15T14:53:58.333660",
  "dedicatedArea": 84
});

var requestOptions = {
  method: 'POST',
  headers: myHeaders,
  body: raw,
  redirect: 'follow'
};

fetch("ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/houses", requestOptions)
  .then(response => response.text())
  .then(result => console.log(result))
  .catch(error => console.log('error', error));
```
{% endtab %}

{% tab title="NodeJs(Axios)" %}
```javascript
var axios = require('axios');
var data = JSON.stringify({
  "jibunAddress": "충남 천안시 서북구 성정동 1438",
  "roadAddress": "충남 천안시 서북구 성정공원3길 4",
  "hangCode": 4413310200,
  "danjiName": "학산리젠다빌 3차",
  "postCode": 31110,
  "cost": 5500,
  "latitude": "36.8261598",
  "longitude": "127.1413382",
  "dealDate": "2023-01-15T14:53:58.333660",
  "dedicatedArea": 84
});

var config = {
  method: 'post',
  url: 'ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/houses',
  headers: { 
    'Content-Type': 'application/json'
  },
  data : data
};

axios(config)
.then(function (response) {
  console.log(JSON.stringify(response.data));
})
.catch(function (error) {
  console.log(error);
});
```
{% endtab %}
{% endtabs %}

### Response 예시

{% tabs %}
{% tab title="JSON" %}
```json
{
    "data": {
        "id": 1
    },
    "message": "부동산 저장 성공",
    "timestamp": "2022-12-27T18:37:14.0025331",
    "errors": null

```
{% endtab %}
{% endtabs %}
