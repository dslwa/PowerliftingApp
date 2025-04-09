# 🏋️ Powerlifting App

A JavaFX desktop application for managing powerlifting competitions, calculating training metrics, and supporting lifters with useful tools like IPF GL, FFMI, BMR, and One Rep Max calculators.

## 📌 Features

- 👤 Add athletes with detailed personal and performance data
- 📊 View competition results in sortable tables
![Table](Screenshots/table.png)
- 🔍 Search athletes by name and federation
- 📈 Calculate:
  - **BMI** (Body Mass Index)

  ![BMI](Screenshots/BMI.png)

  - **FFMI** (Fat-Free Mass Index)
  
  ![FFMI](Screenshots/FFMI.png)

  - **BMR** (Basal Metabolic Rate)
  
  ![BMR](Screenshots/image.png)

  - **IPF GL Score** based on competition style

  ![IPF_GL](Screenshots/GL.png)

  - **One Rep Max** (McGlothin formula)

  ![One Rep Max](Screenshots/1RM.png)

  - **Meet Attempts** prediction based on RPE and training sets

  ![MeetAttempt](Screenshots/meet.png)


## 🎨 Interface

- Clean and responsive **JavaFX GUI**

![GUI](Screenshots/GUI.png)

- Styled using custom **CSS** (`style.css`)
- Centralized layout and themed in black/red

## 🛠️ Technologies

- Java 20
- JavaFX
- Maven
- CSS (for GUI styling)

## 📁 Project Structure

```plaintext
src/
├── main/
│   ├── java/
│   │   └── com/example/powerlifting_app/
│   │       ├── Core/         # Business logic (Athlete, Result, Competition)
│   │       ├── GUI/          # GUI utilities and forms
│   │       └── HelloApplication.java
|   |       └── HelloController.java
│   └── resources/
│       └── com/example/powerlifting_app/
│           ├── style.css     # Application styling
│           └── logo.jpg  # App icon
```

TODO:
DATABASE
More functionalities(eg Calculators)