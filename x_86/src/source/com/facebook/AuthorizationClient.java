// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.facebook;

import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.model.GraphMultiResult;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;
import com.facebook.model.GraphUser;
import com.facebook.widget.WebDialog;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.Serializable;
import java.util.*;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.facebook:
//            AppEventsLogger, SessionLoginBehavior, FacebookException, Request, 
//            HttpMethod, AccessToken, RequestBatch, SessionDefaultAudience, 
//            GetTokenClient, AccessTokenSource, FacebookOperationCanceledException, FacebookServiceException, 
//            FacebookRequestError, Response

class AuthorizationClient
    implements Serializable
{
    static class AuthDialogBuilder extends com.facebook.widget.WebDialog.Builder
    {

        public WebDialog build()
        {
            Bundle bundle = getParameters();
            bundle.putString("redirect_uri", "fbconnect://success");
            bundle.putString("client_id", getApplicationId());
            bundle.putString("e2e", e2e);
            return new WebDialog(getContext(), "oauth", bundle, getTheme(), getListener());
        }

        public AuthDialogBuilder setE2E(String s)
        {
            e2e = s;
            return this;
        }

        private static final String OAUTH_DIALOG = "oauth";
        static final String REDIRECT_URI = "fbconnect://success";
        private String e2e;

        public AuthDialogBuilder(Context context1, String s, Bundle bundle)
        {
            super(context1, s, "oauth", bundle);
        }
    }

    abstract class AuthHandler
        implements Serializable
    {

        protected void addLoggingExtra(String s, Object obj)
        {
            if(methodLoggingExtras == null)
                methodLoggingExtras = new HashMap();
            Map map = methodLoggingExtras;
            String s1;
            if(obj == null)
                s1 = null;
            else
                s1 = obj.toString();
            map.put(s, s1);
        }

        void cancel()
        {
        }

        abstract String getNameForLogging();

        boolean needsInternetPermission()
        {
            return false;
        }

        boolean needsRestart()
        {
            return false;
        }

        boolean onActivityResult(int i, int j, Intent intent)
        {
            return false;
        }

        abstract boolean tryAuthorize(AuthorizationRequest authorizationrequest);

        private static final long serialVersionUID = 1L;
        Map methodLoggingExtras;
        final AuthorizationClient this$0;

        AuthHandler()
        {
            this$0 = AuthorizationClient.this;
            super();
        }
    }

    static class AuthorizationRequest
        implements Serializable
    {

        String getApplicationId()
        {
            return applicationId;
        }

        String getAuthId()
        {
            return authId;
        }

        SessionDefaultAudience getDefaultAudience()
        {
            return defaultAudience;
        }

        SessionLoginBehavior getLoginBehavior()
        {
            return loginBehavior;
        }

        List getPermissions()
        {
            return permissions;
        }

        String getPreviousAccessToken()
        {
            return previousAccessToken;
        }

        int getRequestCode()
        {
            return requestCode;
        }

        StartActivityDelegate getStartActivityDelegate()
        {
            return startActivityDelegate;
        }

        boolean isLegacy()
        {
            return isLegacy;
        }

        boolean needsNewTokenValidation()
        {
            return previousAccessToken != null && !isLegacy;
        }

        void setIsLegacy(boolean flag)
        {
            isLegacy = flag;
        }

        void setPermissions(List list)
        {
            permissions = list;
        }

        private static final long serialVersionUID = 1L;
        private final String applicationId;
        private final String authId;
        private final SessionDefaultAudience defaultAudience;
        private boolean isLegacy;
        private final SessionLoginBehavior loginBehavior;
        private List permissions;
        private final String previousAccessToken;
        private final int requestCode;
        private final transient StartActivityDelegate startActivityDelegate;

        AuthorizationRequest(SessionLoginBehavior sessionloginbehavior, int i, boolean flag, List list, SessionDefaultAudience sessiondefaultaudience, String s, String s1, 
                StartActivityDelegate startactivitydelegate, String s2)
        {
            isLegacy = false;
            loginBehavior = sessionloginbehavior;
            requestCode = i;
            isLegacy = flag;
            permissions = list;
            defaultAudience = sessiondefaultaudience;
            applicationId = s;
            previousAccessToken = s1;
            startActivityDelegate = startactivitydelegate;
            authId = s2;
        }
    }

    static interface BackgroundProcessingListener
    {

        public abstract void onBackgroundProcessingStarted();

        public abstract void onBackgroundProcessingStopped();
    }

    class GetTokenAuthHandler extends AuthHandler
    {

        void cancel()
        {
            if(getTokenClient != null)
            {
                getTokenClient.cancel();
                getTokenClient = null;
            }
        }

        String getNameForLogging()
        {
            return "get_token";
        }

        void getTokenCompleted(AuthorizationRequest authorizationrequest, Bundle bundle)
        {
            getTokenClient = null;
            notifyBackgroundProcessingStop();
            if(bundle != null)
            {
                ArrayList arraylist = bundle.getStringArrayList("com.facebook.platform.extra.PERMISSIONS");
                List list = authorizationrequest.getPermissions();
                if(arraylist != null && (list == null || arraylist.containsAll(list)))
                {
                    AccessToken accesstoken = AccessToken.createFromNativeLogin(bundle, AccessTokenSource.FACEBOOK_APPLICATION_SERVICE);
                    Result result = Result.createTokenResult(pendingRequest, accesstoken);
                    completeAndValidate(result);
                    return;
                }
                ArrayList arraylist1 = new ArrayList();
                Iterator iterator = list.iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    String s = (String)iterator.next();
                    if(!arraylist.contains(s))
                        arraylist1.add(s);
                } while(true);
                if(!arraylist1.isEmpty())
                    addLoggingExtra("new_permissions", TextUtils.join(",", arraylist1));
                authorizationrequest.setPermissions(arraylist1);
            }
            tryNextHandler();
        }

        boolean needsRestart()
        {
            return getTokenClient == null;
        }

        boolean tryAuthorize(AuthorizationRequest authorizationrequest)
        {
            getTokenClient = new GetTokenClient(context, authorizationrequest.getApplicationId());
            if(!getTokenClient.start())
            {
                return false;
            } else
            {
                notifyBackgroundProcessingStart();
                com.facebook.internal.PlatformServiceClient.CompletedListener completedlistener = authorizationrequest. new com.facebook.internal.PlatformServiceClient.CompletedListener() {

                    public void completed(Bundle bundle)
                    {
                        getTokenCompleted(request, bundle);
                    }

                    final GetTokenAuthHandler this$1;
                    final AuthorizationRequest val$request;

            
            {
                this$1 = final_gettokenauthhandler;
                request = AuthorizationRequest.this;
                super();
            }
                }
;
                getTokenClient.setCompletedListener(completedlistener);
                return true;
            }
        }

        private static final long serialVersionUID = 1L;
        private transient GetTokenClient getTokenClient;
        final AuthorizationClient this$0;

        GetTokenAuthHandler()
        {
            this$0 = AuthorizationClient.this;
            super();
        }
    }

    abstract class KatanaAuthHandler extends AuthHandler
    {

        protected boolean tryIntent(Intent intent, int i)
        {
            if(intent == null)
                return false;
            try
            {
                getStartActivityDelegate().startActivityForResult(intent, i);
            }
            catch(ActivityNotFoundException activitynotfoundexception)
            {
                return false;
            }
            return true;
        }

        private static final long serialVersionUID = 1L;
        final AuthorizationClient this$0;

        KatanaAuthHandler()
        {
            this$0 = AuthorizationClient.this;
            super();
        }
    }

    class KatanaLoginDialogAuthHandler extends KatanaAuthHandler
    {

        private Result createCancelOrErrorResult(AuthorizationRequest authorizationrequest, Intent intent)
        {
            String s;
            String s1;
            String s2;
            Bundle bundle = intent.getExtras();
            s = bundle.getString("com.facebook.platform.status.ERROR_TYPE");
            if("UserCanceled".equals(s) || "PermissionDenied".equals(s))
                return Result.createCancelResult(authorizationrequest, intent.getStringExtra("com.facebook.platform.status.ERROR_DESCRIPTION"));
            s1 = bundle.getString("com.facebook.platform.status.ERROR_JSON");
            s2 = null;
            if(s1 == null)
                break MISSING_BLOCK_LABEL_80;
            String s3;
            JVM INSTR new #62  <Class JSONObject>;
            s3 = JSONObjectInstrumentation.init(s1).getString("error_code");
            s2 = s3;
_L2:
            return Result.createErrorResult(authorizationrequest, s, intent.getStringExtra("com.facebook.platform.status.ERROR_DESCRIPTION"), s2);
            JSONException jsonexception;
            jsonexception;
            s2 = null;
            if(true) goto _L2; else goto _L1
_L1:
        }

        private Result handleResultOk(Intent intent)
        {
            Bundle bundle = intent.getExtras();
            String s = bundle.getString("com.facebook.platform.status.ERROR_TYPE");
            if(s == null)
                return Result.createTokenResult(pendingRequest, AccessToken.createFromNativeLogin(bundle, AccessTokenSource.FACEBOOK_APPLICATION_NATIVE));
            if("ServiceDisabled".equals(s))
            {
                addLoggingExtra("service_disabled", "1");
                return null;
            } else
            {
                return createCancelOrErrorResult(pendingRequest, intent);
            }
        }

        private void logEvent(String s, String s1, String s2)
        {
            if(s2 != null)
            {
                AppEventsLogger appeventslogger = AppEventsLogger.newLogger(context, applicationId);
                Bundle bundle = new Bundle();
                bundle.putString("app_id", applicationId);
                bundle.putString("action_id", s2);
                bundle.putLong(s1, System.currentTimeMillis());
                appeventslogger.logSdkEvent(s, null, bundle);
            }
        }

        String getNameForLogging()
        {
            return "katana_login_dialog";
        }

        boolean onActivityResult(int i, int j, Intent intent)
        {
            logEvent("fb_dialogs_native_login_dialog_complete", "fb_native_login_dialog_complete_time", callId);
            Result result;
            if(intent == null)
                result = Result.createCancelResult(pendingRequest, "Operation canceled");
            else
            if(NativeProtocol.isServiceDisabledResult20121101(intent))
                result = null;
            else
            if(j == 0)
                result = createCancelOrErrorResult(pendingRequest, intent);
            else
            if(j != -1)
                result = Result.createErrorResult(pendingRequest, "Unexpected resultCode from authorization.", null);
            else
                result = handleResultOk(intent);
            if(result != null)
                completeAndValidate(result);
            else
                tryNextHandler();
            return true;
        }

        boolean tryAuthorize(AuthorizationRequest authorizationrequest)
        {
            applicationId = authorizationrequest.getApplicationId();
            Intent intent = NativeProtocol.createLoginDialog20121101Intent(context, authorizationrequest.getApplicationId(), new ArrayList(authorizationrequest.getPermissions()), authorizationrequest.getDefaultAudience().getNativeProtocolAudience());
            if(intent == null)
            {
                return false;
            } else
            {
                callId = intent.getStringExtra("com.facebook.platform.protocol.CALL_ID");
                addLoggingExtra("call_id", callId);
                addLoggingExtra("protocol_version", Integer.valueOf(intent.getIntExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", 0)));
                addLoggingExtra("permissions", TextUtils.join(",", intent.getStringArrayListExtra("com.facebook.platform.extra.PERMISSIONS")));
                addLoggingExtra("write_privacy", intent.getStringExtra("com.facebook.platform.extra.WRITE_PRIVACY"));
                logEvent("fb_dialogs_native_login_dialog_start", "fb_native_login_dialog_start_time", callId);
                return tryIntent(intent, authorizationrequest.getRequestCode());
            }
        }

        private static final long serialVersionUID = 1L;
        private String applicationId;
        private String callId;
        final AuthorizationClient this$0;

        KatanaLoginDialogAuthHandler()
        {
            this$0 = AuthorizationClient.this;
            super();
        }
    }

    class KatanaProxyAuthHandler extends KatanaAuthHandler
    {

        private Result handleResultOk(Intent intent)
        {
            Bundle bundle = intent.getExtras();
            String s = bundle.getString("error");
            if(s == null)
                s = bundle.getString("error_type");
            String s1 = bundle.getString("error_code");
            String s2 = bundle.getString("error_message");
            if(s2 == null)
                s2 = bundle.getString("error_description");
            String s3 = bundle.getString("e2e");
            if(!Utility.isNullOrEmpty(s3))
                logWebLoginCompleted(applicationId, s3);
            Result result;
            if(s == null && s1 == null && s2 == null)
            {
                AccessToken accesstoken = AccessToken.createFromWebBundle(pendingRequest.getPermissions(), bundle, AccessTokenSource.FACEBOOK_APPLICATION_WEB);
                result = Result.createTokenResult(pendingRequest, accesstoken);
            } else
            {
                boolean flag = ServerProtocol.errorsProxyAuthDisabled.contains(s);
                result = null;
                if(!flag)
                    if(ServerProtocol.errorsUserCanceled.contains(s))
                        return Result.createCancelResult(pendingRequest, null);
                    else
                        return Result.createErrorResult(pendingRequest, s, s2, s1);
            }
            return result;
        }

        String getNameForLogging()
        {
            return "katana_proxy_auth";
        }

        boolean onActivityResult(int i, int j, Intent intent)
        {
            Result result;
            if(intent == null)
                result = Result.createCancelResult(pendingRequest, "Operation canceled");
            else
            if(j == 0)
                result = Result.createCancelResult(pendingRequest, intent.getStringExtra("error"));
            else
            if(j != -1)
                result = Result.createErrorResult(pendingRequest, "Unexpected resultCode from authorization.", null);
            else
                result = handleResultOk(intent);
            if(result != null)
                completeAndValidate(result);
            else
                tryNextHandler();
            return true;
        }

        boolean tryAuthorize(AuthorizationRequest authorizationrequest)
        {
            applicationId = authorizationrequest.getApplicationId();
            String s = AuthorizationClient.getE2E();
            Intent intent = NativeProtocol.createProxyAuthIntent(context, authorizationrequest.getApplicationId(), authorizationrequest.getPermissions(), s);
            addLoggingExtra("e2e", s);
            return tryIntent(intent, authorizationrequest.getRequestCode());
        }

        private static final long serialVersionUID = 1L;
        private String applicationId;
        final AuthorizationClient this$0;

        KatanaProxyAuthHandler()
        {
            this$0 = AuthorizationClient.this;
            super();
        }
    }

    static interface OnCompletedListener
    {

        public abstract void onCompleted(Result result);
    }

    static class Result
        implements Serializable
    {

        static Result createCancelResult(AuthorizationRequest authorizationrequest, String s)
        {
            return new Result(authorizationrequest, Code.CANCEL, null, s, null);
        }

        static Result createErrorResult(AuthorizationRequest authorizationrequest, String s, String s1)
        {
            return createErrorResult(authorizationrequest, s, s1, null);
        }

        static Result createErrorResult(AuthorizationRequest authorizationrequest, String s, String s1, String s2)
        {
            String s3 = TextUtils.join(": ", Utility.asListNoNulls(new String[] {
                s, s1
            }));
            return new Result(authorizationrequest, Code.ERROR, null, s3, s2);
        }

        static Result createTokenResult(AuthorizationRequest authorizationrequest, AccessToken accesstoken)
        {
            return new Result(authorizationrequest, Code.SUCCESS, accesstoken, null, null);
        }

        private static final long serialVersionUID = 1L;
        final Code code;
        final String errorCode;
        final String errorMessage;
        Map loggingExtras;
        final AuthorizationRequest request;
        final AccessToken token;

        private Result(AuthorizationRequest authorizationrequest, Code code1, AccessToken accesstoken, String s, String s1)
        {
            request = authorizationrequest;
            token = accesstoken;
            errorMessage = s;
            code = code1;
            errorCode = s1;
        }
    }

    static final class Result.Code extends Enum
    {

        public static Result.Code valueOf(String s)
        {
            return (Result.Code)Enum.valueOf(com/facebook/AuthorizationClient$Result$Code, s);
        }

        public static Result.Code[] values()
        {
            return (Result.Code[])$VALUES.clone();
        }

        String getLoggingValue()
        {
            return loggingValue;
        }

        private static final Result.Code $VALUES[];
        public static final Result.Code CANCEL;
        public static final Result.Code ERROR;
        public static final Result.Code SUCCESS;
        private final String loggingValue;

        static 
        {
            SUCCESS = new Result.Code("SUCCESS", 0, "success");
            CANCEL = new Result.Code("CANCEL", 1, "cancel");
            ERROR = new Result.Code("ERROR", 2, "error");
            Result.Code acode[] = new Result.Code[3];
            acode[0] = SUCCESS;
            acode[1] = CANCEL;
            acode[2] = ERROR;
            $VALUES = acode;
        }

        private Result.Code(String s, int i, String s1)
        {
            super(s, i);
            loggingValue = s1;
        }
    }

    static interface StartActivityDelegate
    {

        public abstract Activity getActivityContext();

        public abstract void startActivityForResult(Intent intent, int i);
    }

    class WebViewAuthHandler extends AuthHandler
    {

        private String loadCookieToken()
        {
            return getStartActivityDelegate().getActivityContext().getSharedPreferences("com.facebook.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).getString("TOKEN", "");
        }

        private void saveCookieToken(String s)
        {
            android.content.SharedPreferences.Editor editor = getStartActivityDelegate().getActivityContext().getSharedPreferences("com.facebook.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).edit();
            editor.putString("TOKEN", s);
            if(!editor.commit())
                Utility.logd("Facebook-AuthorizationClient", "Could not update saved web view auth handler token.");
        }

        void cancel()
        {
            if(loginDialog != null)
            {
                loginDialog.dismiss();
                loginDialog = null;
            }
        }

        String getNameForLogging()
        {
            return "web_view";
        }

        boolean needsInternetPermission()
        {
            return true;
        }

        boolean needsRestart()
        {
            return true;
        }

        void onWebDialogComplete(AuthorizationRequest authorizationrequest, Bundle bundle, FacebookException facebookexception)
        {
            Result result;
            if(bundle != null)
            {
                if(bundle.containsKey("e2e"))
                    e2e = bundle.getString("e2e");
                AccessToken accesstoken = AccessToken.createFromWebBundle(authorizationrequest.getPermissions(), bundle, AccessTokenSource.WEB_VIEW);
                result = Result.createTokenResult(pendingRequest, accesstoken);
                CookieSyncManager.createInstance(context).sync();
                saveCookieToken(accesstoken.getToken());
            } else
            if(facebookexception instanceof FacebookOperationCanceledException)
            {
                result = Result.createCancelResult(pendingRequest, "User canceled log in.");
            } else
            {
                e2e = null;
                String s = facebookexception.getMessage();
                boolean flag = facebookexception instanceof FacebookServiceException;
                String s1 = null;
                if(flag)
                {
                    FacebookRequestError facebookrequesterror = ((FacebookServiceException)facebookexception).getRequestError();
                    Object aobj[] = new Object[1];
                    aobj[0] = Integer.valueOf(facebookrequesterror.getErrorCode());
                    s1 = String.format("%d", aobj);
                    s = facebookrequesterror.toString();
                }
                result = Result.createErrorResult(pendingRequest, null, s, s1);
            }
            if(!Utility.isNullOrEmpty(e2e))
                logWebLoginCompleted(applicationId, e2e);
            completeAndValidate(result);
        }

        boolean tryAuthorize(AuthorizationRequest authorizationrequest)
        {
            applicationId = authorizationrequest.getApplicationId();
            Bundle bundle = new Bundle();
            if(!Utility.isNullOrEmpty(authorizationrequest.getPermissions()))
            {
                String s1 = TextUtils.join(",", authorizationrequest.getPermissions());
                bundle.putString("scope", s1);
                addLoggingExtra("scope", s1);
            }
            String s = authorizationrequest.getPreviousAccessToken();
            com.facebook.widget.WebDialog.OnCompleteListener oncompletelistener;
            if(!Utility.isNullOrEmpty(s) && s.equals(loadCookieToken()))
            {
                bundle.putString("access_token", s);
                addLoggingExtra("access_token", "1");
            } else
            {
                Utility.clearFacebookCookies(context);
                addLoggingExtra("access_token", "0");
            }
            oncompletelistener = authorizationrequest. new com.facebook.widget.WebDialog.OnCompleteListener() {

                public void onComplete(Bundle bundle, FacebookException facebookexception)
                {
                    onWebDialogComplete(request, bundle, facebookexception);
                }

                final WebViewAuthHandler this$1;
                final AuthorizationRequest val$request;

            
            {
                this$1 = final_webviewauthhandler;
                request = AuthorizationRequest.this;
                super();
            }
            }
;
            e2e = AuthorizationClient.getE2E();
            addLoggingExtra("e2e", e2e);
            loginDialog = ((com.facebook.widget.WebDialog.Builder)(new AuthDialogBuilder(getStartActivityDelegate().getActivityContext(), applicationId, bundle)).setE2E(e2e).setOnCompleteListener(oncompletelistener)).build();
            loginDialog.show();
            return true;
        }

        private static final long serialVersionUID = 1L;
        private String applicationId;
        private String e2e;
        private transient WebDialog loginDialog;
        final AuthorizationClient this$0;

        WebViewAuthHandler()
        {
            this$0 = AuthorizationClient.this;
            super();
        }
    }


    AuthorizationClient()
    {
    }

    private void addLoggingExtra(String s, String s1, boolean flag)
    {
        if(loggingExtras == null)
            loggingExtras = new HashMap();
        if(loggingExtras.containsKey(s) && flag)
            s1 = (new StringBuilder()).append((String)loggingExtras.get(s)).append(",").append(s1).toString();
        loggingExtras.put(s, s1);
    }

    private void completeWithFailure()
    {
        complete(Result.createErrorResult(pendingRequest, "Login attempt failed.", null));
    }

    private AppEventsLogger getAppEventsLogger()
    {
        if(appEventsLogger == null || appEventsLogger.getApplicationId() != pendingRequest.getApplicationId())
            appEventsLogger = AppEventsLogger.newLogger(context, pendingRequest.getApplicationId());
        return appEventsLogger;
    }

    private static String getE2E()
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("init", System.currentTimeMillis());
        }
        catch(JSONException jsonexception) { }
        if(!(jsonobject instanceof JSONObject))
            return jsonobject.toString();
        else
            return JSONObjectInstrumentation.toString((JSONObject)jsonobject);
    }

    private List getHandlerTypes(AuthorizationRequest authorizationrequest)
    {
        ArrayList arraylist = new ArrayList();
        SessionLoginBehavior sessionloginbehavior = authorizationrequest.getLoginBehavior();
        if(sessionloginbehavior.allowsKatanaAuth())
        {
            if(!authorizationrequest.isLegacy())
            {
                arraylist.add(new GetTokenAuthHandler());
                arraylist.add(new KatanaLoginDialogAuthHandler());
            }
            arraylist.add(new KatanaProxyAuthHandler());
        }
        if(sessionloginbehavior.allowsWebViewAuth())
            arraylist.add(new WebViewAuthHandler());
        return arraylist;
    }

    private void logAuthorizationMethodComplete(String s, Result result, Map map)
    {
        logAuthorizationMethodComplete(s, result.code.getLoggingValue(), result.errorMessage, result.errorCode, map);
    }

    private void logAuthorizationMethodComplete(String s, String s1, String s2, String s3, Map map)
    {
        if(pendingRequest != null) goto _L2; else goto _L1
_L1:
        Bundle bundle;
        bundle = newAuthorizationLoggingBundle("");
        bundle.putString("2_result", Result.Code.ERROR.getLoggingValue());
        bundle.putString("5_error_message", "Unexpected call to logAuthorizationMethodComplete with null pendingRequest.");
_L4:
        bundle.putString("3_method", s);
        bundle.putLong("1_timestamp_ms", System.currentTimeMillis());
        getAppEventsLogger().logSdkEvent("fb_mobile_login_method_complete", null, bundle);
        return;
_L2:
        bundle = newAuthorizationLoggingBundle(pendingRequest.getAuthId());
        if(s1 != null)
            bundle.putString("2_result", s1);
        if(s2 != null)
            bundle.putString("5_error_message", s2);
        if(s3 != null)
            bundle.putString("4_error_code", s3);
        if(map != null && !map.isEmpty())
        {
            JSONObject jsonobject = new JSONObject(map);
            String s4;
            if(!(jsonobject instanceof JSONObject))
                s4 = jsonobject.toString();
            else
                s4 = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
            bundle.putString("6_extras", s4);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void logAuthorizationMethodStart(String s)
    {
        Bundle bundle = newAuthorizationLoggingBundle(pendingRequest.getAuthId());
        bundle.putLong("1_timestamp_ms", System.currentTimeMillis());
        bundle.putString("3_method", s);
        getAppEventsLogger().logSdkEvent("fb_mobile_login_method_start", null, bundle);
    }

    private void logWebLoginCompleted(String s, String s1)
    {
        AppEventsLogger appeventslogger = AppEventsLogger.newLogger(context, s);
        Bundle bundle = new Bundle();
        bundle.putString("fb_web_login_e2e", s1);
        bundle.putLong("fb_web_login_switchback_time", System.currentTimeMillis());
        bundle.putString("app_id", s);
        appeventslogger.logSdkEvent("fb_dialogs_web_login_dialog_complete", null, bundle);
    }

    static Bundle newAuthorizationLoggingBundle(String s)
    {
        Bundle bundle = new Bundle();
        bundle.putLong("1_timestamp_ms", System.currentTimeMillis());
        bundle.putString("0_auth_logger_id", s);
        bundle.putString("3_method", "");
        bundle.putString("2_result", "");
        bundle.putString("5_error_message", "");
        bundle.putString("4_error_code", "");
        bundle.putString("6_extras", "");
        return bundle;
    }

    private void notifyBackgroundProcessingStart()
    {
        if(backgroundProcessingListener != null)
            backgroundProcessingListener.onBackgroundProcessingStarted();
    }

    private void notifyBackgroundProcessingStop()
    {
        if(backgroundProcessingListener != null)
            backgroundProcessingListener.onBackgroundProcessingStopped();
    }

    private void notifyOnCompleteListener(Result result)
    {
        if(onCompletedListener != null)
            onCompletedListener.onCompleted(result);
    }

    void authorize(AuthorizationRequest authorizationrequest)
    {
        if(authorizationrequest != null)
        {
            if(pendingRequest != null)
                throw new FacebookException("Attempted to authorize while a request is pending.");
            if(!authorizationrequest.needsNewTokenValidation() || checkInternetPermission())
            {
                pendingRequest = authorizationrequest;
                handlersToTry = getHandlerTypes(authorizationrequest);
                tryNextHandler();
                return;
            }
        }
    }

    void cancelCurrentHandler()
    {
        if(currentHandler != null)
            currentHandler.cancel();
    }

    boolean checkInternetPermission()
    {
        if(checkedInternetPermission)
            return true;
        if(checkPermission("android.permission.INTERNET") != 0)
        {
            String s = context.getString(com.facebook.android.R.string.com_facebook_internet_permission_error_title);
            String s1 = context.getString(com.facebook.android.R.string.com_facebook_internet_permission_error_message);
            complete(Result.createErrorResult(pendingRequest, s, s1));
            return false;
        } else
        {
            checkedInternetPermission = true;
            return true;
        }
    }

    int checkPermission(String s)
    {
        return context.checkCallingOrSelfPermission(s);
    }

    void complete(Result result)
    {
        if(currentHandler != null)
            logAuthorizationMethodComplete(currentHandler.getNameForLogging(), result, currentHandler.methodLoggingExtras);
        if(loggingExtras != null)
            result.loggingExtras = loggingExtras;
        handlersToTry = null;
        currentHandler = null;
        pendingRequest = null;
        loggingExtras = null;
        notifyOnCompleteListener(result);
    }

    void completeAndValidate(Result result)
    {
        if(result.token != null && pendingRequest.needsNewTokenValidation())
        {
            validateSameFbidAndFinish(result);
            return;
        } else
        {
            complete(result);
            return;
        }
    }

    void continueAuth()
    {
        if(pendingRequest == null || currentHandler == null)
            throw new FacebookException("Attempted to continue authorization without a pending request.");
        if(currentHandler.needsRestart())
        {
            currentHandler.cancel();
            tryCurrentHandler();
        }
    }

    Request createGetPermissionsRequest(String s)
    {
        Bundle bundle = new Bundle();
        bundle.putString("fields", "id");
        bundle.putString("access_token", s);
        return new Request(null, "me/permissions", bundle, HttpMethod.GET, null);
    }

    Request createGetProfileIdRequest(String s)
    {
        Bundle bundle = new Bundle();
        bundle.putString("fields", "id");
        bundle.putString("access_token", s);
        return new Request(null, "me", bundle, HttpMethod.GET, null);
    }

    RequestBatch createReauthValidationBatch(final Result pendingResult)
    {
        final ArrayList fbids = new ArrayList();
        final ArrayList tokenPermissions = new ArrayList();
        String s = pendingResult.token.getToken();
        Request.Callback callback = new Request.Callback() {

            public void onCompleted(Response response)
            {
                GraphUser graphuser;
                try
                {
                    graphuser = (GraphUser)response.getGraphObjectAs(com/facebook/model/GraphUser);
                }
                catch(Exception exception)
                {
                    return;
                }
                if(graphuser == null)
                    break MISSING_BLOCK_LABEL_28;
                fbids.add(graphuser.getId());
            }

            final AuthorizationClient this$0;
            final ArrayList val$fbids;

            
            {
                this$0 = AuthorizationClient.this;
                fbids = arraylist;
                super();
            }
        }
;
        String s1 = pendingRequest.getPreviousAccessToken();
        Request request = createGetProfileIdRequest(s1);
        request.setCallback(callback);
        Request request1 = createGetProfileIdRequest(s);
        request1.setCallback(callback);
        Request request2 = createGetPermissionsRequest(s1);
        request2.setCallback(new Request.Callback() {

            public void onCompleted(Response response)
            {
                GraphMultiResult graphmultiresult;
                GraphObjectList graphobjectlist;
                GraphObject graphobject;
                try
                {
                    graphmultiresult = (GraphMultiResult)response.getGraphObjectAs(com/facebook/model/GraphMultiResult);
                }
                catch(Exception exception)
                {
                    return;
                }
                if(graphmultiresult == null)
                    break MISSING_BLOCK_LABEL_71;
                graphobjectlist = graphmultiresult.getData();
                if(graphobjectlist == null)
                    break MISSING_BLOCK_LABEL_71;
                if(graphobjectlist.size() == 1)
                {
                    graphobject = (GraphObject)graphobjectlist.get(0);
                    tokenPermissions.addAll(graphobject.asMap().keySet());
                }
            }

            final AuthorizationClient this$0;
            final ArrayList val$tokenPermissions;

            
            {
                this$0 = AuthorizationClient.this;
                tokenPermissions = arraylist;
                super();
            }
        }
);
        RequestBatch requestbatch = new RequestBatch(new Request[] {
            request, request1, request2
        });
        requestbatch.setBatchApplicationId(pendingRequest.getApplicationId());
        requestbatch.addCallback(new RequestBatch.Callback() {

            public void onBatchCompleted(RequestBatch requestbatch1)
            {
                if(fbids.size() != 2 || fbids.get(0) == null || fbids.get(1) == null || !((String)fbids.get(0)).equals(fbids.get(1))) goto _L2; else goto _L1
_L1:
                Result result1;
                AccessToken accesstoken = AccessToken.createFromTokenWithRefreshedPermissions(pendingResult.token, tokenPermissions);
                result1 = Result.createTokenResult(pendingRequest, accesstoken);
_L3:
                complete(result1);
                notifyBackgroundProcessingStop();
                return;
_L2:
                Result result = Result.createErrorResult(pendingRequest, "User logged in as different Facebook user.", null);
                result1 = result;
                  goto _L3
                Exception exception1;
                exception1;
                complete(Result.createErrorResult(pendingRequest, "Caught exception", exception1.getMessage()));
                notifyBackgroundProcessingStop();
                return;
                Exception exception;
                exception;
                notifyBackgroundProcessingStop();
                throw exception;
            }

            final AuthorizationClient this$0;
            final ArrayList val$fbids;
            final Result val$pendingResult;
            final ArrayList val$tokenPermissions;

            
            {
                this$0 = AuthorizationClient.this;
                fbids = arraylist;
                pendingResult = result;
                tokenPermissions = arraylist1;
                super();
            }
        }
);
        return requestbatch;
    }

    BackgroundProcessingListener getBackgroundProcessingListener()
    {
        return backgroundProcessingListener;
    }

    boolean getInProgress()
    {
        return pendingRequest != null && currentHandler != null;
    }

    OnCompletedListener getOnCompletedListener()
    {
        return onCompletedListener;
    }

    StartActivityDelegate getStartActivityDelegate()
    {
        if(startActivityDelegate != null)
            return startActivityDelegate;
        if(pendingRequest != null)
            return new StartActivityDelegate() {

                public Activity getActivityContext()
                {
                    return pendingRequest.getStartActivityDelegate().getActivityContext();
                }

                public void startActivityForResult(Intent intent, int i)
                {
                    pendingRequest.getStartActivityDelegate().startActivityForResult(intent, i);
                }

                final AuthorizationClient this$0;

            
            {
                this$0 = AuthorizationClient.this;
                super();
            }
            }
;
        else
            return null;
    }

    boolean onActivityResult(int i, int j, Intent intent)
    {
        if(i == pendingRequest.getRequestCode())
            return currentHandler.onActivityResult(i, j, intent);
        else
            return false;
    }

    void setBackgroundProcessingListener(BackgroundProcessingListener backgroundprocessinglistener)
    {
        backgroundProcessingListener = backgroundprocessinglistener;
    }

    void setContext(final Activity activity)
    {
        context = activity;
        startActivityDelegate = new StartActivityDelegate() {

            public Activity getActivityContext()
            {
                return activity;
            }

            public void startActivityForResult(Intent intent, int i)
            {
                activity.startActivityForResult(intent, i);
            }

            final AuthorizationClient this$0;
            final Activity val$activity;

            
            {
                this$0 = AuthorizationClient.this;
                activity = activity1;
                super();
            }
        }
;
    }

    void setContext(Context context1)
    {
        context = context1;
        startActivityDelegate = null;
    }

    void setOnCompletedListener(OnCompletedListener oncompletedlistener)
    {
        onCompletedListener = oncompletedlistener;
    }

    void startOrContinueAuth(AuthorizationRequest authorizationrequest)
    {
        if(getInProgress())
        {
            continueAuth();
            return;
        } else
        {
            authorize(authorizationrequest);
            return;
        }
    }

    boolean tryCurrentHandler()
    {
        if(currentHandler.needsInternetPermission() && !checkInternetPermission())
        {
            addLoggingExtra("no_internet_permission", "1", false);
            return false;
        }
        boolean flag = currentHandler.tryAuthorize(pendingRequest);
        if(flag)
        {
            logAuthorizationMethodStart(currentHandler.getNameForLogging());
            return flag;
        } else
        {
            addLoggingExtra("not_tried", currentHandler.getNameForLogging(), true);
            return flag;
        }
    }

    void tryNextHandler()
    {
        if(currentHandler != null)
            logAuthorizationMethodComplete(currentHandler.getNameForLogging(), "skipped", null, null, currentHandler.methodLoggingExtras);
_L4:
        if(handlersToTry == null || handlersToTry.isEmpty()) goto _L2; else goto _L1
_L1:
        currentHandler = (AuthHandler)handlersToTry.remove(0);
        if(!tryCurrentHandler()) goto _L4; else goto _L3
_L3:
        return;
_L2:
        if(pendingRequest != null)
        {
            completeWithFailure();
            return;
        }
        if(true) goto _L3; else goto _L5
_L5:
    }

    void validateSameFbidAndFinish(Result result)
    {
        if(result.token == null)
        {
            throw new FacebookException("Can't validate without a token");
        } else
        {
            RequestBatch requestbatch = createReauthValidationBatch(result);
            notifyBackgroundProcessingStart();
            requestbatch.executeAsync();
            return;
        }
    }

    static final String EVENT_EXTRAS_APP_CALL_ID = "call_id";
    static final String EVENT_EXTRAS_DEFAULT_AUDIENCE = "default_audience";
    static final String EVENT_EXTRAS_IS_LEGACY = "is_legacy";
    static final String EVENT_EXTRAS_LOGIN_BEHAVIOR = "login_behavior";
    static final String EVENT_EXTRAS_MISSING_INTERNET_PERMISSION = "no_internet_permission";
    static final String EVENT_EXTRAS_NEW_PERMISSIONS = "new_permissions";
    static final String EVENT_EXTRAS_NOT_TRIED = "not_tried";
    static final String EVENT_EXTRAS_PERMISSIONS = "permissions";
    static final String EVENT_EXTRAS_PROTOCOL_VERSION = "protocol_version";
    static final String EVENT_EXTRAS_REQUEST_CODE = "request_code";
    static final String EVENT_EXTRAS_SERVICE_DISABLED = "service_disabled";
    static final String EVENT_EXTRAS_TRY_LEGACY = "try_legacy";
    static final String EVENT_EXTRAS_TRY_LOGIN_ACTIVITY = "try_login_activity";
    static final String EVENT_EXTRAS_WRITE_PRIVACY = "write_privacy";
    static final String EVENT_NAME_LOGIN_COMPLETE = "fb_mobile_login_complete";
    private static final String EVENT_NAME_LOGIN_METHOD_COMPLETE = "fb_mobile_login_method_complete";
    private static final String EVENT_NAME_LOGIN_METHOD_START = "fb_mobile_login_method_start";
    static final String EVENT_NAME_LOGIN_START = "fb_mobile_login_start";
    static final String EVENT_PARAM_AUTH_LOGGER_ID = "0_auth_logger_id";
    static final String EVENT_PARAM_ERROR_CODE = "4_error_code";
    static final String EVENT_PARAM_ERROR_MESSAGE = "5_error_message";
    static final String EVENT_PARAM_EXTRAS = "6_extras";
    static final String EVENT_PARAM_LOGIN_RESULT = "2_result";
    static final String EVENT_PARAM_METHOD = "3_method";
    private static final String EVENT_PARAM_METHOD_RESULT_SKIPPED = "skipped";
    static final String EVENT_PARAM_TIMESTAMP = "1_timestamp_ms";
    private static final String TAG = "Facebook-AuthorizationClient";
    private static final String WEB_VIEW_AUTH_HANDLER_STORE = "com.facebook.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY";
    private static final String WEB_VIEW_AUTH_HANDLER_TOKEN_KEY = "TOKEN";
    private static final long serialVersionUID = 1L;
    private transient AppEventsLogger appEventsLogger;
    transient BackgroundProcessingListener backgroundProcessingListener;
    transient boolean checkedInternetPermission;
    transient Context context;
    AuthHandler currentHandler;
    List handlersToTry;
    Map loggingExtras;
    transient OnCompletedListener onCompletedListener;
    AuthorizationRequest pendingRequest;
    transient StartActivityDelegate startActivityDelegate;




}
