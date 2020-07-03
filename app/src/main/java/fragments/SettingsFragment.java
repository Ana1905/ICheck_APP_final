package fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.icheck_app_final.R;
import com.example.icheck_app_final.SettingsActivity;

import labs.QueueSingleton;
import labs.UserLab;
import models.User;

public class SettingsFragment extends Fragment {

    TextView mTextViewAccount;
    TextView mTextViewAppearance;
    TextView mTextViewHelp;

    private static final String DIALOG_ACCOUNT = "Dialog Account";
    private static final int REQUEST_ACCOUNT = 1;
    private static final int REQUEST_APPEARANCE = 2;
    private static final int REQUEST_HELP = 3;

    private static final String FILE_NAME = "check it session";
    private static final String CURRENT_USER =  "username";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        //wiring up
        mTextViewAccount=view.findViewById(R.id.TextViewAccount);
        mTextViewAppearance=view.findViewById(R.id.TextViewApAppearance);
        mTextViewHelp=view.findViewById(R.id.TextViewHelp);

        mTextViewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                AccountSettingsDialog dialog = AccountSettingsDialog.newInstance();
                dialog.setTargetFragment(SettingsFragment.this, REQUEST_ACCOUNT);
                assert manager != null;
                dialog.show(manager, DIALOG_ACCOUNT);
            }
        });

        mTextViewAppearance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mTextViewHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode!= Activity.RESULT_OK){
            return;
        }
        switch(requestCode){
            case REQUEST_ACCOUNT:
                updateAccount(data);
                break;
            case REQUEST_APPEARANCE:
                Toast.makeText(getActivity(), "Update Appearance was successful", Toast.LENGTH_LONG).show();
                break;
            case REQUEST_HELP:
                break;
        }
    }

    private void updateAccount(Intent intent){
        final String username = intent.getStringExtra("username");
        final String pwd = intent.getStringExtra("pwd");
        final String email = intent.getStringExtra("email");
        String conf_pwd = intent.getStringExtra("conf_password");
        final String name = intent.getStringExtra("name");

        boolean validation = true;

        assert name != null;
        assert username != null;
        assert pwd != null;
        assert email != null;
        if (name.isEmpty() || username.isEmpty() || pwd.isEmpty() || email.isEmpty())
            validation = false;

        assert conf_pwd != null;
        if (!conf_pwd.isEmpty()) {
            if (validation)
                if (pwd.equals(conf_pwd))
                    validation = false;
        } else {
            validation = false;
        }

        if (validation){
            String searchUser = "https://checkitdatabase.000webhostapp.com/search-user.php?user=" + username + "&pwd=" + pwd;
            String updateUser = "https://checkitdatabase.000webhostapp.com/update-user.php?user=" + username + "&pwd=" + pwd +
                    "&email=" + email + "&name=" + name;

            final StringRequest updateRequest = new StringRequest(Request.Method.GET, updateUser, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    User user = UserLab.get().getCurrentUser();
                    user.setName(name);
                    user.setPassword(pwd);
                    user.setUsername(username);
                    user.setEmail(email);
                    SharedPreferences file = getActivity().getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = file.edit();
                    editor.remove(CURRENT_USER);
                    editor.apply();
                    editor.putString(CURRENT_USER, username);
                    editor.apply();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getActivity(), "There was an error connecting with server", Toast.LENGTH_LONG).show();
                }
            });

            StringRequest searchRequest = new StringRequest(Request.Method.GET, searchUser, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (!response.equals("Unfounded")) {
                        //Username not exists
                        QueueSingleton.get(getActivity()).addToRequestQueue(updateRequest);
                    } else {
                        Toast.makeText(getContext(), "Username already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "There was an error connecting with server", Toast.LENGTH_LONG).show();
                }
            });

            if (username.equals(UserLab.get().getCurrentUser().getUsername())){
                Toast.makeText(getActivity(), "same username", Toast.LENGTH_LONG).show();
                QueueSingleton.get(getActivity()).addToRequestQueue(updateRequest);
            } else {
                Toast.makeText(getActivity(), "other user", Toast.LENGTH_LONG).show();
                QueueSingleton.get(getActivity()).addToRequestQueue(searchRequest);
            }
        }
    }
}
