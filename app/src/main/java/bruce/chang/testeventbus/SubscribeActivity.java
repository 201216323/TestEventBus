package bruce.chang.testeventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SubscribeActivity extends AppCompatActivity {

    Button bt_send_to_main, bt_receive_event;
    TextView tv_receive_event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);
        setTitle("Eventbus发送数据页面");
        bt_send_to_main = (Button) findViewById(R.id.bt_send_to_main);
        bt_receive_event = (Button) findViewById(R.id.bt_receive_event);
        tv_receive_event = (TextView) findViewById(R.id.tv_receive_event);


        //发送数据到主线程
        bt_send_to_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //4：发送数据
                EventBus.getDefault().post(new LocalMessage("风清扬", 20));
                finish();
            }
        });
        //接收粘性事件
        bt_receive_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //4：注册（注册后既可以接收到粘性事件）
                EventBus.getDefault().register(SubscribeActivity.this);
            }
        });
    }

    //3：接收消息
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void LocalMessageStick(LocalMessageStick localMessage) {
        tv_receive_event.setText(localMessage.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //5：解注册
        EventBus.getDefault().unregister(SubscribeActivity.this);
    }
}
