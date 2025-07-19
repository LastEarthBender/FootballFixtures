# Football Fixtures
A modern Android application for browsing football fixtures, team details, and league standings, built with a scalable architecture

## Screenshots
<details>
<summary>Click here</summary>
<table>
<tr>
<th><img alt="04619419-f693-490a-a72f-4a04891c4716.jpeg" src="screenshot/04619419-f693-490a-a72f-4a04891c4716.jpeg" width="300"/></th>
<th><img alt="104c8580-6a7c-4f78-8cd2-eb8e6ac7833f.jpeg" src="screenshot/104c8580-6a7c-4f78-8cd2-eb8e6ac7833f.jpeg" width="300"/></th>
<th><img alt="6e3dfe25-6ff4-49f6-aad3-b93aa61f5c70.jpeg" src="screenshot/6e3dfe25-6ff4-49f6-aad3-b93aa61f5c70.jpeg" width="300"/></th>
</tr>

<tr>
<th>Today's Fixtures</th>
<th>Competitions</th>
<th>Table</th>
</tr>

<tr>
<th><img alt="207341a2-dab8-4123-9e88-6bbcdf8a07be.jpeg" src="screenshot/207341a2-dab8-4123-9e88-6bbcdf8a07be.jpeg" width="300"/></th>
<th><img alt="7832d7db-b76b-4f4f-8316-97ea2320a8de.jpeg" src="screenshot/7832d7db-b76b-4f4f-8316-97ea2320a8de.jpeg" width="300"/></th>
<th><img alt="1ef09c1d-7d48-41fa-ad43-f0c5af0b7cc2.jpeg" src="screenshot/1ef09c1d-7d48-41fa-ad43-f0c5af0b7cc2.jpeg" width="300"/></th>
</tr> 

<tr>
<th>Fixtures</th>
<th>Teams</th>
<th>Teams Details</th>
</tr>

</table>

</details>

## Technologies & Architecture
- Kotlin: Primary programming language, leveraging coroutines and modern features.
- MVVM Architecture: Separation of concerns using ViewModels, Repositories, and UI components.
- ### Jetpack Components:
  - ViewModel & StateFlow for reactive state management
  - Navigation Component for fragment transitions and deep linking
  - Room for local persisting of fixtures and entities
  - ViewBinding for type-safe XML interaction
  - ConstraintLayout: All UIs use flexible modern Android layouts
- Dependency Injection:
  - Hilt Dagger-Hilt for easy, robust DI across the app
- Networking:
  - Retrofit to access the football-data REST API
  - Gson for serialization/deserialization of JSON data
  - OkHttp Logging Interceptor for easier network debugging
- Image Loading:
  - Glide for fast, memory-efficient image loading team emblems, badges, etc
