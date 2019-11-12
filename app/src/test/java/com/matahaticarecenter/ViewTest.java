package com.matahaticarecenter;

import android.view.ViewGroup;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ViewTest {
    public static final String layoutName = "mainLayout";
    public static final String appName = "BasicUI";

    protected void testCompletion(int prevElement, List<Class> elements, ViewGroup layout) {
        //Error message
        String compNumberMsg = "Element(s) was missing";
        String itemCompMsg = " is not suitable";

        //JUnit Test
        int expected = prevElement + elements.size();
        int actual = layout.getChildCount();
        assertTrue(expected - actual + " " + compNumberMsg, expected <= actual);

        if (prevElement + elements.size() <= layout.getChildCount()) {
            for (int idx = prevElement; idx <= elements.size() + prevElement - 1; idx++) {
                assertEquals("Element " + idx + itemCompMsg, elements.get(idx - prevElement), layout.getChildAt(idx).getClass());
            }
        }
    }

    public void testItem(Object expectVal, Object actualVal, String msg, int type) {
        switch (type) {
            case 1:
                assertEquals(msg, expectVal, actualVal);
                break;
            case 2:
                assertNotEquals(msg, expectVal, actualVal);
                break;
            case 3:
                assertTrue(msg, (boolean) actualVal);
                break;
            case 4:
                assertFalse(msg, (boolean) actualVal);
                break;
            case 5:
                assertNull(msg, actualVal);
                break;
            case 6:
                assertNotNull(msg, actualVal);
                break;
        }
    }
}
