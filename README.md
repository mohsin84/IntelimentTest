# IntelimentTest
An test example
The minimum android version for this project is API level 15 (Ice crem sandwitch). It covers more 90% of the devices of the market. 
Also using this API level enable us to use most recent android libraries, ViewGroups and frameworks.

This sample app have two UI pages shown using a Navigation drawer. In first view, there are usage of modern ViewGroups like a ViewPager, RecyclerView and ConstraintLayout which persist user selection using the ViewModels. In the second page, I fetched JSON data from a simple API with retrofit 2 and populated in android spinner with data binding library as per MVVM design. Used dagger 2 for dependency injection. Also used Room persistence database along with other lifecycle aware component.

<b>Design Pattern:</b>
 <ol>
<li>MVVM: 
I used android architecture components in this project following MVVM pattern</li>
</ol>

<b>android libraries</b>
<ol>
<li><a href="https://developer.android.com/topic/libraries/architecture/index.html">Android Architecture Components</a>: Used Live Data, ViewModel, Room ersistent database and other lifecycle aware components</li> 
<li><a href="https://developer.android.com/topic/libraries/data-binding/index.html">Android Data Binding</a>: Using Data binding significantly reduce boiler plate codes. I used one way data binding in this example</li>
<li>a href="https://developer.android.com/topic/libraries/support-library/index.html">Android Support Library</a>: Used for fragments and fragment manager support libraries</li> 
<li><a href="https://google.github.io/dagger/">Dagger 2 </a>: Used for simplified access to shared instances mainly and to show my knowledge on dependency injection</li>
</ol>

<b>3rd Party libraries</b>
  <ol>
<li><a href="http://square.github.io/retrofit/"> Retrofit</a>: Retrofit used for asynchronous Api calls. </li>
</ol>
