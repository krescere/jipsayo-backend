# House 삭제

## 명세

{% swagger method="delete" path="/houses" baseUrl="{{domain}}" summary="부동산 반환을 위한 API 입니다." expanded="true" %}
{% swagger-description %}
쿼리를 이용하여 부동산 정보를 삭제합니다.

\


주로 데이터 처리를 위해 사용합니다.
{% endswagger-description %}

{% swagger-parameter in="query" name="id" type="Long" %}
부동산 아이디
{% endswagger-parameter %}

{% swagger-parameter in="query" name="jibunAddress" type="String" %}
지번 주소
{% endswagger-parameter %}

{% swagger-parameter in="query" name="roadAddress" type="String" %}
도로명 주소
{% endswagger-parameter %}

{% swagger-response status="200: OK" description="부동산 삭제 성공" %}
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
curl --location --request DELETE 'localhost:8080/api/v1/houses?jibunAddress=한남동 810'
```
{% endtab %}

{% tab title="HTTP" %}
```
DELETE /api/v1/houses?jibunAddress=한남동 810 HTTP/1.1
Host: localhost:8080
```
{% endtab %}

{% tab title="JavaScript(Fetch)" %}
```javascript
var requestOptions = {
  method: 'DELETE',
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
  method: 'delete',
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
   "message":"삭제"
}
```
{% endtab %}
{% endtabs %}
