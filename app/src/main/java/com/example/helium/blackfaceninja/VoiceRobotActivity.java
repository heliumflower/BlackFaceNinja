package com.example.helium.blackfaceninja;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class VoiceRobotActivity extends Activity implements View.OnClickListener {

    private static final String TAG = MainActivity.class .getSimpleName();
    //private EditText et_input;//布局文字部分名
    private LinearLayout linearLayout;
    private String speakString;
    private TextView robotOut;
    String midString;
    //private Button btn_startspeech, btn_startspeektext ;//布局中Button名
    private HashMap<String, String> mIatResults = new LinkedHashMap<String , String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initView() ;
        initSpeech() ;

        ((TextView)findViewById(R.id.robot_out)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Patterns.WEB_URL.matcher(speakString).matches() || URLUtil.isValidUrl(speakString)) {
                    //Toast.makeText(getApplicationContext(), speakString,
                    //        Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(speakString));
                    startActivity(intent);
                }else{
                    return;
                }
                //Toast.makeText(getApplicationContext(), "默认Toast样式",
                //        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,

                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_voice_robot);
        //et_input = (EditText) findViewById(R.id.et_input );

        //linearLayout = (LinearLayout) findViewById(R.id.bg_main);
        //linearLayout.setOnClickListener(this);
//        ((TextView)findViewById(R.id.wtgx)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startSpeechDialog();
////                Intent intent = new Intent(MainActivity.this,UrlActivity.class);
////                intent.putExtra("url","www.baidu.com");
////                startActivity(intent);
////                Uri uri = Uri.parse("https://www.baidu.com");
////                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
////                startActivity(intent);
//            }
//        });

        ((ImageView)findViewById(R.id.voice_input)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSpeechDialog();
            }
        });
        //btn_startspeech = (Button) findViewById(R.id.btn_startspeech );
        //btn_startspeektext = (Button) findViewById(R.id.btn_startspeektext );
        //btn_startspeech .setOnClickListener(this) ;
        //btn_startspeektext .setOnClickListener(this) ;

//        robotOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "默认Toast样式",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

        ((TextView)findViewById(R.id.robot_out)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Patterns.WEB_URL.matcher(speakString).matches() || URLUtil.isValidUrl(speakString)) {
                    //Toast.makeText(getApplicationContext(), speakString,
                    //        Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(speakString));
                    startActivity(intent);
                }else{
                    return;
                }
                //Toast.makeText(getApplicationContext(), "默认Toast样式",
                //        Toast.LENGTH_SHORT).show();
            }
        });

//        String testString = "http://m.elong.com/hotel/2102/nlist/#indate=2018-05-18&outdate=2018-05-19&keywords=\" target=\"_blank";
//        String rgex ="^((http|https)://)?.*\"$";
//
////                        String rgex = "href(.*)>";
//        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
//        Matcher matcher = pattern.matcher(testString);
////                        if(matcher.find()){
////
//        String test = matcher.group(0);
//        robotOut.setText(test);

