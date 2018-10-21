package com.locallinkonline.stcatherineschool.activities

import android.net.Uri
import android.os.Bundle
import android.util.SparseArray
import android.view.MenuItem

import com.google.android.material.navigation.NavigationView
import com.locallinkonline.stcatherineschool.R
import com.locallinkonline.stcatherineschool.fragment.LunchFragment
import com.locallinkonline.stcatherineschool.fragment.SchoolScheduleFragment
import com.locallinkonline.stcatherineschool.fragment.TwitterFragment
import com.locallinkonline.stcatherineschool.room.entity.MenuEntity
import com.locallinkonline.stcatherineschool.room.repository.StCatherineRepository

import java.util.ArrayList
import java.util.HashMap

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.locallinkonline.stcatherineschool.rest.api.StCatherineAsync
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), LunchFragment.OnFragmentInteractionListener, SchoolScheduleFragment.OnFragmentInteractionListener, TwitterFragment.OnFragmentInteractionListener {
    private var mDrawerLayout: DrawerLayout? = null
    private val menuNameToIdentifier = HashMap<String, String>()
    private val groupToTypeMap = SparseArray<String>()
    private val groupToMenuItem = HashMap<String, List<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        instance = this

        setContentView(R.layout.main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val mActionBar = supportActionBar
        mActionBar!!.setDisplayHomeAsUpEnabled(true)
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)

        setupNavigationView()
    }

    private fun setupNavigationView() {
        val navigationView = findViewById<NavigationView>(R.id.nav_view)

        mDrawerLayout = findViewById(R.id.drawer_layout)

        configureMenu()

        val fragment = SchoolScheduleFragment()
        val schoolScheduleBundle = Bundle()
        schoolScheduleBundle.putString("title", "School Schedule")
        schoolScheduleBundle.putString("type", "SchoolSchedule")
        schoolScheduleBundle.putString("identifier", "school-schedule")
        fragment.arguments = schoolScheduleBundle

        pushFragment(fragment)

        // Set action to perform when any menu-item is selected.
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // close drawer when item is tapped
            selectFragment(menuItem)
            mDrawerLayout!!.closeDrawers()
            true
        }

        val menuModel = object : LiveData<List<MenuEntity>>() {
            private val stCatherineRepository = StCatherineRepository(application)
            override fun getValue(): List<MenuEntity> {
                return stCatherineRepository.allMenuItems
            }
        }

        menuModel.observe(this, Observer{ reCreateMenu() })
    }

    private fun configureMenu() {
        StCatherineAsync.updateDataResources()

        doAsync {
            val repo = StCatherineRepository(application)
            val groups = repo.menuGroups

            var i = 0
            for (groupIndex in groups.indices) {
                val group = groups[groupIndex]

                val menuNames = ArrayList<String>()
                groupToTypeMap.append(groupIndex, group)

                val entities = repo.getMenuItemsForGroup(group)
                for (j in entities.indices) {
                    menuNames.add(entities[j].displayName)
                    menuNameToIdentifier[entities[j].displayName] = entities[j].identifier
                    i++
                }

                groupToMenuItem[group] = menuNames
            }

            uiThread {
                reCreateMenu()
            }
        }
    }

    private fun selectFragment(item: MenuItem) {
        configureMenu()

        when (item.itemId) {
            100 -> {
                val schoolScheduleFragment = SchoolScheduleFragment()
                val schoolScheduleBundle = Bundle()
                schoolScheduleBundle.putString("title", "School Schedule")
                schoolScheduleBundle.putString("identifier", "school-schedule")
                schoolScheduleBundle.putString("type", "SchoolSchedule")
                schoolScheduleFragment.arguments = schoolScheduleBundle
                pushFragment(schoolScheduleFragment)
            }
            101 -> pushFragment(LunchFragment())
            102 -> pushFragment(TwitterFragment())
            else -> {
                val bundle = Bundle()
                bundle.putString("identifier", menuNameToIdentifier[item.title])
                bundle.putString("type", groupToTypeMap.get(item.groupId))
                bundle.putString("title", item.title.toString())
                val fragment = SchoolScheduleFragment()
                fragment.arguments = bundle
                pushFragment(fragment)
            }
        }
    }

    private fun pushFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        if (fragmentManager != null) {
            val ft = fragmentManager.beginTransaction()
            ft.replace(R.id.rootActivityView, fragment)
            ft.addToBackStack(null)
            ft.commit()
        }
    }

    private fun reCreateMenu() {
        val view = findViewById<NavigationView>(R.id.nav_view)
        val menu = view.menu
        menu.clear()

        val generalMenu = menu.addSubMenu("General")

        //School Schedule
        val item = generalMenu.add(0, 100, 0, R.string.school_schedule_title)
        item.setIcon(R.drawable.ic_dashboard_black_24dp)

        //Lunch Schedule
        val lunchItem = generalMenu.add(0, 101, 0, R.string.lunch_title)
        lunchItem.setIcon(R.drawable.ic_notifications_black_24dp)

        var gIndex = 0
        var i = 0
        for ((group, value) in groupToMenuItem) {
            val subMenu = menu.addSubMenu(gIndex, i, 0, group)
            for (menuItem in value) {
                subMenu.add(gIndex, i, 0, menuItem)
                i++
            }
            gIndex++
        }

        //Social
        val socialMenu = menu.addSubMenu("Social")
        val twitterItem = socialMenu.add(1000, 102, 1000, R.string.twitter_title)
        twitterItem.setIcon(R.drawable.ic_icons8_twitter)

        view.invalidate()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                mDrawerLayout!!.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onFragmentInteraction(uri: Uri) {

    }

    companion object {
        lateinit var instance: MainActivity
            private set
    }
}
