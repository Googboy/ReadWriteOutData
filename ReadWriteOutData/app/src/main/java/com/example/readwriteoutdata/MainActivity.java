package com.example.readwriteoutdata;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class MainActivity extends Activity {
    private EditText et;
    private TextView show;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.editText);
        show = findViewById(R.id.show);

        findViewById(R.id.btnRead).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File sdcard = Environment.getExternalStorageDirectory();
                File myfile = new File(sdcard,"this is my file.txt");
                if (myfile.exists()){
                    FileInputStream fis = null;//文件输入流
                    try {
                        fis = new FileInputStream(myfile);
                        InputStreamReader isr = new InputStreamReader(fis,"utf-8");//输入流的读者
                        char[] input = new char[fis.available()];
                        isr.read(input);
                        isr.close();
                        fis.close();
                        String inString = new String(input);
                        show.setText(inString);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        findViewById(R.id.btnWrite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File sdcard = Environment.getExternalStorageDirectory();//获得当前SD卡的路径
                File myfile = new File(sdcard,"this is my file.txt");//在SD卡中创建文件，第一个参数是当前目标文件的存储路径，第二个参数为文件名称
                if (!sdcard.exists()){
                    Toast.makeText(getApplicationContext(),"当前设备没有内存卡目录",Toast.LENGTH_SHORT).show();
                    return;//如果没有SD卡，就没必要进行下去，直接跳出当前函数的执行
                }
                //存在SD卡的话就直接进行下一步，即下面的创建文件的步骤
                try {
                    myfile.createNewFile();
                    Toast.makeText(getApplicationContext(),"文件创建完成",Toast.LENGTH_SHORT).show();
                    FileOutputStream fos = new FileOutputStream(myfile);
                    OutputStreamWriter osw = new OutputStreamWriter(fos,"utf-8");
                    osw.write(et.getText().toString());
                    osw.flush();//全部加载到缓冲区，以便全部输出
                    osw.close();
                    fos.close();
                    Toast.makeText(getApplicationContext(),"文件写入完成",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
