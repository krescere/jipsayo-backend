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

{% swagger-parameter in="body" name="latitude" type="Double" %}
위도
{% endswagger-parameter %}

{% swagger-parameter in="body" name="longitude" type="Doube" %}
경도
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
curl --location --request POST 'localhost:8080/api/v1/houses' \
--header 'Content-Type: application/json' \
--data-raw '{
    "jibunAddress":"한남동 810",
    "roadAddress":"서울 용산구 독서당로 111",
    "cost":1000,
    "latitude":37.0,
    "longitude":127.0
}'
```
{% endtab %}

{% tab title="HTTP" %}
```
POST /api/v1/houses HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 136

{
    "jibunAddress":"한남동 810",
    "roadAddress":"서울 용산구 독서당로 111",
    "cost":1000,
    "latitude":37.0,
    "longitude":127.0
}
```
{% endtab %}

{% tab title="JavaScript(Fetch)" %}
```javascript
var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

var raw = JSON.stringify({
  "jibunAddress": "한남동 810",
  "roadAddress": "서울 용산구 독서당로 111",
  "cost": 1000,
  "latitude": 37,
  "longitude": 127
});

var requestOptions = {
  method: 'POST',
  headers: myHeaders,
  body: raw,
  redirect: 'follow'
};

fetch("localhost:8080/api/v1/houses", requestOptions)
  .then(response => response.text())
  .then(result => console.log(result))
  .catch(error => console.log('error', error));
```
{% endtab %}

{% tab title="NodeJs(Axios)" %}
```javascript
var axios = require('axios');
var data = JSON.stringify({
  "jibunAddress": "한남동 810",
  "roadAddress": "서울 용산구 독서당로 111",
  "cost": 1000,
  "latitude": 37,
  "longitude": 127
});

var config = {
  method: 'post',
  url: 'localhost:8080/api/v1/houses',
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
    "id": 1
}
```
{% endtab %}
{% endtabs %}
