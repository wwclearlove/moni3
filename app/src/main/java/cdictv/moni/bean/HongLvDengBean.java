package cdictv.moni.bean;

import java.util.List;

public class HongLvDengBean {


    /**
     * code : 1
     * data : [{"id":1,"red":11,"green":9,"yellow":3},{"id":2,"red":17,"green":10,"yellow":5},{"id":3,"red":7,"green":13,"yellow":7},{"id":4,"red":4,"green":18,"yellow":10},{"id":5,"red":7,"green":4,"yellow":3}]
     */

    public int code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 1
         * red : 11
         * green : 9
         * yellow : 3
         */

        public int id;
        public int red;
        public int green;
        public int yellow;
    }
}
