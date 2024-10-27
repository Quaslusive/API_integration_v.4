# **API Integration v.4**

### **Uppgift 4**  
*By Carl Sundberg*

---

### **Overview**
In this task, I've utilized the NASA Near Earth Object Web Service (NeoWs) API to retrieve information about asteroids approaching Earth. The API provides data on asteroid trajectories, velocities, and their potential collision risks. The purpose of the app is to display a list of asteroids based on specific dates and show more detailed information about each one.

### **Endpoints Used**

- **`/feed?start_date=&end_date=`**  
  Retrieves a list of all asteroids approaching Earth within a specific time frame. Users can select a start and end date to fetch the data. This is implemented in the `getDateAsteroids` function, where both start and end dates are sent as parameters.

- **`/feed/today`**  
  Fetches today's asteroids and their detailed data. This endpoint is used in the `getTodayAsteroids` function to display asteroids for the current day.

### **Data Handling & App Structure**
Once the data is fetched from the API, it is directed to various fragments within the app:

- **AsteroidTodayFragment**  
  Displays a list of today's closest asteroids. The information includes the asteroid's name, absolute magnitude, relative speed, collision risk, and more.

- **DateSelectionFragment**  
  Users can select a specific start and end date to retrieve asteroids from the chosen period.

- **AsteroidOrbitFragment**  
  This view visually shows the asteroid's trajectory using a WebView that loads NASA's JPL page for the selected asteroid.

Navigation between fragments is managed with the Navigation Component, allowing seamless transitions between views. The back button follows the steps in the backstack until reaching the main view.

### **Design & Orientation Handling**
The app is designed to function in both portrait and landscape modes. It utilizes `ConstraintLayout` and `Flow` to ensure that graphical components are dynamically positioned and adapt to different screen sizes and orientations.

---

### **Screenshots**

<div align="center">
  
#### Main Screen 
![Main Screen](https://github.com/user-attachments/assets/4452459c-b093-42d7-aa1b-530cc845faed)
 
Features two buttons leading to the app's primary functions.

---
#### Today's Asteroids  
![Today's Asteroids](https://github.com/user-attachments/assets/fe451ceb-4c88-4a78-b4f5-e4648fd83dbd)

Displays a list of the closest asteroids to Earth.

---
#### WebView  
![WebView](https://github.com/user-attachments/assets/adfa880e-18ec-4e3c-a202-5025aa92cb04)

Shows NASA's Orbital View with the asteroid's trajectory.

---
#### Date Selector 
![Date Selector](https://github.com/user-attachments/assets/38a1db8f-f200-45a6-abc3-667240e243ae)  ![Date Selector - Another View](https://github.com/user-attachments/assets/bad6e2f8-cff7-44a9-b2cc-644a612ca442)
 
Allows the user to specify a time frame to retrieve data.

</div>

---

### **Final Thoughts**
Although the app's design is simple, the development was quite challenging. I encountered multiple issues with `RecyclerView` and `NavController` not behaving as expected. Additionally, handling JSON data proved frustrating due to vague error messages, complicating the debugging process. However, layout design—typically a source of frustration—was smoother this time, as I now have a better grasp of `ConstraintLayout` and `Flow`.

