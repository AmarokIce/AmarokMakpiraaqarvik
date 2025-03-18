<h1 align="center">

![](src/main/resources/assets/amarokmakpiraaqarvik/SmallLogo.png)  
**Amaruq Makpiraaqarvik**

</h1>

--- 

<h2 align="center">
A simple coding library for Minecraft NeoForge
</h2>

<div align="center">

![Static Badge](https://img.shields.io/badge/Minecraft-1.21-blue)
![Static Badge](https://img.shields.io/badge/AmarokLibrary%20Version-1.0-red)
![Static Badge](https://img.shields.io/badge/License-MPL%202.0-orange)

</div>


## Using
```groovy
repositories {
    // Other repositories... 
    maven {
        name "AmarokMaven"
        url "http://maven.snowlyicewolf.club/"
        allowInsecureProtocol = true
    }
}

dependencies {
    // Other dependencies... 
    
    implementation "club.someoneice.makpiraaqarvik:AmarokMakpiraaqarvik:1.20-${AmarokLibraryVersion}"
}
```