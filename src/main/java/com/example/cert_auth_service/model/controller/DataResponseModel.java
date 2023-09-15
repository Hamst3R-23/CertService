package com.example.cert_auth_service.model.controller;

import java.util.ArrayList;
import java.util.List;

public class DataResponseModel<T> {

    private List<T> list;

    public DataResponseModel(Iterable<T> source) {
        list = new ArrayList<>();
        for (T t : source)
            list.add(t);
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
