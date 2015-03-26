package mate.vkmusic;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKSdkListener;
import com.vk.sdk.VKUIHelper;
import com.vk.sdk.api.VKError;
import com.vk.sdk.dialogs.VKCaptchaDialog;


public class MainActivity extends ActionBarActivity {

    private static String sTokenKey = "VK_ACCESS_TOKEN";
    private static String[] sMyScope = new String[]{VKScope.AUDIO,VKScope.FRIENDS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VKUIHelper.onCreate(this);
        VKSdk.initialize(sdkListener, "4845684", VKAccessToken.tokenFromSharedPreferences(this, sTokenKey));
        setContentView(R.layout.activity_main);
        VKSdk.authorize(sMyScope, true, false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        VKUIHelper.onResume(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VKUIHelper.onDestroy(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        VKUIHelper.onActivityResult(this, requestCode, resultCode, data);
    }

    private VKSdkListener sdkListener = new VKSdkListener() {
        @Override
        public void onCaptchaError(VKError captchaError) {
            new VKCaptchaDialog(captchaError).show();
        }

        @Override
        public void onTokenExpired(VKAccessToken expiredToken) {
            VKSdk.authorize(sMyScope);
        }

        @Override
        public void onAccessDenied(VKError authorizationError) {
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage(authorizationError.errorMessage)
                    .show();
        }

        @Override
        public void onReceiveNewToken(VKAccessToken newToken) {
            newToken.saveTokenToSharedPreferences(MainActivity.this, sTokenKey);
            Intent i = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(i);
        }

        @Override
        public void onAcceptUserToken(VKAccessToken token) {
            Intent i = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(i);
        }
    };
}
