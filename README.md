# Secret-Path
This is a "Secret Path" application on the Android platform. The main
idea of this application is to test intelligence and intuition of
user in the game format.

The purpose of this mobile application is to create
test for the user and interest him in the tasks that need
non-standard thinking.

This product is developed as a training project within the course
"Industrial practice" of the National University "Kyiv-Mohyla Academy".

## Installation
Download the `.zip` archive with our project or clone this repository with the following command:
```bash
git clone git@github.com:MikLay/Secret-Path.git
```
Then, import the project into **Android Studio**.



## Build variants
Use the Android Studio *Build Variants* button to choose between **production** and **staging** flavors combined with debug and release build types

Or use command line

On Windows
```
gradlew assembleRelease
```

On Mac/Linux
```
./gradlew assembleRelease
```
Then .apk file will be created at `secret-path/app/build/outputs/apk/release/`

## Generating signed APK
From Android Studio:
1. ***Build*** menu
2. ***Generate Signed APK...***
3. Fill in the keystore information *(you only need to do this once manually and then let Android Studio remember it)*

## Maintainers
This project is mantained by:
* [Mykhailo Feduchenko](http://github.com/miklay)
* [Illya Kurochkin](http://github.com/illyakurochkin)
* [Taras Kreshchenko](http://github.com/74r45)
* [Mariia Maimeskul](http://github.com/marviem)


## Contributing

1. Fork it
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -m 'Add some feature')
4. Run the linter (ruby lint.rb').
5. Push your branch (git push origin my-new-feature)
6. Create a new Pull Request
