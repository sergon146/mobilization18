package com.sergon146.mobilization18.core;

import com.sergon146.mobilization18.core.api.PictureApiService;
import com.sergon146.mobilization18.core.rx.RxThreadCallAdapter;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.CipherSuite;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

public class Core {
    private static Core instance;
    private PictureApiService apiService;
    private String endpoint;

    public static Core initInstance(String endpoint) {
        if (instance == null) {
            instance = new Core();
        }

        instance.endpoint = endpoint;
        return instance;
    }

    public static PictureApiService api() {
        return instance.apiService;
    }

    public void initApi() {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new RxThreadCallAdapter(Schedulers.io()))
                .baseUrl(endpoint);

        apiService = retrofitBuilder
                .client(createClientBuilder().build())
                .build()
                .create(PictureApiService.class);
    }

    private OkHttpClient.Builder createClientBuilder() {
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectionSpecs(Collections.singletonList(createConnectionSpec()))
                .connectionPool(new ConnectionPool(5, 30, TimeUnit.SECONDS));
    }

    private ConnectionSpec createConnectionSpec() {
        return new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .cipherSuites(
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
                .build();
    }
}
