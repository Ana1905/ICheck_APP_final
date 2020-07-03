package fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.icheck_app_final.R;

import java.nio.file.Watchable;

import labs.QueueSingleton;
import labs.UserLab;
import models.User;

public class AccountSettingsDialog extends DialogFragment {

    private EditText mEditTextName;
    private EditText mEditTextUsername;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private EditText mEditTextConfirmPassword;

    public static AccountSettingsDialog newInstance(){
        return new AccountSettingsDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_account_settings, null);
        //wiring up
        mEditTextName = view.findViewById(R.id.name_settings);
        mEditTextUsername= view.findViewById(R.id.username_settings);
        mEditTextEmail= view.findViewById(R.id.email_settings);
        mEditTextPassword= view.findViewById(R.id.password_settings);
        mEditTextConfirmPassword= view.findViewById(R.id.confirm_password_settings);

        updateUI();

        mEditTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mEditTextConfirmPassword.setVisibility(View.VISIBLE);
                mEditTextConfirmPassword.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_CANCELED);
                    }
                })
                .create();
    }

    private void updateUI(){
        mEditTextConfirmPassword.setVisibility(View.GONE);
        String name = UserLab.get().getCurrentUser().getName();
        String username = UserLab.get().getCurrentUser().getUsername();
        String password = UserLab.get().getCurrentUser().getPassword();
        String email = UserLab.get().getCurrentUser().getEmail();
        mEditTextName.setText(name);
        mEditTextUsername.setText(username);
        mEditTextPassword.setText(password);
        mEditTextEmail.setText(email);
    }


    private void sendResult(int result){
        if(getTargetFragment() == null){
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("username", mEditTextUsername.getText().toString());
        intent.putExtra("name", mEditTextName.getText().toString());
        intent.putExtra("password", mEditTextPassword.getText().toString());
        intent.putExtra("conf_password", mEditTextConfirmPassword.getText().toString());
        intent.putExtra("email", mEditTextEmail.getText().toString());
        getTargetFragment().onActivityResult(getTargetRequestCode(), result, intent);
    }
}
