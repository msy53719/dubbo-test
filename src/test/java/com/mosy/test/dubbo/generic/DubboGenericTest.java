package com.mosy.test.dubbo.generic;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DubboGenericTest {

	public static void main(String[] args) {

		ApplicationConfig application = new ApplicationConfig();
		application.setName("dubbo-server");
		RegistryConfig registry = new RegistryConfig();
		registry.setAddress("zookeeper://127.0.0.1:2181");
		ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
		reference.setApplication(application);
		reference.setRegistry(registry);
		reference.setInterface("com.generic.server.serivce.UserService");
		reference.setGeneric(true);
		ReferenceConfigCache cache = ReferenceConfigCache.getCache();
		GenericService genericService = cache.get(reference);
		Method[] cls=	reference.getInterfaceClass().getMethods();
		for (Method  me : cls) {
			System.out.println(me.getName());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "test001");
		map.put("phone", "13211111111");
		map.put("sex", "女");
		map.put("age", "19");
		map.put("address", "北京市西城区三里河");

		Object result = genericService.$invoke("reginseter", new String[] { "com.generic.server.dto.UserDto" },
				new Object[] { map });
		System.out.println(result);
		log.debug(JSON.toJSONString(result));
		
		
//		try {
//		Class<?> cls = Class.forName("com.project.framework.service.UserService");
//		reference.setInterface(cls);
//		Method[] method = cls.getMethods();
//		for (Method method2 : method) {
//			System.out.println(method2.getName());
//		}
//	} catch (ClassNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	// reference.setInterface(cls);
	// reference.set
		//reference.get
		//cls.getClass().getName();
		//genericService.getClass().get
		// GenericService genericService1 =reference.get();

		// try {
		// Class<?> cls = Class.forName("");
		// Method[] method = cls.getMethods();
		// for (Method method2 : method) {
		// System.out.println(method2.getName());
		// }
		// } catch (ClassNotFoundException e) {
		//
		// e.printStackTrace();
		// }

		// 基本类型直接调用
		// Object result = genericService.$invoke(
		// "reginseter", new String[] { "java.lang.String" },
		// new Object[] { "1", "2", "3", "4", "5" });
		// //System.out.println(result);
		// Method[]
		// method=genericService.getClass().getMethod("com.project.framework.service.UserService",
		// Object);
	
	}

	@org.junit.Test
	public void genericTest() {

		System.out.println("111");
	}


}
