package com.kyle.ie.auth.view;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kyle.ie.MainActivity;
import com.kyle.ie.R;
import com.kyle.ie.auth.controller.IAuthController;
import com.kyle.ie.auth.form.ILoginForm;
import com.kyle.ie.auth.form.LoginForm;
import com.kyle.ie.common.callback.CallbackException;
import com.kyle.ie.common.callback.ICallback;
import com.kyle.ie.common.callback.Payload;
import com.kyle.ie.common.controller.ControllerFactory;
import com.kyle.ie.common.view.UserDashboardActivityRetriever;

public class LoginActivity extends AppCompatActivity {

    private static String BLANK_STR = "";

    private IAuthController _authController;

    private EditText _emailField;
    private EditText _passwordField;

    private Button _submitBtn;
    private Button _registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _authController = ControllerFactory.getInstance().createAuthController();

        _emailField = (EditText) findViewById(R.id.login_tf_username);
        _passwordField = (EditText) findViewById(R.id.login_tf_password);

        _submitBtn = (Button) findViewById(R.id.login_btn_submit);
        _registerBtn = (Button) findViewById(R.id.login_btn_register);

        _submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ILoginForm form = createLoginForm();
                _authController.login(form, new ICallback<String>() {

                    @Override
                    public void callback(Payload<String> payload) {
                        if(payload.isSuccessful()) {
                            Intent dashboard = new Intent(LoginActivity.this, new UserDashboardActivityRetriever().getDashboardActivityForUserType(payload.getData()));
                            startActivity(dashboard);
                        } else {
                            _passwordField.setText(BLANK_STR);
                            AlertDialog dialog = createFailedLoginDialog(payload.getError());
                            dialog.show();
                        }
                    }
                });
            }
        });
    }

    private ILoginForm createLoginForm() {
        String email = _emailField.getEditableText().toString();
        String password = _passwordField.getEditableText().toString();
        return new LoginForm(email, password);
    }

    private AlertDialog createFailedLoginDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        return builder.create();
    }
}
