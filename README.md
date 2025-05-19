# 📝 Notes App (Offline)

A simple offline Notes app for Android built with **MVVM architecture**, using **Room DB**, **LiveData**, **ViewModel**, **Data Binding**, and **RecyclerView**. Ideal for beginners to understand key Android Jetpack components and local data persistence.

## 📱 Features

- ➕ Add new notes  
- ✏️ Edit existing notes  
- ❌ Delete notes  
- 💾 Store notes locally using Room Database  
- 📋 Display notes in a list using RecyclerView  

## 🔧 Tech Stack

| Layer        | Library/Component    |
|--------------|----------------------|
| UI           | Data Binding, RecyclerView |
| Architecture | MVVM, ViewModel, LiveData |
| Database     | Room Database        |
| Language     | Kotlin               |
| Others       | AndroidX, Material Design |

## 📸 Screenshots

> (Add screenshots here once the app UI is ready)

## 🧠 Concepts Covered

- **MVVM Architecture**  
- **Room Database for local persistence**  
- **LiveData to observe data changes**  
- **ViewModel to handle UI-related data lifecycle**  
- **Data Binding for efficient UI updates**  
- **RecyclerView for dynamic list display**

🗂️ Project Structure
com.example.notesapp
├── data
│   ├── Note.kt
│   ├── NoteDao.kt
│   └── NoteDatabase.kt
├── repository
│   └── NoteRepository.kt
├── ui
│   ├── NoteAdapter.kt
│   └── MainActivity.kt
├── viewmodel
│   └── NoteViewModel.kt
└── utils
    └── NoteViewModelFactory.kt


## 🛠️ Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/SyedAhmed96/Notepad.git
   cd Notepad
