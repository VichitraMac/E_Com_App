package com.manya.tech.ecomapp.data;

public interface OnRequestResponseListener<T> {
    void onSuccess(T response);
    void onFailed(Throwable e);
}
