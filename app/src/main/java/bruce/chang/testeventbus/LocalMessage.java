package bruce.chang.testeventbus;

/**
 * Created by: BruceChang
 * Date on : 2016/12/28.
 * Time on: 18:17
 * Progect_Name:TestEventBus
 * Source Github：
 * Description:Eventbus发送的数据实体类
 * 4：创建发送的数据实体类
 */

public class LocalMessage {

    private String name;
    private int age;

    public LocalMessage(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        String s = "我的名称是：" + name + "\n" + "我的年纪是：" + age;
        return s;
    }
}
