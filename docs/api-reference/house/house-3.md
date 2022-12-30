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

{% swagger-parameter in="query" name="roadAddress" type="String" %}
도로명 주소
{% endswagger-parameter %}

{% swagger-parameter in="query" name="danjiName" type="String" %}
단지명
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
curl --location --request DELETE 'ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/houses?roadAddress=충남_천안시_서북구_성정공원3길_4&danjiName=학산리젠다빌_3차'
```
{% endtab %}

{% tab title="HTTP" %}
```
DELETE /api/v1/houses?roadAddress=충남_천안시_서북구_성정공원3길_4&danjiName=학산리젠다빌_3차 HTTP/1.1
Host: ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com
```
{% endtab %}

{% tab title="JavaScript(Fetch)" %}
```javascript
var requestOptions = {
  method: 'DELETE',
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

var config = {
  method: 'delete',
  url: 'ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/houses?roadAddress=충남_천안시_서북구_성정공원3길_4&danjiName=학산리젠다빌_3차',
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
    "data": null,
    "message": "부동산 삭제 성공",
    "timestamp": "2022-12-27T18:38:17.6775963",
    "errors": null
}
```
{% endtab %}
{% endtabs %}
