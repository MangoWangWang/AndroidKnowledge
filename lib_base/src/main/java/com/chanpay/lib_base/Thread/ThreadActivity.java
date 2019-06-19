package com.chanpay.lib_base.Thread;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chanpay.lib_base.R;

public class ThreadActivity extends AppCompatActivity {

    private MyThread1 mt1;
    private MyThread2 mt2;
    private TextView mTextView;
    private Handler mHandler;

    // 线程变量
    MyTask mTask;

    // 主布局中的UI组件
    Button button,cancel; // 加载、取消按钮
    TextView text; // 更新的UI组件
    ProgressBar progressBar; // 进度条



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        mTextView = findViewById(R.id.tv_message);
        button =  findViewById(R.id.button);
        cancel =  findViewById(R.id.cancel);
        text =  findViewById(R.id.text);
        progressBar =  findViewById(R.id.progress_bar);
    }


    public void StartThread(View view) {
        //步骤2:创建线程类的实例
        //创建二个线程，模拟二个窗口卖票
        mt1 = new MyThread1("窗口1");
        mt2 = new MyThread2("窗口2");

        //步骤3：调用start()方法开启线程
        //启动二个线程，也即是窗口，开始卖票
        mt1.start();
        mt2.start();
    }

    public void StartRunnable(View view) {

        //步骤2:创建线程类的实例
        //因为是两个窗口共卖100张票,即共用资源
        //所以只实例化一个实现了Runnable接口的类
        MyThread3 mt = new MyThread3();

        //因为要创建二个线程，模拟二个窗口卖票
        Thread mt11 = new Thread(mt, "窗口1");
        Thread mt12 = new Thread(mt, "窗口2");

        //步骤3：调用start()方法开启线程
        //启动二个线程，也即是窗口，开始卖票
        mt11.start();
        mt12.start();

    }

    public void StartHandler(View view) {

        // 步骤2：在主线程中创建Handler实例
        mHandler = new Mhandler();

        // 采用继承Thread类实现多线程演示
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 步骤3：创建所需的消息对象
                Message msg = Message.obtain();
                msg.what = 1; // 消息标识
                msg.obj = "A"; // 消息内存存放

                // 步骤4：在工作线程中 通过Handler发送消息到消息队列中
                mHandler.sendMessage(msg);
            }
        }.start();

        // 此处用2个工作线程展示
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 通过sendMessage（）发送
                // a. 定义要发送的消息
                Message msg = Message.obtain();
                msg.what = 2; //消息的标识
                msg.obj = "B"; // 消息的存放
                // b. 通过Handler发送消息到其绑定的消息队列
                mHandler.sendMessage(msg);
            }
        }.start();

    }

    public void StartIntentService(View view) {
    }

    public void StartHandlerThread(View view) {
    }

    public void StopThread(View view) {
        mt1.stop();
        mt2.stop();
    }

    public void StartAsycTask(View view) {
        if (mTask!=null) return;
        mTask = new MyTask();

        // 加载按钮按按下时，则启动AsyncTask
        // 任务完成后更新TextView的文本
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * 步骤3：手动调用execute(Params... params) 从而执行异步线程任务
                 * 注：
                 *    a. 必须在UI线程中调用
                 *    b. 同一个AsyncTask实例对象只能执行1次，若执行第2次将会抛出异常
                 *    c. 执行任务中，系统会自动调用AsyncTask的一系列方法：onPreExecute() 、doInBackground()、onProgressUpdate() 、onPostExecute()
                 *    d. 不能手动调用上述方法
                 */
                if (mTask ==null) return;
                mTask.execute();
                button.setEnabled(false);
            }
        });

        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取消一个正在执行的任务,onCancelled方法将会被调用
                mTask.cancel(true);
            }
        });

    }


    //因为这里需要有两个操作:一个窗口卖票速度是1s/张,一个窗口是3s/张
    //第一个Thread子类实现一个窗口卖票速度是1s/张
    private class MyThread1 extends Thread{

        private int ticket = 100;//一个窗口有100张票
        private String name; //窗口名, 也即是线程的名字

        public MyThread1(String name){
            this.name=name;
        }

        //在run方法里复写需要进行的操作:卖票速度是1s/张
        @Override
        public void run(){
            while (ticket>0){
                ticket--;
                System.out.println(name + "卖掉了1张票，剩余票数为:"+ticket);

                try {
                    Thread.sleep(1000);//卖票速度是1s一张
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //第二个Thread子类实现一个窗口卖票速度是3s/张
    private class MyThread2 extends Thread{

        private int ticket = 100;//一个窗口有100张票
        private String name; //窗口名, 也即是线程的名字

        public MyThread2(String name){
            this.name=name;
        }

        //在run方法里复写需要进行的操作:卖票速度是3s/张
        @Override
        public void run(){
            while (ticket>0){
                ticket--;
                System.out.println(name + "卖掉了1张票，剩余票数为:"+ticket);

                try {
                    Thread.sleep(3000);//卖票速度是1s一张
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    //步骤1:创建线程类，实现Runnable接口
    private class MyThread3 implements Runnable{

        private int ticket = 100;//两个窗口一共要卖100张票

        //在run方法里复写需要进行的操作:卖票速度1s/张
        @Override
        public void run(){
            while (ticket>0){
                ticket--;
                System.out.println(Thread.currentThread().getName() + "卖掉了1张票，剩余票数为:"+ticket);

                try {
                    Thread.sleep(1000);//卖票速度是1s一张
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    // 步骤1：（自定义）新创建Handler子类(继承Handler类) & 复写handleMessage（）方法
    class Mhandler extends Handler {

        // 通过复写handlerMessage() 从而确定更新UI的操作
        @Override
        public void handleMessage(Message msg) {
            // 根据不同线程发送过来的消息，执行不同的UI操作
            // 根据 Message对象的what属性 标识不同的消息
            switch (msg.what) {
                case 1:
                    mTextView.setText("执行了线程1的UI操作");
                    break;
                case 2:
                    mTextView.setText("执行了线程2的UI操作");
                    break;
            }
        }
    }


     class MyTask extends AsyncTask<String, Integer, String> {

        // 方法1：onPreExecute（）
        // 作用：执行 线程任务前的操作
        @Override
        protected void onPreExecute() {
            text.setText("加载中");
            // 执行前显示提示
        }


        // 方法2：doInBackground（）
        // 作用：接收输入参数、执行任务中的耗时操作、返回 线程任务执行的结果
        // 此处通过计算从而模拟“加载进度”的情况
        @Override
        protected String doInBackground(String... params) {

            try {
                int count = 0;
                int length = 1;
                while (count<99) {

                    count += length;
                    // 可调用publishProgress（）显示进度, 之后将执行onProgressUpdate（）
                    publishProgress(count);
                    // 模拟耗时任务
                    Thread.sleep(50);
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        // 方法3：onProgressUpdate（）
        // 作用：在主线程 显示线程任务执行的进度
        @Override
        protected void onProgressUpdate(Integer... progresses) {

            progressBar.setProgress(progresses[0]);
            text.setText("loading..." + progresses[0] + "%");


        }

        // 方法4：onPostExecute（）
        // 作用：接收线程任务执行结果、将执行结果显示到UI组件
        @Override
        protected void onPostExecute(String result) {
            // 执行完毕后，则更新UI
            text.setText("加载完毕");
            mTask = null;
            button.setEnabled(true);
        }

        // 方法5：onCancelled()
        // 作用：将异步任务设置为：取消状态
        @Override
        protected void onCancelled() {
            mTask=null;
            text.setText("已取消");
            progressBar.setProgress(0);
            button.setEnabled(true);

        }
    }



}
