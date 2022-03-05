# Overview

My goal with this application is to learn about developing Android Applications. Along with that, I learned a lot about how the Android operating system works as a whole. I also managed to learn more about UI design with Android Studio, and how the internal storage works on Android devices.

This application is for managing available inventory. The app supports adding new items, managing stock, adding and removing stock, and deleting items.

Usage of the app is pretty straightforward. When the app is opened, you are presented with two choices: add new item, and manage inventory.

Adding a new item is for adding a new item that is not currently managed. Here you can set the name, id, description, and initial inventory.

Managing inventory shows a list (via Android RecyclerView) of all currently managed items. Click on the "Manage" button to add/remove stock, or delete the entire item.

[Software Demo Video](https://youtu.be/Thu-xGjaPbY)

# Development Environment

* Android Studio
* Java
* Tested on a Pixel 4 emulator with API level 30

# Useful Websites

* [Android Developer Website](https://developer.android.com/)
* [W3Schools Java](https://www.w3schools.com/java/)

# Future Work

* Fix crash when clicking on item that no longer exists (Item will disappear the next time that activity is reloaded)
* Add Firebase support for cloud storage
* Add support for using camera to scan barcode and automatically add/remove stock to scanned item