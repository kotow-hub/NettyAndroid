/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.goav.app.socket;

/**
 * Live$.<br/>
 *
 * @time: 16/10/8 11:09.<br/>
 * @author: Created by moo<br/>
 */

public class Constants {


    public static class TYPE {
        public static class REQUEST {
            public static final int PING = 0;
            public static final int LOGIN = 4;
            public static final int REQUEST = 5;
        }

        public static class RESPONSE {
            public static final int ANSWER = 2;
            public static final int RESPONSE = 5;
            public static final int LOGOUT = -4;
        }
    }


    public static class PARAMS {
        public static class RESPONSE {
            public static final String TYPE = "type";
            public static final String CODE = "code";
            public static final String REPLY = "reply";
            public static final String ID = "id";
        }
    }

    public static class CODE {
        public static class RESPONSE {
            public static final int SUCCESS = 200;
            public static final int FAIL = 500;
        }
    }
}
