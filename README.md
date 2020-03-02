### 프로그라피 사전 과제 (Android)

* MVVM 아키텍처를 기반으로 설계하였습니다. 
* Retrofit, Gson 을 이용해 JSON 객체를 읽어와 `Film` data class 객체로 변환하였습니다.
* MVVM 아키텍처에서 흔히 Repository 레이어를 만들지 않은 이유는 Data source 가 하나 뿐이며, 
받아온 정보를 처리하는 데 있어서 복잡한 비즈니스 로직이 없다고 판단했기 때문입니다.   
* ViewModel 에서는 정보를 LiveData 로서 관리합니다. Configuration change 가 일어나도 새롭게 정보를 
네트워크에서 읽어오지 않기 위해 ViewModel 과 LiveData 를 사용했습니다.
* RecyclerView 에서 ListAdapter 와 DiffUtilCallback 을 사용해 리스트를 효율적으로 업데이트 하고자 했습니다. (비록 static 한 API 정보 인 것 같았지만)
* RecyclerView 의 아이템을 클릭하면 DetailActivity 로 넘어가서 세부 정보를 표시하도록 했습니다.

### 설계 다이어그램

[다이어그램 링크](https://drive.google.com/file/d/16Scd2YKn4MVYVFwDwU0Fag6YosI43vUR/view?usp=sharing)

### 데모 영상 
* [넥서스5 (API 25)](https://youtu.be/ZwxdHLO5YVc)
* [픽셀3A (API 29)](https://youtu.be/ERxKOHRkHkk) 