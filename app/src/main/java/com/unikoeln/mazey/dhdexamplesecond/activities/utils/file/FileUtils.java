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

/**
 * This class is used to provide data which is locally available on the device.
 */

public class FileUtils {

    /**
     * Deserializes a file which stores a {@link List} of type {@link EventItem}.</p>
     * To do so, this class initializes an instance of {@link EventListUtil}. Then, a <i>try/catch-block</i> is used to open a {@link FileInputStream} in order
     * to retrieve a locally available file in which a {@link List} of type {@link EventItem} is stored.
     * To retrieve this file the {@link Context} is used: <code>FileInputStream fileInputStream = context.openFileInput("event_item_list.biz");</code>.
     * In a second step, a new {@link ObjectInputStream} is used in order to read the object from the locally available file and returns it.</p>
     * In case, the locally file  or the specified class is not available, an {@link IOException} or {@link ClassCastException} is thrown which is handled
     * in the <i>catch-block</i> of this very method.
     *
     * @param context Android Context
     * @return {@link List} of type {@link EventItem}
     */
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

    /**
     * Serializes a {@link List} of type {@link EventItem} and stores it privately on the device.</p>
     * @param events {@link List} of type {@link EventItem}
     * @param context Android Context.
     */
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

    /**
     * Returns a {@link String} in from the assets.</p>
     * To do so, this method utilises {@link AssetManager} in order to retrieve a file which is stored on the device.
     * To read this file, {@link IOUtils} is used.</p>
     * @param context Android Context
     * @return File Content
     */
    private String getAsstes(Context context) {
        String content = null;
        try {
            AssetManager assetManager = context.getAssets();
            content = IOUtils.toString(assetManager.open("c4me-export_2018-02-21.xml"), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

}
