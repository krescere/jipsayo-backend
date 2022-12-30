# House 수정

## 명세

{% swagger method="put" path="/houses" baseUrl="{{domain}}" summary="부동산 수정을 위한 API 입니다." expanded="true" %}
{% swagger-description %}
쿼리를 이용하여 부동산 정보를 수정합니다.

\


주로 데이터 처리를 위해 사용합니다.
{% endswagger-description %}

{% swagger-parameter in="query" name="id" type="Long" %}
부동산 아이디
{% endswagger-parameter %}

{% swagger-parameter in="query" name="roadAddress" type="String" %}
도로명 주소
{% endswagger-parameter %}

{% swagger-parameter in="query" name="danjiName" type="String" %}
단지명
{% endswagger-parameter %}

{% swagger-parameter in="body" name="jibunAddress" type="String" %}
지번 주소
{% endswagger-parameter %}

{% swagger-parameter in="body" name="roadAddress" type="String" %}
도로명 주소
{% endswagger-parameter %}

{% swagger-parameter in="body" name="cost" type="Long" %}
가격
{% endswagger-parameter %}

{% swagger-parameter in="body" name="latitude" type="Double" %}
위도
{% endswagger-parameter %}

{% swagger-parameter in="body" name="longitude" type="Double" %}
경도
{% endswagger-parameter %}

{% swagger-response status="200: OK" description="부동산 수정 성공" %}
```json
{
    "message":$message
}
```
{% endswagger-response %}
{% endswagger %}

### Request 예시

{% tabs %}
{% tab title="curl" %}
```powershell
curl --location --request PUT 'ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/houses?roadAddress=충남_천안시_서북구_성정공원3길_4&danjiName=학산리젠다빌_3차' \
--header 'Content-Type: application/json' \
--data-raw '{
    "cost":2000
}'
```
{% endtab %}

{% tab title="HTTP" %}
```
PUT /api/v1/houses?roadAddress=충남_천안시_서북구_성정공원3길_4&danjiName=학산리젠다빌_3차 HTTP/1.1
Host: ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com
Content-Type: application/json
Content-Length: 21

{
    "cost":2000
}
```
{% endtab %}

{% tab title="JavaScript(Fetch)" %}
```javascript
var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

var raw = JSON.stringify({
  "cost": 2000
});

var requestOptions = {
  method: 'PUT',
  headers: myHeaders,
  body: raw,
  redirect: 'follow'
};

fetch("ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/houses?roadAddress=충남_천안시_서북구_성정공원3길_4&danjiName=학산리젠다빌_3차", requestOptions)
  .then(response => response.text())
  .then(result => console.log(result))
  .catch(error => console.log('error', error));
```
{% endtab %}

{% tab title="NodeJs(Axios)" %}
```javascript
var axios = require('axios');
var data = JSON.stringify({
  "cost": 2000
});

var config = {
  method: 'put',
  url: 'ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/houses?roadAddress=충남_천안시_서북구_성정공원3길_4&danjiName=학산리젠다빌_3차',
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
    "data": null,
    "message": "부동산 수정 성공",
    "timestamp": "2022-12-27T18:38:02.5930068",
    "errors": null
}
```
{% endtab %}
{% endtabs %}
