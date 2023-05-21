package com.moffy5612.iinteg.misc.util;

import java.lang.reflect.Field;

import com.moffy5612.iinteg.IInteg;
import com.moffy5612.iinteg.misc.annotation.IIntegContentInstance;

import net.minecraftforge.fml.common.Loader;

public class AnnotationUtil {
    public static void initHandler(Class<?> handlerClass){
        Field[] fields = handlerClass.getDeclaredFields();
        for(Field field : fields){
            IIntegContentInstance instance = field.getAnnotation(IIntegContentInstance.class);
            if(instance != null){
                boolean shouldLoad = true;
                for(String id : instance.requiredModIds()){
                    if(!Loader.isModLoaded(id)){
                        shouldLoad = false;
                        break;
                    }
                }
                if(shouldLoad){
                    Class<?> contentClass = instance.contentClass();
                    try{
                        field.set(null, contentClass.newInstance());
                        continue;
                    }catch(IllegalAccessException | IllegalArgumentException | InstantiationException e){
                        IInteg.LOGGER.warning(e.getLocalizedMessage());
                    }
                }
            }
            try{
                field.set(null, null);
            }catch(IllegalAccessException | IllegalArgumentException e){
                IInteg.LOGGER.warning(e.getLocalizedMessage());
            }
        }
    }
}
