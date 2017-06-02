package org.jxjz.common.threadLocal;


public class ThreadLocalManager {
    private static final ThreadLocal<ThreadLocalDto> th = new ThreadLocal<ThreadLocalDto>();  
    
    public static void setEntityClazz(Class clazz){
    	ThreadLocalDto dto = th.get();
    	if(dto == null){
    		dto = new ThreadLocalDto();
    		th.set(dto);
    	}
    	dto.setEntityClass(clazz);
    }
    
    public static Class getEntityClazz(){
    	Class clazz = null;
    	ThreadLocalDto dto = th.get();
    	if(dto != null){
    		clazz = dto.getEntityClass();
    	}
    	return clazz; 
    }
    
    
	
}
