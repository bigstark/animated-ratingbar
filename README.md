# animated-ratingbar
[![license](https://img.shields.io/hexpm/l/plug.svg)](LICENSE)
[![jitpack](https://img.shields.io/badge/jitpack-0.3.0-green.svg)](https://jitpack.io/#bigstark/android-cycler)


[![AnimatedRatingBar Video](https://media.giphy.com/media/l0He3Bk3v79rjoShW/giphy.gif)](http://www.giphy.com/gifs/l0He3Bk3v79rjoShW)

##Include your project
add build.gradle
```
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```
```
dependencies {
    compile 'com.github.bigstark:animated-ratingbar:0.3.0'
}
```


## Usage
``` xml
<com.bigstark.animatedratingbar.lib.AnimatedRatingBar
        android:id="@+id/arb"
        android:layout_width="wrap_content"
        android:layout_height="30dp"
        android:layout_marginTop="100dp"
        app:gapSize="10dp"
        app:progressImage="@mipmap/icon_progress"
        app:secondaryProgressImage="@mipmap/icon_secondary"
        app:max="5"
        app:numStars="5"
        app:rating="4.6"
        />
```

``` java
        arb = (AnimatedRatingBar) findViewById(R.id.arb);
        arb.setRating(4.3f);
        arb.setNumStars(5);
        arb.startAnimate();
```
