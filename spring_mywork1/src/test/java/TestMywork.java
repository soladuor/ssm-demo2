import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soladuor.pojo.Emp;
import org.junit.Test;

public class TestMywork {
    @Test
    public void test() {
        // ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        // EmpService empService = applicationContext.getBean("EmpService", EmpService.class);
        // List<Emp> emps = empService.selectAll();
        // System.out.println("emps = " + emps);
    }

    static class C1 {
        // @JsonProperty("cId") // 序列化时会多一个字段
        // @JsonGetter("cId") // 无法在属性上标注
        // @JsonSetter("cId") // 反序列化中有效，序列化不生效
        private String cId;

        public C1() {
        }

        public C1(String cId) {
            this.cId = cId;
        }

        @JsonProperty("cId")
        // @JsonGetter("cId")
        public String getCId() {
            return cId;
        }

        // @JsonProperty("cId")
        // @JsonSetter("cId")
        public void setCId(String cId) {
            this.cId = cId;
        }

        public String toString() {
            return "C1{cId = " + cId + "}";
        }
    }

    @Test
    public void testC1() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        C1 c1 = new C1("12345");
        String s = objectMapper.writeValueAsString(c1);
        System.out.println("对象转json = " + s);

        String json = "{\"cId\":\"12\"}";
        C1 readValue = objectMapper.readValue(json, C1.class);
        System.out.println("json转对象 = " + readValue);
    }

    @Test
    public void testJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Emp emp = new Emp("id", "na");
        String s = objectMapper.writeValueAsString(emp);
        System.out.println("对象转json = " + s);

        String json = "{\"empId\":\"myid\",\"eName\":\"myName\"}";
        Emp readValue = objectMapper.readValue(json, Emp.class);
        System.out.println("json转对象 = " + readValue);
    }

}
