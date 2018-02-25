package com.castrec.stephane.noteskotlinsample

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.castrec.stephane.noteskotlinsample.commons.Session
import com.castrec.stephane.noteskotlinsample.di.NotesDH
import com.castrec.stephane.noteskotlinsample.notes.fragments.NotesFragment
import com.castrec.stephane.noteskotlinsample.users.fragments.ChatsFragment
import com.castrec.stephane.noteskotlinsample.users.fragments.UsersFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val component by lazy { NotesDH.authenticationComponent() }


    @Inject
    lateinit var session : Session


    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        component.inject(this)

        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

        initTabs(tabs)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

    }

    private fun initTabs(tabLayout : TabLayout) {

        // set icons
        tabLayout.getTabAt(0)!!.setIcon(android.R.drawable.ic_menu_week)
        tabLayout.getTabAt(1)!!.setIcon(android.R.drawable.ic_menu_edit)
        tabLayout.getTabAt(2)!!.setIcon(android.R.drawable.ic_menu_info_details)
    }

    override fun onResume(){
        super.onResume()
        if(session.getToken() == null || session.getToken().token.isNullOrEmpty()){
            //directly go to signin activity
            val i : Intent = Intent(this, SigninActivity::class.java)
            startActivity(i)
            this.finish()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if(position == 2){
                return UsersFragment.newInstance()
            } else if(position == 0) {
                return ChatsFragment.newInstance()
            } else {
                return NotesFragment.newInstance()
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            // return null to show no title.
            return null

        }
        override fun getCount(): Int {
            // Show 3 total pages.
            return 3
        }
    }
}
