package com.example.opengl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.opengl.GLSurfaceView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView mGLView = new GLSurfaceView(this);
        //创建GLSurfaceView绘制器
        MyRenderer myRenderer = new MyRenderer();
        mGLView.setRenderer(myRenderer);
        //只有在数据改变时才进行Render，这样来提高性能
        mGLView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        setContentView(mGLView);
    }
}
