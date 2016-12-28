package bruce.chang.testeventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    Button bt_send, bt_send_stick;
    TextView tv_receive_event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_send = (Button) findViewById(R.id.bt_send);
        bt_send_stick = (Button) findViewById(R.id.bt_send_stick);
        tv_receive_event = (TextView) findViewById(R.id.tv_receive_event);
        //1：注册
        EventBus.getDefault().register(MainActivity.this);
        //跳转到发送页面
        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SubscribeActivity.class));
            }
        });
        //发送粘性事件跳转到发送页面
        bt_send_stick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1:构造消息发送的实体类这里使用LocalMessageStick类
                //2：发送粘性事件消息
                EventBus.getDefault().postSticky(new LocalMessageStick("张无忌", 30));
                startActivity(new Intent(MainActivity.this, SubscribeActivity.class));
            }
        });
    }

    //5：接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void LocalMessage(LocalMessage localMessage) {
        tv_receive_event.setText(localMessage.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //2：解注册
        EventBus.getDefault().unregister(MainActivity.this);
    }
}
