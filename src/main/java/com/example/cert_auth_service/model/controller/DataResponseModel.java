package com.example.cert_auth_service.model.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataResponseModel<T> {
    private List<T> list = new ArrayList();

    public DataResponseModel(Iterable<T> source) {
        /*Iterator var2 = source.iterator();

        while(var2.hasNext()) {
            T t = var2.next();
            this.list.add(t);
        }*/
        for (T t : source){
            list.add(t);
        }

    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

