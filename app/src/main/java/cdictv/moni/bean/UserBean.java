package cdictv.moni.bean;

public class UserBean {

    /**
     * code : 1
     * data : {"username":"张三"}
     */

    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * username : 张三
         */

        public String username;
    }
}
