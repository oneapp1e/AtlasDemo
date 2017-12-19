package bm.hd.mlr.firstbundle;

import android.os.Bundle;
import android.taobao.atlas.remote.transactor.RemoteTransactor;
import android.util.Log;

/**
 * Created by mulinrui on 2017/12/15.
 */
public class funA extends RemoteTransactor {


    @Override
    public Bundle call(String commandName, Bundle args, IResponse callback) {
        Log.e("RemoteTransactor", "MainActivity call  commandName" + commandName);
        return null;
    }

    @Override
    public <T> T getRemoteInterface(Class<T> interfaceClass, Bundle args) {

        Log.e("RemoteTransactor", "MainActivity getRemoteInterface  interfaceClass");
        return null;
    }
}
