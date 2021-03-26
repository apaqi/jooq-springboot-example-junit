package com.github.jooq.example.mockito;


import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Random;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/***
 * https://www.letianbiji.com/java-mockito/mockito-parameter-match-order.html
 * https://www.jianshu.com/p/84dff2508600
 * @Date 2021/3/24
 * @Param
 * @Return
 */
public class MockitoTest {
    @Mock
    private Random random;
    @Mock
    private List<String> testList;

    @Test
    public void testMock() {
        Random mockRandom = mock(Random.class);
        //指定调用 nextInt 方法时，永远返回 100
        when(mockRandom.nextInt()).thenReturn(100);
        Assert.assertEquals(100, mockRandom.nextInt());
        Assert.assertEquals(100, mockRandom.nextInt());
    }

    //注解方式
    @Test
    public void testMockAnnotation() {
        //让注解生效
        MockitoAnnotations.initMocks(this);
        when(random.nextInt()).thenReturn(100);
        Assert.assertEquals(100, random.nextInt());
    }

    //参数匹配
    @Test
    public void testParamMatch() {
        // 精确匹配 0
        when(testList.get(0)).thenReturn("a");
        Assert.assertEquals("a", testList.get(0));
        // 模糊匹配 anyInt()
        when(testList.get(anyInt())).thenReturn("c");
        Assert.assertEquals("c", testList.get(0));
        Assert.assertEquals("c", testList.get(1));
    }
}