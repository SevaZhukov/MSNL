# Multistack Navigation Library

This library will help you to quickly setup multi stack navigation

![logo](https://github.com/MrSwimmer/MSNL/blob/master/photoeditorsdk-export.png)

When you are developing your application with difficult navigation, you want your backstack to works well.

If you use any navigation panel (bottom, drawer, circle, ...), you can destroy routine code from your project by using Multistack Navigation Library.

Multistack Navigation uses some stacks of fragments. Library create separate stack for each item of navigation panel.
If you select item on panel, you will get this stack of this item.

Library use stack of stacks. It means that application will save order calling stacks by each select item of navigation panel.

## Main components

### MSFragmentManager

Analog of usual FragmentManager. It lets setup Multistack Navigation and do navigation transactions.

### MSNavigation

Class for setup Multistack Navigation with navigation panel.

## How to add library in your project

Add it in your root build.gradle at the end of repositories:

```Code
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

Add the dependency

```Code
dependencies {
  implementation 'com.github.MrSwimmer:MSNL:alpha-1.0'
}
```

## How to use

+ Create an Activty as global container for your SingleActivity application
+ Create an object of MSFragmentManager
+ Setup global container id
+ Add Fragment with navigation panel
+ Override fun onBackPressed

```Kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var msFragmentManager: MSFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        msFragmentManager = MSFragmentManager(supportFragmentManager)
        msFragmentManager.globalContainerId = R.id.global_container
        msFragmentManager.addGlobal(MainFragment())
    }

    override fun onBackPressed() {
        val fragment = this.supportFragmentManager.findFragmentById(R.id.global_container)
        (fragment as? IOnBackPressed)?.onBackPressed()
    }
}
```

+ Create an Fragment as local container for your fragments
+ Get instance of msFragmentManager by using extension
+ Setup local container id
+ Create start Fragments
+ Call static fun setupNavigation at MSNavigation
+ Override fun onBackPressed

```Kotlin
class MainFragment : Fragment(), IOnBackPressed {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_main, container, false)
        msFragmentManager.localContainerId = R.id.local_container
        val fragments = arrayListOf(NewsFragment(), MessagesFragment(), FriendsFragment(), ProfileFragment())
        MSNavigation.setupNavigation(msFragmentManager, v.bottomNavigationView, fragments)
        return v
    }
    
    override fun onBackPressed(): Boolean {
        MSNavigation.onBackPressed()
        return true
    }
}
```

That's all! Your navigation is ready. You can use msFragmentManager for doing simple navigation transactions.

## MSFragment methods

### navigate(fragment, args)

```Kotlin
fun navigate(fragment: Fragment, args: Bundle?)
```

Navigate to fragment with arguments in local container

### replace(fragment, args)

```Kotlin
fun replace(fragment: Fragment, args: Bundle?)
```

Remove current fragment and add new with arguments in local container

### navigateGlobal(fragment, args)

```Kotlin
fun navigateGlobal(fragment: Fragment, args: Bundle?)
```

Add fragment in global container

### replaceGlobal(fragment, args)

```Kotlin
fun replaceGlobal(fragment: Fragment, args: Bundle?)
```

Remove current fragment and add new in global container


### add(fragment, args)

```Kotlin
fun add(containerId: Int, fragment: Fragment)
```

Add fragment in local container


### addGlobal(fragment, args)

```Kotlin
fun addGlobal(containerId: Int, fragment: Fragment)
```

Add fragment in global container


### back()

```Kotlin
fun back()
```

Pop fragment from local stack


### backTo(fragmentTag)

```Kotlin
fun backTo(fragmentTag: String)
```

Pop fragment from local stack to fragment with tag

Enjoy!
