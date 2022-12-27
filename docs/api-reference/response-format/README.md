# Response Format

## 응답 커스텀

> 응답 형식을 일정하게 규격화하고 오류에 대응 할 수 있도록 Response를 커스텀하였습니다.

응답이 성공하면 data를 확인하면되고

실패하면 errors 리스트를 확인하고\
error 에 적혀있는 code를 통해 어떤 오류인지 파악 할 수 있습니다.

### Example

{% tabs %}
{% tab title="성공" %}
```json
{
    "data": {
        "id": 1
    },
    "message": "부동산 저장 성공",
    "timestamp": "2022-12-27T14:39:46.2645884",
    "errors": null
}
```
{% endtab %}

{% tab title="성공2" %}
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

{% tab title="오류 발생" %}
```json
{
    "data": null,
    "message": "오류 발생",
    "timestamp": "2022-12-27T18:35:49.3332878",
    "errors": [
        {
            "error": {
                "message": "중복된 지번주소입니다.",
                "code": "H003",
                "detail": null
            }
        }
    ]
}
```
{% endtab %}
{% endtabs %}

## Status

{% hint style="info" %}
상태코드는 응답에 응답이 성공/실패 인지 어떠한 이유 때문인지를\
간략하게 기술해주는 코드입니다.

주로 사용되는 상태코드만 기술하였습니다.
{% endhint %}

### 성공 2XX

* 200 OK : GET, PUT, DELETE 등이 성공했을때 반환됩니다.
* 201 Created : POST 에서 사용됩니다. 데이터가 새롭게 추가되었을때 반환됩니다.

### 클라이언트 오류 4xx

* 400 Bad Request : 요청이 잘못되었음을  의미합니다. 예를들어 중복된 지번주소로 부동산을 POST할때 반환됩니다.
* 404 Not Found : 요청이 존재하지 않을때 반환됩니다. 예를들어 Uri 가 잘못되었을때 반환됩니다.

### 서버 오류 5xx

* 500 Internal Server Error : 서버에서 오류가 발생했는데 사전에 정의한 오류가 아닐때 반환됩니다.&#x20;

## Body

{% hint style="info" %}
Body에 반드시 들어가는 내용들입니다.

요청이 성공했을때는 errors를 제외한\
data, message, timestamp 만 반환됩니다.\
\
요청이 실패했을때는 errors 리스트에\
error 에 대한 설명이 들어갑니다.
{% endhint %}

* data : 반환할 도메인
* message : 한글 설명 메시지
* timestamp : 현재 시각
* errors : 오류 리스트 (null 아니면 오류 발생한것)
  * error : 오류
    * message : 오류한글 설명 메시지
    * code : 오류 코드 (오류의 아이디)
    * detail: 상세한 내역 (사전에 정의된 오류가 아닐때 반환됨.)

{% hint style="warning" %}
오류가 발생했을때는 오류 코드를 보면 쉽게\
예외처리를 할 수 있습니다.
{% endhint %}

{% content-ref url="undefined.md" %}
[undefined.md](undefined.md)
{% endcontent-ref %}
