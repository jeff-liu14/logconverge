package com.example.net.retrofit;


import com.example.net.BuildConfig;
import com.example.net.service.Service;
import com.example.net.utils.Constant;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by moment on 16/5/24.
 */
public class RetrofitUtils {

    private static Retrofit retrofit;

    public RetrofitUtils with() {
        OkHttpClient client = getUnsafeOkHttpClient();
        retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build();

        return this;
    }

    public OkHttpClient getUnsafeOkHttpClient() {
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};
        // Install the all-trusting trust manager
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            sslContext.init(null, trustAllCerts, new SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        // Create an ssl socket factory with our all-trusting manager
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        okBuilder.readTimeout(20, TimeUnit.SECONDS);
        okBuilder.connectTimeout(10, TimeUnit.SECONDS);
        okBuilder.writeTimeout(20, TimeUnit.SECONDS);
        okBuilder.sslSocketFactory(sslSocketFactory);
        okBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        if (BuildConfig.DEBUG) {
//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//            // set your desired log level
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//            okBuilder.addInterceptor(logging);
        }
        okBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                builder.addHeader("Connection", "keep-alive");
                builder.addHeader("Accept", "*/*");
                builder.addHeader("Content-Encoding", "gzip");
                builder.addHeader("clientversion", "");
                builder.addHeader("clientid", "");
                builder.addHeader("devicetype", 3 + "");
                builder.addHeader("devicetoken", "");
                builder.addHeader("accesstoken", "");
                builder.addHeader("qudao", "");
                builder.addHeader("Accept-Language", "");
                builder.addHeader("deviceinfo", "android");
                return chain.proceed(builder.build());
            }
        });
        OkHttpClient client = okBuilder.build();
        return client;
    }

    public Service build() {
        return retrofit.create(Service.class);
    }

}
