package com.mosy.test.dubbo.generic;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DubboGenericTest {

	@org.junit.Test
	public void genericTest() {
		
		System.out.println("111");
	}

	public static void main(String[] args) {

		ApplicationConfig application = new ApplicationConfig();
		application.setName("dubbo-server");

		// 连接注册中心配置
		RegistryConfig registry = new RegistryConfig();
		registry.setAddress("zookeeper://127.0.0.1:2181");

		ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
		reference.setApplication(application);
		reference.setRegistry(registry);
		reference.setInterface("com.project.framework.service.UserService");
		reference.setGeneric(true); // 声明为泛化接口

		ReferenceConfigCache cache = ReferenceConfigCache.getCache();
		GenericService genericService = cache.get(reference);

		// 基本类型以及Date,List,Map等不需要转换，直接调用
		Object result = genericService.$invoke(
				"reginseter", new String[] { "java.lang.String", "java.lang.String", "java.lang.String",
						"java.lang.String", "java.lang.String", "java.lang.String" },
				new Object[] { "1", "2", "3", "4", "5" });
		System.out.println(result);
	}

}
