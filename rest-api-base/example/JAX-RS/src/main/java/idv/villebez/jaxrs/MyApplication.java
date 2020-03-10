package idv.villebez.jaxrs;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import idv.villebez.exception.mapper.InternalExceptionMapper;
import idv.villebez.exception.mapper.JsonMappingExceptionMapper;
import idv.villebez.exception.mapper.JsonParseExceptionMapper;
import idv.villebez.exception.mapper.ValidateExceptionMapper;
import idv.villebez.filter.RequestWrapperFilter;
import idv.villebez.filter.ResponseWrapperFilter;
import idv.villebez.filter.XSSFilter;

@ApplicationPath("")
public class MyApplication extends Application {

	public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(UserService.class);
        s.add(InternalExceptionMapper.class);
        s.add(JsonMappingExceptionMapper.class);
        s.add(JsonParseExceptionMapper.class);
        s.add(ValidateExceptionMapper.class);
        s.add(JacksonJsonProvider.class);
        s.add(JacksonJaxbJsonProvider.class);
        s.add(RequestWrapperFilter.class);
        s.add(ResponseWrapperFilter.class);
        s.add(XSSFilter.class);
        return s;
    }

}
