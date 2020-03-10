package idv.villebez;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.FactoryBean;

import idv.villebez.exception.ValidateException;
import idv.villebez.response.ApiResponse;
import idv.villebez.util.JacksonUtils;

/**
 * @author paul.chen
 *
 * @param <T>
 */
public class ApiResponseProxy<T> implements InvocationHandler, FactoryBean<T> {
	private Logger log = Logger.getLogger(this.getClass());
    
    private Object obj;
    public Object bind(Object obj) { 
        return Proxy.newProxyInstance( 
                           obj.getClass().getClassLoader(), 
                           obj.getClass().getInterfaces(), 
                           this); 
    } 

    public Object invoke(Object proxy, Method method, 
                         Object[] args) throws Throwable { 
    	StringBuffer sb = new StringBuffer("Class:").append(obj.getClass().getName()).append(" ; Method:").append(method.getName()).append(" ; Params:").append(JacksonUtils.writeBeanToJson(args));
    	log.info(sb.toString());
        Object result = null;
        try {
        	result = ApiResponse.success(method.invoke(obj, args));
            
        } catch (InvocationTargetException e){
			Throwable t = e.getTargetException();
			if(t instanceof Exception){
				Exception e1 = (Exception) t;
				if(e1 instanceof ValidateException){
					result = ApiResponse.warn((ValidateException) e1);
				}else{
					result = ApiResponse.fail(e1);
					log.error("API錯誤 ; " + sb.toString(), e1);
				}
			}else{
				throw t;
			}
        }catch (Exception e){
			result = ApiResponse.fail(e);
			log.error("API錯誤 ; " + sb.toString(), e);
        }
        return result; 
    }

	public ApiResponseProxy(Object obj) {
		this.obj = obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getObject() throws Exception {
		return (T) this.bind(obj);
	}

	@Override
	public Class<?> getObjectType() {
		return obj.getClass();
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
