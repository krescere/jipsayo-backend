# DealHistory 조회

## 개요

거래내역을 조회하는 API 입니다.

카카오 주소에 명시된 정확한 도로명+단지명을 입력해주세요.

## 명세

{% swagger method="post" path="/api/v1/research" baseUrl="{{domain}}" summary="거래내역 조회을 위한 API 입니다." expanded="true" %}
{% swagger-description %}

{% endswagger-description %}

{% swagger-parameter in="body" name="roadAddress" required="true" type="String" %}
도로명주소
{% endswagger-parameter %}

{% swagger-parameter in="body" name="danjiName" type="String" required="true" %}
단지명
{% endswagger-parameter %}

{% swagger-response status="200: OK" description="거래내역 조회 성공" %}
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
curl --location 'ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/dealHistories?roadAddress=%EA%B2%BD%EA%B8%B0%20%EA%B3%A0%EC%96%91%EC%8B%9C%20%EB%8D%95%EC%96%91%EA%B5%AC%20%EB%B0%B1%EC%96%91%EB%A1%9C%2027&danjiName=%EC%98%A5%EB%B9%9B%EB%A7%88%EC%9D%8414%EB%8B%A8%EC%A7%80%EC%95%84%ED%8C%8C%ED%8A%B8'
```
{% endtab %}

{% tab title="http" %}
```
GET /api/v1/dealHistories?roadAddress=경기 고양시 덕양구 백양로 27&danjiName=옥빛마을14단지아파트 HTTP/1.1
Host: ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com
```
{% endtab %}

{% tab title="JavaScript(Fetch)" %}
```javascript
var requestOptions = {
  method: 'GET',
  redirect: 'follow'
};

fetch("ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/dealHistories?roadAddress=경기 고양시 덕양구 백양로 27&danjiName=옥빛마을14단지아파트", requestOptions)
  .then(response => response.text())
  .then(result => console.log(result))
  .catch(error => console.log('error', error));
```
{% endtab %}

{% tab title="NodeJs(Axios)" %}
```javascript
const axios = require('axios');

let config = {
  method: 'get',
  maxBodyLength: Infinity,
  url: 'ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/dealHistories?roadAddress=경기 고양시 덕양구 백양로 27&danjiName=옥빛마을14단지아파트',
  headers: { }
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

### Response 예시

{% tabs %}
{% tab title="JSON" %}
```postman_json
{
    "data": {
        "house": {
            "id": 2,
            "jibunAddress": "경기 고양시 덕양구 화정동 1004",
            "roadAddress": "경기 고양시 덕양구 백양로 27",
            "hangCode": 4128112300,
            "danjiName": "옥빛마을14단지아파트",
            "postCode": 10503,
            "latitude": 37.6285682,
            "longitude": 126.8328534,
            "houseDetails": [
                {
                    "id": 2,
                    "count": 0,
                    "dedicatedArea": 50,
                    "deals": [
                        {
                            "id": 2,
                            "cost": 31300,
                            "dealDate": "2023-04-01T00:00:00"
                        },
                        {
                            "id": 6,
                            "cost": 30000,
                            "dealDate": "2023-04-05T00:00:00"
                        }
                    ]
                }
            ]
        }
    },
    "message": "거래내역 조회 성공",
    "timestamp": "2023-04-17T20:44:32.568186",
    "errors": null
}
```
{% endtab %}
{% endtabs %}
