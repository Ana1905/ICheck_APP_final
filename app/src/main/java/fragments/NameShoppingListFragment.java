package fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.icheck_app_final.R;
import com.example.icheck_app_final.SelectProductsActivity;
import com.example.icheck_app_final.SettingsActivity;
import com.example.icheck_app_final.SignUpActivity;

import labs.QueueSingleton;
import labs.UserLab;


public class NameShoppingListFragment extends Fragment {
    Button mContinue;
    EditText mEditTextNameList;

    public static NameShoppingListFragment newInstance() {
        return new NameShoppingListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_name_shopping_list, container, false);
        Toast.makeText(view.getContext(), "Please long press the key", Toast.LENGTH_LONG ).show();
        mContinue = view.findViewById(R.id.ButtonContinueList);
        mEditTextNameList= view.findViewById(R.id.EditTextNameList);

        mContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createList();
            }
        });

                // Inflate the layout for this fragment
        return view;
    }

    private void createList() {
        String listName = mEditTextNameList.getText().toString();
        String username = UserLab.get().getCurrentUser().getUsername();

        String url = "https://checkitdatabase.000webhostapp.com/add-list.php?user=" + username + "&list_name=" + listName;

        final StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), "mandando", Toast.LENGTH_LONG ).show();
                Intent intent = new Intent(getActivity(), SelectProductsActivity.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        QueueSingleton.get(getContext()).addToRequestQueue(request);
    }
}

