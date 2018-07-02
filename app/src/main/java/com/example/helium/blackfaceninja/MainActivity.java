package com.example.helium.blackfaceninja;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private List<Tool> toolList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTool();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ToolAdapter adapter = new ToolAdapter(toolList);
        recyclerView.setAdapter(adapter);
        //adapter
    }

    private void initTool(){
        Tool controlRobot = new Tool("Control Robot",R.drawable.control);
        toolList.add(controlRobot);
        Tool voice = new Tool("Voice",R.drawable.voice);
        toolList.add(voice);
        Tool vnc = new Tool("VNC",R.drawable.vnc);
        toolList.add(vnc);
        Tool Tool0 = new Tool("Tool0",R.drawable.test);
        toolList.add(Tool0);
        Tool Tool1 = new Tool("Tool1",R.drawable.test);
        toolList.add(Tool1);
    }



    public void Jump(String tool) {
        switch (tool){
            case "Control Robot":
                //Intent intent = new Intent(MainActivity.this,PhoneControlActivity.class);
                Intent intent = new Intent("com.example.activitytest.Action_START");
                startActivity(intent);
                break;
            case "voice":
                break;
            case "vnc":
                break;
            default:
                //Toast.makeText(.getContext(),"Waiting for development", Toast.LENGTH_SHORT).show();
        }

    }


//    private static String TAG = "HAHAHA";
//    private Button voice;
//    private Button spaceDesk;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        //getAllAppNames();
//
//        ((Button)(findViewById(R.id.btn_controlRobot))).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,PhoneControlActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        ((Button)(findViewById(R.id.btn_spacedesk))).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Intent intent = new Intent(MainActivity.this,SpacedeskActivity.class);
//                //startActivity(intent);
//                StringBuffer strBuff = new StringBuffer();
//                strBuff.append("spacedesk Beta");
//                List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
//                for (PackageInfo pkInfo : packages){
//                    strBuff.append(pkInfo.toString());
//                }
//
//                PackageManager packageManager = getPackageManager();
//                Intent intent = new Intent();
//                intent = packageManager.getLaunchIntentForPackage("spacedesk Beta");
//                startActivity(intent);
//
//            }
//        });
//
//        voice = (Button)findViewById(R.id.btn_voice);
//        voice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,VoiceRobotActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        spaceDesk = (Button)findViewById(R.id.btn_spacedesk);
//        spaceDesk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    //ComUtils.showToast(MainActivity.this,"跳转添加qq");
////                第一种方式：是可以的跳转到qq主页面，不能跳转到qq聊天界面
//                    //应用的名字:VNC Viewer
//                    //应用的包名字:com.realvnc.viewer.android
//                    Intent intent = getPackageManager().getLaunchIntentForPackage("com.realvnc.viewer.android");
//                    startActivity(intent);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    //ComUtils.showToast(AboutSysActivity.this,"请检查是否安装QQ");
//                }
//            }
//        });
//
//    }
//
//    public void getAllAppNames(){
//
//        int j=0;
//
//        PackageManager pm=getPackageManager();
//        //PackageManager.GET_UNINSTALLED_PACKAGES==8192
//        List<PackageInfo> list2=pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
//        //PackageManager.GET_SHARED_LIBRARY_FILES==1024
//        //List<PackageInfo> list2=pm.getInstalledPackages(PackageManager.GET_SHARED_LIBRARY_FILES);
//        //PackageManager.GET_META_DATA==128
//        //List<PackageInfo> list2=pm.getInstalledPackages(PackageManager.GET_META_DATA);
//        //List<PackageInfo> list2=pm.getInstalledPackages(0);
//        //List<PackageInfo> list2=pm.getInstalledPackages(-10);
//        //List<PackageInfo> list2=pm.getInstalledPackages(10000);
//        for (PackageInfo packageInfo : list2) {
//            //得到手机上已经安装的应用的名字,即在AndriodMainfest.xml中的app_name。
//            String appName=packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
//            //得到应用所在包的名字,即在AndriodMainfest.xml中的package的值。
//            String packageName=packageInfo.packageName;
//            Log.i(TAG, "应用的名字:"+appName);
//            Log.i(TAG, "应用的包名字:"+packageName);
//
//            j++;
//        }
//        Log.i(TAG, "应用的总个数:"+j);
//    }


    public class ToolAdapter extends RecyclerView.Adapter<com.example.helium.blackfaceninja.ToolAdapter.ViewHolder> {

        private List<Tool> mToolList;

        class ViewHolder extends RecyclerView.ViewHolder{
            View toolView;
            ImageView toolImage;
            TextView toolName;

            public ViewHolder(View view){
                super(view);
                toolView = view;
                toolImage = (ImageView) view.findViewById(R.id.tool_image);
                toolName = (TextView) view.findViewById(R.id.tool_name);
            }
        }

        public ToolAdapter(List<Tool> toolList){
            mToolList = toolList;
        }

        @Override
        public com.example.helium.blackfaceninja.ToolAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tool_item,parent,false);
            final com.example.helium.blackfaceninja.ToolAdapter.ViewHolder holder = new com.example.helium.blackfaceninja.ToolAdapter.ViewHolder(view);
            holder.toolView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    Tool tool = mToolList.get(position);
                    Toast.makeText(v.getContext(),"name"+tool.getName(), Toast.LENGTH_SHORT).show();
                    //MainActivity.Jump(tool.getName());

                }
            });

            holder.toolImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    Tool tool = mToolList.get(position);
                    //Toast.makeText(v.getContext(),"image"+tool.getName(), Toast.LENGTH_SHORT).show();
                    switch(tool.getName()){
                        case "Control Robot":
                            Intent intent_CR = new Intent(MainActivity.this,PhoneControlActivity.class);
                            startActivity(intent_CR);
                            break;
                        case "VNC":
                            try {
                                Intent intent_VNC = getPackageManager().getLaunchIntentForPackage("com.realvnc.viewer.android");
                                startActivity(intent_VNC);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case "Voice":
                            Intent intent = new Intent(MainActivity.this,VoiceRobotActivity.class);
                            startActivity(intent);
                            break;
                        default:
                            Toast.makeText(v.getContext(),"image"+tool.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

            return holder;
        }

        @Override
        public void onBindViewHolder(com.example.helium.blackfaceninja.ToolAdapter.ViewHolder holder, int position) {
            Tool tool = mToolList.get(position);
            holder.toolImage.setImageResource(tool.getImageId());
            holder.toolName.setText(tool.getName());
        }

        @Override
        public int getItemCount() {
            return mToolList.size();
        }

    }



}
