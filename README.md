# Secret-Path
This is an application called "Secret Path" for the Android platform. The main
idea of this application is to test intelligence and intuition of
the user in form of a game.

The purpose of this mobile application is to 
test the user and interest him in tasks that need
nonstandard thinking.

This product is developed as a training project within the course
"Industrial practice" of the National University of "Kyiv-Mohyla Academy".

## Installation
Download the `.zip` archive with our project or clone this repository with the following command:
```bash
git clone git@github.com:MikLay/Secret-Path.git
```
Then, import the project into **Android Studio**. You can run the game on either emulator device or your device. To run the game on your device, build `release.apk` (see the section below).



## Build variants
In Android Studio use *Build Variants* button to choose between **production** and **staging** flavors combined with debug and release build types.

Another option to generate the release file is to use command line (in Android Studio go to View > Tool Windows > Terminal):

- on Windows
```
gradlew assembleRelease
```

- on Mac/Linux
```
./gradlew assembleRelease
```
Then the `release.apk` file will be created at `secret-path/app/build/outputs/apk/release/`. You can now run it on your device.

## Generating signed APK
From Android Studio:
1. ***Build*** menu
2. ***Generate Signed APK...***
3. Fill in the keystore information *(you only need to do this manually once; Android Studio will remember it afterwards)*

## Maintainers
This project is mantained by:
* [Mykhailo Feduchenko](http://github.com/miklay)
* [Illya Kurochkin](http://github.com/illyakurochkin)
* [Taras Kreshchenko](http://github.com/74r45)
* [Mariia Maimeskul](http://github.com/marviem)


## Contributing

1. Fork the repository
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -m 'Add some feature')
4. Run the linter (ruby lint.rb')
5. Push your branch (git push origin my-new-feature)
6. Create a new Pull Request
