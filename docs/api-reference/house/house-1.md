# House 반환

## 명세

{% swagger method="get" path="/houses" baseUrl="{{domain}}" summary="부동산 반환을 위한 API 입니다." expanded="true" %}
{% swagger-description %}
쿼리를 이용한 부동산 반환 입니다.

\


주로 프론트 서버에서 사용합니다.
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

{% swagger-response status="200: OK" description="부동산 반환 성공" %}
```json
{
    "id": $house_id,
    "jibunAddress": "$house_jibunAddress",
    "roadAddress": "$house_roadAddress",
    "cost": $house_cost,
    "latitude": $house_latitude,
    "longitude": $house_longitude,
    "createdDate": "$house_createdDate",
    "modifiedDate": "$house_modifiedDate"
}
```
{% endswagger-response %}
{% endswagger %}

### Request 예시

{% tabs %}
{% tab title="curl" %}
```powershell
curl --location --request GET 'localhost:8080/api/v1/houses?jibunAddress=한남동 810'
```
{% endtab %}

{% tab title="HTTP" %}
```
GET /api/v1/houses?jibunAddress=한남동 810 HTTP/1.1
Host: localhost:8080
```
{% endtab %}

{% tab title="JavaScript(Fetch)" %}
```javascript
var requestOptions = {
  method: 'GET',
  redirect: 'follow'
};

fetch("localhost:8080/api/v1/houses?jibunAddress=한남동 810", requestOptions)
  .then(response => response.text())
  .then(result => console.log(result))
  .catch(error => console.log('error', error));
```
{% endtab %}

{% tab title="NodeJs(Axios)" %}
```javascript
var axios = require('axios');

var config = {
  method: 'get',
  url: 'localhost:8080/api/v1/houses?jibunAddress=한남동 810',
  headers: { }
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
        "id": 1,
        "jibunAddress": "한남동 810",
        "roadAddress": "서울 용산구 독서당로 111",
        "cost": 10000000000,
        "latitude": 37.0,
        "longitude": 127.0,
        "createdDate": "2022-12-27T18:37:13",
        "modifiedDate": "2022-12-27T18:37:13"
    },
    "message": "부동산 조회 성공",
    "timestamp": "2022-12-27T18:37:38.5821316",
    "errors": null
}
```
{% endtab %}
{% endtabs %}
