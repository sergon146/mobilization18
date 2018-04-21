package com.sergon146.core.utils;

import java.io.InterruptedIOException;
import java.net.SocketException;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * @author Sergon146 (sergon146@gmail.com)
 * @since 08.04.2018.
 */
public class ErrorManager {

    private static final String LOG_TAG = "ErrorManager";

    private static PublishSubject<Throwable> errors = PublishSubject.create();

    public static Observable<Throwable> errors() {
        return errors
                .filter(throwable -> !"Canceled".equals(throwable.getMessage()))
                .filter(throwable -> !"Socket closed".equals(throwable.getMessage())
                        && !(throwable instanceof SocketException))
                .filter(throwable -> !(throwable instanceof InterruptedIOException))
                //.filter(throwable -> ! (throwable instanceof UnknownHostException))
                .doOnNext((t) -> Logger.w(Logger.getLocation(), LOG_TAG, "errors", t));
    }

    public static void registerError(Throwable t) {
        errors.onNext(t);
    }
}
