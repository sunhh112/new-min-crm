package com.mincrm.util;

import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;

import java.util.List;

/***
 * macro.s 20230403
 */
@Log4j2
public class BeanCopyUtil {

    public static <T> T copy(Object source ,Class<T> clazz){
        if(source == null)
            return null;
        T target = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(source, target);
        return target;
    }

    public static <T> List<T> copy(List<? extends Object> sources , Class<T> clazz){
        if(sources == null) {
            return null;
        }
        List<T> list = Lists.newArrayList();
        for (Object source : sources) {
           list.add(copy(source,clazz));
        }
        return list;
    }


}
