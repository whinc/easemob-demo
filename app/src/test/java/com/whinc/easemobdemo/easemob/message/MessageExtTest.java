package com.whinc.easemobdemo.easemob.message;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * ${END}
 * <p/>
 * <p>Created by whinc on 2016/1/26.
 * Email:xiaohui_hubei@163.com</p>
 */
public class MessageExtTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testValueOf() throws Exception {
        Assert.assertEquals(MessageExt.valueOf(MessageExt.NONE.name()), MessageExt.NONE);
        assertThat(MessageExt.NONE, is(MessageExt.NONE));
        assertThat(MessageExt.PROLONGATION, is(MessageExt.valueOf("PROLONGATION1")));
    }
}