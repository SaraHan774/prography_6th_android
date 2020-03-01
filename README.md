### 프로그라피 사전 과제 (Android)

* MVVM 아키텍처를 기반으로 설계하였습니다. 
* Retrofit, Gson 을 이용해 JSON 객체를 읽어와 `Film` data class 객체로 변환하였습니다. 
* 받아온 정보는 Repository 에서 네트워크 관련된 비즈니스 로직을 처리하도록 했습니다. 
* ViewModel 에서는 정보를 LiveData 로서 관리합니다. Configuration change 가 일어나도 새롭게 정보를 
네트워크에서 읽어오지 않기 위해 ViewModel 과 LiveData 를 사용했습니다.
* RecyclerView 에서 ListAdapter 와 DiffUtil 을 사용해 리스트를 효율적으로 업데이트 하고자 했습니다. (비록 static 한 API 정보 인 것 같았지만)
* RecyclerView 의 아이템을 클릭하면 DetailActivity 로 넘어가서 세부 정보를 표시하도록 했습니다.

### 설계 다이어그램

[다이어그램 링크](https://drive.google.com/file/d/16Scd2YKn4MVYVFwDwU0Fag6YosI43vUR/view?usp=sharing)

### 데모 영상 
* [넥서스5 (API 25)](https://youtu.be/ZwxdHLO5YVc)
* [픽셀3A (API 29)](https://youtu.be/ERxKOHRkHkk) 