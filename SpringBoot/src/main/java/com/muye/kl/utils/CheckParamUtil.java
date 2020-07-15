package com.muye.kl.utils;

import java.util.Collection;

/**
 * Created by ldf on 2018/8/4.
 */
public class CheckParamUtil {
    /**
     * 判断集合是否为空
     * @param collection
     * @return
     */
    public static boolean collectionIsEmpty(Collection collection){
        return (collection == null || collection.size() == 0);
    }
}
