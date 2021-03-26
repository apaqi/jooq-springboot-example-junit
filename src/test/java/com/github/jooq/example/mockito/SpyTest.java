package com.github.jooq.example.mockito;

import com.github.jooq.example.service.ExampleService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;

import static org.mockito.Mockito.*;

/**
 * @Spy，如果发现修饰的变量是 null，会自动调用类的无参构造函数来初始化。
 * 所以如果类没有无参构造函数，必须使用 ExampleService spyExampleService = new ExampleService();方式
 */
public class SpyTest {
    @Spy
    private ExampleService spyExampleService;
    //注解方式
    @Test
    public void test_AnnotationSpy() {
        MockitoAnnotations.initMocks(this);
        Assert.assertEquals(3, spyExampleService.add(1, 2));
        when(spyExampleService.add(1, 2)).thenReturn(10);
        Assert.assertEquals(10, spyExampleService.add(1, 2));
    }

    //测试 spy
    @Test
    public void test_spy() {
        ExampleService spyExampleService = spy(new ExampleService());
        // 默认会走真实方法
        Assert.assertEquals(3, spyExampleService.add(1, 2));
        // 打桩后，不会走了,OngoingStubbing#thenReturn
        when(spyExampleService.add(1, 2)).thenReturn(10);
        Assert.assertEquals(10, spyExampleService.add(1, 2));
        // 但是参数比匹配的调用，依然走真实方法
        Assert.assertEquals(3, spyExampleService.add(2, 1));
    }

    //spy方法需要使用doReturn方法才不会调用实际方法
    @Test
    public void test_doReturn() {
        ExampleService spyExampleService = spy(new ExampleService());
        MockitoAnnotations.initMocks(this);
        doReturn(3).when(spyExampleService).add(1, 2);
        Assert.assertEquals(3, spyExampleService.add(1, 2));
    }
}
