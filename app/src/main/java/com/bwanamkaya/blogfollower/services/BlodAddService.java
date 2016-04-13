package com.bwanamkaya.blogfollower.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.bwanamkaya.blogfollower.data.local.DBFunctions;
import com.bwanamkaya.blogfollower.rx.RxFunc;
import com.bwanamkaya.blogfollower.utils.LogUtils;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class BlodAddService extends IntentService {

    private static final String TAG = LogUtils.makeLogTag(BlodAddService.class);
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_ADD_BLOG = "com.bwanamkaya.blogfollower.services.action.FOO";
    private static final String ACTION_BAZ = "com.bwanamkaya.blogfollower.services.action.BAZ";
    // TODO: Rename parameters
    private static final String BLOG_NAME = "com.bwanamkaya.blogfollower.services.extra.PARAM1";
    private static final String BLOG_URL = "com.bwanamkaya.blogfollower.services.extra.PARAM2";
    DBFunctions dbFunctions;

    public BlodAddService() {
        super("BlodAddService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startAction_AddBlog(Context context, String param1, String param2) {
        Intent intent = new Intent(context, BlodAddService.class);
        intent.setAction(ACTION_ADD_BLOG);
        intent.putExtra(BLOG_NAME, param1);
        intent.putExtra(BLOG_URL, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, BlodAddService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(BLOG_NAME, param1);
        intent.putExtra(BLOG_URL, param2);
        context.startService(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dbFunctions = new DBFunctions(getApplicationContext());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD_BLOG.equals(action)) {
                final String param1 = intent.getStringExtra(BLOG_NAME);
                final String param2 = intent.getStringExtra(BLOG_URL);
                handleAction_AddBlog(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(BLOG_NAME);
                final String param2 = intent.getStringExtra(BLOG_URL);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleAction_AddBlog(String param1, String param2) {
        //RxFunc.wait(dbFunctions, RxFunc.validUrl(param2), param1);
        RxFunc.blog(param2);
        stopSelf();
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }
}
