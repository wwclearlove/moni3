package cdictv.moni.bean;

import java.util.List;

public class ZhantaiBean {

    /**
     * code : 1
     * data : {"id":1,"name":"901路公共汽车","time":"早7点晚9点","num":"1111人","bus":[{"zhantai":"中医院站","num":[{"name":"1号","distance":205},{"name":"2号","distance":1248}]},{"zhantai":"联想大厦站","num":[{"name":"1号","distance":230},{"name":"2号","distance":1073}]}]}
     */

    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * id : 1
         * name : 901路公共汽车
         * time : 早7点晚9点
         * num : 1111人
         * bus : [{"zhantai":"中医院站","num":[{"name":"1号","distance":205},{"name":"2号","distance":1248}]},{"zhantai":"联想大厦站","num":[{"name":"1号","distance":230},{"name":"2号","distance":1073}]}]
         */

        public int id;
        public String name;
        public String time;
        public String num;
        public List<BusBean> bus;

        public static class BusBean {
            /**
             * zhantai : 中医院站
             * num : [{"name":"1号","distance":205},{"name":"2号","distance":1248}]
             */

            public String zhantai;
            public List<NumBean> num;

            public static class NumBean {
                /**
                 * name : 1号
                 * distance : 205
                 */

                public String name;
                public int distance;
            }
        }
    }
}
