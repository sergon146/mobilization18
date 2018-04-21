package com.sergon146.core.utils;

public class Logger {
    private static LoggerDelegate devNull = new LoggerDelegate() {
        @Override
        public void v(String t, String s) {
        }

        @Override
        public void d(String t, String s) {
        }

        @Override
        public void d(String location, String t, String s) {
        }

        @Override
        public void i(Throwable t) {
        }

        @Override
        public void i(String t, String s) {
        }

        @Override
        public void w(String t, String s) {
        }

        @Override
        public void w(String location, String t, String s) {
        }

        @Override
        public void w(String t, String s, Throwable throwable) {
        }

        @Override
        public void w(String location, String t, String s, Throwable throwable) {
        }

        @Override
        public void e(String t, String s, Throwable tr) {
        }

        @Override
        public void userFeedbackException(String t, String s, String uid) {
        }

        @Override
        public void setRemoteSentryEnabled(boolean enabled) {
        }

        @Override
        public void setRemoteCrashlyticsEnabled(boolean enabled) {
        }

        @Override
        public void setRemoteSentrySeverity(long severity) {
        }

        @Override
        public void setRemoteCrashlyticsSeverity(long severity) {
        }
    };
    private static LoggerDelegate sDelegate = devNull;

    public static String getLocation() {
        StringBuilder res = new StringBuilder();
        StackTraceElement s;
        final Exception exception = new Exception();
        StackTraceElement[] traces = exception.getStackTrace();
        final int length = exception.getStackTrace().length;
        for (int i = 1; i < length && i < 5; i++) {
            s = traces[i];
            res.append(s.getFileName()).append(':').append(s.getLineNumber()).append('\n');
        }
        return res.toString();
    }

    public static void v(String t, String s) {
        sDelegate.v(t, s);
    }

    public static void d(String t, String s) {
        sDelegate.d(t, s);
    }

    public static void d(String location, String t, String s) {
        sDelegate.d(location, t, s);
    }

    public static void i(Throwable t) {
        sDelegate.i(t);
    }

    public static void i(String t, String s) {
        sDelegate.i(t, s);
    }

    public static void w(String location, String t, String s) {
        sDelegate.w(location, t, s);
    }

    public static void w(String location, String t, String s, Throwable throwable) {
        sDelegate.w(location, t, s, throwable);
    }

    public static void e(String t, String s, Throwable tr) {
        sDelegate.e(t, s, tr);
    }

    public static void userFeedbackException(String t, String s, String uid) {
        sDelegate.userFeedbackException(t, s, uid);
    }

    public static void setDelegate(LoggerDelegate delegate) {
        sDelegate = delegate == null ? devNull : delegate;
    }


    public interface LoggerDelegate {

        void v(String t, String s);

        void d(String t, String s);

        void d(String location, String t, String s);

        void i(Throwable t);

        void i(String t, String s);

        void w(String t, String s);

        void w(String location, String t, String s);

        void w(String t, String s, Throwable throwable);

        void w(String location, String t, String s, Throwable throwable);

        void e(String t, String s, Throwable tr);

        void userFeedbackException(String t, String s, String uid);

        void setRemoteSentryEnabled(boolean enabled);

        void setRemoteCrashlyticsEnabled(boolean enabled);

        void setRemoteSentrySeverity(long severity);

        void setRemoteCrashlyticsSeverity(long severity);
    }
}
