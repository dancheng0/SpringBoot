package com.muye.kl;

import java.util.*;

/**
 * @Desc:
 *
 * @author : gwh
 * @date : 2020-07-01 17:41
 **/
public class AA {
    public static void main(String[] args) {
        List<Object> list1 = new ArrayList<>();

        LinkedHashMap<String,Object> hashMap11 = new LinkedHashMap<>();

        hashMap11.put("1","a");
        hashMap11.put("4","d");
        hashMap11.put("3","c");
        hashMap11.put("2","b");

        List<Map.Entry<String,Object>> list = new ArrayList<>(hashMap11.entrySet());

        Collections.sort(list,new Comparator<Map.Entry<String,Object>>(){
            @Override
            public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        for(Map.Entry<String,Object> entity : list){
            list1.add(entity.getValue());
        }

        for(int i = 0;i < list1.size();i++){
            System.out.println(list1.get(i));
        }
    }
}
