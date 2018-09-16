package com.locallinkonline.stcatherineschool.rest.tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.room.entity.MenuEntity;
import com.locallinkonline.stcatherineschool.room.repository.StCatherineRepository;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public class MenuAsyncTask extends AsyncTask<Menu, Void, Map<Integer, String>> {

    private final StCatherineRepository repo;
    private final WeakReference<Map<Integer, String>> menuItemToIdentifierRef;
    private final WeakReference<Map<Integer, String>> groupToTypeMap;

    public MenuAsyncTask(Activity activity, Map<Integer, String> menuItemToIdentifier, Map<Integer, String> groupToTypeMap) {
        this.menuItemToIdentifierRef = new WeakReference<>(menuItemToIdentifier);
        this.groupToTypeMap = new WeakReference<>(groupToTypeMap);
        this.repo  = new StCatherineRepository(activity.getApplication());
    }

    @Override
    protected Map<Integer, String> doInBackground(Menu... menus) {
        Menu menu = menus[0];

        SubMenu generalMenu = menu.addSubMenu("General");

        //School Schedule
        MenuItem item = generalMenu.add(0,100,0, R.string.school_schedule_title);
        item.setIcon(R.drawable.ic_dashboard_black_24dp);

        //Lunch Schedule
        MenuItem lunchItem = generalMenu.add(0,101,0, R.string.lunch_title);
        lunchItem.setIcon(R.drawable.ic_notifications_black_24dp);

        List<String> groups = repo.getMenuGroups();
        int i = 0;
        for(int groupIndex = 0; groupIndex < groups.size(); groupIndex++) {
            String group = groups.get(groupIndex);
            SubMenu subMenu = menu.addSubMenu(0, groupIndex,0,group);
            groupToTypeMap.get().put(groupIndex, group);

            List<MenuEntity> entities = repo.getMenuItemsForGroup(group);
            for(int j = 0; j < entities.size(); j++) {
                subMenu.add(groupIndex, i,0, entities.get(j).getDisplayName());
                menuItemToIdentifierRef.get().put(i, entities.get(j).getIdentifier());
                i++;
            }
        }

        //Social
        SubMenu socialMenu = menu.addSubMenu("Social");
        MenuItem twitterItem = socialMenu.add(1000,102,1000, R.string.twitter_title);
        twitterItem.setIcon(R.drawable.ic_icons8_twitter);

        return null;
    }
}