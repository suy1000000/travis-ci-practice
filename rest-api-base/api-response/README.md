# API Response

- Response
- Exception
- Paging
	* CursorPagingModel 游標型分頁，搭配 CursorUtils 對游標加解密編碼
	* OffsetPagingModel 位移型分頁

## Benefit

- 統一API回傳樣式
- 集中Exception Handle
- 分離非商務邏輯之其他事務

## Validate Exception

	{"warning":{"code":"101","desc":"can not be null!!"}}
	
### Validate Type Enum

* PARAMETER("1") 參數檢查
* LOGIC("2") 邏輯檢查

### Validate Parameter Type Enum

* REQUIRED("01") 必填
* LENGTH("02") 長度
* FORMAT("03") 格式
* OTHER("99") 其他

## Except Validate Exception

	{"error":{"exception":"java.lang.ArrayIndexOutOfBoundsException","message":"1"}}

## How to use ##

    <bean id="exampleService" class="org.ApiResponseProxy">
    	<constructor-arg ref="org.ExampleService" />
	</bean>

## Response format

	{"response":Object}
	{"error":{"exception":"java.lang.ArrayIndexOutOfBoundsException","message":"1"}}
	{"warning":{"code":"101","desc":"can not be null!!"}}

