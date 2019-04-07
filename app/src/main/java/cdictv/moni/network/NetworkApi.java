package cdictv.moni.network;


import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cdictv.moni.App;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkApi {
    private static Handler mhandler=new Handler() ;
    private static OkHttpClient sHttpClient=new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .build();
    public static void request(Request request, final Mycall mycall){
        sHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mhandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mycall.faild(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    final String str=response.body().string();
                    mhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(TextUtils.isEmpty(str)){
                                 Toast.makeText(App.instance,"请求数据为空",Toast.LENGTH_LONG).show();
                            }else {
                                mycall.success(str);
                            }
                        }
                    });
            }
        });
    }

}
