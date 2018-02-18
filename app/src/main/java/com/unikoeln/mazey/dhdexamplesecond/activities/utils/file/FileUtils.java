package com.unikoeln.mazey.dhdexamplesecond.activities.utils.file;

import android.content.Context;
import android.content.res.AssetManager;

import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.EventItem;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.eventlist.EventListUtil;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class FileUtils {

    public List<EventItem> deserializeEventItemList(Context context) {
        EventListUtil eventListUtil = new EventListUtil();
        List<EventItem> tmp = null;
        try {
            FileInputStream fileInputStream = context.openFileInput("event_item_list.biz");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            tmp = (List<EventItem>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            tmp = eventListUtil.initList(getAsstes(context));
            serializeEventList(tmp, context);
        }
        return tmp;
    }

    private void serializeEventList(List<EventItem> events, Context context) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("event_item_list.biz", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(events);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getAsstes(Context context) {
        String content = null;
        try {
            AssetManager assetManager = context.getAssets();
            content = IOUtils.toString(assetManager.open("DHd_Tagung2018_sessions_2018-02-01_14-29-58.xml"), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

}
