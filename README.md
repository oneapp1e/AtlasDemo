# AtlasDemo
阿里组件化开发demo

## 使用中遇到的各种坑：

  #### 1. butterknife 注解框架（在组件中不能使用）
    
    作者已经提供了解决办法，使用R2
    (https://github.com/JakeWharton/butterknife)
 
  #### 2. 我们项目使用了科大讯飞的广告sdk（加载广告后无法正常进入组件）
    
    科大讯飞广告sdk里面存在黑科技与阿里的组件化冲突，已经和阿里反馈，他们说后期会加入防劫持机制
     
     此处贴出科大讯飞的黑科技，他们劫持了classLoader
     private static void changePackageClassLoader(Context context, DexClassLoader classLoader) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
            Class<?> c_ActivityThread = context.getClassLoader().loadClass("android.app.ActivityThread");
            Method m_currentActivityThread = c_ActivityThread.getMethod("currentActivityThread", new Class[0]);
            Object currentActivityThread = m_currentActivityThread.invoke((Object)null, new Object[0]);
            String packageName = context.getPackageName();
            Field f_mPackages = c_ActivityThread.getDeclaredField("mPackages");
            f_mPackages.setAccessible(true);
            Map mPackages = (Map)f_mPackages.get(currentActivityThread);
            WeakReference wr = (WeakReference)mPackages.get(packageName);
            Class<?> c_LoadedApk = context.getClassLoader().loadClass("android.app.LoadedApk");
            Field f_mClassLoader = c_LoadedApk.getDeclaredField("mClassLoader");
            f_mClassLoader.setAccessible(true);
            f_mClassLoader.set(wr.get(), classLoader);
        }
        
     针对这样的问题，两个解决办法
     1.反编译科大讯飞的sdk，改写此处劫持代码，然后重新生成jar包（不建议）
     2.联系科大讯飞，说明缘由，让他们更改此处逻辑，重新给出新的jar（建议）我们采用此方案
     
     注意：目前demo给出的是有问题的科大讯飞广告的jar包（Ad_Android_SDK.jar）
     
  