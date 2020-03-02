### 프로그라피 사전 과제 (Android)

#### MVVM, ViewModel, LiveData 
* MVVM 아키텍처를 기반으로 설계하였습니다. 
* Retrofit, Gson 을 이용해 JSON 객체를 읽어와 `Film` data class 객체로 변환하였습니다.
* MVVM 아키텍처에서 흔히 사용되는 Repository 레이어를 만들지 않은 이유는 Data source 가 하나 뿐이며, 
받아온 정보를 처리하는 데 있어서 복잡한 비즈니스 로직이 없다고 판단했기 때문입니다.   
* ViewModel 에서는 정보를 LiveData 로서 관리합니다. Configuration change 가 일어나도 새롭게 정보를 
네트워크에서 읽어오지 않기 위해 ViewModel 과 LiveData 를 사용했습니다.

#### RecyclerView & ListAdapter
* RecyclerView 에서 ListAdapter 와 DiffUtilCallback 을 사용해 리스트를 효율적으로 업데이트 하고자 했습니다. (비록 static 한 API 정보 인 것 같았지만)
* RecyclerView 의 아이템을 클릭하면 DetailActivity 로 넘어가서 세부 정보를 표시하도록 했습니다.

#### etc. 
* DetailActivity 에서 정보의 양이 스크린의 범위를 초과할 경우 텍스트가 잘리지 않도록 상위 레이아웃을 ScrollView 로 설정했습니다.
* DetailActivity 에서 Home 으로 가는 Back 버튼을 ActionBar 에 추가하였습니다. 


### 설계 다이어그램
[다이어그램 링크](https://drive.google.com/file/d/16Scd2YKn4MVYVFwDwU0Fag6YosI43vUR/view?usp=sharing)

### Demonstration 

* Bottom Navigation
<br> 
![BottomNavigation](./demo_bottom_nav.gif)

* Bottom Navigation View Hiding
<br> 
![Nav2](./demo_bottom_hide.gif)

* Scrolling Detail Activity
<br>
![Scroll](./demo_scroll.gif)
 