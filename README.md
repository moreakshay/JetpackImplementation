# JetpackImplementation
Learning best practices in android. Using Android Architecture Components and Coroutines.
<br>This repository follows best practices depicted by the jetpack team in [architecture-components-sample/GithubBrowserSample](https://github.com/android/architecture-components-samples/tree/master/GithubBrowserSample/) ,unfortunately GithubBrowserSample doesn't include the use of Coroutines.
<br>This repo uses [TMDB API](https://developers.themoviedb.org/3/getting-started/introduction).

<h3>Language</h3> 
-Kotlin

<h3>Best Practices for</h3>
  <li>Network calls with the new Retrofit</li>
  <li>Error Handling for network calls</li>
  <li>Initializing LiveData in your ViewModel (using the new LiveData building block)</li>
  <li>Injecting ViewModel in your View (using Dagger Multibinds)</li>

<h3>Android Architecture Components Used</h3>
<ul>
  <li>Navigation</li>
  <li>ViewModel</li>
  <li>LiveData</li>
  <li>DataBinding</li>
  <li>Room</li>
  <li>Paging (Work in Progress)</li>
</ul>
<h3>Other Components Used</h3>
<ul>
  <li>Constraint Layout</li>
  <li>Coroutines</li>
  <li>Retrofit2.6.0+ (ofc with Coroutines)</li>
  <li>Dagger 2</li>
</ul>
<h3>How to use it</h3>
In your local.properties add 
<code>api_key=YOUR_API_KEY</code>







