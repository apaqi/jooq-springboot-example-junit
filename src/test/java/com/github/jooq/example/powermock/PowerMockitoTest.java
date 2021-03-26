package com.github.jooq.example.powermock;

import com.github.jooq.example.service.ExampleService;
import com.github.jooq.example.util.PasswordUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ExampleService.class})//解决静态方法，私有方法，final 方法单元测试问题
public class PowerMockitoTest {

    @Test
    public void test_privateMethod() throws Exception {
        ExampleService exampleService = PowerMockito.spy(new ExampleService());
        PowerMockito.when(exampleService, "sub", anyInt(), anyInt()).thenReturn(20);
        int i = exampleService.publicSub(10, 2);
        Assert.assertEquals(i,12);
    }

    @Test
    public void test_staticMethod() throws Exception {
        ExampleService exampleService = PowerMockito.spy(new ExampleService());
        int i = exampleService.multi(10, 2);
        Assert.assertEquals(i,20);
        //方式2
        PowerMockito.mockStatic(ExampleService.class);
        PowerMockito.when(ExampleService.multi(anyInt(),anyInt())).thenReturn(10);
        Assert.assertEquals(10,ExampleService.multi(10,23));

    }
}
