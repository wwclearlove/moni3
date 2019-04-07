package cdictv.moni.bean;

import java.util.List;

public class RenshuBean {
    /**
     * code : 1
     * data : [{"id":1,"num":"1","renshu":93},{"id":2,"num":"2","renshu":65},{"id":3,"num":"3","renshu":64},{"id":4,"num":"4","renshu":82},{"id":5,"num":"5","renshu":56},{"id":6,"num":"6","renshu":97}]
     */
    public int code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 1
         * num : 1
         * renshu : 93
         */

        public int id;
        public String num;
        public int renshu;
    }
}