//        robotOut.setText();

    }

    private void initSpeech() {
        SpeechUtility. createUtility( this, SpeechConstant. APPID + "=5aca0252" );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btn_startspeech:
//                startSpeechDialog();//语音识别（把声音转文字）
//                break;
//            case R.id. btn_startspeektext:
//                speekText();// 语音合成（把文字转声音）
//                break;
//            case R.id.bg_main:
//                startSpeechDialog();
//                break;
        }

    }

    private void speekText() {
        //1. 创建 SpeechSynthesizer 对象 , 第二个参数： 本地合成时传 InitListener
        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer( this, null);
        //2.合成参数设置，详见《 MSC Reference Manual》 SpeechSynthesizer 类
        //设置发音人（更多在线发音人，用户可参见 附录 13.2
        //mTts.setParameter(SpeechConstant. VOICE_NAME, "vixyun" ); // 设置发音人
        mTts.setParameter(SpeechConstant. VOICE_NAME, "voicer" ); // 设置发音人
        mTts.setParameter(SpeechConstant. SPEED, "50" );// 设置语速
        mTts.setParameter(SpeechConstant. VOLUME, "80" );// 设置音量，范围 0~100
        mTts.setParameter(SpeechConstant. ENGINE_TYPE, SpeechConstant. TYPE_CLOUD); //设置云端
        //设置合成音频保存位置（可自定义保存位置），保存在 “./sdcard/iflytek.pcm”
        //保存在 SD 卡需要在 AndroidManifest.xml 添加写 SD 卡权限
        //仅支持保存为 pcm 和 wav 格式， 如果不需要保存合成音频，注释该行代码
        mTts.setParameter(SpeechConstant. TTS_AUDIO_PATH, "./sdcard/iflytek.pcm" );
        //3.开始合成
        mTts.startSpeaking( speakString , new MySynthesizerListener()) ;

    }

    class MySynthesizerListener implements SynthesizerListener {

        @Override
        public void onSpeakBegin() {
            showTip(" 开始播放 ");
        }

        @Override
        public void onSpeakPaused() {
            showTip(" 暂停播放 ");
        }

        @Override
        public void onSpeakResumed() {
            showTip(" 继续播放 ");
        }

        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos ,
                                     String info) {
            // 合成进度
        }

        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
            // 播放进度
        }

        @Override
        public void onCompleted(SpeechError error) {
            if (error == null) {
                showTip("播放完成 ");
            } else if (error != null ) {
                showTip(error.getPlainDescription( true));
            }
        }

        @Override
        public void onEvent(int eventType, int arg1 , int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话 id，当业务出错时将会话 id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话 id为null
            //if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //     String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //     Log.d(TAG, "session id =" + sid);
            //}
        }
    }

    private void startSpeechDialog() {
        //1. 创建RecognizerDialog对象
        RecognizerDialog mDialog = new RecognizerDialog(this, new MyInitListener()) ;
        //2. 设置accent、 language等参数
        mDialog.setParameter(SpeechConstant. LANGUAGE, "zh_cn" );// 设置中文
        mDialog.setParameter(SpeechConstant. ACCENT, "mandarin" );
        // 若要将UI控件用于语义理解，必须添加以下参数设置，设置之后 onResult回调返回将是语义理解
        // 结果
        // mDialog.setParameter("asr_sch", "1");
        // mDialog.setParameter("nlp_version", "2.0");
        //3.设置回调接口
        mDialog.setListener( new MyRecognizerDialogListener()) ;
        //4. 显示dialog，接收语音输入
        mDialog.show();
        TextView t = (TextView)mDialog.getWindow().getDecorView().findViewWithTag("textlink");
        t.setText("");
    }

    class MyRecognizerDialogListener implements RecognizerDialogListener {

        /**
         * @param results
         * @param isLast  是否说完了
         */
        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            String result = results.getResultString(); //为解析的
            showTip(result) ;
            //System. out.println(" 没有解析的 :" + result);

            String text = JsonParser.parseIatResult(result) ;//解析过后的
            //System. out.println(" 解析后的 :" + text);

            String sn = null;
            // 读取json结果中的 sn字段
            try {
                JSONObject resultJson = new JSONObject(results.getResultString()) ;
                sn = resultJson.optString("sn" );
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mIatResults .put(sn, text) ;//没有得到一句，添加到

//            StringBuffer resultBuffer = new StringBuffer();
//            for (String key : mIatResults.keySet()) {
//                resultBuffer.append(mIatResults .get(key));
//            }

            //听到的文字转成String
            //et_input.setText(resultBuffer.toString());// 设置输入框的文本
            //et_input.setSelection(et_input.length()) ;//把光标定位末尾

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        StringBuffer resultBuffer = new StringBuffer();
                        for (String key : mIatResults.keySet()) {
                            resultBuffer.append(mIatResults .get(key));
                        }

                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                //.url("http://119.27.170.189:8080/ask?qstr=附近的美食&uid=2233333uih")
                                //.url("http://119.27.170.189:8080/ask?qstr=" + resultBuffer.toString() + "&uid=2233333uih")
                                .url("http://192.168.31.78:8080/ask?qstr=" + resultBuffer.toString() + "&uid=2233333uih")
                                .build();
                        Response response = client.newCall(request).execute();
                        String responseData = response.body().string();



                        showRespnse(responseData);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        private void showRespnse(final String response){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONTokener tokener = new JSONTokener(response) ;
                        JSONObject joResult = new JSONObject(tokener);
                        //et_input.setText(joResult.getString("response"));
                        speakString = joResult.getString("response");

                        String rgex ="href=\"(.*?)\" target";
                        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
                        Matcher matcher = pattern.matcher(speakString);
                        if(matcher.find()) {
                            speakString = matcher.group(1);
                        }

                        robotOut = (TextView) findViewById(R.id.robot_out);
                        robotOut.setMovementMethod(ScrollingMovementMethod.getInstance());
                        if(robotOut.getLineCount()>1){
                            robotOut.setGravity(Gravity.LEFT);
                        }else{
                            robotOut.setGravity(Gravity.CENTER);
                        }

                        robotOut.setText(speakString);
                        midString = speakString;
                        if(Patterns.WEB_URL.matcher(speakString).matches() || URLUtil.isValidUrl(speakString)){
                            speakString = "点击链接可查看更多详细内容";
                        }
                        speekText();// 语音合成（把文字转声音）
                        speakString = midString;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @Override
        public void onError(SpeechError speechError) {

        }
    }

    class MyInitListener implements InitListener {

        @Override
        public void onInit(int code) {
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败 ");
            }

        }
    }

    /**
     * 语音识别
     */
    private void startSpeech() {
        //1. 创建SpeechRecognizer对象，第二个参数： 本地识别时传 InitListener
        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer( this, null); //语音识别器
        //2. 设置听写参数，详见《 MSC Reference Manual》 SpeechConstant类
        mIat.setParameter(SpeechConstant. DOMAIN, "iat" );// 短信和日常用语： iat (默认)
        mIat.setParameter(SpeechConstant. LANGUAGE, "zh_cn" );// 设置中文
        mIat.setParameter(SpeechConstant. ACCENT, "mandarin" );// 设置普通话
        //3. 开始听写
        mIat.startListening( mRecoListener);
    }


    // 听写监听器
    private RecognizerListener mRecoListener = new RecognizerListener() {
        // 听写结果回调接口 (返回Json 格式结果，用户可参见附录 13.1)；
        //一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
        //关于解析Json的代码可参见 Demo中JsonParser 类；
        //isLast等于true 时会话结束。
        public void onResult(RecognizerResult results, boolean isLast) {
            //Log.e (TAG, results.getResultString());
            System.out.println(results.getResultString()) ;
            showTip(results.getResultString()) ;
        }

        // 会话发生错误回调接口
        public void onError(SpeechError error) {
            showTip(error.getPlainDescription(true)) ;
            // 获取错误码描述
            //Log. e(TAG, "error.getPlainDescription(true)==" + error.getPlainDescription(true ));
        }

        // 开始录音
        public void onBeginOfSpeech() {
            showTip(" 开始录音 ");
        }

        //volume 音量值0~30， data音频数据
        public void onVolumeChanged(int volume, byte[] data) {
            showTip(" 声音改变了 ");
        }

        // 结束录音
        public void onEndOfSpeech() {
            showTip(" 结束录音 ");
        }

        // 扩展用接口
        public void onEvent(int eventType, int arg1 , int arg2, Bundle obj) {
        }
    };

    private void showTip (String data) {
        //Toast.makeText( this, data, Toast.LENGTH_SHORT).show() ;
    }
}
