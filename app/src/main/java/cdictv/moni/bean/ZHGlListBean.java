package cdictv.moni.bean;

import java.util.List;

public class ZHGlListBean {


    /**
     * code : 1
     * data : [{"id":1,"chepai":"A17666","chezhu":"张一","money":100},{"id":2,"chepai":"A17777","chezhu":"张二","money":100},{"id":3,"chepai":"A17888","chezhu":"张三","money":100},{"id":4,"chepai":"A17999","chezhu":"张四","money":100}]
     */

    private int code;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * chepai : A17666
         * chezhu : 张一
         * money : 100
         */

        private int id;
        private String chepai;
        private String chezhu;
        private int money;
        private Boolean checkbox=false;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getChepai() {
            return chepai;
        }

        public void setChepai(String chepai) {
            this.chepai = chepai;
        }

        public String getChezhu() {
            return chezhu;
        }

        public void setChezhu(String chezhu) {
            this.chezhu = chezhu;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public Boolean getCheckbox() {
            return checkbox;
        }

        public void setCheckbox(Boolean checkbox) {
            this.checkbox = checkbox;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", chepai='" + chepai + '\'' +
                    ", chezhu='" + chezhu + '\'' +
                    ", money=" + money +
                    ", checkbox=" + checkbox +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ZHGlListBean{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
