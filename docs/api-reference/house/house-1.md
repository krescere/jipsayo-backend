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
curl --location --request GET 'ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/houses?roadAddress=충남_천안시_서북구_성정공원3길_4&danjiName=학산리젠다빌_3차'
```
{% endtab %}

{% tab title="HTTP" %}
```
GET /api/v1/houses?roadAddress=충남_천안시_서북구_성정공원3길_4&danjiName=학산리젠다빌_3차 HTTP/1.1
Host: ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com
```
{% endtab %}

{% tab title="JavaScript(Fetch)" %}
```javascript
var requestOptions = {
  method: 'GET',
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
  method: 'get',
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
    "data": {
        "id": 1,
        "jibunAddress": "충남 천안시 서북구 성정동 1438",
        "roadAddress": "충남 천안시 서북구 성정공원3길 4",
        "cost": 5500,
        "hangCode": 4413310200,
        "danjiName": "학산리젠다빌 3차",
        "postCode": 31110,
        "latitude": 36.8261598433825,
        "longitude": 127.141338247074,
        "createdDate": "2022-12-31T00:47:58",
        "modifiedDate": "2022-12-31T00:47:58"
    },
    "message": "부동산 조회 성공",
    "timestamp": "2022-12-31T00:50:11.540945",
    "errors": null
}
```
{% endtab %}
{% endtabs %}
