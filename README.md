# JoystickComposeApp
Joystick Compose App is an example of using the "JoystickComposeView" module written for use in compose projects.
<div style="text-align:center">
<img src="result/demonstration_app.gif" width="200" height="400"/>
</div>

## Usage
Quick Usage  
Step 1 - Download or Clone the library (using Git, or a zip archive to unzip)  
Step 2 - Open your project in Android Studio  
Step 3 - Go to File > New > Import Module  
Step 4 - Find and select JoystickComposeView in your project tree  
Step 5 - Right-click your app in project view and select "Open Module Settings"  
Step 6 - Click the "Dependencies" tab and then the '+' button (Module Dependency)  
Step 7 - Select "JoystickComposeView"  

## SHOW ME THE CODE
At this point, you just need to include the View in any layout to start to use the JoystickComposeView, for example:
```kotlin
  val dx = remember { mutableStateOf(0f) }
  val dy = remember { mutableStateOf(0f) }
    
  JoystickView(
    backgroundColor = Color.Black,
    x = dx,
    y = dy,
    size = 150.dp,
  )
```
In this example, we add the joystick to our project, make the background black, set the size to 150.dp, and pass it variables that will change when the joystick position changes.

## Use with xml
If you want to use a Compose in your XML file, you can add this to your layout file:
```xml
<androidx.compose.ui.platform.ComposeView
    android:id="@+id/my_composable"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```
and then, set the content:
```kotlin
findViewById<ComposeView>(R.id.my_composable).setContent {
  val dx = remember { mutableStateOf(0f) }
  val dy = remember { mutableStateOf(0f) }
    
  JoystickView(
    backgroundColor = Color.Black,
    x = dx,
    y = dy,
    size = 150.dp,
  )
}
```

## Created with
***Jetpack compose*** is the modern toolkit for building native Android UI

## License 
Copyright © 2021 <copyright holders>

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
