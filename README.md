# 🎮 TicTacToe Ultimate - Kotlin Android App

> Developed by **Muhammad Uzair 42**  
> Version: 1.2

---

## 📱 App Overview

**TicTacToe Ultimate** is a fully-featured Tic Tac Toe game for Android built entirely using **Kotlin**, **Android Fragments**, **Activities**, **XML layouts**, and both **2-Player Mode** & **AI Mode** with multiple difficulty levels. 

The project demonstrates clean architecture, separation of concerns, game logic algorithms, beautiful UI design, and strong coding practices for both beginners and intermediate Android developers.

---

## 🌟 Key Features

- 🟦 **Splash Screen** with animation, branding, and developer credits.
- 🟩 **Mode Selection Fragment**:
  - 2-Player Mode
  - AI Mode (Easy, Medium, Hard)
- 🟥 **2-Player Mode**:
  - Real-time scoreboard
  - Color-coded turn indicators
  - Winner announcements with match results
  - Score reset option
- 🟪 **AI Mode**:
  - 3 AI Levels (Easy, Medium, Hard)
  - AI uses **Minimax Algorithm** for unbeatable mode.
- 🔄 **Reusable components** for both gameplay modes.
- 🎨 Clean, simple, modern glassy design with aqua shades.
- 💡 Clear code structure with detailed comments and Kotlin best practices.

---

## 🧮 Algorithm Details

### 1️⃣ 2-Player Mode Logic

- Standard TicTacToe 3x3 board logic.
- Each move is validated before being played.
- Winner determined by:
  - 3 rows
  - 3 columns
  - 2 diagonals
- After each game round, scoreboard updates automatically.
- Turn indicator changes color:
  - 🟣 Player 1 - Purple
  - 🔵 Player 2 - Blue

---

### 2️⃣ AI Mode Logic

| Difficulty | Algorithm Used | Behavior |
| ----------- | -------------- | -------- |
| Easy | Random Move | Makes purely random moves. User can easily win. |
| Medium | Block & Win Strategy | Blocks opponent win and tries to win itself, but doesn't plan ahead. |
| Hard | **Minimax Algorithm** | Fully recursive search of all game states; perfect AI that can't lose. |

#### 🔎 Minimax Algorithm:

- Fully explore all possible game trees.
- Assign scores (+10, -10, 0) for Win, Lose, Draw.
- Use recursion to backtrack and choose the optimal move.
- Implemented with depth to speed up performance.

---

## 🧰 Architecture & Approach

- ✅ **Kotlin Android with XML layouts**
- ✅ **Activities** for navigation.
- ✅ **FragmentContainerView** for mode selection.
- ✅ **Separation of UI & Logic**
- ✅ **Board is a 2D Char Array**
- ✅ **Game engine fully testable and isolated**
- ✅ **Animations using Android XML Animations**
- ✅ **Reusable Score & UI Logic**

---

## 🔄 Complete App Flow


---

## 🎯 Technologies Used

- ✅ Kotlin
- ✅ Android SDK (100% Native)
- ✅ Android XML Layout
- ✅ Fragment + Activity Navigation
- ✅ Object-Oriented Approach
- ✅ Minimax AI Algorithm
- ✅ Material Design
- ✅ Animations & UI Styling

---

## 🚀 Current Limitations

- Only 3x3 Board supported.
- No Multiplayer online yet.
- No game history storage yet.

---

## 🔮 Future Features (Evolving Roadmap)

- 🌐 Online Multiplayer (Firebase or WebSocket)
- 🧠 AI with learning models (ML-based player)
- 📊 Match History & Analytics
- 🎨 Theme Customization (Light/Dark/Custom Themes)
- 🏆 Global Leaderboard
- 🔈 Sound Effects & Haptic Feedback
- 🔎 Accessibility Improvements

---

## 👨‍💻 Developer

Developed by **Muhammad Uzair**  
_Android Enthusiast, AI Learner & Full Stack Explorer_  
**Contact:** *(muhammaduzairali48@gmail.com)*

---

## 📸 Screenshots


> _(*ScreenShot will be visible soon ...)_

---

⭐ **If you like this project, don’t forget to give feedback and suggestions for future improvements.**

