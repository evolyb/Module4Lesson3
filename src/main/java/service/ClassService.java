package service;

import model.Class;

import java.util.ArrayList;
import java.util.List;

public class ClassService {
    private static List<Class> list;
    static {
        list = new ArrayList<>();
        Class clazz1 = new Class(1,"C0620K1");
        Class clazz2 = new Class(2,"C0820H1");
        list.add(clazz1);
        list.add(clazz2);
    }
    public List<Class> getClasses(){
        return list;
    }
    public Class getClassById(int id){
        for (Class clazz: list){
            if (clazz.getId() == id) return clazz;
        }
        return null;
    }
}
