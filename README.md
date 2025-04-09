# 🏋️ Powerlifting Nation

A JavaFX desktop application for managing powerlifting competitions, calculating training metrics, and supporting lifters with useful tools like IPF GL, FFMI, BMR, and One Rep Max calculators.

## 📌 Features

- 👤 Add athletes with detailed personal and performance data
- 📊 View competition results in sortable tables
- 🔍 Search athletes by name and federation
- 📈 Calculate:
  - **BMI** (Body Mass Index)
  - **FFMI** (Fat-Free Mass Index)
  - **BMR** (Basal Metabolic Rate)
  - **IPF GL Score** based on competition style
  - **One Rep Max** (McGlothin formula)
  - **Meet Attempts** prediction based on RPE and training sets

## 🎨 Interface

- Clean and responsive **JavaFX GUI**
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
│   └── resources/
│       └── com/example/powerlifting_app/
│           ├── style.css     # Application styling
│           └── logo.jpg  # App icon