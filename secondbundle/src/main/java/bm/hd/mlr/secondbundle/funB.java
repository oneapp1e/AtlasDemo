package bm.hd.mlr.secondbundle;

import android.os.Bundle;
import android.taobao.atlas.remote.IRemoteTransactor;
import android.taobao.atlas.remote.transactor.RemoteTransactor;
import android.util.Log;

/**
 * Created by mulinrui on 2017/12/15.
 */
public class funB extends RemoteTransactor {

    @Override
    public Bundle call(String commandName, Bundle args, IRemoteTransactor.IResponse callback) {
        Log.e("RemoteTransactor", "SecondActivity call  commandName" + commandName);
        return null;
    }

    @Override
    public <T> T getRemoteInterface(Class<T> interfaceClass, Bundle args) {
        Log.e("RemoteTransactor", "SecondActivity getRemoteInterface  interfaceClass");
        return null;
    }

}
