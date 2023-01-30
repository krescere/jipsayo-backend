---
description: 나이대 이미지 Url 리스트를 반환받습니다.
---

# Age Image Url 반환

{% hint style="info" %}
이미지는 S3에 저장되어있습니다.\
해당 이미지들은 static한 경로가 정해져있습니다.

그러나 어떤 이미지들이 있는지 알 수가 없습니다.\
그래서 해당 API를 통해 이미지 경로를 반환 받을 수 있습니다.
{% endhint %}

{% hint style="danger" %}
이미지는 삭제되거나 수정될 수 있습니다.\
해당 API 요청을 통해 어떤 이미지 Url 이 유효한지 확인후,\
실제 이미지 경로에 접근해야합니다.
{% endhint %}



## 명세

{% swagger method="get" path="/images/age" baseUrl="{{domain}}" summary="나이대 이미지 Url 리스트를 반환받습니다." expanded="true" %}
{% swagger-description %}
집을 구매하는데 얼마나 걸릴지 알려주는 나이대별 이미지 url 리스트입니다. 

\


s3에 접근하기위한 이미지 url이 리스트로 반환됩니다.
{% endswagger-description %}

{% swagger-response status="200: OK" description="연령대 이미지 조회 성공" %}
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
curl --location --request GET 'ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/images/age'
```
{% endtab %}

{% tab title="HTTP" %}
```
GET /api/v1/images/age HTTP/1.1
Host: ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com
```
{% endtab %}

{% tab title="JavaScript(Fetch)" %}
```javascript
var requestOptions = {
  method: 'GET',
  redirect: 'follow'
};

fetch("ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/images/age", requestOptions)
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
  url: 'ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/images/age',
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

{% hint style="info" %}
반환시 저장된 이미지의 이름 순서대로 정렬되어 반환됩니다.
{% endhint %}

{% tabs %}
{% tab title="JSON" %}
```
{
    "data": [
        "https://jipsayo-age.s3.ap-northeast-2.amazonaws.com/age/0.jpg",
        "https://jipsayo-age.s3.ap-northeast-2.amazonaws.com/age/1.jpg",
        "https://jipsayo-age.s3.ap-northeast-2.amazonaws.com/age/2.jpg",
        "https://jipsayo-age.s3.ap-northeast-2.amazonaws.com/age/3.jpg",
        "https://jipsayo-age.s3.ap-northeast-2.amazonaws.com/age/5.jpg",
        "https://jipsayo-age.s3.ap-northeast-2.amazonaws.com/age/6.jpg",
        "https://jipsayo-age.s3.ap-northeast-2.amazonaws.com/age/10.jpg",
        "https://jipsayo-age.s3.ap-northeast-2.amazonaws.com/age/12.jpg",
        "https://jipsayo-age.s3.ap-northeast-2.amazonaws.com/age/14.jpg",
        ...
        "https://jipsayo-age.s3.ap-northeast-2.amazonaws.com/age/91.jpg",
        "https://jipsayo-age.s3.ap-northeast-2.amazonaws.com/age/95.jpg",
        "https://jipsayo-age.s3.ap-northeast-2.amazonaws.com/age/97.jpg",
        "https://jipsayo-age.s3.ap-northeast-2.amazonaws.com/age/100.jpg",
        "https://jipsayo-age.s3.ap-northeast-2.amazonaws.com/age/120.jpg",
        "https://jipsayo-age.s3.ap-northeast-2.amazonaws.com/age/130.jpg",
        "https://jipsayo-age.s3.ap-northeast-2.amazonaws.com/age/150.jpg",
        "https://jipsayo-age.s3.ap-northeast-2.amazonaws.com/age/160.jpg"
    ],
    "message": "연령대 이미지 조회 성공",
    "timestamp": "2023-01-30T10:40:15.9514205",
    "errors": null
}
```
{% endtab %}
{% endtabs %}
