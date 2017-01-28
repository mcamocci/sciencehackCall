package vodaassistant.haikaroselab.com.vodaassistant.Pojos;

/**
 * Created by root on 6/7/16.
 */
public class ServiceItem {

        private String name;
        private String detail;
        public ServiceItem(String name, String detail){
            this.name=name;
            this.detail=detail;
        }

        public String getName(){
            return this.name;
        }

        public void setName(String name){
            this.name=name;
        }

        public String getDetail(){
            return this.detail;
        }

        public void setDetail(String detail){
            this.detail=detail;
        }
}
