package com.acheronsheol.fragmentviewpagerdemo.base.activity;

import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

public class BasePresenter<V extends IBaseView,M extends IBaseModel> implements IBasePresenter {

    private SoftReference<IBaseView> mReferenceView;
    private V mProxyView;
    private M mModel;

    @Override
    public void bindPresenter(IBaseView view) {
        mReferenceView = new SoftReference<>(view);
        mProxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(),
                view.getClass().getInterfaces(),
                new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                if(mReferenceView == null || mReferenceView.get() == null){
                    return null;
                }
                return method.invoke(mReferenceView.get(),objects);
            }
        });

        ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
        if(type != null){
            Type[] types = type.getActualTypeArguments();
            try{
                mModel = (M)((Class<?>)types[1]).newInstance();
            }catch (IllegalAccessException | InstantiationException e){
                e.printStackTrace();
            }
        }

    }

    public V getView() {
        return mProxyView;
    }

    protected M getModel() {
        return mModel;
    }

    @Override
    public void unBindPresenter() {
        mReferenceView.clear();
        mReferenceView = null;
    }



    /*
     * 用户要进行的事件
     * */

    /*
     * 用户事件的结果 或 Socket长连接&Http想要发生的事件(获取数据或是行为)
     * */

}
