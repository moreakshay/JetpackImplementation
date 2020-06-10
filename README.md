# JetpackImplementation
Learning best practices in android using Android Architecture Components and Coroutines.
<br>This repository follows best practices depicted by the jetpack team in [architecture-components-sample/GithubBrowserSample](https://github.com/android/architecture-components-samples/tree/master/GithubBrowserSample/) , also added Coroutines which was missing in GithubBrowserSample.

<h3>Language</h3> 
-Kotlin
<h3>Design</h3>
-Uplabs:<a href="https://www.uplabs.com/posts/movies-app-db57e9f2-c0f4-4c94-99c0-e394952751f2"> Mao Trailer</a>.

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
  <li>Dagger 2</li>
  <li>Coroutines</li>
  <li>Constraint Layout</li>
  <li>Retrofit2.6.0+ (with Coroutines)</li>
</ul>
<h3>How to use it</h3>
This repo uses <a href="https://developers.themoviedb.org/3/getting-started/introduction">TMDB API</a>.
<br>In your local.properties add 
<code>api_key=YOUR_API_KEY</code>







