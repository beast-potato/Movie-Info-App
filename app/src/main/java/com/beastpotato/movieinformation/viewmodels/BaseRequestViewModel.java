package com.beastpotato.movieinformation.viewmodels;

import android.content.Context;
import android.databinding.BaseObservable;

import com.android.volley.VolleyError;
import com.beastpotato.movieinformation.endpoints.RequestBase;
import com.beastpotato.potato.api.net.ApiRequest;

import java.util.List;

/**
 * Created by Oleksiy on 2/22/2016.
 */
public abstract class BaseRequestViewModel<T, U extends RequestBase.FieldsDef, V extends RequestBase<T>> extends BaseObservable {
    private V model;
    private Context context;

    public BaseRequestViewModel(Class<V> clazz, String baseUrl, Context context) {
        this.context = context;
        try {
            model = clazz.getConstructor(String.class, Context.class).newInstance(baseUrl, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<U> validateModel() {
        return (List<U>) model.validateFields();
    }

    public abstract void loadData(final ApiRequest.RequestCompletion<T> completion);

    public V getModel() {
        return model;
    }

    public void setModel(V model) {
        this.model = model;
    }

    public interface OnLoadCompletion {
        void onSuccess();

        void onFailure(VolleyError error);
    }
}
