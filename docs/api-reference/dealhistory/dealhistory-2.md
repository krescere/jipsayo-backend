# DealHistory 필터링

## 개요

거래내역을 필터링하는 API 입니다.\
가격, 거리 기준으로 필터링 됩니다.

{% hint style="info" %}
같은 주소를 갖는 부동산의 경우에는\
최근 거래를 기준으로 갖고옵니다.

\
예를들어 같은 강남역 아파트들이 검색 범위에 해당된다면\
최신 거래된 강남역 아파트만 조회됩니다.
{% endhint %}

## 명세

{% swagger method="post" path="/api/v1/research" baseUrl="{{domain}}" summary="거래내역 필터을 위한 API 입니다." expanded="true" %}
{% swagger-description %}

{% endswagger-description %}

{% swagger-parameter in="body" name="latitude" required="true" type="String" %}
위도
{% endswagger-parameter %}

{% swagger-parameter in="body" name="longitude" type="String" required="true" %}
경도
{% endswagger-parameter %}

{% swagger-parameter in="body" name="lowCost" type="Long" required="true" %}
최저가격
{% endswagger-parameter %}

{% swagger-parameter in="body" name="highCost" type="Long" required="true" %}
최고가격
{% endswagger-parameter %}

{% swagger-parameter in="body" name="time" type="Long" required="true" %}
소요시간
{% endswagger-parameter %}

{% swagger-parameter in="body" name="count" type="Long" required="true" %}
반환갯수
{% endswagger-parameter %}

{% swagger-response status="200: OK" description="거래내역 필터 성공" %}
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
curl --location 'ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/dealHistories/filter?latitude=37.6285682&longitude=126.8328534&lowCost=0&highCost=100000&time=60&count=5'
```
{% endtab %}

{% tab title="http" %}
```
GET /api/v1/dealHistories/filter?latitude=37.6285682&longitude=126.8328534&lowCost=0&highCost=100000&time=60&count=5 HTTP/1.1
Host: ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com
```
{% endtab %}

{% tab title="JavaScript(Fetch)" %}
```javascript
var requestOptions = {
  method: 'GET',
  redirect: 'follow'
};

fetch("ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/dealHistories/filter?latitude=37.6285682&longitude=126.8328534&lowCost=0&highCost=100000&time=60&count=5", requestOptions)
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
  url: 'ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/dealHistories/filter?latitude=37.6285682&longitude=126.8328534&lowCost=0&highCost=100000&time=60&count=5',
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
    "data": [
        {
            "roadAddress": "경기 고양시 덕양구 세솔로 25",
            "danjiName": "동산마을 22단지 호반베르디움",
            "cost": 66000,
            "latitude": 37.6463291,
            "longitude": 126.8888416,
            "time": 43,
            "dealDate": "2023-04-03T00:00:00",
            "dedicatedArea": 84.94
        },
        {
            "roadAddress": "경기 고양시 일산서구 강선로 70",
            "danjiName": "강선마을8단지아파트",
            "cost": 53500,
            "latitude": 37.6698493,
            "longitude": 126.7654758,
            "time": 46,
            "dealDate": "2023-04-04T00:00:00",
            "dedicatedArea": 84.99
        },
        {
            "roadAddress": "경기 김포시 고촌읍 고송로 7",
            "danjiName": "김포 고촌 우방아이유쉘",
            "cost": 71000,
            "latitude": 37.6043797,
            "longitude": 126.7675615,
            "time": 44,
            "dealDate": "2023-04-01T00:00:00",
            "dedicatedArea": 149.09
        },
        {
            "roadAddress": "경기 고양시 덕양구 세솔로 105",
            "danjiName": "삼송마을동원로얄듀크",
            "cost": 62000,
            "latitude": 37.6526202,
            "longitude": 126.8873348,
            "time": 44,
            "dealDate": "2023-04-08T00:00:00",
            "dedicatedArea": 84.96
        },
        {
            "roadAddress": "경기 김포시 유현로 200",
            "danjiName": "풍무 푸르지오",
            "cost": 48500,
            "latitude": 37.6096443,
            "longitude": 126.7326057,
            "time": 55,
            "dealDate": "2023-04-01T00:00:00",
            "dedicatedArea": 72.19
        }
    ],
    "message": "거래내역 필터링 성공",
    "timestamp": "2023-04-17T20:33:45.457565",
    "errors": null
}
```
{% endtab %}
{% endtabs %}
